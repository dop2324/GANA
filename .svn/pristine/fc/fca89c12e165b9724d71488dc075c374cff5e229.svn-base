package egovframework.dnworks.cmm.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import egovframework.com.cmm.service.EgovProperties;

public class MailUtil 
{

	// 네이버
	private final static String user = EgovProperties.getProperty("naver.mail.id"); 		
	// 패스워드 
	private final static String password = EgovProperties.getProperty("naver.mail.pw");
	
	//Gmail
	private final static String userGmail = EgovProperties.getProperty("gmail.mail.id"); 
	// 패스워드 
	private final static String passwordGmail = EgovProperties.getProperty("gmail.mail.pw");
	
	private final static String mailFrom = EgovProperties.getProperty("Globals.SiteName");
	
	/*
	 * 
	 * Naver
	 * 
	 */
	public static void naverMailSend(String mail_to, String mail_title, String mail_content) throws UnsupportedEncodingException, AddressException 
	{
		InternetAddress mail_from = new InternetAddress(user, mailFrom, "UTF-8");  
		naverMailSend(mail_to, mail_from, mail_title, mail_content);
	}
	
	public static void naverMailSend(String mail_to, InternetAddress mail_from, String mail_title, String mail_content) throws UnsupportedEncodingException, AddressException 
	{
		InternetAddress[] toAddr = new InternetAddress[1];
		toAddr[0] = new InternetAddress(mail_to);
		
		naverMailSend(toAddr, mail_from, mail_title, mail_content);
	}
	
	public static void naverMailSend(InternetAddress[] to_mail, String mail_title, String mail_content) throws UnsupportedEncodingException, AddressException 
	{
		InternetAddress mail_from = new InternetAddress(user, mailFrom, "UTF-8"); 
		naverMailSend(to_mail, mail_from, mail_title, mail_content);
	}
	public static void naverMailSend(InternetAddress[] to_mail, InternetAddress mail_from, String mail_title, String mail_content) throws UnsupportedEncodingException 
	{
		ExecutorService executorService = Executors.newSingleThreadExecutor(); 
		executorService.execute(new Runnable() 
		{ 
			@Override  
	        public void run() 
			{
				String host = "smtp.naver.com"; //"125.209.238.155"; //"smtp.naver.com"; 
				
				// SMTP 서버 정보를 설정한다. 
				Properties props = new Properties(); 
				props.put("mail.smtp.host", host); 
				props.put("mail.smtp.port", 587); 
				props.put("mail.smtp.starttls.enable", "true"); 
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.ssl.trust", host);
				props.put("mail.smtp.ssl.protocols","TLSv1.2");
				
				Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() { 
					protected PasswordAuthentication getPasswordAuthentication() { 
						return new PasswordAuthentication(user, password); 
					} 
				}); 
				
				try { 
					MimeMessage message = new MimeMessage(session); 
					message.setFrom(mail_from);
					message.addRecipients(Message.RecipientType.TO, to_mail); 
					message.setSubject(mail_title);
					message.setContent(mail_content, "text/html; charset=UTF-8");
					Transport.send(message); 

				} catch (MessagingException e) { 
					e.printStackTrace(); 
				} 
			}
		});  
	    executorService.shutdown(); 
	}
	
	
	/*
	 * 
	 * Gmail
	 * 
	 */
	public static void gMailSend(String mail_to, String mail_title, String mail_content) throws UnsupportedEncodingException, AddressException 
	{
		InternetAddress mail_from = new InternetAddress(userGmail, mailFrom, "UTF-8");  
		gMailSend(mail_to, mail_from, mail_title, mail_content);
	}
	
	public static void gMailSend(String mail_to, InternetAddress mail_from, String mail_title, String mail_content) throws UnsupportedEncodingException, AddressException 
	{
		InternetAddress[] toAddr = new InternetAddress[1];
		toAddr[0] = new InternetAddress(mail_to);
		
		gMailSend(toAddr, mail_from, mail_title, mail_content);
	}
	
	public static void gMailSend(InternetAddress[] to_mail, String mail_title, String mail_content) throws UnsupportedEncodingException, AddressException 
	{
		InternetAddress mail_from = new InternetAddress(userGmail, mailFrom, "UTF-8"); 
		gMailSend(to_mail, mail_from, mail_title, mail_content);
	}
	
	public static void gMailSend(InternetAddress[] to_mail, InternetAddress mail_from, String mail_title, String mail_content) throws UnsupportedEncodingException 
	{
		ExecutorService executorService = Executors.newSingleThreadExecutor(); 
		executorService.execute(new Runnable() 
		{ 
			@Override  
	        public void run() 
			{
				String host = "smtp.gmail.com"; //"smtp.gmail.com"; 
				Properties props = new Properties(); 
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.host", host); 
				props.put("mail.smtp.port", 587);
				props.put("mail.smtp.ssl.trust", host);
				props.put("mail.smtp.ssl.protocols","TLSv1.2");

				Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() { 
					protected PasswordAuthentication getPasswordAuthentication() { 
						return new PasswordAuthentication(userGmail, passwordGmail); 
					} 
				}); 
				
				try { 
					MimeMessage message = new MimeMessage(session); 
					message.setFrom(mail_from);
					message.addRecipients(Message.RecipientType.TO, to_mail); 
					message.setSubject(mail_title);
					message.setContent(mail_content, "text/html; charset=UTF-8");
					Transport.send(message); 

				} catch (MessagingException e) { 
					e.printStackTrace(); 
				} 

			}
		});  
	    executorService.shutdown();  

	}

}

