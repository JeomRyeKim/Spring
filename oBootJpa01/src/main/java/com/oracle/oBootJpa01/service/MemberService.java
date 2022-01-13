package com.oracle.oBootJpa01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oracle.oBootJpa01.domain.Member;
import com.oracle.oBootJpa01.repository.MemberRepository;

@Service
@Transactional // 동시에 다 들어가거나 동시에 다 안 들어가거나 하도록 처리 - 2시 10분 설명 다시 듣기
public class MemberService {
	private final MemberRepository memberRepository;
	
	@Autowired
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
    // 회원 가입
    public Long join(Member member) {
	     System.out.println("MemberService join member.getId() start->" + member.getId());
	     memberRepository.save(member);
	     return  member.getId();
    }
    
    // 전체 회원 조회
	public List<Member> getListAllMember() {
		List<Member> listMember = memberRepository.findAll();
		System.out.println("MemberService getListAllMember listMember.size()->" + listMember.size());
		
		return listMember;
	}
	
	// 전체 회원 조회 - 검색으로 조건 조회
	public List<Member> getListSearchMember(String searchName) {
		System.out.println("MemberService getListSearchMember start...");
		//String pSearchName = searchName + '%';
		System.out.println("MemberService getListSearchMember searchName->" + searchName);
		List<Member> listMember = memberRepository.findByNames(searchName);
		System.out.println("MemberService getListSearchMember listMember.size()->" + listMember.size());
		
		return listMember;
	}
	
}
