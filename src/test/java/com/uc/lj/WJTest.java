package com.uc.lj;

import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.uc.rest.UcRESTTemplate;
import com.uc.util.BaseTestAbstact;
import com.uc.util.DateUtils;

@Component
public class WJTest extends BaseTestAbstact {

	private static final String SPLIT = "^|";
	
	@Autowired
	File2DBService service;
	
	@Test
	public void test() {
		UcRESTTemplate rest = UcRESTTemplate.getNewInstance();
		HttpHeaders headers = new HttpHeaders();

		headers.add("Host", "hz.5i5j.com");
		headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		headers.add("Accept-language", "en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4");
		headers.add("User-Agent",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:48.0) Gecko/20100101 Firefox/48.0");
		headers.add("Referer", "http://hz.5i5j.com/exchange/p5u1o6n");
		headers.add("Connection", "keep-alive");
		
		String www = "http://hz.5i5j.com";
		//String query = "p5u1o6n";
		String query = "b100e300u1o6n";
		String path = "/Users/yangzhen/logs/fz/" + query + "_wj.txt";
		Path file = Paths.get(path);
		Charset charset = Charset.forName("utf-8");
		int count=0;
		String runDate = DateUtils.getCurrentDateStr();
		HttpEntity<Void> httpEntity = new HttpEntity<Void>(headers);
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		try (BufferedWriter bufferedWriter = Files.newBufferedWriter(file, charset);) {
			for(int j=1; j<201;j++) {
				String url = "http://hz.5i5j.com/exchange/" + query+"n"+j;
				ResponseEntity<String> entity = rest.getEntity(url, httpEntity);
				System.out.println(entity.getBody());
				Document document = Jsoup.parse(entity.getBody());
				Element body = document.getElementsByClass("list-body").first();
				Elements elements = body.getElementsByClass("list-info");
				for(int i=0;i<elements.size();i++) {
					Element element = elements.get(i);
					String href = element.select("a").first().attr("href");
					String fzHref = www + href;
					System.out.println(fzHref);
					String title = element.select("a").first().text();
					System.out.println(title);
					Element element2 = element.getElementsByClass("list-info-l").first();
					String loupan = element2.select("a").get(0).text().split("\\s")[0].trim();
					String city = element2.select("a").get(1).text().trim();
					String xiaoqu = element2.select("a").get(2).text().split("二手")[0].trim();
					System.out.println(loupan + SPLIT + city + SPLIT + xiaoqu);
					
					String desc = element2.getElementsByClass("font-balck").text();
					String a[]  = desc.split("\\s");
					String jushi = a[0];
					String mianji = a[1].split("面积")[1].split("平")[0];
					String directrion = "";
					String buildDesc = "";
					if(a.length>=4) {
						directrion = a[2].trim();
						buildDesc = a[3].trim();
					} else {
						directrion = "";
						buildDesc = a[2].trim();
					}
					System.out.println(Arrays.asList(a));
					String tag = element2.getElementsByClass("publish").first().text();
					System.out.println("tag:" + tag);
					String visitCount = element2.select("li").get(2).text().split("\\s")[1];
					
					String price = element.getElementsByClass("list-info-r").first().select("h3").text().split("万")[0];
					System.out.println("price:" + price);
					String unitPrice = element.getElementsByClass("list-info-r").first().select("p").text().split("元")[0];
					System.out.println("unitPrice:" + unitPrice);
					String hh = (count++) + SPLIT + loupan + SPLIT + jushi + SPLIT + mianji + SPLIT + directrion + SPLIT 
							+  buildDesc + SPLIT + visitCount + SPLIT + price + SPLIT + unitPrice + SPLIT + city + SPLIT
							+ title + SPLIT + fzHref + SPLIT + tag + SPLIT + xiaoqu+ SPLIT+ runDate;
					System.out.println(hh);
					bufferedWriter.write(hh + "\n");
					if(elements.size() < 30) {
						break;
					}
				}
				int thleep = ThreadLocalRandom.current().nextInt(300, 5000);
				Thread.sleep(thleep);
			}
		} catch (Exception e) {
			logger.error("main service error", e);
		}
		stopWatch.stop();
		long cost = stopWatch.getTotalTimeMillis();
		System.out.println("网页共计耗时：" + cost+","+path);
		try {
			service.wjFile2DB(runDate);
		} catch (Exception e) {
			logger.error("test service error",e);
		}
	}
}
