package com.ireslab.electraapp.notification;

import org.json.JSONObject;

public class AndroidPushNotificationRequest {
	
	private String topic; 
	private JSONObject body;
	private String firebaseServiceKey; 
	
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public JSONObject getBody() {
		return body;
	}
	public void setBody(JSONObject body) {
		this.body = body;
	}
	public String getFirebaseServiceKey() {
		return firebaseServiceKey;
	}
	public void setFirebaseServiceKey(String firebaseServiceKey) {
		this.firebaseServiceKey = firebaseServiceKey;
	}
	
	
	

}
