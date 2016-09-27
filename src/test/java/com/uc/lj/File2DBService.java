package com.uc.lj;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.uc.renren.bean.HouseBean;
import com.uc.renren.dao.HouseDao;
import com.uc.util.BaseTestAbstact;
import com.uc.util.DateUtils;

@Component
public class File2DBService  extends BaseTestAbstact {

	private static final Logger logger = LoggerFactory.getLogger(File2DBService.class);
	
	@Autowired
	private HouseDao dao;

	@Test
	public void test() throws Exception {
		for( int i=18;i<=26;i++) {
			String runDate = "2016-09-" + i;
			file2DB(runDate);
		}
	}
	
	public void file2DB(String runDate) throws Exception {
		dao.delete(runDate);
		String log = "/Users/yangzhen/logs/fz/p2p3p4_";
 		String name = log + runDate + ".txt";
 		name = "/Users/yangzhen/logs/fz/co32p2p3p4.txt";
		Path path = Paths.get(name);
		List<String> list = Files.readAllLines(path);
		for (String str : list) {
			logger.info(str);
			HouseBean bean = new HouseBean();
			bean.setSite("lianjia");
			String arr[] = str.split(",");
			int seq = Integer.parseInt(arr[0]); //序号
			bean.setSeq(seq);
			String[] fangzi = StringUtils.split(arr[1], "|");
			bean.setLoupan(fangzi[0].trim()); //楼盘名字
			bean.setJushi(fangzi[1].trim()); //几室几厅
			Double mianji = Double.parseDouble(StringUtils.substringBefore(fangzi[2].trim(), "平米")
					.trim()); //面积
			bean.setArea(mianji);
			bean.setDirection(fangzi[3].trim()); //朝向
			bean.setDecorate(fangzi[4].trim()); //装修
			if(fangzi.length >= 6) {
				bean.setIsDianti(fangzi[5].trim().equals("有电梯"));
			}
			bean.setBuildingDesc(arr[2]); //建筑描述
			Integer buildYear = null;
			Pattern pattern = Pattern.compile("[0-9]{4}");
			Matcher matcher = pattern.matcher(arr[2].trim());
			if(matcher.find()) {
				buildYear = Integer.parseInt(matcher.group());
			}
			bean.setYear(buildYear);
			//0,天苑花园 | 3室2厅 | 124.5平米 | 南 | 其他 | 有电梯,高楼层(共25层) 1998年建塔楼 - 翠苑,250万,单价20081元/平米,0人关注 / 共0次带看 / 刚刚发布,九莲小学,http://hz.lianjia.com/ershoufang/103100594886.html,天苑花园 3室2厅 250万,翠苑,2016-09-26
			Double price = Double.parseDouble(arr[3].split("万")[0]);
			bean.setPrice(price);
			Integer unitPrice = Integer.parseInt(arr[4].split("价")[1].split("元")[0]);

			String[] parr = arr[5].split("/");
			Integer subCount = Integer.parseInt(parr[0].trim().split("人")[0]);
			bean.setSubCount(subCount);
			Integer visitCount = Integer.parseInt(parr[1].trim().split("共")[1].split("次")[0]);
			bean.setVisitCount(visitCount);
			String dateDesc = parr[2].trim();
			String dt = null;
			Date date = DateUtils.getDateStr(arr[arr.length-1]);
			if (dateDesc.equals("刚刚发布")) {
				dt = DateUtils.getDateStr(date);
			} else if (dateDesc.contains("天")) {
				Integer cou = Integer.parseInt(dateDesc.split("天")[0]);
				Date date2 = DateUtils.getDateBeforeOrAfter(date, -cou);
				dt = DateUtils.getDateStr(date2);
			} else if (dateDesc.contains("月")) {
				Integer cou = Integer.parseInt(dateDesc.split("个月")[0]);
				Date date2 = DateUtils.getMonthBeforeOrAfter(date, -cou);
				dt = DateUtils.getDateStr(date2);
				dt = StringUtils.substringBeforeLast(dt, "-") + "-00";
			}
			bean.setPubDate(dt);
			bean.setPubDateDesc(dateDesc);
			bean.setUnitPrice(unitPrice);
			bean.setTag(arr[6]);
			bean.setHref(arr[7]);
			bean.setTitle(arr[8]);
			bean.setXiaoqu(arr[arr.length-2]);
			bean.setCrawlingDate(arr[arr.length-1]);
			logger.info(bean.toString());
			dao.insert(bean);
		}
	}
}
