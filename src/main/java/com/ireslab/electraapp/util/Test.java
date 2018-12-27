package com.ireslab.electraapp.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.itextpdf.text.log.SysoCounter;

public class Test { 
	
	public static  String temp="temp";

	public static void main(String[] args) throws MalformedURLException, IOException {
	/*	
		JSONObject productDetails1 = new JSONObject();
		Map<String, String> p1 =new HashMap<>();
		p1.put("k1", "d1");
		p1.put("k2", "d2");
		
		Map<String, String> p2 =new HashMap<>();
		p2.put("k1", "d1");
		p2.put("k2", "d2");
		
		Map<String, String> p3 =new HashMap<>();
		p3.put("k1", "d1");
		p3.put("k2", "d2");
		List arr = new ArrayList(); 
		arr.add(p1);
		arr.add(p2);
		arr.add(p3);
		try {
			productDetails1.put("productDetails", arr);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(productDetails1);*/
		
		 BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		 System.out.println(bCryptPasswordEncoder.encode("electraapp"));
	}
	
	

}
