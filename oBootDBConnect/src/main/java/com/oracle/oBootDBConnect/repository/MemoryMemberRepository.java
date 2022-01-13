package com.oracle.oBootDBConnect.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oracle.oBootDBConnect.domain.Member1;

public class MemoryMemberRepository implements MemberRepository {
	private static Map<Long, Member1> store = new HashMap<>();
	private static long sequence = 0L; // long값의 0을 시퀀스에 넣어주겠다

	@Override
	public Member1 save(Member1 member1) {
		System.out.println("MemoryMemberRepository save start...");
		member1.setId(++sequence);
		store.put(member1.getId(), member1);
		
		return member1;
	}

	@Override
	public List<Member1> findAll() {
		System.out.println("MemoryMemberRepository findAll start...");
		// store의 value(Member1 객체) <- Map<Long, Member1> store
		List<Member1> listMember = new ArrayList<>(store.values());
		System.out.println("MemoryMemberRepository findAll listMember.size()->" + listMember.size());
		
		return listMember;
	}

}
