package com.oracle.oMVCBoard.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oracle.oMVCBoard.command.BCommand;
import com.oracle.oMVCBoard.command.BContentCommand;
import com.oracle.oMVCBoard.command.BDeleteCommand;
import com.oracle.oMVCBoard.command.BListCommand;
import com.oracle.oMVCBoard.command.BModifyCommand;
import com.oracle.oMVCBoard.command.BReplyCommand;
import com.oracle.oMVCBoard.command.BReplyViewCommand;
import com.oracle.oMVCBoard.command.BWriteCommand;

@Controller
public class BController {
	private static final Logger logger = LoggerFactory.getLogger(BController.class);
	BCommand command = null;
	
	@RequestMapping("/list") // 게시글 전체 보기
	public String list(Model model) {
		logger.info("list start....");
		command = new BListCommand();
		command.execute(model);
		return "list";
	}
	
	@RequestMapping("/content_view") //게시판 상세글 보기
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("content_view()");
		
		model.addAttribute("request", request); // map에 키는 String, 값은 Object로 들어감
		command = new BContentCommand();
		command.execute(model);
		
		return "content_view";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST) //게시글 수정하기
	public String modify(HttpServletRequest request, Model model) {
		logger.info("modify start...");
		
		model.addAttribute("request",request);
		command = new BModifyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/write_view") //새로운 글 작성
	public String write_view(Model model) {
		logger.info("write_view start...");
		return "write_view";
	}
	
	//@RequestMapping(value = "/write", method = RequestMethod.POST)
	@PostMapping(value = "/write")
	public String write(HttpServletRequest request, Model model) {
		logger.info("write start...");
		
		model.addAttribute("request",request);
		command = new BWriteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		logger.info("reply_view start...");
		
		model.addAttribute("request",request);
		command = new BReplyViewCommand();
		command.execute(model);
		
		return "reply_view";
	}
	
	@PostMapping(value = "/reply")
	public String reply(HttpServletRequest request, Model model) {
		logger.info("reply start...");
		
		model.addAttribute("request",request);
		command = new BReplyCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest request, Model model) {
		logger.info("delete start...");
		
		model.addAttribute("request",request);
		command = new BDeleteCommand();
		command.execute(model);
		
		return "redirect:list";
	}
	
}
