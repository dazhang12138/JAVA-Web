package com.da.Photography.util;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
	 // 发件人的 邮箱 和 密码 和smtp服务地址
    public static String myEmailAccount = "photographyweb@163.com";
    public static String myEmailPassword = "zd1125";
    public static String myEmailSMTPHost = "smtp.163.com";
    
    /**
	 * 发送邮件
	 * @param email
	 * @param user
	 * @throws Exception
	 */
	 public static void sendEmail(String email, String Text) throws Exception {
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", Email.myEmailSMTPHost);   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        Session session = Session.getDefaultInstance(props);
        session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log
        MimeMessage message = createMimeMessage(session, Email.myEmailAccount, email, Text);
        Transport transport = session.getTransport();
        transport.connect(Email.myEmailAccount, Email.myEmailPassword);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
	}
    
	 /**
     * 创建发送邮件
     * 找回密码邮件
     * @param session 和服务器交互的会话
     * @param sendMail 发件人邮箱
     * @param receiveMail 收件人邮箱
	 * @param user 用户信息
     * @return 邮件
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail, String Text) throws Exception {
        MimeMessage message = new MimeMessage(session);
        //发件人
        message.setFrom(new InternetAddress(sendMail, "Photography网站管理员", "UTF-8"));
        // 收件人
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "用户", "UTF-8"));
        //邮件主题
        message.setSubject("Photography摄影网站找回密码邮件", "UTF-8");
        //邮件正文
        message.setContent(Text, "text/html;charset=UTF-8");
        // 设置发件时间
//        message.setSentDate(new Date());
        // 保存设置
        message.saveChanges();
        return message;
    }
    
}
