package com.oracle.oMVCBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.oracle.oMVCBoard.dao.BDao;

public class BModifyCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// 1. Map 정의
		Map<String, Object> map = model.asMap();
		// 2. Map으로부터 String bId, bName, bTitle, bContent
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String bId = request.getParameter("bId");
		String bName = request.getParameter("bName");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		// 3. DAO 선언
		BDao dao = new BDao();
		// 4. dao.modify(bId, bName, bTitle, bContent);
		dao.modify(bId, bName, bTitle, bContent);
		// 생성자를 사용하는 것도 아니고 새로 글을 쓰는것도 아니니 model.addAttribute("mvc_board", board);가 필요없음
		// DB에 수정처리를 하면 list에서 수정된 글들을 가져오기만 할 뿐임
	}

}
