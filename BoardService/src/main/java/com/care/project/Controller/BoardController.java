package com.care.project.Controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.care.project.dto.BoardDTO;
import com.care.project.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired HttpSession session;
	@Autowired BoardService service;
	
	@GetMapping(value = "board/boardForm")
	public String board(Model model) {
		String id = (String)session.getAttribute("id");
		if(id == null || id.isEmpty())
			return "redirect:/member/login";
		ArrayList<BoardDTO> boardList = new ArrayList<BoardDTO>();
		boardList = service.boardList();
		model.addAttribute("boardList",boardList);
		
		return "board/boardForm";
	}

}
