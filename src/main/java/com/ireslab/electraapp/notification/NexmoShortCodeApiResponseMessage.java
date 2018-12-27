package com.ireslab.electraapp.notification;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Nitin
 *
 */
@JsonInclude(value = Include.NON_NULL)
public class NexmoShortCodeApiResponseMessage {

	private String to;
	private String network;
	private String status;

	@JsonProperty("message-id")
	private String message_id;

	@JsonProperty("client-ref")
	private String client_ref;

	@JsonProperty("remaining-balance")
	private String remaining_balance;

	@JsonProperty("message-price")
	private String message_price;

	@JsonProperty("error-text")
	private String error_text;

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to
	 *            the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return the network
	 */
	public String getNetwork() {
		return network;
	}

	/**
	 * @param network
	 *            the network to set
	 */
	public void setNetwork(String network) {
		this.network = network;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the message_id
	 */
	public String getMessage_id() {
		return message_id;
	}

	/**
	 * @param message_id
	 *            the message_id to set
	 */
	public void setMessage_id(String message_id) {
		this.message_id = message_id;
	}

	/**
	 * @return the client_ref
	 */
	public String getClient_ref() {
		return client_ref;
	}

	/**
	 * @param client_ref
	 *            the client_ref to set
	 */
	public void setClient_ref(String client_ref) {
		this.client_ref = client_ref;
	}

	/**
	 * @return the remaining_balance
	 */
	public String getRemaining_balance() {
		return remaining_balance;
	}

	/**
	 * @param remaining_balance
	 *            the remaining_balance to set
	 */
	public void setRemaining_balance(String remaining_balance) {
		this.remaining_balance = remaining_balance;
	}

	/**
	 * @return the message_price
	 */
	public String getMessage_price() {
		return message_price;
	}

	/**
	 * @param message_price
	 *            the message_price to set
	 */
	public void setMessage_price(String message_price) {
		this.message_price = message_price;
	}

	/**
	 * @return the error_text
	 */
	public String getError_text() {
		return error_text;
	}

	/**
	 * @param error_text
	 *            the error_text to set
	 */
	public void setError_text(String error_text) {
		this.error_text = error_text;
	}

	@Override
	public String toString() {
		return "NexmoShortCodeApiResponseMessage [to=" + to + ", network=" + network + ", status=" + status
				+ ", message_id=" + message_id + ", client_ref=" + client_ref + ", remaining_balance="
				+ remaining_balance + ", message_price=" + message_price + ", error_text=" + error_text + "]";
	}
}
