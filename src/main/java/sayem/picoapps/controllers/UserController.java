package sayem.picoapps.controllers;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sayem.picoapps.domains.User;
import sayem.picoapps.repositories.UserRepository;
import sayem.picoapps.services.UserService;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String hello(){
		return "index";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String registrationPage(){
		return "user/register";
	}
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registration(@ModelAttribute User user,BindingResult bResult){
		if (bResult.hasErrors()) {
			System.out.println(bResult.toString());
		}
		user.setPassword(new ShaPasswordEncoder(256).encodePassword(user.getPassword(), null));
		user.getRoles().add("ROLE_USER");
		userRepository.saveAndFlush(user);
		return "redirect:/?message=Registration Successful!";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginPage(){
		return "user/login";
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam Map<String, String> map,Model model,HttpSession httpSession){
		if (userService.isUserRegistered(map.get("username"))) {
			if (userService.isPasswordValidForUser(map.get("username"), map.get("password"))) {
				model.addAttribute("message", "Login Successful!");
				httpSession.setAttribute("user", userRepository.findByUsername(map.get("username")));
			}else {
				model.addAttribute("message","Password Wrong");
			}
		}else {
			model.addAttribute("message","User Not Registered!");
		}
		return "user/login";
	}
}
