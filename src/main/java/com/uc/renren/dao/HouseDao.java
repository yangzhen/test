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
			+ "`title`,`up`) "
		+ "VALUES("
		+ "	#{seq}, #{site}, #{loupan}, "
		+ "#{jushi}, #{area}, #{direction}, "
		+ "#{decorate}, #{isDianti}, #{buildingDesc},"
		+ " #{year}, #{price}, #{unitPrice}, "
		+ "#{subCount}, #{visitCount}, #{pubDate}, "
		+ "#{pubDateDesc}, #{href}, #{xiaoqu}, "
		+ "#{crawlingDate}, #{shiqu}, #{tag}, "
		+ "#{title},#{up})")
	public int insert(HouseBean bean);
	
	@Select("select * from hz_fz where href=#{href} order by id desc limit 1")
	public HouseBean find(@Param("href")String href);
	
	@Delete("delete from hz_fz where crawlingDate = #{crawlingDate} and site = #{site}")
	public int delete(@Param("crawlingDate")String date,@Param("site")String site);

	@Delete("delete from hz_fz_dt_stat where crawlingDate = #{crawlingDate} and site = #{site} and type='fz_all' ")
	public int deleteStat(@Param("crawlingDate")String date,@Param("site")String site);

	@Insert("insert into hz_fz_dt_stat(type,crawlingDate,site,fz_count) values ('fz_all',#{crawlingDate},#{site},#{total})")
	public int insertStat(@Param("crawlingDate")String date,@Param("site")String site,@Param("total")Integer total);
}
