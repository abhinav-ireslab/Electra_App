/**
 * 
 */
package com.ireslab.electraapp.web;

import java.math.BigInteger;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ireslab.electraapp.exception.BusinessException;
import com.ireslab.electraapp.model.AgentRequest;
import com.ireslab.electraapp.model.AgentRequestBody;
import com.ireslab.electraapp.model.AgentResponse;
import com.ireslab.electraapp.service.AgentService;
import com.ireslab.electraapp.util.AppStatusCodes;
import com.ireslab.electraapp.util.PropConstants;

/**
 * @author iRESlab
 *
 */
@RestController
public class AgentController {

	@Autowired
	private ObjectWriter objectWriter;

	@Autowired
	private AgentService agentService;

	private static final Logger LOG = LoggerFactory.getLogger(AgentController.class);
	

	/**
	 * @param agentRequest
	 * @param request
	 * @return agentResponse
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/agentRegister", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public AgentResponse agentRegister(@RequestBody AgentRequest agentRequest, HttpServletRequest request)
			throws JsonProcessingException {

		LOG.debug("Request received from Agent Registration - " + agentRequest.toString());

		AgentResponse agentResponse = agentService.registerAgent(agentRequest);
		LOG.debug("Register agent response sent - " + objectWriter.writeValueAsString(agentResponse));

		return agentResponse;
	}

	/**
	 * @param agentRequestBody
	 * @param request
	 * @return agentResponse
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value = "/getAgent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public AgentResponse getAgent(@RequestBody AgentRequestBody agentRequestBody, HttpServletRequest request)
			throws JsonProcessingException {

		// Getting username details from Spring Security Context

		BigInteger mobileNumber = agentRequestBody.getMobileNumber();
		String countryDialCode = agentRequestBody.getCountryDialCode();

		AgentResponse agentResponse = null;
		LOG.debug("Get agent request received - \n\t mobileNumber : " + mobileNumber + ",\n\t countryCode : "
				+ countryDialCode);

		if (mobileNumber == null || countryDialCode == null) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, AppStatusCodes.INVALID_REQUEST,
					PropConstants.INVALID_REQUEST);
		}

		agentResponse = agentService.getAgent(agentRequestBody);
		LOG.debug("Get agent response sent - " + objectWriter.writeValueAsString(agentResponse));

		return agentResponse;
	}

}
