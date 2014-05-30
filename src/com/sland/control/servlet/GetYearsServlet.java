package com.sland.control.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.CommentJSONEntity;
import net.HttpService;

import com.google.gson.Gson;
import com.sland.model.dao.CommentDao;
import com.sland.model.dao.MagazineDao;
import com.sland.model.dao.PeriodicalDao;
import com.sland.model.pojo.Comment;
import com.sland.model.pojo.MyEntity;
import com.sland.model.pojo.Periodical;
import com.sland.model.pojo.Topic;

public class GetYearsServlet extends HttpServlet {

	String separator = System.getProperty("file.separator"); // 文件分割符号

	// 生成网络服务对象
	HttpService httpService = new HttpService();
	// 容器对象，获得真实路径
	ServletContext servletContext = null;
	// gson对象
	Gson gson = new Gson();

	/**
	 * Constructor of the object.
	 */
	public GetYearsServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 把生成的路径，输出为 json
		PrintWriter out = response.getWriter();

		// ---------------------------------------------
		//查询 year 表中所有年份字段，得到集合对象
		MagazineDao mdao = new MagazineDao();
		List yearlist = mdao.findAllStatuYears();
		
		// -----------------------------------------------------
		// 返回的 JSON 对象，包含所有年份字段的 数组对象
		Gson gson = new Gson();
		String jsonstr = gson.toJson(yearlist);

		// //////////////////////////////
		out.println(jsonstr);

		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
