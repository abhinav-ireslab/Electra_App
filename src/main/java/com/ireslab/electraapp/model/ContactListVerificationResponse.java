package com.ireslab.electraapp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactListVerificationResponse extends GenericResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 828507758189327978L;

	private List<ContactDetails> contactList;

	/**
	 * @return the contactDetailsList
	 */
	public List<ContactDetails> getContactList() {
		return contactList;
	}

	/**
	 * @param contactDetailsList
	 *            the contactDetailsList to set
	 */
	public void setContactList(List<ContactDetails> contactList) {
		this.contactList = contactList;
	}
}
