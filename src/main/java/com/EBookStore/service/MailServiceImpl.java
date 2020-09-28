package com.EBookStore.service;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.EBookStore.model.Mail;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService{
	
	private final JavaMailSender javaMailSender;

	@Async
	@Override
	public void sendMail(Mail mail) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getRecepient());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getContent());
        mailMessage.setFrom("bookstore@gmail.com");
        try {
        javaMailSender.send(mailMessage);
        }catch(Exception e){
        	System.out.println("eroor occured while sending mail to : " +mail.getRecepient());
        }
	}
	

}
