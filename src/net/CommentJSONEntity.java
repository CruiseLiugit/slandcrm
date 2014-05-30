package net;

public class CommentJSONEntity {

	// Fields
	private Integer id;
	private String comment; // 内容
	private String time; // 时间
	private Integer statu; // 状态

	private String title; // 标题
	private String taobaourl; // 淘宝链接
	
	private String topicThumbname;       //所选图片名称
	private String coverpageThunmbname;  //封面缩略图
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Integer getStatu() {
		return statu;
	}
	public void setStatu(Integer statu) {
		this.statu = statu;
	}
	
	public String getTopicThumbname() {
		return topicThumbname;
	}
	public void setTopicThumbname(String topicThumbname) {
		this.topicThumbname = topicThumbname;
	}
	public String getCoverpageThunmbname() {
		return coverpageThunmbname;
	}
	public void setCoverpageThunmbname(String coverpageThunmbname) {
		this.coverpageThunmbname = coverpageThunmbname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTaobaourl() {
		return taobaourl;
	}
	public void setTaobaourl(String taobaourl) {
		this.taobaourl = taobaourl;
	}
	
	
	
	
	

}
