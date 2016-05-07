package sayem.picoapps.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendMail(String to,String subject,String body){
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message,true);
			helper.setSubject(subject);
			helper.setTo(to);
			helper.setText(body, true);
		} catch (MessagingException e) {
			System.out.println(e.toString());
		} 
		javaMailSender.send(message);
	}

}
