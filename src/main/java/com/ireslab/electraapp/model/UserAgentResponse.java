/**
 * 
 */
package com.ireslab.electraapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ireslab.sendx.electra.model.GenericResponse;

/**
 * @author User
 *
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAgentResponse extends GenericResponse {

	
	private static final long serialVersionUID = 4292493400808006956L;

	private AgentResponse agentResponse;

	/**
	 * @return the agentResponse
	 */
	public AgentResponse getAgentResponse() {
		return agentResponse;
	}

	/**
	 * @param agentResponse the agentResponse to set
	 */
	public void setAgentResponse(AgentResponse agentResponse) {
		this.agentResponse = agentResponse;
	}
	
	
	

	

}
