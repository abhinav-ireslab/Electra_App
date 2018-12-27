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
@Table(name = "oauth_refresh_token")
@NamedQuery(name = "OAuthRefreshToken.findAll", query = "SELECT a FROM OAuthRefreshToken a")
public class OAuthRefreshToken implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "token_id")
	private String tokenId;

	@Column(name = "token")
	private String token;

	@Column(name = "authentication")
	private String authentication;

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
}
