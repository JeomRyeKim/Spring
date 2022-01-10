package com.oracle.oMVCBoard.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.oracle.oMVCBoard.dao.BDao;
import com.oracle.oMVCBoard.dto.BDto;

public class BListCommand implements BCommand {

	@Override
	public void execute(Model model) {
		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.list();
		System.out.println("BListCommand dtos.size() -->" + dtos.size());
		model.addAttribute("list", dtos);
		
	}

}
