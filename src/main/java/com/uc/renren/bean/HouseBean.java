package com.uc.renren.bean;

public class HouseBean {

	//爬取序号
	private Integer seq;

	private String site;

	//楼盘名字 兴隆家园
	private String loupan;

	//几室几厅
	private String jushi;

	//面积
	private double area;

	//朝向
	private String direction;

	//装修
	private String decorate;

	private Boolean isDianti;

	//电梯、楼层、年代描述
	private String buildingDesc;

	//年代
	private Integer year;

	//总价
	private Integer price;

	//单价每平方
	private Integer unitPrice;

	//关注次数
	private Integer subCount;

	//访问次数
	private Integer visitCount;

	//房子发布网上日期 yyyy-MM-dd
	private String pubDate;

	//日期描述
	private String pubDateDesc;

	//链接
	private String href;

	//行政区域 高碑店
	private String xiaoqu;

	//爬取日期
	private String crawlingDate;

	//市区 朝阳区
	private String shiqu;

	//亮点描述 满屋唯一、小学之类
	private String tag;

	private String title;

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getLoupan() {
		return loupan;
	}

	public void setLoupan(String loupan) {
		this.loupan = loupan;
	}

	public String getJushi() {
		return jushi;
	}

	public void setJushi(String jushi) {
		this.jushi = jushi;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getDecorate() {
		return decorate;
	}

	public void setDecorate(String decorate) {
		this.decorate = decorate;
	}

	public String getBuildingDesc() {
		return buildingDesc;
	}

	public void setBuildingDesc(String buildingDesc) {
		this.buildingDesc = buildingDesc;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Integer getSubCount() {
		return subCount;
	}

	public void setSubCount(Integer subCount) {
		this.subCount = subCount;
	}

	public Integer getVisitCount() {
		return visitCount;
	}

	public void setVisitCount(Integer visitCount) {
		this.visitCount = visitCount;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getPubDateDesc() {
		return pubDateDesc;
	}

	public void setPubDateDesc(String pubDateDesc) {
		this.pubDateDesc = pubDateDesc;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getXiaoqu() {
		return xiaoqu;
	}

	public void setXiaoqu(String xiaoqu) {
		this.xiaoqu = xiaoqu;
	}

	public String getCrawlingDate() {
		return crawlingDate;
	}

	public void setCrawlingDate(String crawlingDate) {
		this.crawlingDate = crawlingDate;
	}

	public String getShiqu() {
		return shiqu;
	}

	public void setShiqu(String shiqu) {
		this.shiqu = shiqu;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Boolean getIsDianti() {
		return isDianti;
	}

	public void setIsDianti(Boolean isDianti) {
		this.isDianti = isDianti;
	}

	@Override
	public String toString() {
		return "HouseBean [seq=" + seq + ", site=" + site + ", loupan=" + loupan + ", jushi="
				+ jushi + ", area=" + area + ", direction=" + direction + ", decorate=" + decorate
				+ ", isDianti=" + isDianti + ", buildingDesc=" + buildingDesc + ", year=" + year
				+ ", price=" + price + ", unitPrice=" + unitPrice + ", subCount=" + subCount
				+ ", visitCount=" + visitCount + ", pubDate=" + pubDate + ", pubDateDesc="
				+ pubDateDesc + ", href=" + href + ", xiaoqu=" + xiaoqu + ", crawlingDate="
				+ crawlingDate + ", shiqu=" + shiqu + ", tag=" + tag + ", title=" + title + "]";
	}

}
