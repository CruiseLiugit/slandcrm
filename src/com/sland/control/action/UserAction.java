package com.sland.control.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.sland.model.dao.UserDao;
import com.sland.model.pojo.MyEntity;
import com.sland.model.pojo.Userinfo;

public class UserAction extends BaseAction implements RequestAware {
	// 得到页面输入的用户名，密码属性
	private Userinfo info;
	private final UserDao dao = new UserDao();
	private Map<String, Object> request;

	public Userinfo getInfo() {
		return info;
	}

	public void setInfo(Userinfo info) {
		this.info = info;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	// 处理登录操作
	public String login() throws Exception {
		Userinfo user = dao.login(info.getLoginname(), info.getLoginpwd());

		if (user == null) {
			// this.addActionError("用户名或密码错误，请重新输入!");
			// this.addFieldError("loginerror", "用户名或密码错误，请重新输入!");
			request.put("loginerror", "用户名或密码错误，请重新输入!");
			return Constants.PASSWORD_MISMATCH_FIELD;
		}

		super.getSession().put("loguser", user);
		super.getSession().put("username", user.getRealname());

		// 得到工作台上的数据信息
		List allMagazines = dao.findAllMagziones();
		List allTopics = dao.findAllTopics();
		//List allComments = dao.findAllComment();
		if(allMagazines==null || allTopics==null)
		{
			super.getSession().put("magazinesnum", 0);
			super.getSession().put("topicsnum", 0);
			super.getSession().put("commentsnum", 0);
		}
		super.getSession().put("magazinesnum", allMagazines.size());
		super.getSession().put("topicsnum", allTopics.size());
		//super.getSession().put("commentsnum", allComments.size());

		return Constants.LOGIN_OK;
	}

	// 处理退出操作
	public String logout() throws Exception {
		super.getSession().remove("loguser");
		super.getSession().remove("username");
		super.getSession().remove("orderlast");
		super.getSession().remove("customnumbers");
		return "logout_ok";
	}

	// 注册新用户
	public String signup() throws Exception {
		info.setTypeid(1);
		System.out.println("realname =" + info.getRealname()
				+ "  , loginname =" + info.getLoginname() + " , loginpwd = "
				+ info.getLoginpwd());

		dao.add(info);
		
		request.put("signupMsg", "新用户 " + info.getRealname() + "注册成功输入!");

		return "singup_ok";
	}

	// 修改密码
	public String resetpassword() throws Exception {
		Userinfo loginuser = (Userinfo)super.getSession().get("loguser");
		loginuser.setLoginpwd(info.getLoginpwd());
		
		dao.update(loginuser);
		
		request.put("resetMsg", "用户 " + loginuser.getRealname() + "密码修改成功，下次退出使用新密码登陆!");

		return "singup_ok";
	}

}
