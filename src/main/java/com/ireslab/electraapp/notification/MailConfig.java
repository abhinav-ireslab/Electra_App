package com.ireslab.electraapp.notification;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Nitin
 *
 */
@Configuration("mailConfig")
@PropertySource(value = "classpath:mail_config.properties")
@ConfigurationProperties(prefix = "mail")
public class MailConfig {

	private static final String ADDRESS_DELIMITER = ",";

	private String host;
	private Integer port;
	private String username;
	private String password;
	private String debug;
	private String smtpAuth;
	private String transportProtocol;
	private String smtpStarttlsEnable;
	private String smtpSocketfactoryClass;

	private String fromAddress;

	private String[] ccAddresses;
	private String[] bccAddresses;

	private String defaultSubject;
	
	private String consoleLink;
	private String fromName;
	
	

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
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
	 * @param host
	 *            the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @return the port
	 */
	public Integer getPort() {
		return port;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public void setPort(Integer port) {
		this.port = port;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the debug
	 */
	public String getDebug() {
		return debug;
	}

	/**
	 * @param debug
	 *            the debug to set
	 */
	public void setDebug(String debug) {
		this.debug = debug;
	}

	/**
	 * @return the smtpAuth
	 */
	public String getSmtpAuth() {
		return smtpAuth;
	}

	/**
	 * @param smtpAuth
	 *            the smtpAuth to set
	 */
	public void setSmtpAuth(String smtpAuth) {
		this.smtpAuth = smtpAuth;
	}

	/**
	 * @return the transportProtocol
	 */
	public String getTransportProtocol() {
		return transportProtocol;
	}

	/**
	 * @param transportProtocol
	 *            the transportProtocol to set
	 */
	public void setTransportProtocol(String transportProtocol) {
		this.transportProtocol = transportProtocol;
	}

	/**
	 * @return the smtpStarttlsEnable
	 */
	public String getSmtpStarttlsEnable() {
		return smtpStarttlsEnable;
	}

	/**
	 * @param smtpStarttlsEnable
	 *            the smtpStarttlsEnable to set
	 */
	public void setSmtpStarttlsEnable(String smtpStarttlsEnable) {
		this.smtpStarttlsEnable = smtpStarttlsEnable;
	}

	/**
	 * @return the smtpSocketfactoryClass
	 */
	public String getSmtpSocketfactoryClass() {
		return smtpSocketfactoryClass;
	}

	/**
	 * @param smtpSocketfactoryClass
	 *            the smtpSocketfactoryClass to set
	 */
	public void setSmtpSocketfactoryClass(String smtpSocketfactoryClass) {
		this.smtpSocketfactoryClass = smtpSocketfactoryClass;
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
	public void setBccAddresses(String bccAddresses) {

		if (bccAddresses != null && bccAddresses.length() > 0) {
			this.bccAddresses = bccAddresses.split(ADDRESS_DELIMITER);
		}
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
	public void setCcAddresses(String ccAddresses) {

		if (ccAddresses != null && ccAddresses.length() > 0) {
			this.ccAddresses = ccAddresses.split(ADDRESS_DELIMITER);
		}
	}

	/**
	 * @return the defaultSubject
	 */
	public String getDefaultSubject() {
		return defaultSubject;
	}

	/**
	 * @param defaultSubject
	 *            the defaultSubject to set
	 */
	public void setDefaultSubject(String defaultSubject) {
		this.defaultSubject = defaultSubject;
	}

	public String getConsoleLink() {
		return consoleLink;
	}

	public void setConsoleLink(String consoleLink) {
		this.consoleLink = consoleLink;
	}

}
