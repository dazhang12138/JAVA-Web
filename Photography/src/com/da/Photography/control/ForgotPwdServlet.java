package com.da.Photography.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.mail.*;
import javax.mail.internet.*;

import java.util.Properties;

import com.da.Photography.biz.UserBiz;
import com.da.Photography.dto.User;
import com.da.Photography.util.Log;

/**
 * 找回密码，获取邮箱地址，查询用户信息发送邮件。
 * @author ZhangDa
 *
 */
public class ForgotPwdServlet extends HttpServlet {
	 // 发件人的 邮箱 和 密码 和smtp服务地址
    public static String myEmailAccount = "photographyweb@163.com";
    public static String myEmailPassword = "zd1125";
    public static String myEmailSMTPHost = "smtp.163.com";

	/**
	 * serialVersionUID = -481812124329592151L
	 */
	private static final long serialVersionUID = -481812124329592151L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		if(email == null || email.equals("")) {
			request.setAttribute("result", "邮件发送失败！邮箱不能为空.");
		}else {
			UserBiz uBiz = new UserBiz();
			User user = uBiz.detecUserEmail(email);
			if(user == null) {
				request.setAttribute("result", "邮件发送失败！此邮箱未注册.");
			}else {
				try {
					sendEmail(email,user);
					request.setAttribute("result", "邮件已发送.");
				} catch (Exception e) {
					request.setAttribute("result", "邮件发送失败.");
					Log.LOGGER.debug("发送邮件失败 : " + e.getMessage());
					e.printStackTrace();
				}
			}
		}
		request.getRequestDispatcher("forgot.jsp").forward(request, response);
	}
	/**
	 * 发送邮件
	 * @param email
	 * @param user
	 * @throws Exception
	 */
	 public void sendEmail(String email, User user) throws Exception {
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log
        MimeMessage message = createMimeMessage(session, myEmailAccount, email, user);
        Transport transport = session.getTransport();
        transport.connect(myEmailAccount, myEmailPassword);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
	}
	 /**
     * 创建发送邮件
     * @param session 和服务器交互的会话
     * @param sendMail 发件人邮箱
     * @param receiveMail 收件人邮箱
	 * @param user 用户信息
     * @return 邮件
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail, User user) throws Exception {
        MimeMessage message = new MimeMessage(session);
        //发件人
        message.setFrom(new InternetAddress(sendMail, "Photography网站管理员", "UTF-8"));
        // 收件人
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, user.getU_name(), "UTF-8"));
        //邮件主题
        message.setSubject("Photography摄影网站找回密码邮件", "UTF-8");
        //邮件正文
        message.setContent("您好  " + user.getU_name() + " 先生/小姐。<br> 我是Photography摄影网站后台管理员.<br>我们收到您的找回密码要求,已将您的Photography网站登录用户名和密码发送至您的邮箱.<br>如果是您本人操作请记住登录信息并尽快删除邮件.<br>如果不是您本人在操作找回Photography网站密码，请尽快前往我们网站修改个人信息,并注意个人信息以及登录信息泄露问题.<br>我们Photography网站承诺不会将个人信息及隐私泄露给任何人,请您放心."
        		+ "您的登录用户名:" + user.getU_uname() + ";登录密码:" + user.getU_pwd() + ";请牢记您的用户名密码。"
        				+ "<br>我们的承诺:<br><br>    为了营造一个开放和欢迎的环境，我们的贡献者和维护者承诺，无论年龄、体型、残疾、种族、性别身份和表情、经验、国籍、个人外貌、种族、宗教、性别身份和取向，都将参与到我们的平台和社区中，为每个人提供免费的体验。"
        				+ "<br>    In the interest of fostering an open and welcoming environment, we ascontributors and maintainers pledge to making participation in our project andour community a harassment-free experience for everyone, regardless of age, bodysize, disability, ethnicity, gender identity and expression, level of experience,nationality, personal appearance, race, religion, or sexual identity andorientation.", "text/html;charset=UTF-8");
        // 设置发件时间
//        message.setSentDate(new Date());
        // 保存设置
        message.saveChanges();
        return message;
    }
}
