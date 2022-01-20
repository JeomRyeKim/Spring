package com.oracle.oBootMybatis03.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis03.model.Dept;

@Repository
public class DeptDaoImpl implements DeptDao {
	@Autowired	// 필드 연결
	private SqlSession session;

	@Override
	public List<Dept> deptSelect() {
		System.out.println("EmpDaoImpl deptSelect start...");
		List<Dept> deptList = null;
		try {
			deptList = session.selectList("TKselectDept");
		} catch (Exception e) {
			System.out.println("EmpDaoImpl deptSelect Exception->" + e.getMessage());
		}
		return deptList;
	}

}
