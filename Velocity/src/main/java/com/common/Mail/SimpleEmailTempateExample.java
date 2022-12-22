package com.common.Mail;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SimpleEmailTempateExample {
	public static void main(String[] args) {
		  Mail mail = new Mail();
		  mail.setMailFrom("c2hila@naver.com");
		  mail.setMailTo("ii001887@gmail.com");
		  mail.setMailSubject("Subject - Send Email using Spring Velocity Template");
		  mail.setTemplateName("emailtemplate.vm");
		  
		  
		  ApplicationContext context = new FileSystemXmlApplicationContext("/src/main/webapp/WEB-INF/spring/spring-beans.xml");
		  
		  Mailer mailer = (Mailer) context.getBean("mailer");
		  mailer.sendMail(mail);
		}
}
