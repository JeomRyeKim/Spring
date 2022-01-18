package com.oracle.oBootMybatis03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootMybatis03.dao.EmpDao;
import com.oracle.oBootMybatis03.model.Emp;

@Service
public class EmpServiceImpl implements EmpService {
	@Autowired
	private EmpDao ed;
	
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

}
