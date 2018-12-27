/**
 * 
 */
package com.ireslab.electraapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Nitin
 *
 */
@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgentRequest extends GenericRequest {

	private static final long serialVersionUID = -6410246179154799396L;

	private String agentName;
	private Long agentMobNo;
	private String locateAgent;
	private String profileImageValue;
	private String idProofImageValue;
	private String businessId;
	private String fiatCurrency;
	private String cryptoCurrency;
	private String businessAdd;
	private String businessLong;
	private String businessLat;
	private String countryDialCode;
	private String correlationId;

	public String getAgentName() {
		return agentName;
	}

	public Long getAgentMobNo() {
		return agentMobNo;
	}

	public String getLocateAgent() {
		return locateAgent;
	}

	public String getProfileImageValue() {
		return profileImageValue;
	}

	public String getIdProofImageValue() {
		return idProofImageValue;
	}

	public String getBusinessId() {
		return businessId;
	}

	public String getFiatCurrency() {
		return fiatCurrency;
	}

	public String getCryptoCurrency() {
		return cryptoCurrency;
	}

	public String getBusinessAdd() {
		return businessAdd;
	}

	public String getBusinessLong() {
		return businessLong;
	}

	public String getBusinessLat() {
		return businessLat;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public AgentRequest setAgentMobNo(Long agentMobNo) {
		this.agentMobNo = agentMobNo;
		return this;
	}

	public void setLocateAgent(String locateAgent) {
		this.locateAgent = locateAgent;
	}

	public void setProfileImageValue(String profileImageValue) {
		this.profileImageValue = profileImageValue;
	}

	public AgentRequest setIdProofImageValue(String idProofImageValue) {
		this.idProofImageValue = idProofImageValue;
		return this;
	}

	public AgentRequest setBusinessId(String businessId) {
		this.businessId = businessId;
		return this;
	}

	public AgentRequest setFiatCurrency(String fiatCurrency) {
		this.fiatCurrency = fiatCurrency;
		return this;
	}

	public AgentRequest setCryptoCurrency(String cryptoCurrency) {
		this.cryptoCurrency = cryptoCurrency;
		return this;
	}

	public AgentRequest setBusinessAdd(String businessAdd) {
		this.businessAdd = businessAdd;
		return this;
	}

	public AgentRequest setBusinessLong(String businessLong) {
		this.businessLong = businessLong;
		return this;
	}

	public AgentRequest setBusinessLat(String businessLat) {
		this.businessLat = businessLat;
		return this;
	}

	public String getCountryDialCode() {
		return countryDialCode;
	}

	public AgentRequest setCountryDialCode(String countryDialCode) {
		this.countryDialCode = countryDialCode;
		return this;
	}

	public String getCorrelationId() {
		return correlationId;
	}

	public AgentRequest setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AgentRequest [agentName=" + agentName + ", agentMobNo=" + agentMobNo + ", locateAgent=" + locateAgent
				+ ", businessId=" + businessId + ", fiatCurrency=" + fiatCurrency + ", cryptoCurrency=" + cryptoCurrency
				+ ", businessAdd=" + businessAdd + ", businessLong=" + businessLong + ", businessLat=" + businessLat
				+ ", countryDialCode=" + countryDialCode + ", correlationId=" + correlationId + "]";
	}
}