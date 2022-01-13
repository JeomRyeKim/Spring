package com.oracle.oBootDBConnect.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	private static final Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@RequestMapping("hello")
	public String hello(Model model) {
		logger.info("hello start...");
		model.addAttribute("hdata", "hello Start");
		
		return "hello";
	}
	
	@GetMapping("ajaxString")
	@ResponseBody // http://localhost:8385/ajaxString?ajaxName=aaa <- 화면 : aaa
	public String ajaxString(@RequestParam("ajaxName") String aName) {
		System.out.println("HelloController ajaxString aName->" + aName);
		return aName;
	}
	
	@GetMapping("/")
	public String home() {
		System.out.println("HelloController home start...");
		return "home";
	}
	
	
}
