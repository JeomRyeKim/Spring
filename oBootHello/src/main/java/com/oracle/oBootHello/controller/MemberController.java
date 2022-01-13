package com.oracle.oBootHello.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootHello.domain.Member1;
import com.oracle.oBootHello.service.MemberService;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	private final MemberService memberService;
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@GetMapping(value = "/members/inputForm")
	public String inputForm() {
		System.out.println("MemberController /members/inputForm Start..");
		return "members/inputMemberForm";
	}

	@PostMapping(value = "/members/save")
	public String save(Member1 member1) {
		System.out.println("MemberController /members/save Start..");
		Long id = memberService.join(member1);
		System.out.println("MemberController /members/save id->"+id);
      return "redirect:/";
	}

	@GetMapping(value ="/members/memberList")
	public String memberList(Model model) {
		logger.info("memberList start..");
		List<Member1> memberLists = memberService.allMembers();
		model.addAttribute("memberLists", memberLists);
		logger.info("memberLists.size()-> {}",memberLists.size());
		
	  return "members/memberList";
	}

}
