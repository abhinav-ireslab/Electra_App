/**
 * 
 */
package com.ireslab.electraapp.service.impl;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ireslab.electraapp.entity.Account;
import com.ireslab.electraapp.exception.BusinessException;
import com.ireslab.electraapp.model.AgentRequest;
import com.ireslab.electraapp.model.AgentRequestBody;
import com.ireslab.electraapp.model.AgentResponse;
import com.ireslab.electraapp.repository.AccountRepository;
import com.ireslab.electraapp.service.AccountService;
import com.ireslab.electraapp.service.AgentService;
import com.ireslab.electraapp.service.ProfileImageService;
import com.ireslab.electraapp.service.TransactionalApiService;
import com.ireslab.electraapp.util.AppStatusCodes;
import com.ireslab.electraapp.util.PropConstants;

@Service
public class AgentServiceImpl implements AgentService {

	@Autowired
	private ProfileImageService profileImageService;

	@Autowired
	private AccountRepository accountRepo;

	@Autowired
	private AccountService accountService;

	@Autowired
	private TransactionalApiService transactionalApiService;

	/* (non-Javadoc)
	 * @see com.ireslab.electraapp.service.AgentService#registerAgent(com.ireslab.electraapp.model.AgentRequest)
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public AgentResponse registerAgent(AgentRequest agentRequest) {

		AgentResponse agentResponse = null;
		boolean isUpdateUserAccount = false;

		Account account = accountRepo.findBymobileNumber(BigInteger.valueOf(agentRequest.getAgentMobNo()));
		if (account == null) {
			throw new BusinessException(HttpStatus.OK, AppStatusCodes.ACCOUNT_NOT_EXISTS,
					PropConstants.ACCOUNT_NOT_EXISTS);
		}

		// Saving Profile Image in database
		if (agentRequest.getProfileImageValue() != null && !agentRequest.getProfileImageValue().isEmpty()) {

			String profileImageUrl = profileImageService.saveImage("profile", agentRequest.getAgentMobNo().toString(),
					agentRequest.getProfileImageValue());
			account.setProfileImageUrl(profileImageUrl);
			isUpdateUserAccount = true;
		}

		// Saving ID Proof Image in database
		if (agentRequest.getIdProofImageValue() != null && !agentRequest.getIdProofImageValue().isEmpty()) {

			String idProofImageUrl = profileImageService.saveImage("idproof", agentRequest.getAgentMobNo().toString(),
					agentRequest.getIdProofImageValue());
			account.setIdProofImageUrl(idProofImageUrl);
			isUpdateUserAccount = true;
		}

		// Updating profile data in database
		if (isUpdateUserAccount) {
			accountRepo.save(account);
		}

		AgentResponse agentResponses = transactionalApiService.invokeAgentOnboardingApi(agentRequest,
				account.getUserCorrelationId());

		if (agentResponses == null) {
			agentResponse = new AgentResponse(HttpStatus.OK.value(), AppStatusCodes.INTERNAL_SERVER_ERROR,
					PropConstants.INTERNAL_SERVER_ERROR);
		} else {
			agentResponse = new AgentResponse(HttpStatus.OK.value(), AppStatusCodes.SUCCESS,
					"Your request to become agent is registered and sent for approval.");
		}

		return agentResponse;

	}

	/* (non-Javadoc)
	 * @see com.ireslab.electraapp.service.AgentService#getAgent(com.ireslab.electraapp.model.AgentRequestBody)
	 */
	@Override
	public AgentResponse getAgent(AgentRequestBody agentRequestBody) {

		Account account = accountService.getAccount(agentRequestBody.getMobileNumber(),
				agentRequestBody.getCountryDialCode());

		if (account == null) {
			throw new BusinessException(HttpStatus.OK, AppStatusCodes.ACCOUNT_NOT_EXISTS,
					PropConstants.ACCOUNT_NOT_EXISTS);
		}
		agentRequestBody.setUserCorreletionId(account.getUserCorrelationId());
		AgentResponse agentResponses = transactionalApiService.invokeGetAgentAPI(agentRequestBody);

		// Agent doesn't exists in
		if (agentResponses == null) {
			agentResponses = new AgentResponse();
			agentResponses.setAgentAddress(account.getResidentialAddress());
			agentResponses.setCode(AppStatusCodes.SUCCESS);
			agentResponses.setStatus(HttpStatus.OK.value());
			agentResponses.setMessage(PropConstants.SUCCESS);
		} else {

			agentResponses.setCode(AppStatusCodes.SUCCESS);
			agentResponses.setStatus(HttpStatus.OK.value());
			agentResponses.setMessage(PropConstants.SUCCESS);
		}

		agentResponses.setProfileImageUrl(account.getProfileImageUrl());
		agentResponses.setIdProofImageUrl(account.getIdProofImageUrl());

		return agentResponses;
	}
}
