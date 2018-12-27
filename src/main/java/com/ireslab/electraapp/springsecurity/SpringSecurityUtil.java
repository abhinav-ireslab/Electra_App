package com.ireslab.electraapp.springsecurity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Nitin
 *
 */
public class SpringSecurityUtil {

	/**
	 * @return
	 */
	public static String[] usernameFromSecurityContext() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails details = (UserDetails) authentication.getPrincipal();

		if (details != null) {
			return details.getUsername().split("_");
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("Pass@4321"));
	}
}