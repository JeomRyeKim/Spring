package com.oracle.oBootMybatis03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.oBootMybatis03.model.Dept;
import com.oracle.oBootMybatis03.model.Emp;
import com.oracle.oBootMybatis03.service.EmpService;
import com.oracle.oBootMybatis03.service.Paging;

@Controller
public class EmpController {
	@Autowired
	private EmpService es;
	
	@RequestMapping(value = "list")
	public String list(Emp emp, String currentPage, Model model) {
		System.out.println("EmpController list Start...");
		int total = es.total();	//Emp count -> 14
		System.out.println("EmpController list total->" + total);
		System.out.println("EmpController currentPage->" + currentPage);
		//						42	  NULL(0,1...) 2
		Paging pg = new Paging(total, currentPage);
		emp.setStart(pg.getStart());	// 시작시 1
		emp.setEnd(pg.getEnd());		// 시작시 10
		List<Emp> listEmp = es.listEmp(emp);
		System.out.println("EmpController list listEmp.size()->" + listEmp.size());
		model.addAttribute("listEmp", listEmp);
		model.addAttribute("pg", pg);
		model.addAttribute("total", total);
		
		return "list";
	}
	
	@GetMapping(value = "detail")
	public String detail(int empno, Model model) { // pk인 empno로 쿼리문에서 select하고 리턴값으론 Emp 정보 전부를 받음
		// Emp emp = es.detail(emp)
		// Dao -> 	 ed.detail(emp)
		// mapper -> tkEmpSelOne , empno
		System.out.println("EmpController detail start...");
		Emp emp = es.detail(empno);
		model.addAttribute("emp", emp);
		
		return "detail";
	}
	
	@GetMapping(value = "updateForm")
	public String updateForm(int empno, Model model) {
		System.out.println("EmpController updateForm start...");
		Emp emp = es.detail(empno);
		model.addAttribute("emp", emp);
		
		return "updateForm";
	}
	
	@PostMapping(value = "update")
	public String update(Emp emp, Model model) {
		//int k = es.update(emp);
	    //Dao -> kkk = session.update("TKempUpdate",emp);
	    //mapper -> TKempUpdate
		System.out.println("EmpController update start...");
		int uptCnt = es.update(emp);
		System.out.println("EmpController update Count->" + uptCnt);
		model.addAttribute("uptCnt", uptCnt);				//Test Controller간 Data 전달
		model.addAttribute("kk3", "Message Test");			//Test Controller간 Data 전달
		
		return "forward:list";		// uptCnt 값을 list.jsp에 나타냄
		//return "redirect:list";	- uptCnt 값을 list.jsp에 못 나타냄
	}
	
	@RequestMapping(value = "writeForm")
	public String writeForm(Model model) {
		//Emp emp = null;
		// 관리자 사번만 Get
		List<Emp> empList = es.listManager();
		System.out.println("EmpController writeForm emplist.size()->" + empList.size());
		model.addAttribute("empMngList", empList);	// emp Manager List
//		List<Dept> deptList = es.deptSelect();
//		model.addAttribute("deptList", deptList);	// dept
		
		return "writeForm";
	}
}
