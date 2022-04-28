package com.tyss.schedulemail.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.tomcat.jni.Time;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	//sec  min hour dayOfTheMonth month dayOfTheWeek
	@Scheduled(cron = "0 44 16 * * *")
	public void sendMail() {
		final String username = "from-mail@gmail.com";
		final String password = "password";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {

			Message message = new MimeMessage(session);
			Transport transport = session.getTransport();
			Address[] from = InternetAddress.parse("to-mail@gmail.com");
			message.addFrom(from);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("from-mail@gmail.com"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress("to-mail@gmail.com"));

			message.setSubject("Gentle reminder");
			message.setText("Hey its "+ LocalDate.now());

			transport.connect("smtp.gmail.com", username, password);
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}
