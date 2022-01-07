package com.oracle.mvc041;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	//get방식은 text형식(데이터는 무제한으로 넘길 수 있음)으로감, post는 바이너리파일까지 가능(4KB 안으로 넘어갈 수 있음 - 이미지,동영상 등) - 선생님 5교시 설명 다시 듣기
	@RequestMapping(method = RequestMethod.GET, value = "/student")
	public String goStudent(HttpServletRequest request, Model model) {
		System.out.println("RequestMethod.GET");
		String id = request.getParameter("id");
		System.out.println("GET   id : " + id);
		
		model.addAttribute("studentId", id);
		return "student/studentId";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/student")
	public ModelAndView goStudent(HttpServletRequest request) {
		System.out.println("RequestMethod.POST");
		String id = request.getParameter("id");
		System.out.println("POST   id : " + id);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("student/studentId");
		mv.addObject("studentId", id);
		return mv;
	}
	
	
	
	
}
