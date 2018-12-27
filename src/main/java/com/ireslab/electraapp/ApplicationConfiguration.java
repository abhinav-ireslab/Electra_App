package com.ireslab.electraapp;

import java.util.Properties;
import java.util.concurrent.Executor;

import org.apache.velocity.app.VelocityEngine;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ireslab.electraapp.notification.MailConfig;
import com.ireslab.electraapp.service.ProfileImageService;
import com.ireslab.electraapp.service.impl.ProfileImageServiceImpl;

/**
 * @author Nitin
 *
 */
@Configuration
@EnableAsync
@EnableConfigurationProperties
public class ApplicationConfiguration {

	private static final String MESSAGES_PROPERTIES_FILE = "classpath:messages";

	@Autowired
	private MailConfig mailConfig;

	/**
	 * @return
	 */
	@Bean(name = "threadPoolTaskExecutor")
	public Executor threadPoolTaskExecutor() {

		ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setMaxPoolSize(20);
		threadPoolTaskExecutor.setThreadNamePrefix("Scheduled-Transactions-Task-Executor");

		return new ThreadPoolTaskExecutor();
	}

	/**
	 * @return
	 */
	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource getMessageSource() {

		ReloadableResourceBundleMessageSource reloadableResourceBundleMessageSource = new ReloadableResourceBundleMessageSource();
		reloadableResourceBundleMessageSource.setBasename(MESSAGES_PROPERTIES_FILE);
		reloadableResourceBundleMessageSource.setCacheSeconds(200);
		reloadableResourceBundleMessageSource.setDefaultEncoding("UTF-8");
		return reloadableResourceBundleMessageSource;
	}

	/**
	 * @return
	 */
	@Bean
	@DependsOn(value = { "mailConfig" })
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		mailSender.setHost(mailConfig.getHost());
		mailSender.setPort(mailConfig.getPort());
		mailSender.setUsername(mailConfig.getUsername());
		mailSender.setPassword(mailConfig.getPassword());

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable", mailConfig.getSmtpStarttlsEnable());
		javaMailProperties.put("mail.smtp.auth", mailConfig.getSmtpAuth());
		javaMailProperties.put("mail.smtp.socketFactory.class", mailConfig.getSmtpSocketfactoryClass());
		javaMailProperties.put("mail.transport.protocol", mailConfig.getTransportProtocol());
		javaMailProperties.put("mail.debug", mailConfig.getDebug());

		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}

	/**
	 * @return
	 * @throws Exception
	 */
	@Bean
	public VelocityEngine velocityEngine() throws Exception {

		Properties properties = new Properties();
		properties.setProperty("input.encoding", "UTF-8");
		properties.setProperty("output.encoding", "UTF-8");
		properties.setProperty("resource.loader", "class");
		properties.setProperty("class.resource.loader.class",
				"org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

		return new VelocityEngine(properties);
	}

	/**
	 * @return
	 */
	@Bean(name = "modelMapper")
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

	/**
	 * @return
	 */
	@Bean(name = "objectWriter")
	public ObjectWriter getObjectWriter() {
		return new ObjectMapper().writerWithDefaultPrettyPrinter();
	}

	/**
	 * @return
	 */
	@Bean(name = "restTemplate")
	public RestTemplate getRestTemplate() {

		return new RestTemplate();
	}
	

	@Bean
	public ProfileImageService getProfileImageService() {
		return new ProfileImageServiceImpl();
	}

}
