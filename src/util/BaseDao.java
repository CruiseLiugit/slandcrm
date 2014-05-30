package util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sland.model.pojo.MyEntity;

public class BaseDao {
	private Session session = null;
	private Transaction tran = null;
	private Query query = null;

	/**
	 * 打开关闭 Session 对象
	 * 
	 * @return
	 */
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}

	public void closeSession() {
		HibernateSessionFactory.closeSession();
	}

	public void add(MyEntity entity) {
		if (entity != null) {
			session = this.getSession();
			try {
				tran = session.beginTransaction();
				session.save(entity);
				tran.commit();
			} catch (Exception e) {
				tran.rollback();
			} finally {
				this.closeSession();
			}
		}
	}

	public void update(MyEntity entity) {
		if (entity != null) {
			session = this.getSession();
			try {
				tran = session.beginTransaction();
				session.update(entity);
				tran.commit();
			} catch (Exception e) {
				tran.rollback();
			} finally {
				this.closeSession();
			}
		}
	}

	/**
	 * 查询
	 * 
	 * @param entity
	 * @param id
	 * @return
	 */
	public MyEntity getById(MyEntity entity, Integer id) {
		MyEntity obj = null;

		session = this.getSession();
		try {
			obj = (MyEntity) session.get(entity.getClass(), id);
		} catch (Exception e) {

		} finally {
			this.closeSession();
		}

		return obj;
	}

	public List<MyEntity> list(String table) {
		List<MyEntity> list = null;

		session = this.getSession();
		try {
			query = session.createQuery("from " + table);
			list = query.list();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			this.closeSession();
		}

		return list;
	}

	public List<MyEntity> list(String table, int first, int max) {
		List<MyEntity> list = null;

		session = this.getSession();
		try {
			query = session.createQuery("from " + table);
			query.setFirstResult(first);
			query.setMaxResults(max);
			list = query.list();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			this.closeSession();
		}

		return list;
	}

	// 执行 HQL 语句
	public List<MyEntity> executeListHql(String hql) {
		List<MyEntity> list = null;
		session = this.getSession();
		try {
			query = session.createQuery(hql);
			list = query.list();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			this.closeSession();
		}

		return list;
	}

	public List<MyEntity> executeListHql(String hql, int first, int max) {
		List<MyEntity> list = null;
		session = this.getSession();
		try {
			query = session.createQuery(hql);
			query.setFirstResult(first);
			query.setMaxResults(max);
			list = query.list();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			this.closeSession();
		}

		return list;
	}

	public MyEntity executeUniqueHql(String hql) {
		MyEntity entity = null;
		session = this.getSession();
		try {
			query = session.createQuery(hql);
			entity = (MyEntity) query.uniqueResult();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			this.closeSession();
		}

		return entity;
	}

}
