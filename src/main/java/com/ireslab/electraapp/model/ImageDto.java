package com.ireslab.electraapp.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String profileImage;
	private String idProof;
	private String residentialAddress;
	private String profileImageName;
	private String idProofName;
	
	
	public String getProfileImageName() {
		return profileImageName;
	}
	public String getIdProofName() {
		return idProofName;
	}
	public void setProfileImageName(String profileImageName) {
		this.profileImageName = profileImageName;
	}
	public void setIdProofName(String idProofName) {
		this.idProofName = idProofName;
	}
	private String imageUrl;
	
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public String getIdProof() {
		return idProof;
	}
	public String getResidentialAddress() {
		return residentialAddress;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	public void setIdProof(String idProof) {
		this.idProof = idProof;
	}
	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
	} 
	
	

}
