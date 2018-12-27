package com.ireslab.electraapp.web;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ireslab.electraapp.model.GenericRequest;
import com.ireslab.electraapp.model.GenericResponse;

/**
 * @author iRESlab
 *
 */
@Component
public class BaseController {

	private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

	@Autowired
	private ObjectWriter objectWriter;

	public enum RequestType {
		CHECK_MOBILE_NUMBER_REGISTRATION, REQUEST_ACTIVATION_CODE, VALIDATE_ACTIVATION_CODE, SIGNUP, LOAD_TOKENS, TRANSFER_TOKENS, CASHOUT_TOKENS
	}

	/**
	 * @param userMobile
	 * @param requestType
	 * @param genericRequest
	 * @throws JsonProcessingException
	 */
	public void nameRequestThread(String userMobile, RequestType requestType, GenericRequest genericRequest)
			throws JsonProcessingException {
		nameRequestThread(null, userMobile, requestType, genericRequest, null);
	}

	/**
	 * @param userMobile
	 * @param requestType
	 * @param genericRequest
	 * @param paramMap
	 * @throws JsonProcessingException
	 */
	public void nameRequestThread(String userMobile, RequestType requestType, GenericRequest genericRequest,
			Map<String, String> infoMap) throws JsonProcessingException {
		nameRequestThread(null, userMobile, requestType, genericRequest, infoMap);
	}

	/**
	 * @param userMobile
	 * @param requestType
	 * @param paramMap
	 * @throws JsonProcessingException
	 */
	public void nameRequestThread(String userMobile, RequestType requestType, Map<String, String> paramMap)
			throws JsonProcessingException {
		nameRequestThread(null, userMobile, requestType, null, paramMap);
	}

	/**
	 * @param userMobile
	 * @param requestType
	 * @throws JsonProcessingException
	 */
	public void nameRequestThread(String userMobile, RequestType requestType) throws JsonProcessingException {
		nameRequestThread(null, userMobile, requestType, null, null);
	}

	/**
	 * @param threadName
	 * @param userMobile
	 * @param requestType
	 * @throws JsonProcessingException
	 */
	public void nameRequestThread(String threadName, String userMobile, RequestType requestType,
			GenericRequest genericRequest, Map<String, String> infoMap) throws JsonProcessingException {

		if (threadName == null) {
			threadName = "User=" + userMobile;
		}

		if (infoMap != null && !infoMap.isEmpty()) {

			for (Map.Entry<String, String> entry : infoMap.entrySet()) {
				threadName = threadName + " | " + entry.getKey() + "=" + entry.getValue();
			}
		}

		threadName = threadName + " | Request Type=" + requestType;

		Thread.currentThread().setName(threadName);

		if (genericRequest != null) {
			LOG.debug("JSON Request - " + objectWriter.writeValueAsString(genericRequest));
		}
	}

	/**
	 * @param genericResponse
	 * @return
	 */
	protected ResponseEntity<GenericResponse> prepareApiResponse(GenericResponse genericResponse) {

		return new ResponseEntity<GenericResponse>(genericResponse, HttpStatus.OK);
	}
}
