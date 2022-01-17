package com.oracle.oBootMybatis03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oracle.oBootMybatis03.dao.EmpDao;

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

}
