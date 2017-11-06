package com.da.Photography.daoImpl;

import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.da.Photography.dao.BaseDaoInterface;

public class BaseHibDao extends HibernateDaoSupport implements BaseDaoInterface {
	Session session;
	
	/**
	 * 创建数据库链接
	 */
	@Override
	public void getConn() {
		session = this.getSessionFactory().getCurrentSession();
	}

	
}
