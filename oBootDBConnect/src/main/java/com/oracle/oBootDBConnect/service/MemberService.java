package com.oracle.oBootDBConnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootDBConnect.domain.Member1;
import com.oracle.oBootDBConnect.repository.MemberRepository;

@Service //<- Component로 만들어줘야 컴퓨터가 인식을 함.
public class MemberService {
	private final MemberRepository memberRepository; // 스프링에서는 new 객체 만드는 jsp의 방식을 사용하지 말자고 이렇게 표현하는 것

	// MemberRepository memberRepository = new MemberRepository 를 아래 @Autowired로 쓴다 같은 효과
	@Autowired //DI방식
	public MemberService(MemberRepository memberRepository) { // 생성자
		this.memberRepository = memberRepository;
	}
	
	//회원 가입
	public Long join(Member1 member1) {
		System.out.println("MemberService join start...");
		memberRepository.save(member1);
		return member1.getId();
	}
	
	// 전체 회원 조회
	public List<Member1> allMembers() {
		System.out.println("MemberService allMembers start...");
		List<Member1> memList = null;
		memList = memberRepository.findAll();
		System.out.println("memList.size()->" + memList.size());
		
		return memList;
	}

}
