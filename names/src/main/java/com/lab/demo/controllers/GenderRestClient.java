package com.lab.demo.controllers;

import java.util.HashMap;

import org.springframework.web.client.RestTemplate;

import com.lab.demo.model.GenderItem;

public class GenderRestClient
{
	private static final String baseGenderUrl = "https://gender-api.com/get";
	private static final String apiKey = "pzrpPuBRPwVUlTGZqb";
	
	public GenderItem getGenderInfo(String searchName)
	{
		String urlString = baseGenderUrl + "?"
										 + "name={name}&key={key}";
		
		HashMap<String, String> urlParams = new HashMap<String, String>();
		urlParams.put("name", searchName);
		urlParams.put("key", apiKey);
		
		RestTemplate restTemplate = new RestTemplate();
		GenderItem item = restTemplate.getForObject(urlString, GenderItem.class, urlParams);
		
		return item;
	}
}
