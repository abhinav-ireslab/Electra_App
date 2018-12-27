/**
 * 
 */
package com.ireslab.electraapp.service;

import com.ireslab.electraapp.model.AgentRequest;
import com.ireslab.electraapp.model.AgentRequestBody;
import com.ireslab.electraapp.model.AgentResponse;

public interface AgentService {

	public AgentResponse registerAgent(AgentRequest agentRequest);

	public AgentResponse getAgent(AgentRequestBody agentRequestBody);

}
