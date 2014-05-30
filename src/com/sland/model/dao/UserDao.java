package com.sland.model.dao;

import java.util.List;

import util.BaseDao;

import com.sland.model.pojo.MyEntity;
import com.sland.model.pojo.Userinfo;

public class UserDao extends BaseDao {

	/* ==========Find Property Methods========== */
	public Userinfo login(String name, String pwd) {
		Userinfo info = null;
		String hql = "from Userinfo where loginname='" + name
				+ "' and loginpwd='" + pwd + "'";
		info = (Userinfo) super.executeUniqueHql(hql);
		return info;
	}
	
	
	public List findAllMagziones() {
		List<MyEntity> list = null;
		try {
			String hql = "from Magazine";
			list = super.executeListHql(hql);
			return list;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	
	public List findAllTopics() {
		List<MyEntity> list = null;
		try {
			String hql = "from Topic";
			list = super.executeListHql(hql);
			return list;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public List findAllComment() {
		List<MyEntity> list = null;
		try {
			String hql = "from Comment";
			list = super.executeListHql(hql);
			return list;
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
