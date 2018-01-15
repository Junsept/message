package com.mail;

import java.io.File;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class SpringMailSender {
	@Resource
	private JavaMailSenderImpl mailSender;
	@Resource
	private SimpleMailMessage smm;  
	
	Logger log = LoggerFactory.getLogger(SpringMailSender.class);
	/**
	 * 发送简单的文本
	 * @param subject 主题
	 * @param message 内容
	 * @param from 发件人
	 * @param to 收件人
	 */
	public void sendSimpleMail(String subject, String message, String from, String...to){  		
        smm.setFrom(from);
        smm.setTo(to);
        smm.setText(message);
        smm.setSubject(subject);
		mailSender.send(smm);
	}
	
	/**
	 * 发送简单的html
	 * @param subject
	 * @param message
	 * @param html
	 * @param from
	 * @param to
	 */
	public void sendHtmlMail(String subject, String html, String from, String...to){
		MimeMessage mailMessage = mailSender.createMimeMessage();
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage); 	
	    try {
			messageHelper.setTo(to);
		    messageHelper.setFrom(from); 
		    messageHelper.setSubject(subject); 
		    //true 表示启动HTML格式的邮件 
		    messageHelper.setText(html, true); 		    
			mailSender.send(mailMessage);
		} catch (MessagingException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} 
	}
	/**
	 * 带图片的邮件
	 * @param subject
	 * @param html
	 * @param from
	 * @param to
	 * @throws MessagingException
	 */
	public void sendHtmlMailWithImg(String subject, String html, String from, String...to) throws MessagingException{
		MimeMessage mailMessage = mailSender.createMimeMessage();
		//注意这里的boolean,等于真的时候才能嵌套图片，在构建MimeMessageHelper时候，所给定的值是true表示启用，         
	    //multipart模式 
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true); 	
	    try {
			messageHelper.setTo(to);
		    messageHelper.setFrom(from); 
		    messageHelper.setSubject(subject); 
		    //true 表示启动HTML格式的邮件 
		    messageHelper.setText(html, true); 
		    FileSystemResource img = new FileSystemResource(new File("D:/123.jpg")); 		    
		    messageHelper.addInline("aaa", img); 
		    
			mailSender.send(mailMessage);
		} catch (MessagingException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} 
	}
	
	public void sendHtmlMailWithFile(String subject, String html, String from, String...to) throws MessagingException{
		MimeMessage mailMessage = mailSender.createMimeMessage();
		//注意这里的boolean,等于真的时候才能嵌套图片，在构建MimeMessageHelper时候，所给定的值是true表示启用，         
	    //multipart模式 
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage, true, "utf-8"); 	
	    try {
			messageHelper.setTo(to);
		    messageHelper.setFrom(from); 
		    messageHelper.setSubject(subject); 
		    //true 表示启动HTML格式的邮件 
		    messageHelper.setText(html, true); 
		    FileSystemResource file = new FileSystemResource(new File("C:/Users/Administrator/Desktop/一些问题.docx")); 		    
		    //这里的方法调用和插入图片是不同的。 
		    messageHelper.addAttachment("一些问题.docx",file); 
		    
			mailSender.send(mailMessage);
		} catch (MessagingException e) {
			log.error(e.getMessage());
			e.printStackTrace();
		} 
	}
	
}
