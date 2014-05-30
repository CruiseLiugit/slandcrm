package com.sland.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

import com.sland.model.pojo.Magazine;
import com.sland.model.pojo.MyEntity;
import com.sland.model.pojo.Periodical;
import com.sland.model.pojo.Topic;
import com.sland.model.pojo.Year;
import com.sland.model.pojo.entity.PeriodicalPageEntity;

import util.BaseDao;
import util.DBConnection;
import util.HibernateSessionFactory;
import util.JavaTools;

public class PeriodicalDao extends BaseDao {

	// 插入一个 主题
	public void saveTopic(Topic transientInstance) {
		super.add(transientInstance);
	}

	// 根据封面缩略图的名称，查询一个期刊的 id
	public Periodical findPeriodicalByCoverThunbname(String coverthunbname) {
		Periodical entity = null;
		try {
			String hql = "from Periodical where coverpageThunmbname='"
					+ coverthunbname+"'";
			//System.out.println("hql = "+hql);
			
			entity = (Periodical) super.executeUniqueHql(hql);
			return entity;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	// 根据 Periodical id 查找 periodical 对象
	public Periodical findPeriodicalByPid(int id) {
		Periodical peri = null;
		try {
			peri = (Periodical) super.getById(new Periodical(), id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return peri;
	}

	// 根据id 查找所有的 主题图片
	public List findAllTopicByPid(int id) {
		List<MyEntity> list = null;
		try {
			String hql = "from Topic where periodical.id=" + id;
			list = super.executeListHql(hql);
			return list;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	// 赞
	public Topic getTopicByThunbname(String thumbname) {
		Topic entity = null;
		DBConnection dbc = new DBConnection();
		Connection conn = dbc.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 两个表联合查询
			String sql1 = "select * from topic where topicThumbname ='"
					+ thumbname.trim() + "'";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			while (rs.next()) {
				entity = new Topic();
				entity.setId(rs.getInt("id"));
				entity.setTopicName(rs.getString("topicName"));
				// entity.setIntro(rs.getString("intro"));
				entity.setTopicThumbname(rs.getString("topicThumbname"));
				entity.setTopicPath(rs.getString("topicPath"));
				// 期刊与内页图片
				Periodical periodical = new Periodical();
				periodical.setId(rs.getInt("periodicalID"));
				entity.setPeriodical(periodical);
				System.out.println("aaaaaaa");

				entity.setColumn1(rs.getString("column1")); // 大图地址
				entity.setColumn2(rs.getString("column2")); // 缩略图地址
				// entity.setColumn3(rs.getString("column3")); // null
				entity.setColumn4(new Integer(rs.getString("column4"))); // 赞
			}

			if (entity != null) {
				this.approveTopic(entity);
			}
			
			
		} catch (RuntimeException re) {
			System.out.println("error 1");

			throw re;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("error 2");

			e.printStackTrace();
		} finally {
			dbc.colseConnection(conn);
		}
		

		return entity;
	}

	public void approveTopic(Topic topic) {
		// 先查找到 指定 id 的 topic ，然后把 column4 属性 +1，再存入数据库
		try {
			// System.out.println("approveTopic ="+topic);
			// System.out.println("tipic column 4 = "+topic.getColumn4());

			int approve = topic.getColumn4().intValue();

			topic.setColumn4(new Integer(approve + 1));
			super.update(topic);

		} catch (RuntimeException re) {
			System.out.println("error 1");
			throw re;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error 2");
		}
	}

	// 取出所有的 期刊表数据
	public List<Periodical> findAllPeriodicals(String month) {
		List<Periodical> list = new ArrayList<Periodical>();
		DBConnection dbc = new DBConnection();
		Connection conn = dbc.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// 两个表联合查询
			String sql1 = "select * from periodical where magazineID in (select id from magazine where month='"
					+ month + "')";

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			while (rs.next()) {
				Periodical entity = new Periodical();
				entity.setId(rs.getInt("id"));
				entity.setCoverpageName(rs.getString("coverpageName"));
				entity.setCoverpageThunmbname(rs
						.getString("coverpageThunmbname"));

				// entity.setCoverPath(rs.getString("coverPath"));
				// 11-24 晚修改，<CoverPage
				// CoverPath="2013/201302/201302_1/frontcover/sfrontcover_1385223250.jpg"
				// 改为 <CoverPage
				// CoverPath="2013/201302/201302_1/frontcover/sfrontcover_1385223250.html"
				String coverPath = rs.getString("coverPath");
				entity.setCoverPath(JavaTools.getExtentionPrefix(coverPath)
						+ ".html");
				// magazineID ????
				entity.setColumn1(rs.getString("column1")); // 当月数
				entity.setColumn2(rs.getString("column2")); // 总期刊数

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

	// /////////////为生成 month.xml 做的所有查询////////////////
	// 先按照月份，查询 periodical 表
	// 根据查询得到 periodical_id 查询所有的 topic 表生成 xml
	public Map findAllMonthPerodical(String month) {
		Map map = new HashMap();
		List<Periodical> list = this.findAllPeriodicals(month);

		if (list == null || list.size() == 0) {
			return null;
		}
		// if (list.size()!=1) {
		for (Periodical periodical : list) {
			int pid = periodical.getId();
			List topicList = this.findAllTopicByPid(pid);

			map.put(month, periodical);
			map.put("topiclist", topicList);
		}
		/*
		 * }else { Periodical periodical = list.get(0); int pid =
		 * periodical.getId(); List topicList = this.findAllTopicByPid(pid);
		 * 
		 * map.put(month, periodical); map.put("topiclist", topicList); }
		 */
		return map;
	}

}
