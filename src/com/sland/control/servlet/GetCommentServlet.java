package com.sland.control.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.CommentJSONEntity;
import net.HttpService;
import com.google.gson.Gson;
import com.sland.model.dao.CommentDao;
import com.sland.model.dao.PeriodicalDao;
import com.sland.model.pojo.Comment;
import com.sland.model.pojo.Periodical;
import com.sland.model.pojo.Topic;

public class GetCommentServlet extends HttpServlet {

	String separator = System.getProperty("file.separator"); // 文件分割符号

	// 页面请求参数
	private String thunbname; // 请求图片缩略图名称
	private String perthumb; // 所属期刊封面缩略图的名称，以查询当期 id

	// 生成网络服务对象
	HttpService httpService = new HttpService();
	// 容器对象，获得真实路径
	ServletContext servletContext = null;
	// gson对象
	Gson gson = new Gson();

	/**
	 * Constructor of the object.
	 */
	public GetCommentServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 把生成的路径，输出为 json
		PrintWriter out = response.getWriter();

		// 接收参数，topicThunbname
		thunbname = request.getParameter("thunbname");
		perthumb = request.getParameter("perthumb");

		//System.out.println("-------- thunbname ="+thunbname);
		//System.out.println("-------- perthumb ="+perthumb);
		
		if (thunbname == null || "".equals(thunbname.trim())
				|| perthumb == null || "".equals(perthumb)) {
			out.print("{}");
		} else {

			// ---------------------------------------------
			// 根据 期刊封面图名称，找到 期刊 perid
			PeriodicalDao pdao = new PeriodicalDao();
			// 得到期刊封面的缩略图名称
			// 根据封面的缩略图名称，查询得到期刊 id
			Periodical peri = pdao.findPeriodicalByCoverThunbname(perthumb);
			int pid = peri.getId();
			
			//System.out.println("-------- periId ="+pid);
			
			// 根据 所选图名称，查询得到 topicid
			CommentDao cdao = new CommentDao();
			Topic topic = cdao.findTopicByThumbname(thunbname.trim());
			int tid = topic.getId();
			
			//System.out.println("-------- tid ="+tid);

			// 根据 tid 、pid 到 comment 表中查询，得到一个 comment 对象
			Comment comm = cdao.findCommentByTidAndPid(peri, topic);
			
			//-----------------------------------------------------
			//返回的 JSON 对象
			CommentJSONEntity jsonentity = new CommentJSONEntity();
			jsonentity.setId(comm.getId());
			jsonentity.setTitle(comm.getColumn1());
			jsonentity.setTaobaourl(comm.getColumn2());
			jsonentity.setTime(comm.getTime());
			jsonentity.setComment(comm.getComment());    //产品描述
			jsonentity.setTopicThumbname(thunbname);    //所选图片名称
			jsonentity.setCoverpageThunmbname(perthumb); //封面缩略图

			Gson gson = new Gson();
			String jsonstr = gson.toJson(jsonentity);

			// //////////////////////////////
			out.println(jsonstr);
		}

		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
