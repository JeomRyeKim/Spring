package com.oracle.oBootJpa02.repository;

import java.util.List;

import com.oracle.oBootJpa02.domain.Member;

public interface MemberRepository {
	Member save(Member member);

	List<Member> findAll();

	Member findByMember(Long id);
	
	// MemberService에선 void로 리턴값이 없도록 설정했지만 repository에서 int형으로 바꿔서 받을 수도 있음!
	// void였지만 int로 만들어주면 나중에 int result=1 or 0에 따른 쿼리문을 짜서 수정했습니다 or 수정되지 않았습니다 하고 나타나는 쿼리를 짤 수도 있음
	int updateByMember(Member member); 
	
}
