package com.uc.lj;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import com.uc.renren.dao.HouseDao;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.uc.rest.UcRESTTemplate;
import com.uc.util.BaseTestAbstact;
import com.uc.util.DateUtils;

@Component
public class LJTest  extends BaseTestAbstact {

	@Autowired
	File2DBService service;

	@Autowired
	private HouseDao dao;

	public void test() {

		UcRESTTemplate rest = UcRESTTemplate.getNewInstance();
		HttpHeaders headers = new HttpHeaders();

		headers.add("Host", "app.api.lianjia.com");
		//20160824_ios:bfc180c140018c5c0407016202a5248f38c5cf5f
		String hh = "bfc180c140018c5c0407016202a5248f38c5cf5f";
		headers.add("Authorization",
				"MjAxNjA4MjRfaW9zOmJmYzE4MGMxNDAwMThjNWMwNDA3MDE2MjAyYTUyNDhmMzhjNWNmNWY=");
		headers.add("Lianjia-Version", "7.0.1");
		headers.add("Accept-Language", "zh-Hans-CN;q=1");
		headers.add("Accept-Encoding", "gzip, deflate");
		headers.add("Lianjia-Timestamp", "1473395885.677372");
		headers.add("Accept", "*/*");
		headers.add("User-Agent", "HomeLink 7.0.1;iPhone7,1;iOS 9.3.4;");
		//headers.add("Referer", "lianjia%3A%2F%2FLJSecondHandHouseListV2ViewController%3Fcity_id%3D330100%26condition%3Dbp100ep200%26limit_count%3D20%26limit_offset%3D0%26request_ts%3D1473396278");
		//headers.add("Page-Schema", "lianjia%3A%2F%2FLJSecondHandHouseDetailV2ViewController");
		headers.add("Connection", "keep-alive");
		//headers.add("Cookie", "lianjia_uuid=A372EFCD-E7DC-4E53-B280-94CDB7A69937; lianjia_ssid=CEB8F945-C5B5-443B-A50D-119E59D248A4; lianjia_udid=152ACA22-73C9-4E1D-B603-E63EAD0C7A62");
		headers.add("Lianjia-Device-Id", "6C4BA9F2-FDCC-48B1-973E-6EDA2C9AC468");

		///house/ershoufang/detailV3?house_code=103100101382&request_ts=1473398559
		//house_code=103100488740&request_ts=1473396461
		String url = "https://app.api.lianjia.com/house/ershoufang/detailV3?house_code=103100101382&request_ts=1473398559";

		String a = "103100101382+1473398559";
		String b = DigestUtils.md5Hex(a);
		System.out.println(b);
		assertEquals(b, hh);
		//		
		//		HttpEntity<Void> httpEntity = new HttpEntity<Void>(headers);
		//		ResponseEntity<String> entity = rest.getEntity(url, httpEntity);
		//		System.out.println(entity.getBody());
	}

	public void dohh(CountDownLatch latch) {
		StopWatch watch = new StopWatch();
		watch.start();
		try {
			logger.info("lianjia start");
			testPC();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		latch.countDown();
		watch.stop();
		logger.info("lianjia end,cost:" + watch.getNanoTime()/1000);
	}
	@Test
	public void testPC() throws InterruptedException {
		UcRESTTemplate rest = UcRESTTemplate.getNewInstance();
		HttpHeaders headers = new HttpHeaders();

		headers.add("Host", "hz.lianjia.com");
		headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		headers.add("Accept-language", "en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4");
		headers.add("User-Agent",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:48.0) Gecko/20100101 Firefox/48.0");
		headers.add("Referer", "http://hz.lianjia.com/ershoufang/l1l2l3a2a3a4p2p3/");
		headers.add("Connection", "keep-alive");

		String query = "co32p2p3p4"; //100-150万,150-200万,200-300万 按时间排序
		//String query = "p4p5"; //200-300 300-500
		//String query = "l3l2a2a3p2p3/"; //100-200万,50-90平,2-3室
		
		String path = "/Users/yangzhen/logs/fz/" + query + ".txt";
		Path file = Paths.get(path);
		Charset charset = Charset.forName("utf-8");
		AtomicInteger atm = new AtomicInteger();
		HttpEntity<Void> httpEntity = new HttpEntity<Void>(headers);
		try (BufferedWriter bufferedWriter = Files.newBufferedWriter(file, charset);) {
			long start = System.currentTimeMillis();
			int pageSize = 301;
			for (int i = 1; i <= pageSize; i++) {
				String url = "http://hz.lianjia.com/ershoufang/pg" + i + query; //100-200万
				try {
					ResponseEntity<String> entity = rest.getEntity(url, httpEntity);
					String text = entity.getBody();
					System.out.println(text);
					Document document = Jsoup.parse(text);
					if(i==1) {
						String number = document.select("h2.total.fl").first().getElementsByTag("span").text();
						dao.deleteStat(DateUtils.getCurrentDateStr(),"lianjia");
						dao.insertStat(DateUtils.getCurrentDateStr(),"lianjia",Integer.parseInt(number));
						//pageSize = Integer.parseInt(number)*8/300;
						pageSize = Integer.parseInt(number)/30;
						System.out.println("lianjia pageSize:" + pageSize+",totalNumber:" + number);
					}
					Elements elements = document.getElementsByClass("listContent").first()
							.getElementsByClass("info");
					System.out.println("lianjia,url:" + url+",elements.size():" + elements.size());
					for (Element element : elements) {
						System.out.println(element);
						String houseHref = element.getElementsByClass("title").select("a[href]").first()
								.attr("href"); //房子详情连接
						String houseTitle = element.getElementsByClass("title").select("a[href]").first()
								.text(); //房子标题

						String xiaoqunfo = element.getElementsByClass("houseInfo").select("a[href]")
								.first().attr("href"); //小区详情连接
						String houseInfo = element.getElementsByClass("houseInfo").text(); //房子详情

						System.out.println("houseFlood,communityName,communityInfo");
						String houseFlood = element.getElementsByClass("positionInfo").text(); //所属楼层
						String communityInfo = element.getElementsByClass("positionInfo").select("a[href]")
								.first().attr("href"); //小区房子列表
						String communityName = element.getElementsByClass("positionInfo").select("a[href]")
								.first().text(); //小区名字
						
						String followInfo = element.getElementsByClass("followInfo").text(); //房子查看次数
						System.out.println("houseFlood,followInfo,communityName,communityInfo");
						System.out.println(houseFlood + "," + followInfo + "," + communityName + ","
								+ communityInfo);
						
						Elements elementsTag = element.getElementsByClass("tag"); //房子推荐亮点
						String tag = null;
						for (Element element2 : elementsTag) {
							tag = element2.text() + " ";
						}
						tag = tag.substring(0, tag.length() - 1);
						String totalPrice = element.getElementsByClass("totalPrice").first().text(); //总价
						String unitPrice = element.getElementsByClass("unitPrice").first().text()
								.split(" ")[0]; //单价
						String split = "^|";
						String hh = (atm.incrementAndGet()) + split + houseInfo + split + houseFlood + split + totalPrice + split + unitPrice + split + followInfo + split
								+ tag + split + houseHref + split + houseTitle + split + communityName+split + DateUtils.getCurrentDateStr();
						System.out.println(hh+",lianjiapaqu");
						bufferedWriter.write(hh + "\n");
					}
					int thleep = ThreadLocalRandom.current().nextInt(500, 3000);
					Thread.sleep(thleep);
					if (elements.size() < 30) {
						break;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("网页共计耗时：" + (System.currentTimeMillis() - start)+","+path);
			Thread.sleep(5000);
			long time = System.currentTimeMillis();
			System.out.println("共计耗时：" + (System.currentTimeMillis() - time)+","+path);
		} catch (IOException e) {
			logger.error("main service error", e);
		} catch (Exception e) {
			logger.error("testPC service error",e);
		}
		String runDate = DateUtils.getCurrentDateStr();
		try {
			service.ljFile2DB(runDate);
		} catch (Exception e) {
			logger.error("testPC service error",e);
		}
	}

	public static void main(String[] args) {
		System.out.println(DateUtils.getCurrentDateStr());
		int i = ThreadLocalRandom.current().nextInt(100, 200);
		System.out.println(i);
		String path = "/Users/yangzhen/logs/fz/a.txt";
		Path file = Paths.get(path);
		Charset charset = Charset.forName("utf-8");
		try (BufferedWriter bufferedWriter = Files.newBufferedWriter(file, charset,
				StandardOpenOption.WRITE);) {
			bufferedWriter.write("aa" + "\n");
			bufferedWriter.write("bb" + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
