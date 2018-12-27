package com.ireslab.electraapp.exception;

import java.util.Locale;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ireslab.electraapp.model.GenericResponse;

/**
 * @author Nitin
 *
 */
@RestControllerAdvice
public class ApplicationExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationExceptionHandler.class);

	@Autowired
	ObjectWriter objectWriter;

	@Autowired
	MessageSource messageSource;

	/**
	 * Global Exception handler for Business Exceptions
	 * 
	 * @param busExp
	 * @return
	 * @throws JsonProcessingException
	 */
	@ExceptionHandler(BusinessException.class)
	@ResponseBody
	public GenericResponse handleBusinessException(BusinessException busExp) throws JsonProcessingException {

		GenericResponse genericResponse = null;
		LOGGER.error("Handling Business Exception - " + busExp.toString());

		genericResponse = new GenericResponse();
		genericResponse.setStatus(busExp.getStatus().value());
		genericResponse.setCode(busExp.getCode());
		genericResponse.setMessage(messageSource.getMessage(busExp.getMessage(), null, Locale.getDefault()));

		LOGGER.debug("Returning response - " + objectWriter.writeValueAsString(genericResponse));

		return genericResponse;
	}

	/**
	 * Global Exception Handler for System Exceptions
	 * 
	 * @param exp
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<GenericResponse> handleSystemException(Exception exp) {

		LOGGER.error(ExceptionUtils.getStackTrace(exp));

		return new ResponseEntity<GenericResponse>(
				new GenericResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), 101, "Internal Server Error"),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
