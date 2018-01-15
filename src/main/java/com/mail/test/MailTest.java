package com.mail.test;

import javax.mail.MessagingException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mail.SpringMailSender;



public class MailTest {
	public static void main(String[] args) throws MessagingException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-mail.xml");
		SpringMailSender sender = (SpringMailSender)context.getBean("springMailSender");
		String subject = "测试Spring发送邮件";
		String from = "1262072743@qq.com";
		String [] to = new String[]{"zhongmj@gzsendi.cn", "1262072743@qq.com"};
		
		//sender.sendSimpleMail(subject, "一封邮件", from, to);
		
		//String html = "<html><head></head><body><h1>hello!!spring html Mail</h1></body></html>";
		//sender.sendHtmlMail(subject, html, from, to);
		
		//String html = "<html><head></head><body><h1>hello!!spring image html mail</h1>" + 
		//		"<img src='cid:aaa'/></body></html>";
		//sender.sendHtmlMailWithImg(subject, html, from, to);
				
		String html = "<html><head></head><body><h1>你好：附件中有学习资料！</h1></body></html>";
		sender.sendHtmlMailWithFile(subject, html, from, to);
	}
}
