package com.uc.renren.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
	
	@Select("select id from hz_fz where href=#{href} and price=#{price}")
	public int find(@Param("href")String href,@Param("price")int price);
	
	@Delete("delete from hz_fz where crawlingDate = #{crawlingDate} and site = #{site}")
	public int delete(@Param("crawlingDate")String date,@Param("site")String site);
}
