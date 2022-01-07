package com.oracle.mvc042;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@RequestMapping("/index")
	public String goIndex() {
		System.out.println("controller Index Start...");
		
		return "index";
	}
	
	@RequestMapping("/studentView1")
	// StudentInformation : DTO
	// index에서 입력된 정보가 밑의 줄에서 StudentInformation타입의 studentInformation으로 값이 다 입력이 되고 그 입력된 값들이 studentInfo이름으로 저장이 됨
	// 저장된 값을 studentView2로 보내주는 것.
	public String studentView1(@ModelAttribute("studentInfo") StudentInformation studentInformation) { 
		System.out.println("HomeController studentView1 Start... ");
		//여기서 작업할 게 있는경우 이렇게 쓰면 됨
		//return "studentView";
		return "studentView2";
	}
	
	//위와 같은 코드를 풀어서 쓰면 이렇게 됨
	@RequestMapping("/studentView2")
	public String studentView2(StudentInformation studentInformation, Model model) { 
		System.out.println("HomeController studentView2 Start... ");
		model.addAttribute("studentInfo", studentInformation);
		return "studentView2";
	}
	
	
}
