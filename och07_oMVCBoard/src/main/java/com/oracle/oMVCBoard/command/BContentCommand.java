package com.oracle.oMVCBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.oracle.oMVCBoard.dao.BDao;
import com.oracle.oMVCBoard.dto.BDto;

public class BContentCommand implements BCommand {

	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap(); //asMap() : model에 있는걸 map으로 꺼내기 위해 사용
		HttpServletRequest request = (HttpServletRequest) map.get("request"); //map에 키를 주면 값이 request에 들어감
		String bId = request.getParameter("bId");
		BDao dao = new BDao();
		BDto board = dao.contentView(bId);
		
		model.addAttribute("mvc_board", board);

	}

}
