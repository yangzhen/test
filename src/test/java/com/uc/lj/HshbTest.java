package com.uc.lj;

import com.uc.renren.dao.HouseDao;
import com.uc.rest.UcRESTTemplate;
import com.uc.util.BaseTestAbstact;
import com.uc.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
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

import java.io.BufferedWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class HshbTest extends BaseTestAbstact {

	private static final String SPLIT = "^|";
	
	@Autowired
	File2DBService service;

	@Autowired
	private HouseDao dao;

	public void dohh(CountDownLatch latch) {
		logger.info("hshb start");
		StopWatch watch = new StopWatch();
		watch.start();
		test();
		latch.countDown();
		watch.stop();
		logger.info("hshb end,cost:" + watch.getTotalTimeMillis());
	}

	@Test
	public void test() {
		UcRESTTemplate rest = UcRESTTemplate.getNewInstance();
		HttpHeaders headers = new HttpHeaders();

		headers.add("Host", "hz.hshb.cn");
		headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		headers.add("Accept-language", "en-US,en;q=0.8,zh-CN;q=0.6,zh;q=0.4");
		headers.add("User-Agent",
				"Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:48.0) Gecko/20100101 Firefox/48.0");
		headers.add("Referer", "http://hz.hshb.cn/chushou/pz_100-300s_1lg1");
		headers.add("Connection", "keep-alive");
		
		String www = "http://hz.hshb.cn/chushou/";
		String query = "pz_100-300s_1lg";
		String path = "/Users/yangzhen/logs/fz/hshb.txt";
		Path file = Paths.get(path);
		Charset charset = Charset.forName("utf-8");
		AtomicInteger atm = new AtomicInteger();
		String runDate = DateUtils.getCurrentDateStr();
		HttpEntity<Void> httpEntity = new HttpEntity<Void>(headers);
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Pattern pattern = Pattern.compile("[0-9\\.].*");
		try (BufferedWriter bufferedWriter = Files.newBufferedWriter(file, charset);) {
			int pageSize = 301;
			for(int j=1; j<=230;j++) {
				String url = www + query+j;
				try{

					ResponseEntity<String> entity = rest.getEntity(url, httpEntity);
					//System.out.println("hshb url:" + url+",content:" + entity.getBody());
					Document document = Jsoup.parse(entity.getBody());
					if(j==1) {
						String number = document.select("div.m-hnit").first().select("span").text();
						pageSize = Integer.parseInt(number)/20;
						System.out.println("hshb,pageSize:" + pageSize+",totalNumber:" + number);
						dao.deleteStat(DateUtils.getCurrentDateStr(),"hshb");
						dao.insertStat(DateUtils.getCurrentDateStr(),"hshb",Integer.parseInt(number));
					}
					Element body = document.getElementsByClass("m-hLstPl").first();
					Elements elements = body.getElementsByClass("m-liInf");
					System.out.println("hshb,url:" + url+",elements.size():" + elements.size());
					for(int i=0;i<elements.size();i++) {
						Element element = elements.get(i);
						String href = element.select("div.mhName").first().select("a").attr("href");
						String fzHref = "http://hz.hshb.cn" + href;
						String title = element.select("a").first().text();
						String loupan = element.select("div.m-rows.ico-h1").select("a").first().text();
						Elements elements1 = element.select("div.m-rows.ico-h1").select("a");
						String shiqu = elements1.get(1).text();
						String xiaoqu = "";
						if(elements1.size()==3) {
							xiaoqu = elements1.get(2).text();
						}
						String visitCount = element.select("div.m-rows.ico-h3").text().split("带看")[1].split("次")[0];
						String tag = element.select("div.tabCon").text();
						String price = element.select("div.m-total").first().text().split("万")[0].trim();
						String unitPrice = element.select("div.m-pril").first().text().split("单价")[1].split("元")[0].trim();
						String desc = element.select("div.m-rows.ico-h2").first().text();
						String[] aa = StringUtils.split(desc,"|");
						String jushi = aa[0].trim();
						String area = aa[1].trim().split("平")[0].trim();
						Matcher matcher = pattern.matcher(area);
						if(matcher.find()) {
							area = matcher.group();
						}
						String chaoxiang = "";
						String zhuangxiu = "";
						String buildDesc = "";
						if(aa.length==5) {
							chaoxiang = aa[2].trim();
							zhuangxiu = aa[3].trim();
							buildDesc = aa[4].trim();
						} else {
							buildDesc = aa[2].trim();
						}
						String hh = atm.incrementAndGet()+SPLIT+loupan+SPLIT+jushi+SPLIT+area+SPLIT+chaoxiang+SPLIT
								+buildDesc+SPLIT+visitCount+SPLIT+ price+SPLIT+unitPrice+SPLIT+shiqu+ SPLIT
								+title+SPLIT+fzHref+SPLIT+tag+SPLIT+xiaoqu+SPLIT+runDate;
						System.out.println(hh+",hshbjpaqu");
						bufferedWriter.write(hh + "\n");
					}
					if(elements.size() < 20) {
						break;
					}
					int thleep = ThreadLocalRandom.current().nextInt(200, 800);
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
		System.out.println("hshb网页共计耗时：" + cost+","+path);
		try {
			service.hshbFile2DB(runDate);
		} catch (Exception e) {
			logger.error("test service error",e);
		}
	}

	public static void main(String[] args) {
		String a = "2室1厅  |  62.42平米  |  朝南  |   低楼层 共(7层)";
		String[] aa = StringUtils.split(a,"|");
		System.out.println(Arrays.asList(aa));
		System.out.println(a.split("\\|")[0].trim()+",a.length:" + aa.length);
		String dd = "  62.42";
		System.out.println(Double.parseDouble("27500.0".trim().replace(" ","")));
		Pattern pattern = Pattern.compile("[0-9\\.].*");
		Matcher matcher = pattern.matcher(dd);
		if(matcher.find()) {
			System.out.println(matcher.group());
		}
	}
}
