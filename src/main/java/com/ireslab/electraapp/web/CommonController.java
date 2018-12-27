package com.ireslab.electraapp.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ireslab.electraapp.service.CommonService;
import com.ireslab.electraapp.service.TransactionalApiService;
import com.ireslab.sendx.electra.model.BankDetailResponse;
import com.ireslab.sendx.electra.model.BankDetailsRequest;
import com.ireslab.sendx.electra.model.PaymentRequest;
import com.ireslab.sendx.electra.model.PaymentResponse;
import com.ireslab.sendx.electra.model.ProductRequest;
import com.ireslab.sendx.electra.model.ProductResponse;
import com.ireslab.sendx.electra.model.SendxElectraRequest;
import com.ireslab.sendx.electra.model.SendxElectraResponse;

/**
 * @author iRESlab
 *
 */
@RestController

public class CommonController {
	
private static final Logger LOG = LoggerFactory.getLogger(CommonController.class);
	
	@Autowired
	private ObjectWriter objectWriter;
	
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private TransactionalApiService transactionalApiService;
	
	
	/**
	 * 
	 * use to get available product list of client by registered mobile number.
	 * 
	 * @param productRequest
	 * @return productResponse
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value="products/{mobileNumber}",method=RequestMethod.POST)
	public ResponseEntity<ProductResponse> getProducetList(@RequestBody ProductRequest productRequest) throws JsonProcessingException{
		LOG.info("Product Request received : "+objectWriter.writeValueAsString(productRequest));
		ProductResponse productResponse = commonService.getProductList(productRequest);
		LOG.info("Product Response send for products ");
		return new ResponseEntity<>(productResponse,HttpStatus.OK);
	}
	
	/**
	 * use to push notification of invoice to purchaser.
	 * 
	 * @param paymentRequest
	 * @return productResponse
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value="sendInvoicePayload",method=RequestMethod.POST)
    public ResponseEntity<PaymentResponse> sendInvoicePayload(@RequestBody PaymentRequest paymentRequest) throws JsonProcessingException{
		LOG.debug("Send Invoice Payload Request received : "+objectWriter.writeValueAsString(paymentRequest));
		//paymentRequest.setOffline(true);
		PaymentResponse productResponse = commonService.sendInvoicePayload(paymentRequest);
		LOG.info("Response send for invoice payload ");
		return new ResponseEntity<>(productResponse,HttpStatus.OK);
	}
	
	/**
	 *  use to make payment to seller by purchaser for purchase the products and 
	 * also use to mail the invoice and receipt to seller and purchaser in case of online invoice.
	 * 
	 * @param paymentRequest
	 * @return productResponse
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value="makePayment",method=RequestMethod.POST)
	public ResponseEntity<PaymentResponse> getProducetList(@RequestBody PaymentRequest paymentRequest) throws JsonProcessingException{
		LOG.debug("Make payment for Product Request received : "+objectWriter.writeValueAsString(paymentRequest));
		//paymentRequest.setOffline(true);
	    PaymentResponse productResponse = commonService.makePayment(paymentRequest);
	    LOG.info("Response send for make payment ");
		return new ResponseEntity<>(productResponse,HttpStatus.OK);
	}
	
	/**
	 * use to mail invoice and receipt to seller and purchaser in case of offline invoice.
	 * 
	 * @param paymentRequest
	 * @return productResponse
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value="generateReceiptInvoice",method=RequestMethod.POST)
	public ResponseEntity<PaymentResponse> generateReceiptInvoice(@RequestBody PaymentRequest paymentRequest) throws JsonProcessingException{
		LOG.debug("Request for generate recipt and invoice : "+objectWriter.writeValueAsString(paymentRequest));
	    PaymentResponse productResponse = commonService.generateReceiptInvoice(paymentRequest);
	    LOG.info("Response send for generate receipt invoice ");
	    return new ResponseEntity<>(productResponse,HttpStatus.OK);
	}
	
	
	/**
	 * use to update specific parameters of app users which are use to send push notification.
	 * 
	 * @param sendxElectraRequest
	 * @return sendxElectraResponse
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value="updateDeviceSpecificParameter",method=RequestMethod.POST)
	public ResponseEntity<SendxElectraResponse> updateDeviceSpecificParameter(@RequestBody SendxElectraRequest sendxElectraRequest) throws JsonProcessingException{
		LOG.debug("Device specific parameter update request received : "+objectWriter.writeValueAsString(sendxElectraRequest));
		SendxElectraResponse sendxElectraResponse = commonService.updateDeviceSpecificParameter(sendxElectraRequest);
		LOG.info("Response send for update device specific ");
		return new ResponseEntity<>(sendxElectraResponse,HttpStatus.OK);
	}
	
	
	
	/**
	 * use to get bank details of client by email address.
	 * 
	 * @param bankDetailsRequest
	 * @return bankDetailResponse
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value="getBankDetailByClientEmail",method=RequestMethod.POST)
    public ResponseEntity<BankDetailResponse> getBankDetailByClientEmail(@RequestBody BankDetailsRequest bankDetailsRequest) throws JsonProcessingException{
		LOG.info("Request for get bank detail by mailId received : "+objectWriter.writeValueAsString(bankDetailsRequest));
		BankDetailResponse bankDetailResponse = transactionalApiService.getBankDetailByClientEmail(bankDetailsRequest);
		LOG.info("Response send for client bank details ");
		return new ResponseEntity<>(bankDetailResponse,HttpStatus.OK);
	}
	
	/**
	 *  use to update notification.
	 * 
	 * @param sendxElectraRequest
	 * @return sendxElectraResponse
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value="updateNotification",method=RequestMethod.POST)
    public ResponseEntity<SendxElectraResponse> updateNotification(@RequestBody SendxElectraRequest sendxElectraRequest) throws JsonProcessingException{
		LOG.debug("Request received to update Notification "+objectWriter.writeValueAsString(sendxElectraRequest));
		SendxElectraResponse sendxElectraResponse = commonService.updateNotification(sendxElectraRequest);
		LOG.info("Response send for update Notification ");
		return new ResponseEntity<>(sendxElectraResponse,HttpStatus.OK);
	}

	
	/**
	 * use to get notification list of app user.
	 * 
	 * @param mobileNumber
	 * @param countryDailCode
	 * @return sendxElectraResponse
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value="getAllNotification/{countryDailCode}/{mobileNumber}",method=RequestMethod.GET)
    public ResponseEntity<SendxElectraResponse> getAllNotification(@PathVariable("mobileNumber") String mobileNumber,@PathVariable("countryDailCode") String countryDailCode) throws JsonProcessingException{
		
		LOG.info("Request recieved for get all notification ");
		SendxElectraResponse sendxElectraResponse = commonService.getAllNotification(mobileNumber);
		LOG.info("Response send for get all notification ");
		return new ResponseEntity<>(sendxElectraResponse,HttpStatus.OK);
	}
	
	/**
	 * use to download invoice by purchaser.
	 * 
	 * @param paymentRequest
	 * @param request
	 * @param response
	 * @throws JsonProcessingException
	 */
	@RequestMapping(value="downloadInvoicePdf",method=RequestMethod.POST,  produces = "application/pdf")
	public void downloadInvoicePdf(@RequestBody PaymentRequest paymentRequest,HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException{
		LOG.info("Request for generate recipt and invoice ");
	    commonService.downloadInvoicePdf(paymentRequest, request, response);
		
	}
	
}
