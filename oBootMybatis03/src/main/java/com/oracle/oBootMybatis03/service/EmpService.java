package com.oracle.oBootMybatis03.service;

import java.util.List;

import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.Emp;
import com.oracle.oBootMybatis03.model.EmpDept;

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
}
