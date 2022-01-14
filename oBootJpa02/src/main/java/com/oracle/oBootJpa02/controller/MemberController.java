package com.oracle.oBootJpa02.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootJpa02.domain.Member;
import com.oracle.oBootJpa02.service.MemberService;

@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	private final MemberService memberService;
	@Autowired
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/")
	public String home() {
		System.out.println("MemberController home start...");
		return "members/index";
	}
	
	@GetMapping(value = "/members/new") //Member 신규 생성
	public String createForm() {
		System.out.println("MemberController /members/new Start...");
		return "/members/createMemberForm";
	}
	
	
	@PostMapping(value = "members/save") 
	public String create(Member member) {
		System.out.println("MemberController create start...");
		System.out.println("MemberController create member.getTeamname()->" + member.getTeamname());
		System.out.println("MemberController create member.getName()->" + member.getName());
		memberService.join(member);
		
		return "redirect:/";
	}
	
	@GetMapping(value = "/members") //Member List 조회
	public String listMember(Model model) {
		List<Member> memberList = memberService.getListAllMember();
		System.out.println("MemberController listMember memberList.get(0).getName()->" + memberList.get(0).getName());	// 가볍게 1개만 조회할 때 이렇게 사용
		System.out.println("MemberController listMember memberList.get(0).getTeam().getName()->" + memberList.get(0).getTeam().getName());
//		System.out.println("MemberController listMember memberList.get(1).getName()->" + memberList.get(1).getName());
//		System.out.println("MemberController listMember memberList.get(1).getTeam().getName()->" + memberList.get(1).getTeam().getName());
		model.addAttribute("members", memberList);
		return "/members/memberList";
	}
	
	@GetMapping(value = "/memberModifyForm") // memberModifyForm?id=1 - ?id=1은 변수 선언한 Long id가 받음(예전엔 request로 받았었음)
	public String memberModify(Long id, Model model) {
		System.out.println("MemberController memberModify id->" + id);
		Member member = memberService.findByMember(id);
		
		System.out.println("MemberController memberModify member.getId()->" + member.getId());
		System.out.println("MemberController memberModify member.getName()->" + member.getName());
		System.out.println("MemberController memberModify member.getTeam().getName()->" + member.getTeam().getName());
		model.addAttribute("member", member);
		
		return "members/memberModify";
	}
	
	@GetMapping(value = "/members/memberUpdate")
	public String memberUpdate(Member member ,Model model) {
		System.out.println("MemberController memberUpdate member.getId()->" + member.getId());
		System.out.println("MemberController memberUpdate member.getName()->" + member.getName());
		System.out.println("MemberController memberUpdate member.getTeamname()->" + member.getTeamname());
		memberService.memberUpdate(member);
		
		return "redirect:/members";
	}
	
}
