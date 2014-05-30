package com.sland.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.sland.model.pojo.MyEntity;
import com.sland.model.pojo.Comment;
import com.sland.model.pojo.Periodical;
import com.sland.model.pojo.Topic;

import util.BaseDao;
import util.DBConnection;
import util.JavaTools;

/**
 * 评论管理的 Dao
 * 
 * @author SDJG
 * 
 */
public class CommentDao extends BaseDao {

	// 插入一个评论
	public void saveComment(Comment transientInstance) {
		super.add(transientInstance);
	}

	// 修改一个评论
	public void updateComment(Comment transientInstance) {
		super.update(transientInstance);
	}

	// 201404 第二版新增方法，用于插入一个 评论
	// 根据 topicThumbname 查询一个 Topic 对象
	public Topic findTopicByThumbname(String thumbname) {
		Topic entity = null;
		try {
			String hql = "from Topic where topicThumbname='" + thumbname + "'";
			entity = (Topic) super.executeUniqueHql(hql);
			return entity;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	// 20140507 第二版新增方法，用于修改一个 评论时
	// 根据 commentid 查询一个 Comment 对象
	public Comment findCommentById(String commentid) {
		Comment entity = null;
		try {
			String hql = "from Comment where  id=" + commentid.trim()
					+ " and statu=1";
			entity = (Comment) super.executeUniqueHql(hql);
			return entity;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	// 201404 第二版新增方法，用户在 App 中，点击某个图片，信息 按钮的时候
	// 根据 topidid perid ，查询 返回查询到一个 评论对象
	public Comment findCommentByTidAndPid(Periodical peri, Topic topic) {
		Comment entity = null;
		DBConnection dbc = new DBConnection();
		Connection conn = dbc.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String sql1 = "select * from comment where topicID="
					+ topic.getId() + " and periodicalID=" + peri.getId();
			//System.out.println("查询一张图片的 sql =" + sql1);

			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql1);
			while (rs.next()) {
				entity = new Comment();
				entity.setId(rs.getInt("id"));
				entity.setTime(rs.getString("time"));
				entity.setStatu(rs.getInt("statu"));
				entity.setComment(rs.getString("comment"));
				entity.setTopic(topic);
				entity.setPeriodical(peri);
				entity.setColumn1(rs.getString("column1")); // 标题
				entity.setColumn2(rs.getString("column2")); // 淘宝链接
			}

		} catch (RuntimeException re) {
			throw re;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbc.colseConnection(conn);
		}
		return entity;
	}


	// 取出所有的评论列表
	public List findAllComment() {
		List<MyEntity> list = null;
		try {
			String hql = "from Comment";
			// list = super.executeListHql(hql);
			return super.list("Comment");
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public List<MyEntity> findAllUnCheckComments() {
		List<MyEntity> list = null;
		try {
			String hql = "from Comment where statu=1";
			list = super.executeListHql(hql);
			return list;
		} catch (RuntimeException re) {
			throw re;
		}
	}

}
