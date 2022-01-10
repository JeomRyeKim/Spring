package com.oracle.oMVCBoard.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.oMVCBoard.command.BCommand;
import com.oracle.oMVCBoard.command.BContentCommand;
import com.oracle.oMVCBoard.command.BListCommand;

@Controller
public class BController {
	private static final Logger logger = LoggerFactory.getLogger(BController.class);
	BCommand command = null;
	
	@RequestMapping("/list")
	public String list(Model model) {
		logger.info("list start....");
		command = new BListCommand();
		command.execute(model);
		return "list";
	}
	
	@RequestMapping("/content_view")
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("content_view()");
		
		model.addAttribute("request", request); // map에 키는 String 값은 Object로 들어감
		command = new BContentCommand();
		command.execute(model);
		
		return "content_view";
	}
	
	
}
