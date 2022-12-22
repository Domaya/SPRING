package com.common.Mail;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class Mailer {
	
	@Autowired
	private MailSender mailSender;
	
	@Autowired
	 private VelocityEngine velocityEngine;

	 public void setMailSender(MailSender mailSender) {
	  this.mailSender = mailSender;
	 }

	 public void setVelocityEngine(VelocityEngine velocityEngine) {
	  this.velocityEngine = velocityEngine;
	 }

	 public void sendMail(Mail mail) {
	  SimpleMailMessage message = new SimpleMailMessage();
	  
	  message.setFrom(mail.getMailFrom());
	  message.setTo(mail.getMailTo());
	  message.setSubject(mail.getMailSubject());

	  Template template = velocityEngine.getTemplate("./src/main/webapp/resources/templates/" + mail.getTemplateName());

	  VelocityContext velocityContext = new VelocityContext();
	  velocityContext.put("firstName", "LIM");
	  velocityContext.put("lastName", "Junhan");
	  velocityContext.put("location", "Seoul");
	  
	  StringWriter stringWriter = new StringWriter();
	  template.merge(velocityContext, stringWriter);
	  
	  message.setText(stringWriter.toString());
	  
	  mailSender.send(message);
	 }
}