package com.uc.rest;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import com.alibaba.fastjson.JSONObject;

public class UcRestTemplateTest {
	
	@Test
	public void testPost() {
		UcRESTTemplate ucRESTTemplate = new UcRESTTemplate();
		String host = "http://test-renren-sales.ucredit.com/renren-sales";
		HttpHeaders headers = new HttpHeaders();
		headers.set("name", "yxy");
		headers.set("uid", "123");
		headers.set("Authorization", "uuuuuuuu");
		
		headers.add("Cookie", "uuid="+UUID.randomUUID().toString());
		headers.add("Cookie", "love=true");
		
		LinkedMultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("name", "hhhh");
		
		HttpEntity<LinkedMultiValueMap<String,String>> httpEntity = new HttpEntity<LinkedMultiValueMap<String,String>>(multiValueMap, headers);
		ResponseEntity<String> responseEntity = ucRESTTemplate.postEntity(host+"/test",httpEntity);
		String resp = responseEntity.getBody();
		System.out.println("testPost:" + resp);
		JSONObject json = JSONObject.parseObject(resp);
		Integer resCode = json.getInteger("code");
		Assert.assertEquals(0, resCode.intValue());
	}
	
	@Test
	public void testGet() {
		UcRESTTemplate ucRESTTemplate = new UcRESTTemplate();
		String host = "http://test-renren-sales.ucredit.com/renren-sales";
		HttpHeaders headers = new HttpHeaders();
		headers.set("name", "yxy");
		headers.set("uid", "123");
		headers.set("Authorization", "uuuuuuuu");
		
		headers.add("Cookie", "uuid="+UUID.randomUUID().toString());
		headers.add("Cookie", "love=true");
		
		LinkedMultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
		multiValueMap.add("name", "hhhh");
		multiValueMap.add("uid", "123");
		
		HttpEntity<?> httpEntity = new HttpEntity<LinkedMultiValueMap<String,String>>(headers);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(host+"/test").queryParams(multiValueMap);
		ResponseEntity<String> responseEntity = ucRESTTemplate.getEntity(builder.build().encode().toString(), httpEntity);
		String resp = responseEntity.getBody();
		System.out.println("testGet:" + resp);
		JSONObject json = JSONObject.parseObject(resp);
		Integer resCode = json.getInteger("code");
		Assert.assertEquals(0, resCode.intValue());
	}
	
}
