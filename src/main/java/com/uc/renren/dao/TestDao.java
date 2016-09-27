package com.uc.renren.dao;

import org.apache.ibatis.annotations.Param;

import com.uc.renren.bean.TestEntry;

/**
 * TestDao
 * 
 * @author Wang Zhao
 * @date 2015年11月23日 下午4:54:18
 */
@MyBatisRepository
public interface TestDao {

	/**
	 * 获取test信息
	 * 
	 * @param id
	 * @return
	 */
	public TestEntry getTestInfo(@Param("id") int id);

	public int updateTextById(@Param("id") int id, @Param("text")String text);
	
	public int updateTextUniqueByName(@Param("name")String name, @Param("text")String text);
	
}
