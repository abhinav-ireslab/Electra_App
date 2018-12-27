package com.ireslab.electraapp.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ireslab.electraapp.model.SignupRequest;

public class JSONBuilder {

	private static ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();

	public static void main(String[] args) throws JsonProcessingException {

		SignupRequest signupRequest = new SignupRequest();
		signupRequest.setFirstName("Nitin");
		signupRequest.setLastName("Malik");
		signupRequest.setCountryDialCode("+65");
		signupRequest.setMobileNumber(9711355293l);
		signupRequest.setPassword("1234566");

		System.out.println(objectWriter.writeValueAsString(signupRequest));
	}

}
