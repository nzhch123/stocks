package com.invest.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

@Component
@PropertySource({"classpath:config.properties"})
public class MailUtil implements InitializingBean {
//sendEmailAccount:发件人邮箱

//receiveMailAccount:收件人邮箱 

//sendEmailPwd :发件人邮箱密码

// PS: 某些邮箱服务器为了增加邮箱本身密码的安全性，给 SMTP 客户端设置了独立密码（有的邮箱称为“授权码”）,
// 对于开启了独立密码的邮箱, 这里的邮箱密码必需使用这个独立密码（授权码）。

    //打开邮箱-->上方找到设置 -->账户 -->找到（POP3/IMAP/SMTP/Exchange/CardDAV/CalDAV服务）-->开启pop3 -->获得授权码；
    @Value("${sendEmailAccount}")
    private String sendEmailAccountCopy;
    private static String sendEmailAccount;
    @Value("${receiveMailAccount}")
    public String receiveMailAccountCopy;
    public static String receiveMailAccount;
    @Value("${sendEmailPwd}")
    public String sendEmailPwdCopy;
    public static String sendEmailPwd;
    @Value("${emailNickName}")
    public String sendEmailNickNameCopy;
    public static String sendEmailNickName;
    //发件人邮箱服务器地址
    @Value("${emailProtocolType}")
    public String emailProtocolTypeCopy;
    public static String emailProtocolType;
    @Value("${sendEmailSMTPHost}")
    public String sendEmailSMTPHostCopy;
    public static String sendEmailSMTPHost;
    @Value("${smtpPort}")
    public String smtpPortCopy = "465";
    public static String smtpPort = "465";
    @Value("${sslSocketFactory}")
    public String sslSocketFactoryCopy;
    public static String sslSocketFactory;

    public static String prt;

    @Override
    public void afterPropertiesSet() throws Exception {
        sendEmailAccount = sendEmailAccountCopy;
        receiveMailAccount = receiveMailAccountCopy;
        sendEmailPwd = sendEmailPwdCopy;
        sendEmailNickName = sendEmailNickNameCopy;
        emailProtocolType = emailProtocolTypeCopy;
        sendEmailSMTPHost = sendEmailSMTPHostCopy;
        sslSocketFactory = sslSocketFactoryCopy;

    }

// 发件人邮箱的 SMTP 服务器地址, 必须准确, 不同邮件服务器地址不同, 一般格式为: smtp.xxx.com
// 网易163邮箱的 SMTP 服务器地址为: smtp.163.com

// 收件人邮箱（替换为自己知道的有效邮箱）


    public static void sendMessage(String subject, String content) throws Exception {
// 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties(); // 参数配置
        props.setProperty("mail.transport.protocol", emailProtocolType); // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", sendEmailSMTPHost); // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true"); // 需要请求认证
        props.put("mail.smtp.ssl.enable", "true");
//ssl安全认证
        props.setProperty("mail.smtp.port", smtpPort);
//设置socketfactory
        props.setProperty("mail.smtp.socketFactory.class", sslSocketFactory);
//只处理SSL的连接, 对于非SSL的连接不做处理
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", smtpPort);

// 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getInstance(props);
        session.setDebug(true); // 设置为debug模式, 可以查看详细的发送 log

// 3. 创建一封邮件
        MimeMessage message = createMimeMessage(session, sendEmailAccount, receiveMailAccount, subject, content);

// 4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();

// 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
//
// PS_01: 成败的判断关键在此一句, 如果连接服务器失败, 都会在控制台输出相应失败原因的 log,
// 仔细查看失败原因, 有些邮箱服务器会返回错误码或查看错误类型的链接, 根据给出的错误
// 类型到对应邮件服务器的帮助网站上查看具体失败原因。
//
// PS_02: 连接失败的原因通常为以下几点, 仔细检查代码:
// (1) 邮箱没有开启 SMTP 服务;
// (2) 邮箱密码错误, 例如某些邮箱开启了独立密码;
// (3) 邮箱服务器要求必须要使用 SSL 安全连接;
// (4) 请求过于频繁或其他原因, 被邮件服务器拒绝服务;
// (5) 如果以上几点都确定无误, 到邮件服务器网站查找帮助。
//
// PS_03: 仔细看log, 认真看log, 看懂log, 错误原因都在log已说明。
        transport.connect(sendEmailAccount, sendEmailPwd);

// 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());

// 7. 关闭连接
        transport.close();
    }

    /**
     * 创建一封只包含文本的简单邮件
     *
     * @param session     和服务器交互的会话
     * @param sendMail    发件人邮箱
     * @param receiveMail 收件人邮箱
     * @return
     * @throws Exception
     */
    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail, String subject, String content) throws Exception {
// 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);

// 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
        message.setFrom(new InternetAddress(sendMail, "nizhichao", "UTF-8"));

// 3. To: 收件人（可以增加多个收件人、抄送、密送）
// CC:抄送人，BCC:密送
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "1055665333@qq.com", "UTF-8"));

// 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
        message.setSubject(subject, "UTF-8");

// 5. Content: 邮件正文（可以使用html标签）（内容有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改发送内容）
        message.setContent(content, "text/html;charset=utf-8");

// 6. 设置发件时间
        message.setSentDate(new Date());

// 7. 保存设置
        message.saveChanges();

        return message;
    }


}