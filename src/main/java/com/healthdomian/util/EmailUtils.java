package com.healthdomian.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
	
	@Autowired
	private JavaMailSender mailSnder;
	
	public boolean sendEmail(String to , String subject , String body) {
		boolean isSent=false;
		
		try {
			
			MimeMessage mimeMessage=mailSnder.createMimeMessage();
			MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(mimeMessage);
			mimeMessageHelper.setTo(to);
			mimeMessageHelper.setSubject(subject);
			mimeMessageHelper.setText( body , true);
			
			mailSnder.send(mimeMessage);
			
			isSent=true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return isSent;
	}
	

}
