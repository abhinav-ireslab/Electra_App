package com.ireslab.electraapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Nitin
 *
 */
@SpringBootApplication
@ComponentScan("com.ireslab.electraapp")
public class ElectraAppApplication extends SpringBootServletInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.boot.web.support.SpringBootServletInitializer#
	 * configure(org.springframework.boot.builder.SpringApplicationBuilder)
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ElectraAppApplication.class);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ElectraAppApplication.class, args);
	}
}
