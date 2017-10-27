package com.da.Photography.daoImpl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.da.Photography.dao.BaseDaoInterface;
import com.da.Photography.util.HibernateSessionFactory;

public class BaseHibDao implements BaseDaoInterface {
	Session session;
	Transaction tran;
	
	/**
	 * 开启事务
	 */
	@Override
	public void beginTran() {
		getConn();
		if(session != null){
			tran = session.beginTransaction();
		}
	}
	/**
	 * 提交事务
	 */
	@Override
	public void commitTran() {
		if(tran != null){
			tran.commit();
		}
	}
	/**
	 * 回滚事务
	 */
	@Override
	public void rollbackTran() {
		if(tran != null){
			tran.rollback();
		}
	}
	/**
	 * 创建数据库链接
	 */
	@Override
	public void getConn() {
		session = HibernateSessionFactory.getSession();
	}
	/**
	 * 关闭资源
	 */
	@Override
	public void closeAll() {
		HibernateSessionFactory.closeSession();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	
}
