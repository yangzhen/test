package com.uc.renren.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import com.uc.renren.bean.HouseBean;

@MyBatisRepository
public interface HouseDao {

	@Insert("INSERT INTO `hz_fz` (`seq`, `site`, `loupan`, "
			+ "`jushi`, `area`, `direction`, "
			+ "`decorate`, `isDianti`, `buildingDesc`, "
			+ "`year`, `price`, `unitPrice`, "
			+ "`subCount`, `visitCount`, `pubDate`, "
			+ "`pubDateDesc`, `href`, `xiaoqu`, "
			+ "`crawlingDate`, `shiqu`, `tag`, "
			+ "`title`) "
		+ "VALUES("
		+ "	#{seq}, #{site}, #{loupan}, "
		+ "#{jushi}, #{area}, #{direction}, "
		+ "#{decorate}, #{isDianti}, #{buildingDesc},"
		+ " #{year}, #{price}, #{unitPrice}, "
		+ "#{subCount}, #{visitCount}, #{pubDate}, "
		+ "#{pubDateDesc}, #{href}, #{xiaoqu}, "
		+ "#{crawlingDate}, #{shiqu}, #{tag}, "
		+ "#{title})")
	public int insert(HouseBean bean);
	
	@Delete("delete from hz_fz where crawlingDate = #{crawlingDate}")
	public int delete(String date);
}
