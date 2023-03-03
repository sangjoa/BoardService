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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.project.dto.BoardDTO;
import com.care.project.dto.MemberDTO;
import com.care.project.repository.BoardRepository;
import com.care.project.repository.MemberRepository;
import com.care.project.service.BoardService;

@Controller
public class BoardController {
	
	@Autowired HttpSession session;
	@Autowired BoardService service;
	@Autowired MemberRepository mrepository;
	@Autowired BoardRepository repository;
	
	
	
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
	
	
	@RequestMapping("board/viewForm")
	public String boardView(Model model, String bI) {
		
		BoardDTO board = service.view(bI);
		MemberDTO member = mrepository.findByNick(board.getWriter());
				
		if(board == null)
			return "board/boardForm";
		
		model.addAttribute("member",member);
		model.addAttribute("board",board);
		service.vCountInc(bI);
		return "board/viewForm";
	}
	
	@GetMapping("board/deleteForm")
	public String boardDelete(Model model, String bI, String fName) {
		if(loginCheck())
			return "redirect:/member/login";
		model.addAttribute("bI",bI);
		model.addAttribute("fName",fName);
		
		return "board/deleteForm";
	}
	
	@PostMapping("board/deleteForm")
	public String boardDeleteProc(String pw, String confirm, String fName, String bI, RedirectAttributes ra) {
		if(loginCheck())
			return "redirect:/member/login";
		
		String msg = service.boardDelete(pw,confirm,fName,bI);
		if(msg.equals("게시글 삭제 완료")) {
			return "redirect:board";
		}
		
		ra.addFlashAttribute("msg",msg);
		
		return "board/deleteForm?bI="+bI+"fName="+fName;
	}
	
	@GetMapping("board/updateForm")
	public String boardUpdate(Model model, String bI) {
		if(loginCheck())
			return "redirect:/member/login";
		BoardDTO board = repository.boardView(Integer.parseInt(bI));
		
		if(board == null)
			return "board/boardForm";
		
		model.addAttribute("board",board);
		
		return "board/updateForm";
		
	}
	
	@PostMapping("board/updateForm")
	public String boardUpdateProc(String bI,BoardDTO board, RedirectAttributes ra) {
		if(loginCheck())
			return "redirect:/member/login";
		
		board.setBoardId(Integer.parseInt(bI));
		
		String msg = service.boardUpdate(board);
		if(msg.equals("게시글 수정 성공"))
			return "redirect:/board/boardForm";
		
		ra.addAttribute("msg",msg);
		
		return "board/updateForm?bI="+board.getBoardId();
	}
	
	public boolean loginCheck() {
		String id = (String)session.getAttribute("id");
		
		if(id == null)
			return true;
		
		return false;
	}
	
	
	

}
