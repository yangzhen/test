package com.uc.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public class LogTest extends BaseTestAbstact{
	
	@Test
	public void test() {
		logger.info("aaaaaaaaaa");
		logger.info("aaa,{},bb{},yyy,aa",10,11,22);
		String a = "天苑花园 | 3室2厅 | 124.5平米 | 南 | 其他 | 有电梯";
		String[] arr = a.split("|");
		System.out.println(arr.length);
		arr = StringUtils.split(a, "|");
		System.out.println(arr.length);
		
		String aaa = "高楼层(共11层) 2005年建塔楼 - 北景园2005";
		Pattern pattern = Pattern.compile("[0-9]{4}");
		Matcher matcher = pattern.matcher(aaa);
		if(matcher.find()) {
			System.out.println(matcher.group());
		}
		
	}

}
