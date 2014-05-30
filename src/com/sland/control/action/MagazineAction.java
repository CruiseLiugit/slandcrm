package com.sland.control.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.sland.model.dao.MagazineDao;
import com.sland.model.pojo.MyEntity;
import com.sland.model.pojo.Year;

import util.JavaTools;

public class MagazineAction extends BaseAction implements RequestAware {

	private Year year;
	private int yid;

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	


	public int getYid() {
		return yid;
	}

	public void setYid(int yid) {
		this.yid = yid;
	}




	private Map<String, Object> request;

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	// /////////////////////////////////////////////////////////////////
	//杂志模块，增加年份
	public String addYear() throws Exception {
		String str = year.getYearname();
		System.out.println("得到页面数据,year title=" + year.getTitle());

		// 正则表达式验证
		boolean flag = JavaTools.isYear(str);
		System.out.println("flag = "+flag);
		if (!flag) {
			request.put("addYearError", "输入分类编号格式错误，请重新输入!");
		} else {
			MagazineDao dao = new MagazineDao();
			List<MyEntity> list = dao.findByYear(str);
			//System.out.println("list = "+list);
			if (list != null && list.size() >= 1) {
				request.put("addYearError", "输入分类编号已经存在，请重新输入!");
			}else
			{
				year.setStatu(1);
				dao.add(year);
			}
		}
		
		return "addYear_ok";
	}

	public String toEditYear() throws Exception {
		//System.out.println("...............修改新年份");
		//System.out.println("得到页面数据,year_id =" + yid);

		//取出所有的年份列表
		MagazineDao dao = new MagazineDao();
		List<Year> list = dao.findAllYears();
		Year year = dao.findByYearById(yid);
		System.out.println("更新 year ="+year);
		
		request.put("yearlist", list);
		request.put("edityear", year);
		
		return "editYear";
	}
	
	public String editYear() throws Exception {
		String str = year.getYearname();
		//System.out.println("得到页面数据,year_name =" + str+"  id ="+year.getId()+" statu ="+year.getStatu());

		/* 新项目输入中文，这里不需要验证 */
		// 正则表达式验证
		boolean flag = JavaTools.isYear(str);
		//System.out.println("flag = "+flag);
		if (!flag) {
			request.put("editYearError", "输入分类编号格式错误，请重新输入!");
		} else {
			MagazineDao dao = new MagazineDao();
			List<MyEntity> list = dao.findByYear(str);
			//System.out.println("list = "+list);
			if (list != null && list.size() >= 1) {
				request.put("editYearError", "您输入分类编号已经存在，请重新输入!");
			}else
			{
				dao.updateYear(year);
			}
		}

		return "addYear_ok";
	}
	
	public String deleteYear() throws Exception {
		//System.out.println("...............删除新年份");
		//System.out.println("得到页面数据,year_id =" + yid);
		MagazineDao dao = new MagazineDao();
		Year delYear = dao.findByYearById(yid);
		delYear.setStatu(0);
	
		dao.updateYear(delYear);
		
		return "addYear_ok";
	}
	
	
	public String updateYear() throws Exception {
		//System.out.println("...............还原新年份");
		//System.out.println("得到页面数据,year_id =" + yid);
		MagazineDao dao = new MagazineDao();
		Year delYear = dao.findByYearById(yid);
		delYear.setStatu(1);
	
		dao.updateYear(delYear);
		
		return "addYear_ok";
	}
	
	
	
	
	
}
