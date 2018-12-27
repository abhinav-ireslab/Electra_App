package com.ireslab.electraapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import com.ireslab.electraapp.notification.SendxConfig;

/**
 * @author Nitin
 *
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Autowired
	private SendxConfig sendxConfig;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.config.annotation.web.configuration.
	 * ResourceServerConfigurerAdapter#configure(org.springframework.security.
	 * config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/getExchangeDetails", "/getProfileImage", "/allCountryDetails",
				"/checkMobileNumberRegistration", "/requestActivationCode", "/validateActivationCode", "/register",
				"/validateContacts", "/verifyContactList", "/hello", "/allCheckoutBankDetails", "/miscConfigDetails",
				"/testSMS", "/testMail", "/updatePassword", "/updateMpin", "/test/*", "/getExchangeDetails", "/electra/generateCompanyCode", "/electra/checkMobileNumberRegistration", "/electra/register")
				.permitAll();
		http.authorizeRequests().anyRequest().authenticated();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.oauth2.config.annotation.web.configuration.
	 * ResourceServerConfigurerAdapter#configure(org.springframework.security.
	 * oauth2.config.annotation.web.configurers. ResourceServerSecurityConfigurer)
	 */
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(sendxConfig.resourceIds).stateless(false);
	}
}
