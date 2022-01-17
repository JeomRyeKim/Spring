package com.oracle.oBootMybatis03.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmpDaoImpl implements EmpDao {
	@Autowired
	private SqlSession session; // JDBC : DataSource / JPA : EntityManager / Mybatis : SqlSession
	
	@Override
	public int total() {
		int tot = 0;
		System.out.println("EmpDaoImpl Start total....");
		
		//Result => 14
		try {
			tot = session.selectOne("tkEmpTotal"); // tk : 태광 선생님 성함 / selectOne : 하나의 row를 받을 때
			System.out.println("EmpDaoImpl total tot->" + tot);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl total Exception->" + e.getMessage());
		}
		return tot;
	}

}
