package com.oracle.oBootMybatis03.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis03.model.Member3;

@Repository
public class Member1DaoImpl implements Member1Dao {
	@Autowired
	private SqlSession session;
	
	@Override
	public int memCount(String id) {
		int result = 0;
		System.out.println("Member1DaoImpl id->" + id);
		try {
			result = session.selectOne("memCount", id);
		} catch (Exception e) {
			System.out.println("Member1DaoImpl memCount Exception->" + e.getMessage());
		}
		
		return result;
	}

	@Override
	public List<Member3> listMem(Member3 member3) {
		System.out.println("Member1DaoImpl listMem start");
		List<Member3> listMember3 = null;
		try {
			listMember3 = session.selectList("listMember3", member3);
		} catch (Exception e) {
			System.out.println("Member1DaoImpl listMem Exception->" + e.getMessage());
		}
		return listMember3;
	}

}
