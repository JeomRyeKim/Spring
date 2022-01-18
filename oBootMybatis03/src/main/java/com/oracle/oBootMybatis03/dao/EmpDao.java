package com.oracle.oBootMybatis03.dao;

import java.util.List;

import com.oracle.oBootMybatis03.model.Emp;

public interface EmpDao {
	int 	  total();

	List<Emp> listEmp(Emp emp);

	Emp detail(int empno);

	int update(Emp emp);

	List<Emp> listManager();

}
