package com.oracle.oBootJpa01.repository;

import java.util.List;

import com.oracle.oBootJpa01.domain.Member;

public interface MemberRepository {
	Member save(Member member);

	List<Member> findAll();

	List<Member> findByNames(String name);
}
