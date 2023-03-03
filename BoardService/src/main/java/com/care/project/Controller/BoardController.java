package com.care.project.Controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.project.dto.MemberDTO;
import com.care.project.repository.MemberRepository;
import com.care.project.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired HttpSession session;
	@Autowired BoardService service;
	@Autowired MemberRepository mrepository;
	
	
	
	@RequestMapping("board/boardForm")
	public String boardList(Model model, @RequestParam(value="currentPage", required = false, defaultValue = "1")int currentPage,
			String search, String select, HttpServletRequest req ) {
		
		ArrayList<MemberDTO> memberList = mrepository.memberList();
		model.addAttribute("memberList",memberList);
		
		service.boardList(model, currentPage, search, select, req);
		
		return "board/boardForm";
	}
	
	@GetMapping("board/writeForm")
	public String write(Model model) {
		if(loginCheck())
			return "redirect:member/login";
		
		String id = (String)session.getAttribute("id");
		MemberDTO member = mrepository.findById(id);
		model.addAttribute("member",member);
		
		return "board/writeForm";
	}
	
	@PostMapping("board/writeForm")
	public String writeProc(MultipartHttpServletRequest multi, Model model) {
		
		if(loginCheck())
			return "redirect:member/login";
		
		String msg = service.write(multi);
		
		if(msg.equals("게시글 작성 완료"))
			return "redirect:boardForm";
		model.addAttribute("msg",msg);
		
		return "board/writeForm";
	}
	
	
	
	public boolean loginCheck() {
		String id = (String)session.getAttribute("id");
		
		if(id == null)
			return true;
		
		return false;
	}

}
