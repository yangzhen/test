package com.uc.dao;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.uc.renren.bean.TestEntry;
import com.uc.renren.dao.TestDao;
import com.uc.util.BaseTestAbstact;

public class TestDaoTest extends BaseTestAbstact {

	@Autowired
	private TestDao dao;
	
	@Test
	public void testGetTestInfo() {
		int id = 1;
		TestEntry testEntry = dao.getTestInfo(id);
		assertNotNull(testEntry);
		logger.info("entry:" + JSON.toJSONString(testEntry));
		
		int result = dao.updateTextById(testEntry.getId(), testEntry.getText());
		System.out.println("=====" + result);
		
		TestEntry testEntry2 = dao.getTestInfo(id);
		assertNotNull(testEntry2);
		logger.info("entry:" + JSON.toJSONString(testEntry2));
		
		int resultUnique = dao.updateTextUniqueByName(testEntry.getName(), testEntry.getText());
		System.out.println("=========" + resultUnique);
		
		TestEntry testEntry3 = dao.getTestInfo(id);
		assertNotNull(testEntry3);
		logger.info("entry:" + JSON.toJSONString(testEntry3));
	}
	

}
