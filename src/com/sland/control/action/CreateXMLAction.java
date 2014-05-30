package com.sland.control.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.sland.model.dao.CreateXML;
import com.sland.model.dao.MagazineDao;
import com.sland.model.pojo.Year;

public class CreateXMLAction extends BaseAction implements RequestAware,
		SessionAware {
	private Map<String, Object> request;
	private Map<String, Object> session;

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	// 跳转到杂志页面
	public String magazineListXML() throws Exception {
		// 取出所有的年份列表
		CreateXML cxml = new CreateXML();
		cxml.createListXML();

		//request.put("yearlist", list);

		return Constants.MAGAZINE;
	}

}
