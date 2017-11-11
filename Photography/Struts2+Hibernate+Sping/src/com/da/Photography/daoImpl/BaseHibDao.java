package com.da.Photography.daoImpl;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.da.Photography.dao.BaseDaoInterface;
import com.da.Photography.util.HibernateSessionFactory;

public class BaseHibDao extends HibernateDaoSupport implements BaseDaoInterface {
	Session session;
	Transaction tran;
	
	/**
	 * 开启事务
	 */
	@Override
	public void beginTran() {
		getConn("");
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
		session = this.getSessionFactory().getCurrentSession();
	}
	@Override
	public void getConn(String str) {
		session = HibernateSessionFactory.getSession();
	}
	
}
