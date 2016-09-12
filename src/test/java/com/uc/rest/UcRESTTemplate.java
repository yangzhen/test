package com.uc.rest;

import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

public class UcRESTTemplate {

	private static final int default_connectionTimeout = 30 * 1000;
	private static final int default_socketTimeout = 30 * 1000;
	private int connectionTimeout = 30 * 1000;
	private int socketTimeout = 30 * 1000;
	private int minsTimeoutTime = 1000;

	private RestTemplate restTemplate;

	private static final Logger logger = LoggerFactory.getLogger(UcRESTTemplate.class);

	public UcRESTTemplate() {
		this(UcRESTTemplate.default_connectionTimeout, UcRESTTemplate.default_socketTimeout);
	}

	public UcRESTTemplate(int connectionTimeout, int socketTimeout) {
		this.connectionTimeout = connectionTimeout < this.minsTimeoutTime ? UcRESTTemplate.default_connectionTimeout
				: connectionTimeout;
		this.socketTimeout = socketTimeout < this.minsTimeoutTime ? UcRESTTemplate.default_socketTimeout
				: socketTimeout;
		this.restTemplate = buildRestTemplate();
	}

	public static UcRESTTemplate getNewInstance() {
		UcRESTTemplate ucRESTTemplate = new UcRESTTemplate();
		return ucRESTTemplate;
	}

	private RestTemplate buildRestTemplate() {
		CloseableHttpClient httpClient = buildHttpClient();
		ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(
				httpClient);
		RestTemplate rt = new RestTemplate(requestFactory);
		StringHttpMessageConverter messageConverters = new StringHttpMessageConverter(Charset.forName("utf-8"));
		List<HttpMessageConverter<?>> list = new ArrayList();
		list.add(messageConverters);
		rt.setMessageConverters(list);
		return rt;
	}

	private CloseableHttpClient buildHttpClient() {
		RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectionTimeout)
				.setSocketTimeout(socketTimeout).build();
		SSLConnectionSocketFactory sslsf = null;
		try {
			SSLContextBuilder builder = new SSLContextBuilder();
			builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
			sslsf = new SSLConnectionSocketFactory(builder.build());
		} catch (KeyManagementException e) {
			logger.error("init http client SSLContextBuilder error", e);
		} catch (NoSuchAlgorithmException e) {
			logger.error("init http client SSLContextBuilder error", e);
		} catch (KeyStoreException e) {
			logger.error("init http client SSLContextBuilder error", e);
		}
		HttpClientBuilder cb = HttpClients.custom().disableAutomaticRetries()
				.setSSLHostnameVerifier(new NoopHostnameVerifier())
				.setDefaultRequestConfig(requestConfig).setSSLSocketFactory(sslsf);
		return cb.build();
	}

	public ResponseEntity<String> getEntity(String url, HttpEntity<?> request) {
		return getEntity(url, request, String.class);
	}

	public <T> ResponseEntity<T> getEntity(String url, HttpEntity<?> request, Class<T> responseType) {
		try {
			return restTemplate.exchange(url, HttpMethod.GET, request, responseType);
		} catch (HttpStatusCodeException ex) {
			handleServerException(url, ex);
			return null;
		} catch (Exception e) {
			logger.error("http 请求异常,url:" + url, e);
			throw new RuntimeException("http 请求异常,url:" + url, e);
		}
	}

	public ResponseEntity<String> postEntity(String url, HttpEntity<?> request) {
		try {
			return restTemplate.postForEntity(url, request, String.class);
		} catch (HttpStatusCodeException ex) {
			return handleServerException(url, ex);
		} catch (Exception e) {
			logger.error("http 请求异常,url:" + url, e);
			throw new RuntimeException("http 请求异常,url:" + url, e);
		}
	}

	protected ResponseEntity<String> handleServerException(String url, HttpStatusCodeException ex) {
		HttpStatus statusCode = ex.getStatusCode();
		String respString = ex.getResponseBodyAsString();
		logger.error("url:" + url + ",statusCode:" + statusCode, ex);
		switch (statusCode) {
		case CONFLICT:
			return new ResponseEntity<>(respString, statusCode);
		case NOT_FOUND:
			throw new RuntimeException("URL not found: " + url);
		default:
			logger.error("Response string:\n" + respString);
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) JSON.parse(respString);
			if (map.containsKey("stackTrace")) {
				Exception ori = JSON.parseObject(respString, Exception.class);
				throw new RuntimeException("Exception thrown from server", ori);
			} else {
				throw new RuntimeException(respString);
			}
		}
	}

	/**
	 * 发送get请求，获取返回结果
	 * 
	 * @param <T>
	 * @param url
	 * @param request
	 * @param cls
	 * @return
	 */
	public <T> T sendHttpsGet(String url, Class<T> cls) {
		return restTemplate.getForObject(url, cls);
	}

}