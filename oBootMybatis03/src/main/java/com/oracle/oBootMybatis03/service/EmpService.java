package com.oracle.oBootMybatis03.service;

import java.util.HashMap;
import java.util.List;

import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.DeptVO;
import com.oracle.oBootMybatis03.model.Emp;
import com.oracle.oBootMybatis03.model.EmpDept;
import com.oracle.oBootMybatis03.model.Member3;

public interface EmpService {
	int 	  total();
	
	List<Emp> listEmp(Emp emp);

	Emp detail(int empno);

	int update(Emp emp);

	List<Emp> listManager();

	List<Dept> deptSelect();

	int insert(Emp emp);

	int delete(int empno);

	List<EmpDept> listEmpDept();

	void insertDept(DeptVO deptVO);

	void selListDept(HashMap<String, Object> map);

	int memCount(String id);

	List<Member3> listMem(Member3 member3);

	List<EmpDept> listEmp(EmpDept empDept); // 오버로딩(다형성)

	String deptName(int deptno);
}
