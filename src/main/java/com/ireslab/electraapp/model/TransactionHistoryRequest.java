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
public class TransactionHistoryRequest extends GenericRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7018853616890940368L;
	
	private String date;
	
	private boolean allLedger;
	private boolean offlineLedger;

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	public boolean getAllLedger() {
		return allLedger;
	}

	public void setAllLedger(boolean allLedger) {
		this.allLedger = allLedger;
	}

	public boolean getOfflineLedger() {
		return offlineLedger;
	}

	public void setOfflineLedger(boolean offlineLedger) {
		this.offlineLedger = offlineLedger;
	}
}
