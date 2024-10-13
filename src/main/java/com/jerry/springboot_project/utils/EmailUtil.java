package com.jerry.springboot_project.utils;

import com.jerry.springboot_project.model.email.EmailDetailRequest;
import lombok.extern.slf4j.Slf4j;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


/**
 * 邮箱工具类
 *
 * @author Jerry 2024.10.13
 */
@Slf4j
public class EmailUtil {
    //邮件服务器主机名
    // QQ邮箱的 SMTP 服务器地址为: smtp.qq.com
    private static String myEmailSMTPHost = "邮件服务器主机名";

    //发件人邮箱
    private static String myEmailAccount = "发件人邮箱";

    //发件人邮箱密码（授权码）
    //在开启SMTP服务时会获取到一个授权码，把授权码填在这里
    private static String myEmailPassword = "授权码";

    // 定义一个正则表达式来匹配电子邮箱格式
    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    // 编译正则表达式为一个Pattern对象
    private static final java.util.regex.Pattern EMAIL_PATTERN =
            java.util.regex.Pattern.compile(EMAIL_REGEX);

    /**
     * 验证给定的字符串是否为有效的电子邮箱格式
     *
     * @param email 要验证的字符串
     * @return 如果字符串是有效的电子邮箱格式，则返回true；否则返回false
     */
    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        // 使用Matcher对象来匹配输入字符串和正则表达式
        java.util.regex.Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    /**
     * 邮件单发（自由编辑短信，并发送，适用于私信）
     *
     * @param email 收件箱地址

     * @throws Exception
     */

    public static String sendEmail(String email,String title,String detail) {
        Transport ts = null;
        try {


            Properties prop = new Properties();
            //设置QQ邮件服务器
            prop.setProperty("mail.host", myEmailSMTPHost);
            //邮件发送协议
            prop.setProperty("mail.transport.protocol", "smtp");
            //需要验证用户名密码
            prop.setProperty("mail.smtp.auth", "true");

            //关于QQ邮箱，还要设置SSL加密，加上以下代码即可
//        MailSSLSocketFactory sf = new MailSSLSocketFactory();
//        sf.setTrustAllHosts(true);
//        prop.put("mail.smtp.ssl.enable", "true");
//        prop.put("mail.smtp.ssl.socketFactory", sf);

            //使用JavaMail发送邮件的5个步骤
            //1.创建定义整个应用程序所需的环境信息的Session对象
            Session session = Session.getDefaultInstance(prop, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    //发件人邮件用户名、授权码
                    return new PasswordAuthentication(myEmailAccount,
                            myEmailPassword);
                }
            });

            //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            session.setDebug(false);

            //2、通过session得到transport对象
            ts = session.getTransport();

            //3、使用邮箱的用户名和授权码连上邮件服务器
            ts.connect(myEmailSMTPHost,myEmailAccount, myEmailPassword);

            //4，创建邮件
            //4-1.txt，创建邮件对象
            MimeMessage message = new MimeMessage(session);

            //4-2，指明邮件的发件人
            message.setFrom(new InternetAddress(myEmailAccount));

            //4-3，指明邮件的收件人
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));

            //4-4，邮件标题
            message.setSubject(title);

            //4-5，邮件文本内容
            message.setContent(detail,"text/html;charset=UTF-8");

            //4-6，发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            //5，关闭连接
            ts.close();

        } catch (Exception e) {
            return "发送邮件失败";
        }
        return "发送成功";
    }

    public static String getRegisterEmailDetail(EmailDetailRequest emailDetailRequest){
       return  "<!DOCTYPE html>  \n" +
                "<html lang=\"zh-CN\">  \n" +
                "<head>  \n" +
                "    <meta charset=\"UTF-8\">  \n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">  \n" +
                "    <title>验证码邮件</title>  \n" +
                "    <style>  \n" +
                "        body {  \n" +
                "            font-family: Arial, sans-serif;  \n" +
                "            line-height: 1.6;  \n" +
                "            margin: 0;  \n" +
                "            padding: 0;  \n" +
                "            background-color: #f4f4f4;  \n" +
                "        }  \n" +
                "        .container {  \n" +
                "            max-width: 600px;  \n" +
                "            margin: 0 auto;  \n" +
                "            padding: 20px;  \n" +
                "            background-color: #fff;  \n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);  \n" +
                "        }  \n" +
                "        h1 {  \n" +
                "            color: #333;  \n" +
                "            margin-bottom: 20px;  \n" +
                "        }  \n" +
                "        p {  \n" +
                "            color: #666;  \n" +
                "            margin-bottom: 20px;  \n" +
                "        }  \n" +
                "        .highlight {  \n" +
                "            background-color: #ffeb3b;  \n" +
                "            padding: 10px;  \n" +
                "            color: #000;  \n" +
                "            font-weight: bold;  \n" +
                "        }  \n" +
                "        .button {  \n" +
                "            display: inline-block;  \n" +
                "            background-color: #2196f3;  \n" +
                "            color: #fff;  \n" +
                "            text-align: center;  \n" +
                "            padding: 10px 20px;  \n" +
                "            text-decoration: none;  \n" +
                "            border-radius: 5px;  \n" +
                "        }  \n" +
                "        .button:hover {  \n" +
                "            background-color: #1e88e5;  \n" +
                "        }  \n" +
                "    </style>  \n" +
                "</head>  \n" +
                "<body>  \n" +
                "    <div class=\"container\">  \n" +
                "        <h1>您的验证码已发送</h1>  \n" +
                "        <p>  \n" +
                "            尊敬的"+emailDetailRequest.getEmail()+"，  \n" +
                "        </p>  \n" +
                "        <p>  \n" +
                "            您好！为了确保您的账户安全并顺利完成[指定操作，如：密码重置、注册验证、登录验证等]，  \n" +
                "            我们已向您的邮箱发送了一个六位数的验证码。  \n" +
                "        </p>  \n" +
                "        <p class=\"highlight\">  \n" +
                "            您的验证码是：<span>"+emailDetailRequest.getCode()+"</span>  \n" +
                "        </p>  \n" +
                "        <p>  \n" +
                "            <strong>请注意：</strong>  \n" +
                "            - 该验证码在发送后的5分钟内有效。如果超时未使用，您将需要重新请求验证码。  \n" +
                "            - 验证码仅可使用一次，一旦验证成功即失效。  \n" +
                "            - 请不要将验证码透露给任何人，以确保您的账户安全。  \n" +
                "        </p>  \n" +
                "        <p>  \n" +
                "            请在收到此邮件后，立即打开<a href=#>链接</a>，并在提示的位置输入上述验证码。  \n" +
                "            完成验证后，您将继续进行[后续操作，如：设置新密码、完成注册等]。  \n" +
                "        </p>  \n" +
                "            如问题仍未解决，请联系我们的客服支持团队，我们将竭诚为您提供帮助。  \n" +
                "        </p>  \n" +
                "        <p>  \n" +
                "            客服支持联系方式：  \n" +
                "            - 电话：[客服电话]  \n" +
                "            - 邮箱：[客服邮箱]  \n" +
                "        </p>  \n" +
                "    </div>  \n" +
                "</body>  \n" +
                "</html>";
    }

}
