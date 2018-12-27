package com.ireslab.electraapp.notification;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@PropertySource(value = "classpath:notification_config.properties")
@ConfigurationProperties
@Service
public class AndroidPushNotificationsService {
	                             
	private String firebaseServiceKey;
	private String firebaseApiUrl;
	private static final String FIREBASE_SERVER_KEY = "AAAAXv_fEMI:APA91bGbytwJju9AeHQYVUcAFJs-pfbt56nNQ7bD4eD9I-SNG9slX_9Tg3d-0DorruQF2aAKHlQOwA90BDeZl277NP5p2tjkDh6l94Ew1bAXk_kKIUoeiimOGLqzhpPKLfTKJyhiDzTe";
	private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";
	
	@Async
	public CompletableFuture<String> send(HttpEntity<String> entity, String firebaseServiceKey) {
		//public CompletableFuture<String> send(HttpEntity<String> entity) {

		RestTemplate restTemplate = new RestTemplate();

		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		//interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
		interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + firebaseServiceKey));
		System.out.println("Firebase key in electraApp ::::: -----> "+firebaseServiceKey);
		interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
		restTemplate.setInterceptors(interceptors);

		//String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);
		String firebaseResponse = restTemplate.postForObject(firebaseApiUrl, entity, String.class);

		return CompletableFuture.completedFuture(firebaseResponse);
	}
	
	public String sendPushNotification(AndroidPushNotificationRequest androidPushNotificationRequest) {
		
		HttpEntity<String> request = new HttpEntity<>(androidPushNotificationRequest.getBody().toString());

		CompletableFuture<String> pushNotification = send(request, androidPushNotificationRequest.getFirebaseServiceKey());
		//CompletableFuture<String> pushNotification = send(request);
		CompletableFuture.allOf(pushNotification).join();

		try {
			String firebaseResponse = pushNotification.get();
			System.out.println("Request json send : "+androidPushNotificationRequest.getBody().toString());
			System.out.println("PUSH MESSAGE RESPONSE JSON :"+firebaseResponse);
			return firebaseResponse;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*public String getFirebaseServiceKey() {
		return firebaseServiceKey;
	}

	public void setFirebaseServiceKey(String firebaseServiceKey) {
		this.firebaseServiceKey = firebaseServiceKey;
	}
*/
	public String getFirebaseApiUrl() {
		return firebaseApiUrl;
	}

	public void setFirebaseApiUrl(String firebaseApiUrl) {
		this.firebaseApiUrl = firebaseApiUrl;
	}
	
	
}
