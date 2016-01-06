package com.uc.util;

import org.junit.Test;

public class LogTest extends BaseTestAbstact{
	
	@Test
	public void test() {
		logger.info("aaaaaaaaaa");
		logger.info("aaa,{},bb{},yyy,aa",10,11,22);
	}

}
