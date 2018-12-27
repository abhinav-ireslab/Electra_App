package com.ireslab.electraapp.notification;

import java.util.List;
import java.util.Map;

/**
 * @author Nitin
 *
 */
public class MailMessage {

	private MailType mailType;

	private String toEmailAddresses[];

	private String ccAddresses[];

	private String bccAddresses[];

	private String fromAddress;

	private String subject;

	private String messageBody;

	private String messageSignature;

	private String contentType;

	private List<Object> attachments;

	private Map<String, Object> model;

	/**
	 * @return the mailType
	 */
	public MailType getMailType() {
		return mailType;
	}

	/**
	 * @param mailType
	 *            the mailType to set
	 */
	public void setMailType(MailType mailType) {
		this.mailType = mailType;
	}

	/**
	 * @return the toEmailAddresses
	 */
	public String[] getToEmailAddresses() {
		return toEmailAddresses;
	}

	/**
	 * @param toEmailAddresses
	 *            the toEmailAddresses to set
	 */
	public void setToEmailAddresses(String[] toEmailAddresses) {
		this.toEmailAddresses = toEmailAddresses;
	}

	/**
	 * @return the ccAddresses
	 */
	public String[] getCcAddresses() {
		return ccAddresses;
	}

	/**
	 * @param ccAddresses
	 *            the ccAddresses to set
	 */
	public void setCcAddresses(String[] ccAddresses) {
		this.ccAddresses = ccAddresses;
	}

	/**
	 * @return the bccAddresses
	 */
	public String[] getBccAddresses() {
		return bccAddresses;
	}

	/**
	 * @param bccAddresses
	 *            the bccAddresses to set
	 */
	public void setBccAddresses(String[] bccAddresses) {
		this.bccAddresses = bccAddresses;
	}

	/**
	 * @return the fromAddress
	 */
	public String getFromAddress() {
		return fromAddress;
	}

	/**
	 * @param fromAddress
	 *            the fromAddress to set
	 */
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the messageBody
	 */
	public String getMessageBody() {
		return messageBody;
	}

	/**
	 * @param messageBody
	 *            the messageBody to set
	 */
	public void setMessageBody(String messageBody) {
		this.messageBody = messageBody;
	}

	/**
	 * @return the messageSignature
	 */
	public String getMessageSignature() {
		return messageSignature;
	}

	/**
	 * @param messageSignature
	 *            the messageSignature to set
	 */
	public void setMessageSignature(String messageSignature) {
		this.messageSignature = messageSignature;
	}


	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType
	 *            the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return the attachments
	 */
	public List<Object> getAttachments() {
		return attachments;
	}

	/**
	 * @param attachments
	 *            the attachments to set
	 */
	public void setAttachments(List<Object> attachments) {
		this.attachments = attachments;
	}

	/**
	 * @return the model
	 */
	public Map<String, Object> getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(Map<String, Object> model) {
		this.model = model;
	}

}
