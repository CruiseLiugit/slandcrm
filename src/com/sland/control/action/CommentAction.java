package com.sland.control.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import util.JavaTools;
import util.TimeTools;

import com.sland.model.dao.CommentDao;
import com.sland.model.dao.MagazineDao;
import com.sland.model.pojo.MyEntity;
import com.sland.model.pojo.Periodical;
import com.sland.model.pojo.Topic;

import com.sland.model.pojo.Comment;

/**
 * 评论管理
 * 
 * @author SDJG
 * 
 */
public class CommentAction extends BaseAction implements RequestAware {

	private String periodicalid;
	private String topicid;
	private String comment;

	private Map<String, Object> request;

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	// /////////////////////////////////////////////////////////////////
	// 评论模块，增加评论
	public String addComment() throws Exception {
		System.out.println("得到页面数据,periodicalid =" + periodicalid
				+ " , topicid=" + topicid + " , comment = " + comment);
		Comment comm = new Comment();
		int pid = new Integer(periodicalid.trim());
		Periodical per = new Periodical();
		per.setId(pid);
		comm.setPeriodical(per);

		Topic top = new Topic();
		top.setId(new Integer(topicid.trim()));
		comm.setTopic(top);

		comm.setComment(comment);
		comm.setStatu(1); // 状态为1 表示刚刚插入，未审核

		// 系统当前时间
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		String time = TimeTools.getString2Time(ts);
		comm.setTime(time);

		CommentDao dao = new CommentDao();
		dao.saveComment(comm);

		List list = dao.findAllUnCheckComments();

		request.put("uncheckcomments_list", list);

		return "add_ok";
	}

	// 评论模块，查询所有状态为没有评价过的评论
	public String findUnCheckComment() throws Exception {
		System.out.println("查询所有状态为 1 的评论");

		CommentDao dao = new CommentDao();
		List list = dao.findAllUnCheckComments();

		request.put("uncheckcomments_list", list);

		return "add_ok";
	}

	// /////////////////////////////////////////////////////////////////

	public String getPeriodicalid() {
		return periodicalid;
	}

	public void setPeriodicalid(String periodicalid) {
		this.periodicalid = periodicalid;
	}

	public String getTopicid() {
		return topicid;
	}

	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
