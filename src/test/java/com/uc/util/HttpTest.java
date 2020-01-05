package com.uc.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.tools.ant.taskdefs.condition.Http;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class HttpTest {

    Logger logger = LoggerFactory.getLogger(HttpTest.class);

    private static ScheduledExecutorService monitorExecutor;

    public RestTemplate test() throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext
                ,null, null, NoopHostnameVerifier.INSTANCE);

        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", csf)
                .build();

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        //最大连接数3000
        connectionManager.setMaxTotal(3000);
        //路由链接数400
        connectionManager.setDefaultMaxPerRoute(400);


        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(60000)
                .setConnectTimeout(60000)
                .setConnectionRequestTimeout(10000)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();

        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .evictExpiredConnections()
                .evictIdleConnections(30, TimeUnit.SECONDS)
                .build();
        requestFactory.setHttpClient(httpClient);

        monitorExecutor = Executors.newScheduledThreadPool(1);
        monitorExecutor.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //关闭异常连接
                connectionManager.closeExpiredConnections();
                //关闭5s空闲的连接
                connectionManager.closeIdleConnections(3000, TimeUnit.MILLISECONDS);
                logger.info("close expired and idle for over 5s connection");
            }
        }, 10000, 10000, TimeUnit.MILLISECONDS);

        RestTemplate restTemplate = new RestTemplate(requestFactory);

        return restTemplate;
    }


    public static void main(String[] args) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        HttpTest rest  = new HttpTest();
        RestTemplate template = rest.test();
        ResponseEntity<String> a = template.getForEntity("https://www.jianshu.com/p/82f30208ae56", String.class);
        System.out.println(a.getBody());
    }

}

class IdleConnectionMonitorThread extends Thread {

    private final HttpClientConnectionManager connMgr;
    private volatile boolean exitFlag = false;

    public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr) {
        this.connMgr = connMgr;
        setDaemon(true);
    }

    @Override
    public void run() {
        while (!this.exitFlag) {
            synchronized (this) {
                try {
                    this.wait(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 关闭失效的连接
            connMgr.closeExpiredConnections();
            // 可选的, 关闭30秒内不活动的连接
            connMgr.closeIdleConnections(30, TimeUnit.SECONDS);
        }
    }

    public void shutdown() {
        this.exitFlag = true;
        synchronized (this) {
            notify();
        }
    }

}
