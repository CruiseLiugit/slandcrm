package com.sland.model.pojo;

import java.util.HashSet;
import java.util.Set;



public class Magazine implements java.io.Serializable,MyEntity {

	// Fields

	private Integer id;
	private String month;
	private Integer periodicalnum;
	private String wholeperiodicanum;
	private String title;
	private String synopsis;
	private String frontcover;
	private String catalogcover;
	private String ppath;
	private String column1;  //小图路径
	private String column2;  //大图路径
	private String column3;  //二级分类名称
	private Integer column4;
	
	private Year year;
	
	private Set<Periodical> periodicallist = new HashSet();;

	// Constructors

	/** default constructor */
	public Magazine() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	public String getMonth() {
		return this.month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getPeriodicalnum() {
		return this.periodicalnum;
	}

	public void setPeriodicalnum(Integer periodicalnum) {
		this.periodicalnum = periodicalnum;
	}

	public String getWholeperiodicanum() {
		return this.wholeperiodicanum;
	}

	public void setWholeperiodicanum(String wholeperiodicanum) {
		this.wholeperiodicanum = wholeperiodicanum;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSynopsis() {
		return this.synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getFrontcover() {
		return this.frontcover;
	}

	public void setFrontcover(String frontcover) {
		this.frontcover = frontcover;
	}

	public String getCatalogcover() {
		return this.catalogcover;
	}

	public void setCatalogcover(String catalogcover) {
		this.catalogcover = catalogcover;
	}


	public String getPpath() {
		return this.ppath;
	}

	public void setPpath(String ppath) {
		this.ppath = ppath;
	}


	public String getColumn1() {
		return this.column1;
	}

	public void setColumn1(String column1) {
		this.column1 = column1;
	}

	public String getColumn2() {
		return this.column2;
	}

	public void setColumn2(String column2) {
		this.column2 = column2;
	}

	public String getColumn3() {
		return this.column3;
	}

	public void setColumn3(String column3) {
		this.column3 = column3;
	}

	public Integer getColumn4() {
		return this.column4;
	}

	public void setColumn4(Integer column4) {
		this.column4 = column4;
	}

	public Set getPeriodicallist() {
		return periodicallist;
	}

	public void setPeriodicallist(Set<Periodical> periodicallist) {
		this.periodicallist = periodicallist;
	}

		
	
	
	
}