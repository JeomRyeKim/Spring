package com.oracle.oMVCBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.oracle.oMVCBoard.dao.BDao;

public class BDeleteCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// 1. Map 선언
		Map<String, Object> map = model.asMap();
		// 2.  Map -> bId추출
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String bId = request.getParameter("bId");
		// 3. DAO선언
		BDao dao = new BDao();
		// 4. dao.delete(bId);
		dao.delete(bId);


	}

}
