package com.da.Photography.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.DownBizInterface;
import com.da.Photography.bizImpl.DownBiz;
import com.da.Photography.dao.AlbumsDaoInterface;
import com.da.Photography.daoImpl.AlbumsHibDao;
import com.da.Photography.entity.PaPicture;
import com.da.Photography.entity.PaUser;
import com.da.Photography.util.HibernateSessionFactory;
import com.da.Photography.util.Log;

/**
 * 下载辅助
 * 
 * @author ZhangDa
 *
 */
public class AD1Servlet extends HttpServlet {

	/**
	 * serialVersionUID = 2387456341185761607L
	 */
	private static final long serialVersionUID = 2387456341185761607L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String aid = request.getParameter("aid");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		PaUser user = (PaUser) request.getSession().getAttribute("user");
		if (user == null) {
			out.write("请先登录");
		} else {
			if (aid == null || aid.equals("")) {
				out.write("下载出错!请稍后重试.");
			} else {
				// 检测用户积分是否够下载、积分扣除、专辑创建者增加积分、记录下载记录
				long uid = user.getUId();
				DownBizInterface dBiz = new DownBiz();
				AlbumsDaoInterface aDao = new AlbumsHibDao();
				List<PaPicture> pics = new ArrayList<>(); // 专辑内图片集
				try {
					pics = aDao.queryAPictureByAid(aid);
				} catch (SQLException e) {
					Log.LOGGER.debug("查询所有图片失败" + e.getMessage());
					e.printStackTrace();
				}
				if(pics.size()==0) {
					out.write("已经下载专辑内所有图片，如果您之前下载过其中的图片，将不会扣除您对应的积分.");
				}else {
					int prices = 0;
					for (PaPicture pic : pics) {
						prices += pic.getPPrice();
					}
					if (user.getUPrice() >= prices) {
						// 积分足够
						// 记录、扣除、增加积分
						boolean flag = dBiz.down2((int)uid, pics);
						if (flag) {
							out.write("已经下载专辑内所有图片，如果您之前下载过其中的图片，将不会扣除您对应的积分.");
						}else {
							out.write("下载失败！请稍后在试.");
						}
					} else {
						// 积分不足
						out.write("您的积分不足,请充值或赚取积分后下载.");
					}
				}
			}
		}
		HibernateSessionFactory.closeSession();
		out.flush();
		out.close();
	}

}
