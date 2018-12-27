package com.ireslab.electraapp.notification;

/**
 * @author Nitin
 *
 */
public enum MailType {

	WELCOME_EMAIL("welcome-email-template.vm");

	/**
	 * @param emailTemplate
	 */
	private MailType(String emailTemplate) {
		this.emailTemplate = emailTemplate;
	}

	private String emailTemplate;

	/**
	 * @return
	 */
	public String getEmailTemplate() {
		return emailTemplate;
	}
}
