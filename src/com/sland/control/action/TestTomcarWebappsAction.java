package com.sland.control.action;

import java.io.File;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import util.JavaTools;

public class TestTomcarWebappsAction extends BaseAction implements RequestAware {

	String separator = System.getProperty("file.separator"); // 文件分割符号

	private Map<String, Object> request;

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	// /////////////////////////////////////////////////////////////////
	// 期刊模块，仅仅跳转到增加期刊页面
	// 访问链接 http://localhost:8080/naill/testtomcat!totest.action
	public String totest() throws Exception {
		// //////////////////////////////////////////////////////////
		String realP = ServletActionContext.getServletContext()
				.getRealPath("/");

		String realPath = ServletActionContext.getServletContext().getRealPath(
				"upload")
				+ separator;

		System.out.println("===============realPath =" + realPath);
		
		 //获取是项目的绝对路径
        System.out.println("方法二:"+System.getProperty("user.dir"));
        System.out.println("方法二:"+new File("").getAbsolutePath());
        
        
        String root = ServletActionContext.getServletContext().getRealPath("/");
        String wuli = root.substring(0,root.lastIndexOf("/"));
        System.out.println("方法三:"+wuli);
        
        System.out.println("方法四:"+Constants.getResourcesPath());
		
		return "test_ok";
	}
	
	
}
