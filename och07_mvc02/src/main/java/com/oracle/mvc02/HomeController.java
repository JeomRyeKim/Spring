package com.oracle.mvc02;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

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
@Controller //DispatcherServlet에서 부를 수 있는 컨트롤러가 됨
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
	
	@RequestMapping(value = "/board/view") //RequestMapping : HandlerMapping
	public String view() {
		logger.info("Logger board/view Start..."); // INFO : com.oracle.mvc02.HomeController - Logger board/view Start...
		System.out.println("sysout board/view");   // sysout board/view
		
		return "board/view";
	}
	
	@RequestMapping("/board/content")
	public String content(Model model) {
		System.out.println("content controller sysout board/view");
		
		model.addAttribute("id", 30);
		return "board/content";
	}
	
	@RequestMapping("/board/reply")
	public ModelAndView reply() {
		System.out.println("reply controller sysout board/reply");
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", 30);
		mv.setViewName("board/reply");
		
		return mv;
	}

}
