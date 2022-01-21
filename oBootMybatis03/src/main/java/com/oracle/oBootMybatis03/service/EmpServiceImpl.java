package com.oracle.oBootMybatis03.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootMybatis03.dao.DeptDao;
import com.oracle.oBootMybatis03.dao.EmpDao;
import com.oracle.oBootMybatis03.dao.Member1Dao;
import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.DeptVO;
import com.oracle.oBootMybatis03.model.Emp;
import com.oracle.oBootMybatis03.model.EmpDept;
import com.oracle.oBootMybatis03.model.Member3;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired	// 필드 연결
	private EmpDao ed;
	@Autowired	// 필드 연결
	private DeptDao dd;
	@Autowired
	private Member1Dao md;
	
	@Override
	public int total() {
		System.out.println("EmpServiceImpl Start total...");
		int totCnt = ed.total();
		System.out.println("EmpServiceImpl total totCnt->" + totCnt);
		return totCnt;
	}

	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		System.out.println("EmpServiceImpl listEmp start...");
		empList = ed.listEmp(emp);
		System.out.println("EmpServiceImpl listEmp empList.size()->" + empList.size());
		return empList;
	}

	@Override
	public Emp detail(int empno) {
		System.out.println("EmpServiceImpl detail start...");
		Emp emp = ed.detail(empno);
		return emp;
	}

	@Override
	public int update(Emp emp) {
		System.out.println("EmpServiceImpl update start...");
		int kkk = ed.update(emp);
		
		return kkk;
	}

	@Override
	public List<Emp> listManager() {
		System.out.println("EmpServiceImpl listManager start...");
		List<Emp> empList = ed.listManager();
		System.out.println("EmpServiceImpl listManager empList.size()->" + empList.size());
		
		return empList;
	}

	@Override
	public List<Dept> deptSelect() {
		System.out.println("EmpServiceImpl deptSelect start...");
		List<Dept> deptList = dd.deptSelect();
		System.out.println("EmpServiceImpl deptSelect deptList.size()->" + deptList.size());
		
		return deptList;
	}

	@Override
	public int insert(Emp emp) {
		System.out.println("EmpServiceImpl insert start...");
		int result = ed.insert(emp);
		
		return result;
	}

	@Override
	public int delete(int empno) {
		System.out.println("EmpServiceImpl delete start...");
		int result = ed.delete(empno);
		
		return result;
	}

	@Override
	public List<EmpDept> listEmpDept() {
		System.out.println("EmpServiceImpl listEmpDept start...");
		List<EmpDept> listEmpDept = ed.listEmpDept();
		System.out.println("EmpServiceImpl listEmpDept listEmpDept.size()->" + listEmpDept.size());
		
		return listEmpDept;
	}

	@Override
	public void insertDept(DeptVO deptVO) {
		System.out.println("EmpServiceImpl insertDept start...");
		dd.insertDept(deptVO);
	}

	@Override
	public void selListDept(HashMap<String, Object> map) {
		System.out.println("EmpServiceImpl selListDept start...");
		dd.selListDept(map);
	}

	@Override
	public int memCount(String id) {
		System.out.println("EmpServiceImpl memCount start");
		System.out.println("EmpServiceImpl memCount id->" + id);
		
		return md.memCount(id);
	}

	@Override
	public List<Member3> listMem(Member3 member3) {
		System.out.println("EmpServiceImpl listMem start");
		
		return md.listMem(member3);
	}

	@Override
	public List<EmpDept> listEmp(EmpDept empDept) {
		System.out.println("EmpServiceImpl listEmp start");
		
		return ed.listEmp(empDept);
	}

}
