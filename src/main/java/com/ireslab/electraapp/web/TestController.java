package com.ireslab.electraapp.web;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.spring4.SpringTemplateEngine;

import com.ireslab.electraapp.entity.Account;
import com.ireslab.electraapp.notification.AndroidPushNotificationsService;
import com.ireslab.electraapp.repository.AccountRepository;
import com.ireslab.electraapp.repository.OAuthAccessTokenRepository;
import com.ireslab.electraapp.service.CommonService;
import com.ireslab.electraapp.service.impl.TestServiceImpl;
import com.ireslab.electraapp.service.impl.TransactionalApiServiceImpl;
import com.ireslab.electraapp.util.ReceiptAndInvoiceUtil;
import com.ireslab.sendx.electra.dto.ProductDto;
import com.ireslab.sendx.electra.dto.ReceiptAndInvoiceUtilDto.BillToAddress;
import com.ireslab.sendx.electra.dto.ReceiptAndInvoiceUtilDto.CompanyDetails;
import com.ireslab.sendx.electra.dto.ReceiptAndInvoiceUtilDto.Detailtable;
import com.ireslab.sendx.electra.model.NotificationRequest;
import com.ireslab.sendx.electra.model.PaymentRequest;
import com.ireslab.sendx.electra.model.SendxElectraRequest;



@RestController
@RequestMapping(value = "/test/*")
public class TestController {
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionalApiServiceImpl trasactionalApiService;
	@Autowired
	OAuthAccessTokenRepository accessTokenRepo;
	
	@Autowired
	private TestServiceImpl testServiceImpl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private CommonService  commonService;
	
	@Autowired
	private ReceiptAndInvoiceUtil receiptAndInvoiceUtil;
	
	
	

	@RequestMapping(value = "deleteAccessToken", method = RequestMethod.GET)
	public void deleteAccessToken(@RequestParam(value = "userName") String userName) {

		/*System.out.println("TestController.deleteAccessToken(), username - " + userName);
		OAuthAccessToken authAccessToken = accessTokenRepo.findByUserName(userName);

		System.out.println(authAccessToken.toString());
		accessTokenRepo.delete(authAccessToken);*/
		double num = 0.00;
		DecimalFormat decimalFormat = new DecimalFormat("#.00");
		String numberAsString = decimalFormat.format(num);
		System.out.println(numberAsString);

		//System.out.println("Deleted");
	}
	
	
	@RequestMapping(value = "getAllTransactionDetails", method = RequestMethod.POST)
	public void getAllTransactionDetails(@RequestBody SendxElectraRequest sendxElectraRequest) {
	
		testServiceImpl.getAllTransactionalDetails(sendxElectraRequest);

		//System.out.println("Deleted");
	}
	
	
private final String TOPIC = "f78d420a9cc2c449";
	
	@Autowired
	AndroidPushNotificationsService androidPushNotificationsService;

	@RequestMapping(value = "send", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> send() throws JSONException {

		JSONObject body = new JSONObject();
		body.put("to", "/topics/" + TOPIC);
		body.put("priority", "high");

		JSONObject notification = new JSONObject();
		notification.put("title", "JSA Notification");
		notification.put("body", "Happy Message!");
		
		JSONObject data = new JSONObject();
		data.put("Key-1", "JSA Data 1");
		data.put("Key-2", "JSA Data 2");

		body.put("notification", notification);
		body.put("data", data);

		HttpEntity<String> request = new HttpEntity<>(body.toString());

		CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request,"");
		CompletableFuture.allOf(pushNotification).join();

		try {
			String firebaseResponse = pushNotification.get();
			
			System.out.println(firebaseResponse);
			
			return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
	}
	
	
	@RequestMapping(value = "oauthTest", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> oauthImplimentation() throws JSONException {
		Account account = accountRepository.findByUserCorrelationId("83289214-02d0-487d-818f-8f435ab580e3");
		
		System.out.println(trasactionalApiService.retrieveApiAccessToken());
		
		

		return new ResponseEntity<>("oauthTest..!!", HttpStatus.OK);
	}
	
	
	
	
	
	@RequestMapping(value = "truliooTest", method = RequestMethod.GET, produces = "application/json")
	public String truliooTest() throws JSONException {

		/*// Trulioo - code for connection check
		String connCheckUrl = "https://api.globaldatacompany.com/connection/v1/sayhello/sach";
		ResponseEntity<String> response= restTemplate.getForEntity( connCheckUrl, String.class);
		
		System.out.println("Response : body - "+response.getBody()+"\n Code - "+response.getStatusCodeValue());*/
		
		
		// Trulioo - code for test authentication
		String connTestUrl = "https://api.globaldatacompany.com/connection/v1/testauthentication";
		String  password = "Sendxteam@12345";
		
		//identity authentication
		String  username = "SendX_Demo_API";
		HttpEntity<String> requestHeader = new HttpEntity<String>(createHeaders(username, password));
		ResponseEntity<String> response = restTemplate.exchange(connTestUrl, HttpMethod.GET, requestHeader, String.class);
		
		System.out.println("Identity Authentication Url - "+connTestUrl);
		System.out.println("\n Response : body - "+response.getBody()+", Code - "+response.getStatusCodeValue());

		return "Response : body - "+response.getBody()+"\n Code - "+response.getStatusCodeValue();
	}


	private HttpHeaders createHeaders(final String username, final String password) {
		return new HttpHeaders() {
			{
				String auth = username + ":" + password;
				// Charset.forName("US-ASCII")
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}
	
	
	@RequestMapping(value = "testMethod", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> testMethod() throws JSONException {
		System.out.println("Test Method Executed..!!");
		
		NotificationRequest notificationRequest = new NotificationRequest();
		commonService.saveNotificationData(notificationRequest);
		return new ResponseEntity<>("Test Method Executed..!!", HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "testThymeleaf", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> testThymeleaf() throws JSONException {
		System.out.println("Thymeleaf Test Method Executed..!!");
		 String pack = this.getClass().getPackage().getName().replace(".", "/");
		 System.out.println(pack);
		System.out.println(build("testing.. template"));
		
		return new ResponseEntity<>("Test Method Executed..!!", HttpStatus.OK);
	}
	
	
	public String build(String message) {
		
		com.ireslab.sendx.electra.dto.ReceiptAndInvoiceUtilDto dto = new com.ireslab.sendx.electra.dto.ReceiptAndInvoiceUtilDto();
		//----------
	    CompanyDetails companyDetails = new CompanyDetails();
		companyDetails.setMerchantName("SANTOSH");
		companyDetails.setEmailAddress("mahto.mailbox@gmail.com");
		companyDetails.setCountryDialCode("+91");
		companyDetails.setMobileNumber("9015293893");
		companyDetails.setResidentialAddress("A-54, Block A, Sector 57, Noida, Uttar Pradesh 201301, India India");
		companyDetails.setGstNo("GST1234");
		//----------
		
		Detailtable detailTable = new Detailtable();
		detailTable.setInvoiceAndReceiptNumber("RXC-000878");
		detailTable.setInvoiceAndReceiptGeneratedDate("Thursday 25/10/2018 12:17 AM");
		detailTable.setCurrencySymbol("₹");
		detailTable.setGrandTotalString("654.16");
		detailTable.setPaymentTerm("60 Days");
		
		BillToAddress billToAddress = new BillToAddress();
		billToAddress.setInvoiceType("TAX-INVOICE");
		billToAddress.setEmailAddress("sachinclient@yopmail.com ");
		billToAddress.setMobileNumber("+91 - 9632587419");
		billToAddress.setPurchaserName("SachinClient ");
		billToAddress.setResidentialAddress("A-54, Block A, Sector 57, Noida, Uttar Pradesh 201301, India India");
		billToAddress.setSubtotalHeader("subtotalHeader");
		billToAddress.setTotalString("totalString");
		billToAddress.setDiscountString("discountString");
		billToAddress.setTotalHeader("totalHeader");
		billToAddress.setGstTotalString("gstTotalString");
		billToAddress.setGrandTotalString("grandTotalString");
		billToAddress.setCustomerNotes("<ol style=\"padding-left:15px;\">\r\n" + 
				"							<li>dasdfjsh\\n djkfhdfh\r\n" + 
				"								<br/>dgxcgergnetyxqewtt\r\n" + 
				"							</li>\r\n" + 
				"							<li>hjght thet rthrt rthrth hrth rhrt jsfh dfjwefhc fhwejhcmwefhwejhqweuiorh jhqwekfjyhadfkhweuir fvedwrre54 gwrdfbere</li>\r\n" + 
				"							<li>bjakb bfhaf afakh ashfbaksf kaka</li>\r\n" + 
				"						</ol>");
		billToAddress.setTermsAndCondition("<ol style=\"padding-left:15px;\">\r\n" + 
				"							<li>sdfsmnadfbkdjsaf</li>\r\n" + 
				"							<li>rtyrty rhtyr gfdhrt rthrt</li>\r\n" + 
				"							<li>afasdhk adhfasdfg afkalf afkgakf</li>\r\n" + 
				"						</ol>");
		
		PaymentRequest paymentRequest = new PaymentRequest();
		ProductDto product1 =new ProductDto();
		ProductDto product2 =new ProductDto();
		
		List<ProductDto> plist=new ArrayList<>();
		plist.add(product1);
		plist.add(product2);
		paymentRequest.setProductList(plist);
		
		dto.setCompanyDetails(companyDetails);
		dto.setDetailTable(detailTable);
		dto.setBillToAddress(billToAddress);
		dto.setPaymentRequest(paymentRequest);
		dto.setReceiptSpan(4);
		dto.setInvDisSpan(0);
		dto.setTdSpan(8);
		dto.setIsgsnt(false);
		dto.setIsgstAppliend(false);
		return receiptAndInvoiceUtil.companyDetailsForInvoice(dto);
    }
	
	
}
