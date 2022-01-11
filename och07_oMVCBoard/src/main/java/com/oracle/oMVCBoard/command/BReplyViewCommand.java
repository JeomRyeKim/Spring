package com.oracle.oMVCBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.oracle.oMVCBoard.dao.BDao;
import com.oracle.oMVCBoard.dto.BDto;

public class BReplyViewCommand implements BCommand {

	@Override
	public void execute(Model model) {
		// 1. Map 선언
		Map<String, Object> map = model.asMap();
		// 2. Map -> bid Get
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		String bId = request.getParameter("bId");
		// 3. Dao 선언
		BDao dao = new BDao();
		// 4. BDto dto = dao.reply_view(bId);
		BDto dto = dao.reply_view(bId);
		model.addAttribute("reply_view", dto);
	}

}
