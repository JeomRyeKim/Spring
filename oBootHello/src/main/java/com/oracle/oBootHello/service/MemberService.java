package com.oracle.oBootHello.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootHello.domain.Member1;
import com.oracle.oBootHello.repository.MemberRepository;

@Service
public class MemberService {
	 private final  MemberRepository memberRepository;
	 //MemberRepository memberRepository = new MemoryMemberRepository();
	   @Autowired
	   public MemberService(MemberRepository memberRepository) {
		   this.memberRepository = memberRepository;
	   }
	   
	   // 회원 가입
	   public Long join(Member1 member1) {
		     System.out.println("MemberService join start... ");
		     memberRepository.save(member1);
		     return  member1.getId();
	   }

	   // 전체회원 조회
	   public List<Member1>   allMembers() {
		System.out.println("MemberService allMembers start...");
	 	List<Member1> memList = null;
	   	memList =  memberRepository.findAll(); 
	   	System.out.println("memList.size()->"+ memList.size());
		return memList;   
	   }
	 
}
