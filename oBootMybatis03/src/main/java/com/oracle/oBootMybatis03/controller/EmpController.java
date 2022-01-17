package com.oracle.oBootMybatis03.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oracle.oBootMybatis03.model.Emp;
import com.oracle.oBootMybatis03.service.EmpService;

@Controller
public class EmpController {
	@Autowired
	private EmpService es;
	
	@RequestMapping(value = "list")
	public String list(Emp emp, String currentPage, Model model) {
		System.out.println("EmpController list Start...");
		int total = es.total();	//Emp count -> 14
		System.out.println("EmpController list total->" + total);
		model.addAttribute("total", total);
		
		return "list";
	}
}
