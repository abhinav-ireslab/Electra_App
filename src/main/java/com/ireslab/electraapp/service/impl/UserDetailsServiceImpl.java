package com.ireslab.electraapp.service.impl;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ireslab.electraapp.entity.Account;
import com.ireslab.electraapp.service.AccountService;

/**
 * @author Nitin
 *
 */
@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private AccountService accountService;

	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		String[] usernameToken = userName.split("_");
		BigInteger mobileNumber = new BigInteger(usernameToken[1]);
		String countryDialCode = usernameToken[0];

		LOG.debug("Checking account details for mobileNumber - " + mobileNumber + ", and countryDialCode - "
				+ countryDialCode);

		Account account = accountService.getAccount(mobileNumber, countryDialCode);
		if (account == null) {
			LOG.error("Account not exists for mobileNumber - " + mobileNumber + ", and countryDialCode - "
					+ countryDialCode);
			throw new UsernameNotFoundException("User not found.");
		}

		Set<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		UserDetails UserDetails = new com.ireslab.electraapp.springsecurity.UserCredentials(userName, account.getPassword(),
				account.getMpin(), authorities);
		return UserDetails;
	}
}
