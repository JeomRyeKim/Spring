package com.oracle.security1;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	 // web.xml - <url-pattern>*.html</url-pattern>때문에 .html을 붙여줌
	@RequestMapping("/login.html") // user/123 admin/123 둘 다 들어갈 수 있음
	public String login(Model model) {
		System.out.println("HomeController login Start...");
		return "security/login";
	}
	
	@RequestMapping("/welcome.html") // user/123으로 들어가면 권한이 없다고 뜸 admin/123으로 로그인해야 들어갈 수 있음
	public String welcome(Model model) {
		System.out.println("HomeController welcome Start...");
		return "security/welcome";
	}
	
}
