package com.ireslab.electraapp.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.jvnet.hk2.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ireslab.electraapp.model.UserProfile;
import com.ireslab.electraapp.notification.SendxConfig;
import com.ireslab.electraapp.service.ProfileImageService;

/**
 * @author iRESlab
 *
 */
@Service
public class ProfileImageServiceImpl implements ProfileImageService {
	private static final Logger LOG = LoggerFactory.getLogger(ProfileImageServiceImpl.class);

	private static final String URI_SEPARATOR = "/";

	@Autowired
	private SendxConfig sendexConfig;

	/* (non-Javadoc)
	 * @see com.ireslab.electraapp.service.ProfileImageService#saveProfileImage(com.ireslab.electraapp.model.UserProfile, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public UserProfile saveProfileImage(UserProfile userProfile, HttpServletRequest request) {

		Date date = new Date();

		String time = "" + date.getTime();

		String imageValueInBase64String = userProfile.getProfileImageValue();
		

		if (imageValueInBase64String != null) {

			
			String formatName = "profile-" + userProfile.getMobileNumber() + "-" + time + ".jpg";
			byte[] imageByte = Base64.decodeBase64(imageValueInBase64String);
			

			String catalinaHome = System.getenv("CATALINA_HOME");
			catalinaHome = (catalinaHome == null) ? System.getProperty("catalina.home") : catalinaHome;

			StringBuilder directory = new StringBuilder();
			directory.append(catalinaHome);
			directory.append(File.separator);
			directory.append("webapps");
			directory.append(File.separator);
			directory.append(sendexConfig.imageDirectoryRelativePath);
			directory.append(File.separator);
			directory.append(formatName);

			FileOutputStream fileOutputStream = null;

			try {
				LOG.debug("Saving Image to directory - " + directory);
				fileOutputStream = new FileOutputStream(directory.toString());
				fileOutputStream.write(imageByte);
				String imageUrl = sendexConfig.appBaseUrl + URI_SEPARATOR + sendexConfig.imageDirectoryRelativePath
						+ URI_SEPARATOR + formatName;
				
				LOG.debug("imageUrl - " + imageUrl);

				
				userProfile.setProfileImageUrl(imageUrl);
			} catch (IOException e) {
				LOG.debug("error occured while writting image to directory :" + directory);
				e.printStackTrace();
			} finally {

				try {
					fileOutputStream.flush();
					fileOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		return userProfile;
	}

	

	/* (non-Javadoc)
	 * @see com.ireslab.electraapp.service.ProfileImageService#saveImage(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String saveImage(String imageName, String mobileNumber, String imageBase64) {

		Date date = new Date();
		String time = "" + date.getTime();
		String imageUrl = null;

		String formatImageName = null;

		if (imageName.equals("profile")) {
			formatImageName = "profile-" + mobileNumber + "-" + time + ".jpg";
		} else if (imageName.equals("idproof")) {
			formatImageName = "idproof-" + mobileNumber + "-" + time + ".jpg";
		} else if (imageName.equals("residentialproof")) {
			formatImageName = "residentialproof-" + mobileNumber + "-" + time + ".jpg";
		}

		if (imageBase64 != null) {

			

			byte[] imageByte = Base64.decodeBase64(imageBase64);

			String catalinaHome = System.getenv("CATALINA_HOME");
			catalinaHome = (catalinaHome == null) ? System.getProperty("catalina.home") : catalinaHome;

			StringBuilder directory = new StringBuilder();
			directory.append(catalinaHome);
			directory.append(File.separator);
			directory.append("webapps");
			directory.append(File.separator);
			directory.append(sendexConfig.imageDirectoryRelativePath);
			directory.append(File.separator);
			directory.append(formatImageName);

			FileOutputStream fileOutputStream = null;

			try {
				LOG.debug("Saving Image to directory - " + directory);

				fileOutputStream = new FileOutputStream(directory.toString());
				fileOutputStream.write(imageByte);
				imageUrl = sendexConfig.appBaseUrl + URI_SEPARATOR + sendexConfig.imageDirectoryRelativePath
						+ URI_SEPARATOR + formatImageName;

			} catch (IOException e) {
				LOG.debug("Error occured while writting image to directory :" + directory);
				e.printStackTrace();
			} finally {

				try {
					fileOutputStream.flush();
					fileOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return imageUrl;
	}
}
