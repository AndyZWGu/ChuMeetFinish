package com.admin.controller;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AdminMailService {
	
	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
	public void sendMail(String to, String subject, String messageText) {
			
	   try {
		   // 設定使用SSL連線至 Gmail smtp Server
		   Properties props = new Properties();
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.socketFactory.port", "465");
		   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.port", "465");

       // ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
       // ●須將myGmail的【安全性較低的應用程式存取權】打開
	     final String myGmail = "chumeetservice@gmail.com";
	     final String myGmail_password = "ChuMeet123";
		   Session session = Session.getInstance(props, new Authenticator() {
			   protected PasswordAuthentication getPasswordAuthentication() {
				   return new PasswordAuthentication(myGmail, myGmail_password);
			   }
		   });

		   Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(myGmail));
		   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		  
		   //設定信中的主旨  
		   message.setSubject(subject);
		   //設定信中的內容 
		   message.setContent(messageText, "text/html; charset=utf-8");

		   Transport.send(message);
		   System.out.println("傳送成功!");
     }catch (MessagingException e){
	     System.out.println("傳送失敗!");
	     e.printStackTrace();
     }
   }
	
	 public static void main (String args[]){

      String to = "chumeetservice@gmail.com";
      
      String subject = "揪咪ChuMeet E-mail 註冊確認信";
      
      String ch_name = "ChuMeetAdmin003";
      String passRandom = "ChuMeetWeb0083";
      String messageText = "<img src=\"https://i.imgur.com/IN3wmJe.png\"><h2>親愛的 "+ "路人甲" +" 您好:</h2><br><p>歡迎您註冊成為揪咪ChuMeet會員。</p><p>註冊完成後，ChuMeet將會提供您更多的服務資訊與內容。 </p><br><h5>ChuMeet歡迎您的加入! ChuMeet服務團隊</h5><br><h4>如有任何問題歡迎來信ChuMeet客服信箱: service@chumeet.com</h4>"; 
      
      
      AdminMailService mailService = new AdminMailService();
      mailService.sendMail(to, subject, messageText);
      

   }


}