package com.oracle.oBootAPI01.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.oracle.oBootAPI01.domain.Member;

@Repository
public class JpaMemberRepository implements MemberRepository {
	private final EntityManager em;
	
	public JpaMemberRepository(EntityManager em) { // 생성자로 만드는건 다시는 바꾸지 않겠다라는 의미(무조건,반드시는 아님)
		this.em = em;
	}

	@Override
	public Long save(Member member) {
		// 회원 저장
		em.persist(member);
		return member.getId();
	}

	@Override
	public List<Member> findAll() {
		// SQL No, JPA 문법
		List<Member> memberList = em.createQuery("select m from Member m", Member.class).getResultList();
		return memberList;
	}

}
