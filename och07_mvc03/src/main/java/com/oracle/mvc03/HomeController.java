package com.oracle.mvc03;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.oracle.mvc03.dto.Member;

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
	
	@RequestMapping("board/confirmId")
	public String confirmId(HttpServletRequest request, Model model) {
		System.out.println("Controller confirmId start...");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		System.out.println("Controller confirmId id->"+id);
		System.out.println("Controller confirmId pw->"+pw);
		model.addAttribute("id", id);
		model.addAttribute("pw", pw);
		return "board/confirmId";
	}
	
	@RequestMapping("board/chechId") // 주소창에 아이디,비번을 치는것 말고 필수적으로 시스템내에서 아이디,비번을 받아서 출력해야함 - 값 입력 안 하면 400 잘못된 요청으로 에러남
	public String checkId(@RequestParam("id") String idd, @RequestParam("pw") int pswd, Model model) {
		System.out.println("Controller chechId id->"+idd);
		System.out.println("Controller chechId pw->"+pswd);
		model.addAttribute("identify", idd);
		model.addAttribute("password", pswd);
		return "board/chechId";
	}
	
	@RequestMapping("member/join")
	public String joinData(@RequestParam("name") String name, @RequestParam("id") String id, @RequestParam("pw") String pw, 
						   @RequestParam("email") String email, Model model) {
		System.out.println("Controller member join name->"+name);
		System.out.println("Controller member join id->"+id);
		System.out.println("Controller member join pw->"+pw);
		System.out.println("Controller member join email->"+email);
		Member member = new Member();
		member.setName(name);
		member.setId(id);
		member.setPw(pw);
		member.setEmail(email);
		
		model.addAttribute("member", member);
		
		return "member/join";
	}
	
	
	
}
