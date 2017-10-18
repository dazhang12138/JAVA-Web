package com.da.Photography.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.da.Photography.biz.UserBiz;
import com.da.Photography.dao.DownDao;
import com.da.Photography.dto.User;
import com.da.Photography.util.Email;
import com.da.Photography.util.Log;

/**
 * 用户申请管理员设置通过或拒绝
 * @author ZhangDa
 *
 */
public class SetUserAdminServlet extends HttpServlet {

	/**
	 * serialVersionUID = -500541279399734222L
	 */
	private static final long serialVersionUID = -500541279399734222L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");//1通过2拒绝
		String uid = request.getParameter("uid");
		if(type == null || type.equals("") || uid == null || uid.equals("")) {
			request.setAttribute("result", "处理失败!");
		}else {
			UserBiz uBiz = new UserBiz();
			DownDao dDao = new DownDao();
			if(type.equals("1")) {
				uBiz.updateRoleUserById(uid);
			}
			uBiz.deleteApplyByUId(uid);
			User user;
			try {
				dDao.beginTran();
				user = dDao.queryUserByuid(Integer.valueOf(uid));
				dDao.commitTran();
				String Text = "您好  " + user.getU_name() + " 先生/小姐。<br> 我是Photography摄影网站后台管理员.<br>我们收到您的申请网站管理的要求,首先非常感谢您对我们网站的贡献及支持,我们很希望更多的人加入我们，以及加入我们内部管理.<br>我们管理人员经过大会讨论对于用户您的申请我们给出处理结果如下:"
						+ "<br><span style=\"color: red;\">" + (type.equals("1") ? "通过,恭喜您，你已经是我们Photography摄影网站的后台管理员，请您务必遵守管理员守则。" : "不通过，抱歉！经过我们讨论您不适合做我们社区管理员，请您谅解。") +"</span>"
						+ "<br>我们的承诺:<br><br>    为了营造一个开放和欢迎的环境，我们的贡献者和维护者承诺，无论年龄、体型、残疾、种族、性别身份和表情、经验、国籍、个人外貌、种族、宗教、性别身份和取向，都将参与到我们的平台和社区中，为每个人提供免费的体验。"
						+ "<br>    In the interest of fostering an open and welcoming environment, we ascontributors and maintainers pledge to making participation in our project andour community a harassment-free experience for everyone, regardless of age, bodysize, disability, ethnicity, gender identity and expression, level of experience,nationality, personal appearance, race, religion, or sexual identity andorientation.";
				Email.sendEmail(user.getU_email(),Text);
			} catch (SQLException e) {
				Log.LOGGER.debug("查询用户信息出错" + e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				Log.LOGGER.debug("发送邮件出错" + e.getMessage());
				e.printStackTrace();
			}
		}
		request.getRequestDispatcher("admin/allAply.jsp").forward(request, response);
	}

}
