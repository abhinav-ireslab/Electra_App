/**
 * 
 */
package com.ireslab.electraapp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author ireslab
 *
 */
@Entity
@Table(name = "oauth_access_token")
@NamedQuery(name = "OAuthAccessToken.findAll", query = "SELECT a FROM OAuthAccessToken a")
public class OAuthAccessToken implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "token_id")
	private String tokenId;

	@Column(name = "token")
	private String token;

	@Column(name = "authentication_id")
	private String authenticationId;

	@Column(name = "user_name")
	private String userName;

	@Column(name = "client_id")
	private String clientId;

	@Column(name = "authentication")
	private String authentication;

	@Column(name = "refresh_token")
	private String refreshToken;

	/**
	 * @return the tokenId
	 */
	public String getTokenId() {
		return tokenId;
	}

	/**
	 * @param tokenId
	 *            the tokenId to set
	 */
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the authenticationId
	 */
	public String getAuthenticationId() {
		return authenticationId;
	}

	/**
	 * @param authenticationId
	 *            the authenticationId to set
	 */
	public void setAuthenticationId(String authenticationId) {
		this.authenticationId = authenticationId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId
	 *            the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the authentication
	 */
	public String getAuthentication() {
		return authentication;
	}

	/**
	 * @param authentication
	 *            the authentication to set
	 */
	public void setAuthentication(String authentication) {
		this.authentication = authentication;
	}

	/**
	 * @return the referenceToken
	 */
	public String getRefresh_token() {
		return refreshToken;
	}

	/**
	 * @param refresh_token
	 *            the referenceToken to set
	 */
	public void setRefresh_token(String refresh_token) {
		this.refreshToken = refresh_token;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OAuthAccessToken [tokenId=" + tokenId + ", token=" + token + ", authenticationId=" + authenticationId
				+ ", userName=" + userName + ", clientId=" + clientId + ", authentication=" + authentication
				+ ", referenceToken=" + refreshToken + "]";
	}
}
