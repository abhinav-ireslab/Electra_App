package com.ireslab.electraapp.notification;

import java.io.File;
import java.io.StringWriter;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author Nitin
 *
 */
@Component
public class MailService {

	private static final Logger LOG = LoggerFactory.getLogger(MailService.class);
	private static final String ENCODING = "UTF-8";

	@Autowired
	private MailConfig mailConfig;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ireslab.sendx.notification.MailService#sendEmail(com.ireslab.sendx.
	 * notification.MailMessage)
	 */
	@Async
	public void sendEmail(MailMessage mailMessage) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		String emailSubject = mailMessage.getSubject();
		if (emailSubject == null) {
			emailSubject = mailConfig.getDefaultSubject();
		}

		String fromAddress = mailMessage.getFromAddress();
		if (fromAddress == null) {
			fromAddress = mailConfig.getFromAddress();
		}

		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			if (mailMessage.getCcAddresses() != null) {
				mimeMessageHelper.setCc(mailMessage.getCcAddresses());
			} else if (mailConfig.getCcAddresses() != null) {
				mimeMessageHelper.setCc(mailConfig.getCcAddresses());
			}

			if (mailMessage.getBccAddresses() != null) {
				mimeMessageHelper.setBcc(mailMessage.getBccAddresses());
			} else if (mailConfig.getBccAddresses() != null) {
				mimeMessageHelper.setBcc(mailConfig.getBccAddresses());
			}

			mimeMessageHelper.setSubject(emailSubject);
			mimeMessageHelper.setFrom(fromAddress,mailConfig.getFromName());
			mimeMessageHelper.setTo(mailMessage.getToEmailAddresses());
			mailMessage.setMessageBody(getContentFromTemplate(mailMessage.getModel(), mailMessage.getMailType()));
			mimeMessageHelper.setText(mailMessage.getMessageBody(), true);

			mailSender.send(mimeMessageHelper.getMimeMessage());

		} catch (Exception exp) {
			LOG.error("Error occurred while sending email - " + ExceptionUtils.getStackTrace(exp));
		}
	}
	
	
	
	@Async
	public void sendEmailElectraClient(MailMessage mailMessage) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		String emailSubject = mailMessage.getSubject();
		if (emailSubject == null) {
			emailSubject = mailConfig.getDefaultSubject();
		}

		String fromAddress = mailMessage.getFromAddress();
		if (fromAddress == null) {
			fromAddress = mailConfig.getFromAddress();
		}

		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			if (mailMessage.getCcAddresses() != null) {
				mimeMessageHelper.setCc(mailMessage.getCcAddresses());
			} else if (mailConfig.getCcAddresses() != null) {
				mimeMessageHelper.setCc(mailConfig.getCcAddresses());
			}

			if (mailMessage.getBccAddresses() != null) {
				mimeMessageHelper.setBcc(mailMessage.getBccAddresses());
			} else if (mailConfig.getBccAddresses() != null) {
				mimeMessageHelper.setBcc(mailConfig.getBccAddresses());
			}

			mimeMessageHelper.setSubject(emailSubject);
			mimeMessageHelper.setFrom(fromAddress,mailConfig.getFromName());
			mimeMessageHelper.setTo(mailMessage.getToEmailAddresses());
			mimeMessageHelper.setText(mailMessage.getMessageBody(), true);

			mailSender.send(mimeMessageHelper.getMimeMessage());

		} catch (Exception exp) {
			LOG.error("Error occurred while sending email - " + ExceptionUtils.getStackTrace(exp));
		}
	}
	
	
	@Async
	public void sendEmailInvoiceAndReceipt(MailMessage mailMessage, File Invoice, File reciept) {
		MimeMessage mimeMessage = mailSender.createMimeMessage();

		String emailSubject = mailMessage.getSubject();
		if (emailSubject == null) {
			emailSubject = mailConfig.getDefaultSubject();
		}

		String fromAddress = mailMessage.getFromAddress();
		if (fromAddress == null) {
			fromAddress = mailConfig.getFromAddress();
		}

		try {
			MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

			if (mailMessage.getCcAddresses() != null) {
				mimeMessageHelper.setCc(mailMessage.getCcAddresses());
			} else if (mailConfig.getCcAddresses() != null) {
				mimeMessageHelper.setCc(mailConfig.getCcAddresses());
			}

			if (mailMessage.getBccAddresses() != null) {
				mimeMessageHelper.setBcc(mailMessage.getBccAddresses());
			} else if (mailConfig.getBccAddresses() != null) {
				mimeMessageHelper.setBcc(mailConfig.getBccAddresses());
			}
			
			FileSystemResource invoiceFile = new FileSystemResource(Invoice); 
			FileSystemResource receiptFile = new FileSystemResource(reciept); 
			
			mimeMessageHelper.addAttachment("invoice.pdf",invoiceFile);
			mimeMessageHelper.addAttachment("receipt.pdf",receiptFile);

			mimeMessageHelper.setSubject(emailSubject);
			mimeMessageHelper.setFrom(fromAddress,mailConfig.getFromName());
			mimeMessageHelper.setTo(mailMessage.getToEmailAddresses());
			mimeMessageHelper.setText(mailMessage.getMessageBody(), true);

			mailSender.send(mimeMessageHelper.getMimeMessage());

		} catch (Exception exp) {
			LOG.error("Error occurred while sending email - " + ExceptionUtils.getStackTrace(exp));
		}
	}
	
	
	

	/**
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public String getContentFromTemplate(Map<String, Object> model, MailType mailType) throws Exception {

		VelocityContext context = new VelocityContext();
		model.keySet().forEach(key -> {
			context.put(key, model.get(key));
		});

		StringWriter content = new StringWriter();
		try {
			velocityEngine.mergeTemplate(mailType.getEmailTemplate(), ENCODING, context, content);

		} catch (Exception exp) {
			LOG.error("Error occurred while loading email template - " + ExceptionUtils.getStackTrace(exp));
			throw new Exception(exp);
		}
		return content.toString();
	}
}
