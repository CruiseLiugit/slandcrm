package com.sland.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.sland.model.pojo.Magazine;
import com.sland.model.pojo.Year;
import com.sland.model.pojo.MyEntity;
import com.sland.model.pojo.entity.PeriodicalPageEntity;

import util.BaseDao;
import util.DBConnection;

public class MagazineDao extends BaseDao {
	// //////////////////////////////////////
	// 杂志模块的数据库实现
	// //////////////////////////////////////
	// 插入一个年份
	public void saveYear(Year transientInstance) {
		super.add(transientInstance);
	}

	// 更新一个年份，实现修改和删除
	public void updateYear(Year transientInstance) {
		super.update(transientInstance);
	}

	// 取出所有的年份列表
	public List findAllYears() {
		List<MyEntity> list = null;
		try {
			String hql = "from Year";
			// list = super.executeListHql(hql);
			return super.list("Year");
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List<MyEntity> findAllStatuYears() {
		List<MyEntity> list = null;
		try {
			String hql = "from Year where statu=1 order by id desc";
			//System.out.println("***********fiadAllStatuYears Sql = "+hql);
			list = super.executeListHql(hql);
			
			
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				MyEntity myEntity = (MyEntity) iterator.next();
				//System.out.println(myEntity.getClass());
				
				boolean flag = myEntity instanceof Year;
				//System.out.println("flag ="+flag);
				if (flag) {
					Year year = (Year)myEntity;
					System.out.println("year ="+year+" , id ="+year.getId()+" , status ="+year.getStatu()+" , name ="+year.getYearname());
					System.out.println("title ="+year.getTitle());
				}
			}
			
			return list;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	// 增加时，判断年份是否已经存在
	public List<MyEntity> findByYear(String year) {
		List<MyEntity> list = null;
		String hql = "from Year where yearname=" + year;
		list = super.executeListHql(hql);
		return list;
	}

	// 修改年份时，查找到所选择的 id
	public Year findByYearById(int yearid) {
		Year y = null;
		y = (Year) super.getById(new Year(), yearid);
		return y;
	}

	public Year findYear(String year) {
		String hql = "from Year where yearname=" + year;
		List<MyEntity> list = super.executeListHql(hql);
		if (list == null) {
			return null;
		}
		return (Year) list.get(0);
	}

	// /////////////为生成 list.xml 做的所有查询////////////////
	//先查询得到所有的年，根据年份创建一个 HashMap
	//然后按照年份查询，得到每个年份中所有的月份，创建多个 List
	public Map<String,List> getListXml()
	{
		Map<String,List> map =null;
		
		List<MyEntity> listYear = this.findAllStatuYears();
		System.out.println("********* getListXml ***********");
		if (listYear == null) {
			return null;
		}
		
		map = new HashMap<String,List>();
		//System.out.println("****** new map  yearsSize "+listYear.size()+"*******");
		for (MyEntity year : listYear) {
			//System.out.println("^^^^^^^^循环^^^^^^^");
			if (year instanceof Year) {
				Year temp = (Year)year;
				//System.out.println(" key ="+temp.getYearname());
				List<MyEntity> tempList = this.getAllMagazineForListXML1(temp.getYearname());
				//System.out.println("value = "+tempList);
				map.put(temp.getYearname(),tempList);
			}
		}
		
		return map;
	}
	
	public List<MyEntity> getAllMagazineForListXML1(String year) {
		List<MyEntity> list = null;
		try {
			String hql = "from Magazine where year.yearname="+year;
			list = super.executeListHql(hql);
		} catch (RuntimeException re) {
			throw re;
		}

		// *******这里测试时注意，期刊总数
		return list;
	}
	
	// /////////////为生成 list.xml 做的所有查询////////////////
	
	

	// 插入一个月期刊，这里插入两个表 magazine periodical
	// PeriodicalAction.java 类中，插入 一条 magazine ，代表一个月的期刊数目
	// magazine 表中的数据用于生成 List.xml 文件
	// 1 得到目前总的期刊数目
	public int getWholePeriodical() {
		List<MyEntity> list = null;
		try {
			String hql = "from Magazine";
			list = super.executeListHql(hql);
		} catch (RuntimeException re) {
			throw re;
		}

		if (list == null) {
			return 0;
		}

		// *******这里测试时注意，期刊总数
		return list.size() == 0 ? 1 : (list.size() + 1);
	}

	// 2 得到指定月份的期刊数目
	public int getMonthPeriodical(String month) {
		List<MyEntity> list = null;
		try {
			String hql = "from Magazine where month=" + month;
			list = super.executeListHql(hql);
		} catch (RuntimeException re) {
			throw re;
		}

		if (list == null) {
			return 0;
		}
		return list.size();
	}

	// 插入一个期刊
	public void saveMonth(Magazine transientInstance) {
		super.add(transientInstance);
	}

	// 点击 菜单-期刊 链接，查询所有的期刊
	public List findAllPeriodicals() {
		List<PeriodicalPageEntity> list = new ArrayList<PeriodicalPageEntity>();
		DBConnection dbc = new DBConnection();
		Connection conn = dbc.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "select m.id i,m.month m,m.title t,m.column2 c from Magazine m  group by m.month order by m.id desc";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				PeriodicalPageEntity entity = new PeriodicalPageEntity();
				entity.setMid(rs.getInt("i"));
				String yearMonth = rs.getString("m");
				entity.setYearmonth(yearMonth);
				String m = yearMonth.substring(5, 7);
				entity.setMonth(m);
				entity.setTitle(rs.getString("t"));
				entity.setThumimage(rs.getString("c"));
				list.add(entity);
			}

		} catch (RuntimeException re) {
			throw re;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbc.colseConnection(conn);
		}

		return list;
	}

	// 查询到所有的期刊后，点击任意一个期刊，查看里面的期刊明细
	public List fincAllDetailPeriodicals(String yearmonth) {
		List<PeriodicalPageEntity> list = new ArrayList<PeriodicalPageEntity>();
		DBConnection dbc = new DBConnection();
		Connection conn = dbc.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "select m.id i,m.month m,m.title t,m.column2 c from Magazine m  where m.month='"
					+ yearmonth + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				PeriodicalPageEntity entity = new PeriodicalPageEntity();
				entity.setMid(rs.getInt("i"));
				String yearMonth = rs.getString("m");
				entity.setYearmonth(yearMonth);
				String m = yearMonth.substring(5, 7);
				entity.setMonth(m);
				entity.setTitle(rs.getString("t"));
				entity.setThumimage(rs.getString("c"));
				list.add(entity);
			}

		} catch (RuntimeException re) {
			throw re;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbc.colseConnection(conn);
		}

		return list;
	}

}
