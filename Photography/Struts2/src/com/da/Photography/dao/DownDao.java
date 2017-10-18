package com.da.Photography.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.da.Photography.dto.Down;
import com.da.Photography.dto.User;

public class DownDao extends BaseDao{

	/**
	 * 判断积分是否足够下载图片
	 * @param u_id
	 * @param pid
	 * @return
	 * @throws SQLException 
	 */
	public boolean queryPriceSupport(int u_id, String pid) throws SQLException {
		boolean flag = false;
		String sql = "Select (t.U_PRICE-p.P_PRICE) p From PA_USER t, PA_PICTURE p Where t.U_ID = ? And p.P_ID = ?";
		Object[] params = {u_id,pid};
		rs = executeQuery(sql, params);
		int result = 0;
		if(rs.next()) {
			result = rs.getInt("p");
		}
		if(result >= 0) {
			flag = true;
		}
		return flag;
	}
	/**
	 * 扣除用户积分
	 * @param u_id
	 * @param pid
	 * @return
	 * @throws SQLException 
	 */
	public int deductUserPrice(int u_id, String pid) throws SQLException {
		String sql = "update pa_user set u_price=u_price-(Select P_PRICE From PA_PICTURE Where P_ID = ?) where u_id=?";
		Object[] params = {pid,u_id};
		int result = executeUpdate(sql, params);
		return result;
	}
	/**
	 * 增加图片存在专辑的创建者的积分
	 * @param pid
	 * @return
	 * @throws SQLException 
	 */
	public int addUserPrice(String pid) throws SQLException {
		String sql = "update pa_user set "
				+ "u_price=u_price+(Select P_PRICE From PA_PICTURE Where PA_PICTURE.P_ID = ?) "
				+ "where u_id=(Select a.U_ID From PA_PICTURE p, PA_ALBUMS a Where a.A_ID = p.A_ID And p.P_ID = ?)";
		Object[] params = {pid,pid};
		int result = executeUpdate(sql, params);
		return result;
	}
	/**
	 * 更新记录
	 * @param u_id
	 * @param pid
	 * @return
	 * @throws SQLException 
	 */
	public int recordDown(int u_id, String pid) throws SQLException {
		String sql1 = "Select P_PRICE From PA_PICTURE Where P_ID = ?";
		Object[] params1 = {pid};
		String update = "";
		rs = executeQuery(sql1, params1);
		if(rs.next()) {
			update = "-" + rs.getString("P_PRICE");
		}
		String sql = "insert into pa_down values((select nvl(max(d_id),0)+1 from pa_down),"
				+ "?,sysdate,2,?,?)";
		Object[] params = {u_id,update,pid};
		int result = executeUpdate(sql, params);
		return result;
	}
	/**
	 * 更新记录
	 * @param u_id
	 * @param pid
	 * @return
	 * @throws SQLException 
	 */
	public int recordDown2(String pid) throws SQLException {
		String sql1 = "Select p.P_PRICE,a.u_id From PA_PICTURE p,PA_ALBUMS a Where a.a_id=p.a_id and p.P_ID = ?";
		Object[] params1 = {pid};
		String update = "";
		String u_id = "";
		rs = executeQuery(sql1, params1);
		if(rs.next()) {
			update = "+" + rs.getString("P_PRICE");
			u_id = rs.getString("u_id");
		}
		String sql = "insert into pa_down values((select nvl(max(d_id),0)+1 from pa_down),"
				+ "?,sysdate,3,?,?)";
		Object[] params = {u_id,update,pid};
		int result = executeUpdate(sql, params);
		return result;
	}
	/**
	 * 查找下载记录是否存在
	 * @param u_id
	 * @param pid
	 * @return 返回结果 true 存在下载记录,false不存在记录
	 * @throws SQLException 
	 */
	public boolean queryDownByUidAndPid(int u_id, String pid) throws SQLException {
		boolean falg = false;
		String sql = "Select t.U_ID, t.P_ID, t.D_TYPE From PA_DOWN t "
				+ "Where t.U_ID = ? "
				+ "And t.P_ID = ? "
				+ "And t.D_TYPE = 2";
		Object[] params = {u_id,pid};
		rs = executeQuery(sql, params);
		if(rs.next()) {
			falg = true;
		}
		return falg;
	}
	/**
	 * 查询记录
	 * @param type
	 * @param u_id
	 * @return
	 * @throws SQLException 
	 */
	public List<Down> queryAllDown(String type, int u_id) throws SQLException {
		List<Down> downs = new ArrayList<>();
		String sql = "select * from PA_DOWN t";
		if(type.equals("1")) {
			sql += " where u_id=" + u_id;
		}
		sql += " order by D_id DESC ";
		rs = executeQuery(sql);
		while(rs.next()) {
			Down d = new Down();
			d.setD_id(rs.getInt("d_id"));
			d.getUser().setU_id(rs.getInt("u_id"));
			d.getPic().setP_id(rs.getInt("p_id"));
			d.setD_date(rs.getTimestamp("d_date"));
			d.setD_type(rs.getInt("d_type"));
			d.setD_update(rs.getString("d_update"));
			downs.add(d);
		}
		return downs;
	}
	/**
	 * 查询用户信息通过用户编号
	 * @param uid
	 * @return
	 * @throws SQLException 
	 */
	public User queryUserByuid(int uid) throws SQLException {
		User user = null;
		String sql = "select * from PA_USER where u_id=?";
		Object[] params = {uid};
		rs = executeQuery(sql, params);
		if(rs.next()) {
			user = new User();
			user.setU_id(rs.getInt("u_id"));
			user.setU_name(rs.getString("u_name"));
			user.setU_phone(rs.getString("u_phone"));
			user.setU_uname(rs.getString("u_uname"));
			user.setU_pwd(rs.getString("u_pwd"));
			user.setU_email(rs.getString("u_email"));
			user.setU_price(rs.getInt("u_price"));
			user.setU_role(rs.getString("u_role"));
			user.setU_signday(rs.getInt("u_signday"));
			user.setU_signdate(rs.getDate("u_signdate"));
			user.setU_balance(rs.getDouble("u_balance"));
		}
		return user;
	}
}
