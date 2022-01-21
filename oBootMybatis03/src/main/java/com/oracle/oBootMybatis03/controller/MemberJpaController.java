package com.oracle.oBootMybatis03.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.oracle.oBootMybatis03.domain.Member;
import com.oracle.oBootMybatis03.service.MemberJpaService;

@Controller
public class MemberJpaController {
	private final MemberJpaService memberJpaService;
	
	@Autowired
	public MemberJpaController(MemberJpaService memberJpaService) {
		this.memberJpaService = memberJpaService;
	}

	@GetMapping(value="/memberJpa/new")
	public String createForm() {
		System.out.println("MemberController /members/new start.. ");
		return "memberJpa/createMemberForm";
	}

	@PostMapping(value = "/memberJpa/save")
	public String create(Member member) {
		System.out.println("MemberController create start.. ");
		System.out.println("member.getId()->"+member.getId());
		System.out.println("member.getName()->"+member.getName());
		memberJpaService.join(member);
		
		return "memberJpa/createMemberForm";
	}
	
	@GetMapping(value = "/members")
	public String listMember(Model model) {
		System.out.println("MemberController listMember start.. ");
		List<Member> memberList = memberJpaService.getListAllMember();
		model.addAttribute("members", memberList);
		return "memberJpa/memberList";
	}

	@GetMapping(value = "/memberJpa/memberUpdateForm")
	public String memberUpdateForm(Long id, Model model) {
		Member member = null;
		String rtnJsp = "";
		System.out.println("MemberController memberUpdateForm id->"+id);
		Optional<Member> maybeMember = memberJpaService.findById(id);
		if (maybeMember.isPresent()) {
			System.out.println("MemberController memberUpdateForm maybeMember Is Not NULL");
			member = maybeMember.get();
		    model.addAttribute("member", member);
			rtnJsp = "memberJpa/memberModify";
		} else {
			System.out.println("MemberController memberUpdateForm maybeMember Is NULL");
			model.addAttribute("message", "member가 존재하지 않으니, 입력부터 수행해 주세요");
			rtnJsp = "forward:/members";
		}
	
		return rtnJsp;
	}
	@GetMapping(value = "/memberJpa/memberUpdate")
	public String memberUpdate(Member member, Model model) {
		
		System.out.println("MemberController memberUpdate id->"+member.getId());
		System.out.println("MemberController memberUpdate member.getName->"+member.getName());
		memberJpaService.memberUpdate(member);
		return "redirect:/members";
	}
	
}
