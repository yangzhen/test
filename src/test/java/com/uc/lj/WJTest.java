package com.uc.lj;

import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import com.uc.renren.dao.HouseDao;
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

	@Autowired
	private HouseDao dao;


	public void dohh(CountDownLatch latch) {
		logger.info("5i5j start");
		StopWatch watch = new StopWatch();
		watch.start();
		test();
		latch.countDown();
		watch.stop();
		logger.info("5i5j end,cost:" + watch.getTotalTimeMillis());
	}

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
		AtomicInteger atm = new AtomicInteger();
		String runDate = DateUtils.getCurrentDateStr();
		HttpEntity<Void> httpEntity = new HttpEntity<Void>(headers);
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		try (BufferedWriter bufferedWriter = Files.newBufferedWriter(file, charset);) {
			int pageSize = 301; //5i5j存在bug,331页之后无法访问,请求error
			for(int j=1; j<=331;j++) {
				String url = "http://hz.5i5j.com/exchange/" + query+"n"+j;
				try{
					ResponseEntity<String> entity = rest.getEntity(url, httpEntity);
					//System.out.println("5i5j url:"+url+",content:" + entity.getBody());
					Document document = Jsoup.parse(entity.getBody());
//					if(j==1) {
//						String number = document.select("font.font-houseNum").first().text();
//						//pageSize = Integer.parseInt(number)*8/300;
//						pageSize = Integer.parseInt(number)/30;
//						System.out.println("5i5j,pageSize:" + pageSize+",totalNumber:" + number);
//						dao.deleteStat(DateUtils.getCurrentDateStr(),"5i5j");
//						dao.insertStat(DateUtils.getCurrentDateStr(),"5i5j",Integer.parseInt(number));
//					}
					Element body = document.getElementsByClass("list-body").first();
					Elements elements = body.getElementsByClass("list-info");
					System.out.println("5i5j,url:" + url+",elements.size():" + elements.size());
					for(int i=0;i<elements.size();i++) {
						Element element = elements.get(i);
						String href = element.select("a").first().attr("href");
						String fzHref = www + href;
						String title = element.select("a").first().text();
						Element element2 = element.getElementsByClass("list-info-l").first();
						String loupan = element2.select("a").get(0).text().split("\\s")[0].trim();
						String city = element2.select("a").get(1).text().trim();
						String xiaoqu = element2.select("a").get(2).text().split("二手")[0].trim();

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
						String visitCount = element2.select("li").get(2).text().split("\\s")[1];

						String price = element.getElementsByClass("list-info-r").first().select("h3").text().split("万")[0];
						String unitPrice = element.getElementsByClass("list-info-r").first().select("p").text().split("元")[0];
						String hh = atm.incrementAndGet() + SPLIT + loupan + SPLIT + jushi + SPLIT + mianji + SPLIT + directrion + SPLIT
								+  buildDesc + SPLIT + visitCount + SPLIT + price + SPLIT + unitPrice + SPLIT + city + SPLIT
								+ title + SPLIT + fzHref + SPLIT + tag + SPLIT + xiaoqu+ SPLIT+ runDate;
						System.out.println(hh+",5i5jpaqu");
						bufferedWriter.write(hh + "\n");
					}
					int thleep = ThreadLocalRandom.current().nextInt(200, 1500);
					Thread.sleep(thleep);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			logger.error("main service error", e);
		}
		stopWatch.stop();
		long cost = stopWatch.getTotalTimeMillis();
		System.out.println("5i5j网页共计耗时：" + cost+","+path);
		try {
			service.wjFile2DB(runDate);
		} catch (Exception e) {
			logger.error("test service error",e);
		}
	}
}
