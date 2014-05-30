package com.sland.model.pojo;

public class Comment implements java.io.Serializable,MyEntity {

	// Fields
	private Integer id;
	private String comment; //内容
	private String time;    //时间
	private Integer statu;  //状态
	
	private String column1;  //标题
	private String column2;  //淘宝链接
	private String column3;
	private Integer column4;

	private Topic topic;
	private Periodical periodical;
	
	// Constructors

	/** default constructor */
	public Comment() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTime() {
		return this.time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getStatu() {
		return this.statu;
	}

	public void setStatu(Integer statu) {
		this.statu = statu;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
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