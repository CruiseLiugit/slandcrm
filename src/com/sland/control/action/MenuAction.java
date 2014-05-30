package com.sland.control.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.sland.model.dao.MagazineDao;
import com.sland.model.pojo.MyEntity;
import com.sland.model.pojo.Userinfo;
import com.sland.model.pojo.Year;
import com.sland.model.pojo.entity.PeriodicalPageEntity;

//菜单项目的跳转控制类
public class MenuAction extends BaseAction implements RequestAware {

	private Map<String, Object> request;

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	// 跳转到首页
	public String homepage() throws Exception {
		return Constants.HOMEPAGE;
	}

	// 跳转到杂志页面
	public String magazinepage() throws Exception {
		// 取出所有的年份列表
		MagazineDao dao = new MagazineDao();
		List<Year> list = dao.findAllYears();

		request.put("yearlist", list);

		return Constants.MAGAZINE;
	}

	// 跳转到期刊页面
	public String periodicalpage() throws Exception {
		// 取出所有状态为 1 的年份列表
		MagazineDao dao = new MagazineDao();
		List<MyEntity> list = dao.findAllStatuYears();

		// 取出所有 magazine 表中的数据
		request.put("yearlist", list);
		request.put("yearSize", list.size());

		// 得到所有页面需要 的杂志对象
		List<PeriodicalPageEntity> list2 = dao.findAllPeriodicals();
		request.put("periodicallist", list2);

		return Constants.PERIODICAL;
	}

	// 跳转到主题页面
	public String topicpage() throws Exception {
		return Constants.TOPICPAGE;
	}

	
	//跳转到注册新用户页面
	public String register() throws Exception {
		return Constants.REGISTER_USER;
	}

	//跳转到修改密码页面
	public String modifyPwd() throws Exception {
		return Constants.MODIFY_PASSWORD;
	}
	
}
