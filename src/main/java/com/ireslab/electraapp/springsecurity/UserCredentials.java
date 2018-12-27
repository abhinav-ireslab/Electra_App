package com.ireslab.electraapp.springsecurity;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Nitin
 *
 */
public class UserCredentials extends org.springframework.security.core.userdetails.User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mPIN;

	public UserCredentials(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		// TODO Auto-generated constructor stub
	}

	public UserCredentials(String username, String password, String mPIN, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.mPIN = mPIN;
	}

	public UserCredentials(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public String getmPIN() {
		return mPIN;
	}

	public void setmPIN(String mPIN) {
		this.mPIN = mPIN;
	}
}
