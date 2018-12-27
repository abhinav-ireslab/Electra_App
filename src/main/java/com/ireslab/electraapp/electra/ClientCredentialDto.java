package com.ireslab.electraapp.electra;
public class ClientCredentialDto {
	
	private Integer clientId;
	private Integer accessTokenValidity;
	private String additionalInformation;
	private String authorities;
	private String clientApiKey;
	private String clientApiSecret;
	private String grantTypes;
	private String resourceIds;
	private String scopes;
	private String status;
	public Integer getClientId() {
		return clientId;
	}
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
	public Integer getAccessTokenValidity() {
		return accessTokenValidity;
	}
	public void setAccessTokenValidity(Integer accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}
	public String getAdditionalInformation() {
		return additionalInformation;
	}
	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
	public String getAuthorities() {
		return authorities;
	}
	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}
	public String getClientApiKey() {
		return clientApiKey;
	}
	public void setClientApiKey(String clientApiKey) {
		this.clientApiKey = clientApiKey;
	}
	public String getClientApiSecret() {
		return clientApiSecret;
	}
	public void setClientApiSecret(String clientApiSecret) {
		this.clientApiSecret = clientApiSecret;
	}
	public String getGrantTypes() {
		return grantTypes;
	}
	public void setGrantTypes(String grantTypes) {
		this.grantTypes = grantTypes;
	}
	public String getResourceIds() {
		return resourceIds;
	}
	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}
	public String getScopes() {
		return scopes;
	}
	public void setScopes(String scopes) {
		this.scopes = scopes;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
