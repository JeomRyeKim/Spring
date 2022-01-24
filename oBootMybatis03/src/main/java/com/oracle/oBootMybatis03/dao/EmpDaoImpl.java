package com.oracle.oBootMybatis03.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.oracle.oBootMybatis03.model.Emp;
import com.oracle.oBootMybatis03.model.EmpDept;

@Repository
public class EmpDaoImpl implements EmpDao {
	@Autowired	// 필드 연결
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

	@Override
	public List<Emp> listEmp(Emp emp) {
		List<Emp> empList = null;
		System.out.println("EmpDaoImpl listEmp start...");
		try {
			// Naming Rule					Map ID		  parameter
			//empList = session.selectList("tkEmpListAll3", emp); // row가 여러건
			empList = session.selectList("tkEmpListAll", emp); // row가 여러건
		} catch (Exception e) {
			System.out.println("EmpDaoImpl listEmp Exception->" + e.getMessage());
		}
		return empList;
	}

	@Override
	public Emp detail(int empno) {
		System.out.println("EmpDaoImpl detail start...");
		Emp emp = null;
		try {
			//						 mapper ID	   parameter
			emp = session.selectOne("tkEmpSelOne", empno); // 넘겨줄 데이터가 1개일 때는 이렇게 넘겨주고 2개 이상이면 DTO로 넘겨주기
			System.out.println("EmpDaoImpl detail emp.getEname()->" + emp.getEname());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl detail Exception->" + e.getMessage());
		}
		return emp;
	}

	@Override
	public int update(Emp emp) {
		System.out.println("EmpDaoImpl update start...");
		int kkk = 0;
		try {
			kkk = session.update("TKempUpdate",emp);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl update Exception->" + e.getMessage());
		}
		return kkk;
	}

	@Override
	public List<Emp> listManager() {
		System.out.println("EmpDaoImpl listManager start...");
		List<Emp> empList = null;
		try {
			// emp 관리자만 Select			  Naming Rule								
			empList = session.selectList("tkSelectManager");
		} catch (Exception e) {
			System.out.println("EmpDaoImpl listManager Exception->" + e.getMessage());
		}
		return empList;
	}

	@Override
	public int insert(Emp emp) {
		System.out.println("EmpDaoImpl insert start...");
		int result = 0;
		try {
			result = session.insert("insertEmp", emp);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl insert Exception->" + e.getMessage());
		}
		return result;
	}

	@Override
	public int delete(int empno) { // 실제로 프로젝트때는 완전 지우면 안되고 다른 테이블에 저장해놔야함
		System.out.println("EmpDaoImpl delete start...");
		int result = 0;
		try {
			result = session.delete("deleteEmp", empno);
			System.out.println("EmpDaoImpl delete result->" + result);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl delete Exception->" + e.getMessage());
		}
		return result;
	}

	@Override
	public List<EmpDept> listEmpDept() {
		System.out.println("EmpDaoImpl listEmpDept start...");
		List<EmpDept> empDept = null;
		try {
			empDept = session.selectList("TKlistEmpDept");
			System.out.println("EmpDaoImpl listEmpDept empDept.size()->" + empDept.size());
		} catch (Exception e) {
			System.out.println("EmpDaoImpl listEmpDept Exception->" + e.getMessage());
		}
		return empDept;
	}

	@Override
	public List<EmpDept> listEmp(EmpDept empDept) {
		System.out.println("EmpDaoImpl listEmp start");
		
		return session.selectList("TKlistEmpDept", empDept); // 전에 쓴 mapper 재활용
	}

	@Override
	public String deptName(int deptno) {
		System.out.println("EmpDaoImpl deptName start");
		String resultStr = "";
		try {
			resultStr = session.selectOne("TKdeptName", deptno);
			System.out.println("EmpDaoImpl deptName resultStr-> " + resultStr);
		} catch (Exception e) {
			System.out.println("EmpDaoImpl deptName Exception->" + e.getMessage());
		}
		return resultStr;
	}




}
