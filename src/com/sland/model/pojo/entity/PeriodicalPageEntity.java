package com.sland.model.pojo.entity;

/**
 * periodicals.jsp 页面显示所有期刊信息专用 bean
 * 
 * @author SDJG
 * 
 */
public class PeriodicalPageEntity {
	private int mid;
	private String yearmonth;
	private String month;  //专门作为 手风琴的 id
	private String title;
	private String thumimage;

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getYearmonth() {
		return yearmonth;
	}

	public void setYearmonth(String yearmonth) {
		this.yearmonth = yearmonth;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumimage() {
		return thumimage;
	}

	public void setThumimage(String thumimage) {
		this.thumimage = thumimage;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	
	
}
