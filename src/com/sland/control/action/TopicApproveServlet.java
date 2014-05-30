package com.sland.control.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sland.model.dao.PeriodicalDao;
import com.sland.model.pojo.Topic;

public class TopicApproveServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//   http://localhost:8080/nailall/approve?topicThunbname=1385835221451.jpg
		response.setContentType("text/html; charset=UTF-8");
		String topicThunbname = request.getParameter("topicThunbname");
		System.out.println("主题模块，图片预览 点赞 的图片 id = " + topicThunbname);
		
		PeriodicalDao pdao = new PeriodicalDao();
		//按照id 给 图片 topic 表中 column3 列 +1
		Topic topic = pdao.getTopicByThunbname(topicThunbname);
		String result = "";
		if (topic == null) {
			result = "{\"message\":\"操作失败\",\"result\":\"0\",\"number\":\"0\"}";
		}else
		{
			int approveNumber = topic.getColumn4()+1;
			result ="{\"message\":\"操作成功\",\"result\":\"1\",\"number\":\""+approveNumber+"\"}";
		}
		
		PrintWriter out = response.getWriter();
		out.println(result);
	}
}
