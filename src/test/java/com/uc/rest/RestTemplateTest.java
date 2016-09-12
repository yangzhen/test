package com.uc.rest;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

public class RestTemplateTest {
	
	static {
		URL url = Thread.currentThread().getContextClassLoader()
				.getResource("logback.xml");
		String name = url.getFile();
		System.setProperty("logback.configurationFile", name);
	}

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Test
	public void testForm() {
		RestTemplate restTemplate = new RestTemplate();
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1000).
				setConnectionRequestTimeout(1000).setSocketTimeout(1000).
				build();
		CloseableHttpClient httpClient = HttpClients.custom().
				setSSLHostnameVerifier(new NoopHostnameVerifier())
				.setDefaultRequestConfig(requestConfig)
				.build();
		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		HttpHeaders headers = new HttpHeaders();
		restTemplate.setRequestFactory(httpRequestFactory);
		
//		headers.set("name", "yxy");
//		headers.set("uid", "123");
//		headers.set("Authorization", "uuuuuuuu");
		String host = "http://www.zhihu.com/";
		ResponseEntity<String> res = null;
		try {
			List<NameValuePair> list = new ArrayList<>();
			list.add(new BasicNameValuePair("name", "yxy"));
			MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
			multiValueMap.add("name", "xxx");
			HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<MultiValueMap<String, String>>(multiValueMap, headers);
			res = restTemplate.postForEntity(host+"/test", httpEntity, String.class);
			System.out.println(JSON.toJSONString(res.getHeaders()));
			logger.info("res:" + res.getBody());
			
		} catch (HttpStatusCodeException e) {
			String result = e.getResponseBodyAsString();
			HttpHeaders headers2 = e.getResponseHeaders();
			logger.info("headers:" + JSON.toJSONString(headers2));
			List<String> list = headers2.get("Set-Cookie");
			System.out.println("===========:"+list);
			logger.info("res:" + result, e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("testForm service error",e);
		}
		
	}

}
