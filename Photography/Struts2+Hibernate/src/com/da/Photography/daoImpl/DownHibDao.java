package com.da.Photography.daoImpl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;

import com.da.Photography.dao.DownDaoInterface;
import com.da.Photography.entity.PaDown;
import com.da.Photography.entity.PaUser;

public class DownHibDao extends BaseHibDao implements DownDaoInterface {

	@Override
	public boolean queryPriceSupport(int u_id, String pid) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int deductUserPrice(int u_id, String pid) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addUserPrice(String pid) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int recordDown(int u_id, String pid) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int recordDown2(String pid) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean queryDownByUidAndPid(int u_id, String pid) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * 查询全部积分动态
	 */
	@Override
	public List<PaDown> queryAllDown(String type, long u_id) throws SQLException {
		String hql = "from PaDown ";
		if(type.equals("1")) {
			hql += " where paUser.UId=" + u_id;
		}
		hql += " order by DId DESC ";
		Query q = session.createQuery(hql);
		return q.list();
	}

	@Override
	public PaUser queryUserByuid(int uid) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 查询最大编号
	 */
	@Override
	public long maxId() {
		String sql = "select nvl(max(d_id),0) from Pa_Down";
		SQLQuery sq = session.createSQLQuery(sql);
		long result = Long.valueOf(sq.list().get(0).toString());
		return result;
	}
	
}
