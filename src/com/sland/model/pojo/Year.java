package com.sland.model.pojo;

/**
 * Year entity. @author MyEclipse Persistence Tools
 */

public class Year implements java.io.Serializable,MyEntity {

	// Fields
	private Integer id;
	private String yearname;
	private String title;  //新增标题字段
	private Integer statu;

	// Constructors

	/** default constructor */
	public Year() {
	}


	// Property accessors
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getYearname() {
		return this.yearname;
	}

	public void setYearname(String yearname) {
		this.yearname = yearname;
	}

	public Integer getStatu() {
		return this.statu;
	}

	public void setStatu(Integer statu) {
		this.statu = statu;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	
	

}