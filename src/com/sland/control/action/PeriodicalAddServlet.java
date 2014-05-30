package com.sland.control.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.oreilly.servlet.MultipartRequest;
import com.sland.control.action.fileupload.MyFileRenamePolicy;

public class PeriodicalAddServlet extends HttpServlet {

	private String fileSavePath;

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		fileSavePath = this.getServletContext().getInitParameter("filepath");
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		//获得输入参数值
		String month = request.getParameter("month");
		String title = request.getParameter("title");
		String synopsis = request.getParameter("synopsis");
		
		System.out.println(".............month  :"+month);
		System.out.println(".............title  :"+title);
		System.out.println(".............synopsis  :"+synopsis);
		
		
		
		//System.out.println(".............  :"+);
		
		//重命名规则
		MyFileRenamePolicy rename = new MyFileRenamePolicy(month,1);

		// 文件大小限制 5M
		MultipartRequest mpr = new MultipartRequest(request, fileSavePath,
				5 * 1024 * 1024,rename);
		
		System.out.println("文件上传对象 mpr = "+mpr);
		Enumeration enums = mpr.getFileNames();
		System.out.println("文件上传结果  enums ="+enums);
		
		String upFilename = "";
		
		for (int i = 0; enums.hasMoreElements(); i++) {
			upFilename = mpr.getFilesystemName((String) enums.nextElement());
		}
		
		
		
		
	}

}
