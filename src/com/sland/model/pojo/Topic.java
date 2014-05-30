package com.sland.model.pojo;


public class Topic implements java.io.Serializable,MyEntity {

	// Fields
	private Integer id;
	private String topicName;
	private String topicThumbname;
	private String topicPath;
	private String intro;
	private String column1; //大图路径
	private String column2; //缩略图路径
	private String column3; 
	private Integer column4; //赞的数量
	
	//期刊与内页图片
	private Periodical periodical;

	// Constructors

	/** default constructor */
	public Topic() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTopicName() {
		return this.topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public String getTopicThumbname() {
		return this.topicThumbname;
	}

	public void setTopicThumbname(String topicThumbname) {
		this.topicThumbname = topicThumbname;
	}

	public String getTopicPath() {
		return this.topicPath;
	}

	public void setTopicPath(String topicPath) {
		this.topicPath = topicPath;
	}

	public String getIntro() {
		return this.intro;
	}

	public void setIntro(String intro) {
		this.intro = intro;
	}

	public Periodical getPeriodical() {
		return periodical;
	}

	public void setPeriodical(Periodical periodical) {
		this.periodical = periodical;
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

}