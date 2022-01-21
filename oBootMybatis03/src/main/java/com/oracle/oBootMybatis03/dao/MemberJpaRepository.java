package com.oracle.oBootMybatis03.dao;

import java.util.List;
import java.util.Optional;

import com.oracle.oBootMybatis03.domain.Member;

public interface MemberJpaRepository {
	Member 			 save(Member member);
	List<Member>     findAll();
	Optional<Member> findById(Long id);
	void             updateByMember(Member member);

}
