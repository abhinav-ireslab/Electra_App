package com.ireslab.electraapp.dto;

import java.util.Date;

public class NotificationDto {
	
	
	
	private Integer notificationId;
	
	
	private String correlationId;
	
	
	private String notificationType;
	
	private String notificationData;
	
	
	private Date createdDate;

	
	private Date modifiedDate;
	
	private Boolean status;
	
	
	private Boolean invoice;
	
    private String mobileNumber;
	
	
	private String emailAddress;
	
	
	private Boolean isOffline;
	
	
	private Boolean isPaymentConfirm;
	
	
	public Integer getNotificationId() {
		return notificationId;
	}


	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}


	public String getCorrelationId() {
		return correlationId;
	}


	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}


	public String getNotificationType() {
		return notificationType;
	}


	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}


	public String getNotificationData() {
		return notificationData;
	}


	public void setNotificationData(String notificationData) {
		this.notificationData = notificationData;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public Date getModifiedDate() {
		return modifiedDate;
	}


	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}


	public Boolean getStatus() {
		return status;
	}


	public void setStatus(Boolean status) {
		this.status = status;
	}


	public Boolean getInvoice() {
		return invoice;
	}


	public void setInvoice(Boolean invoice) {
		this.invoice = invoice;
	}


	public String getMobileNumber() {
		return mobileNumber;
	}


	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}


	public String getEmailAddress() {
		return emailAddress;
	}


	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}


	public Boolean getIsOffline() {
		return isOffline;
	}

	public void setIsOffline(Boolean isOffline) {
		this.isOffline = isOffline;
	}

	public Boolean getIsPaymentConfirm() {
		return isPaymentConfirm;
	}

	public void setIsPaymentConfirm(Boolean isPaymentConfirm) {
		this.isPaymentConfirm = isPaymentConfirm;
	}


	



}
