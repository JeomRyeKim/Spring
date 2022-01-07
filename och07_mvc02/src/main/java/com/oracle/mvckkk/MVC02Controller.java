package com.oracle.mvckkk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MVC02Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(MVC02Controller.class); //클래스 명과 맞춰줘야함!

	@RequestMapping(value = "/board/view2") //RequestMapping : HandlerMapping
	public String view() {
		logger.info("MVC02Controller Logger board/view2 Start..."); // INFO : com.oracle.mvc02.HomeController - Logger board/view Start...
		System.out.println("MVC02Controller sysout board/view2");   // sysout board/view
		
		return "board/view2";
	}
}
