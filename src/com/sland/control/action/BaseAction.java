package com.sland.control.action;

import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport implements ApplicationAware,
		SessionAware {

	private Map<String, Object> application;
	private Map<String, Object> session;

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map getSession() {
		return session;
	}

	public void setApplication(Map<String, Object> value) {
		application = value;
	}

	public Map getApplication() {
		return application;
	}

	// ---- Task property (utilized by UI) ----
	/**
	 * <p>
	 * Field to store workflow task.
	 * </p>
	 * <p/>
	 * <p>
	 * The Task is used to track the state of the CRUD workflows. It can be set
	 * to Constant.CREATE, Constant.EDIT, or Constant.DELETE as needed.
	 * </p>
	 */
	private String task = null;

	/**
	 * <p>
	 * Provide worklow task.
	 * </p>
	 * 
	 * @return Returns the task.
	 */
	public String getTask() {
		return task;
	}

	/**
	 * <p>
	 * Store new workflow task.
	 * </p>
	 * 
	 * @param value
	 *            The task to set.
	 */
	public void setTask(String value) {
		task = value;
	}

	// ---- Token property (utilized by UI) ----

	/**
	 * <p>
	 * Field to store double-submit guard.
	 * </p>
	 */
	private String token = null;

	/**
	 * <p>
	 * Provide Token.
	 * </p>
	 * 
	 * @return Returns the token.
	 */
	public String getToken() {
		return token;
	}

	/**
	 * <p>
	 * Store new Token.
	 * </p>
	 * 
	 * @param value
	 *            The token to set.
	 */
	public void setToken(String value) {
		token = value;
	}

}
