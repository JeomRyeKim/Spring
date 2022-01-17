package com.oracle.oBootAPI01.repository;

import java.util.List;

import com.oracle.oBootAPI01.domain.Member;

public interface MemberRepository {
	Long save(Member member);
	List<Member> findAll();
}
