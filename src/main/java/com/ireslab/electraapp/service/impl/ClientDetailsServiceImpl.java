package com.ireslab.electraapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import com.ireslab.electraapp.notification.SendxConfig;

/**
 * @author iRESlab
 *
 */
@Primary
@Service(value = "clientDetailsService")
public class ClientDetailsServiceImpl implements ClientDetailsService {

	@Autowired
	private SendxConfig sendxConfig;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.provider.ClientDetailsService#
	 * loadClientByClientId(java.lang.String)
	 */
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		

		BaseClientDetails clientDetails = new BaseClientDetails(sendxConfig.clientId, sendxConfig.resourceIds,
				sendxConfig.scopes, sendxConfig.grantTypes, sendxConfig.authorities);
		clientDetails.setClientSecret(sendxConfig.clientSecret);

		
		return clientDetails;
	}
}
