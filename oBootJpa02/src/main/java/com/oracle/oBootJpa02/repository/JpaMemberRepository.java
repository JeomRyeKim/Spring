package com.oracle.oBootJpa02.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.oracle.oBootJpa02.domain.Member;
import com.oracle.oBootJpa02.domain.Team;

public class JpaMemberRepository implements MemberRepository {
	private final EntityManager em;
	
	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}

	@Override
	public Member save(Member member) {
		// 팀 저장 - fk로 연관관계 매핑이 되어있기 때문에 팀에 없는 회원이 멤버에 있어선 안 되니까 팀 먼저 저장하고 회원 저장하는 것
		Team team = new Team();
		team.setName(member.getTeamname());
		em.persist(team);	// DB insert
		
		// 회원 저장
		member.setTeam(team);	// 단방향 연관관계 설정, 참조 저장 (다대일 관계로 FK되어있는 member에게 team에 저장된 회원들 정보를 넘겨줌)
		em.persist(member);
		return member;
	}

	@Override
	public List<Member> findAll() {	
		// "select m from Member m"은  Member.class를 위한 쿼리문임 그래서 Member.java클래스에 들어가서 @Table,@GeneratedValue를 보고 createQuery가 진짜 쿼리를 만들어서 em으로 디비에 접속하여 값을 가져오는 것!
		// <Member>와 Member.class 가 같아야 함. 값을 1개만 받아오면 Member memberList = em...이런식으로 받아도 되지만, 여러개의 값을 받으므로 List형식으로 받음 그래서 받아오는 값도 List형식으로 바꿔주기 위해 getResultList() 사용
		List<Member> memberList = em.createQuery("select m from Member m", Member.class).getResultList();
		return memberList;
	}

	@Override
	public Member findByMember(Long id) {
		//						Entity		  PK
		Member member = em.find(Member.class, id); // 조인 테이블 알아서 조인걸어서 가지고 보냄
		return member;
	}

	@Override
	public int updateByMember(Member member) {
		int result = 0; //결과값
		System.out.println("JpaMemberRepository updateByMember member.getId()->" + member.getId());
		Member member3 = em.find(Member.class, member.getId());
		
		if(member3 != null) {
			// 팀 저장(수정)
			System.out.println("JpaMemberRepository updateByMember member.getTeamid()->" + member.getTeamid());
			Team team = em.find(Team.class, member.getTeamid());
			if(team != null) {
				System.out.println("JpaMemberRepository updateByMember member.getTeamname()->" + member.getTeamname());
				team.setName(member.getTeamname()); //수정처리
				em.persist(team); //수정된 내용 입력(update) - 수정됐으면 update, 없다가 생긴거면 insert 알아서 판단해서 처리해줌
			}
			
			// 회원 저장(수정)
			System.out.println("JpaMemberRepository updateByMember member.getName()->" + member.getName());
			member3.setTeam(team); // 단방향 연관관계 설정, 참조 저장
			member3.setName(member.getName()); // 단방향 연관관계 설정, 참조 저장
			em.persist(member3); //수정된 내용 입력(update)
			System.out.println("JpaMemberRepository updateByMember member.getName()->" + member.getName());
			result = 1;
		}else {
			System.out.println("JpaMemberRepository updateByMember No Exist...");
			result = 0;
		}
		return result;
	}

}
