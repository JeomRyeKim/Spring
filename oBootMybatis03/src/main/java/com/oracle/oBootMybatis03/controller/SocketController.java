package com.oracle.oBootMybatis03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SocketController {

	@RequestMapping("/chat")
	public ModelAndView chat() {
		System.out.println("SocketController chat start");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("chat");
		
		return mv;
	}
}
