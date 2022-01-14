package com.oracle.oBootJpa02.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootJpa02.domain.Member;
import com.oracle.oBootJpa02.repository.MemberRepository;

@Service
@Transactional
public class MemberService {
	private final MemberRepository memberRepository;
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	// 회원 가입
	public Member join(Member member) {
		System.out.println("MemberService join member.getName()->" + member.getName());
	    memberRepository.save(member);
	    
	    return  member;
	}
	
	// 전체 회원 조회
	public List<Member> getListAllMember() {
		List<Member> listMember = memberRepository.findAll();
		System.out.println("MemberService getListAllMember listMember.size()->" + listMember.size());
		return listMember;
	}
	
	// 회원 상세 조회
	public Member findByMember(Long memberId) {
		Member member1 = memberRepository.findByMember(memberId);
		System.out.println("MemberService findByMember member1.getId()->" + member1.getId());
		System.out.println("MemberService findByMember member1.getName()->" + member1.getName());
		System.out.println("MemberService findByMember member1.getTeam().getName()->" + member1.getTeam().getName());
		return member1;
	}
	
	// 회원 수정
	public void memberUpdate(Member member) {
		System.out.println("MemberService memberUpdate member.getName()->" + member.getName());
		System.out.println("MemberService memberUpdate member.getTeamname()->" + member.getTeamname());
		memberRepository.updateByMember(member); 
	}
	
}
