package com.oracle.oBootMybatis03.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.DeptVO;

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

	@Override
	public void insertDept(DeptVO deptVO) {
		System.out.println("EmpDaoImpl insertDept start...");
		try {
			session.selectOne("ProcDept", deptVO);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl insertDept Exception->" + e.getMessage());
			System.out.println("EmpDaoImpl insertDept Exception->" + e.getMessage());
		}
	}

	@Override
	public void selListDept(HashMap<String, Object> map) {
		System.out.println("EmpDaoImpl selListDept start...");
		try {
			session.selectOne("ProcDeptList", map);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl selListDept Exception->" + e.getMessage());
		}
	}

}
