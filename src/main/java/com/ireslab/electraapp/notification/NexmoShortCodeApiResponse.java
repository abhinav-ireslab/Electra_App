package com.ireslab.electraapp.notification;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Nitin
 *
 */
@JsonInclude(value = Include.NON_NULL)
public class NexmoShortCodeApiResponse {

	@JsonProperty("message-count")
	private String message_count;

	@JsonProperty("messages")
	private List<NexmoShortCodeApiResponseMessage> messages;

	public String getMessage_count() {
		return message_count;
	}

	public void setMessage_count(String message_count) {
		this.message_count = message_count;
	}

	public List<NexmoShortCodeApiResponseMessage> getMessages() {
		return messages;
	}

	public void setMessages(List<NexmoShortCodeApiResponseMessage> messages) {
		this.messages = messages;
	}
}
