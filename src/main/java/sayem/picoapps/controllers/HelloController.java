package sayem.picoapps.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sayem.picoapps.services.MailService;

@Controller
public class HelloController {
	@Autowired
	private MailService mailService;
	
	@RequestMapping(value="/mail/send",method=RequestMethod.GET)
	@ResponseBody
	public String sendMail(){
		mailService.sendMail("sayemkcn@gmail.com", "Test Message from JavaMail", "Congratulations! You've successfully received a test message sent with JavaMail");
		return "Success!";
	}
}
