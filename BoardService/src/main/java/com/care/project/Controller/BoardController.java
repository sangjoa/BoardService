package com.care.project.Controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	
	
	@GetMapping("board/writeForm")
	public String write() {
		return "board/writeForm";
	}
	
	@PostMapping("board/writeForm")
	public String writeProc(MultipartHttpServletRequest multi, Model model) {
		String msg = service.write(multi);
		model.addAttribute("msg",msg);
		if(msg.equals("게시글 작성 완료"))
			return "redirect:boardForm";
		if(msg.equals("로그인 이후 이용 가능한 서비스입니다."))
			return "member/login";
		return "board/writeForm";
	}
	
	
	
	public boolean loginCheck() {
		String id = (String)session.getAttribute("id");
		
		if(id == null)
			return true;
		
		return false;
	}

}
