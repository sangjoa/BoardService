package com.care.project.Controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.project.dto.MemberDTO;
import com.care.project.repository.MemberRepository;
import com.care.project.service.MemberService;

@Controller
public class MemberController {
	
	
	@Autowired private MemberService service;
	@Autowired HttpSession session;
	@Autowired MemberRepository repository;
	
	@RequestMapping("default/header")
	public String header() {
		return "default/header";
	}
	
	@RequestMapping("default/main")
	public String main() {
		return "default/main";
	}
	
	@RequestMapping("default/footer")
	public String footer() {
		return "default/footer";
	}
	
	@GetMapping("member/index")
	public String index() {
		return "member/index";
	}
	
	@GetMapping("member/login")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("member/login")
	public String login(MemberDTO member) {
		String msg = service.login(member);
		if(msg.equals("로그인 성공")) {
			session.setAttribute("id", member.getId());
			return "member/index";
		}
		return "member/login";
	}

	@GetMapping("member/register")
	public String register() {
		return "member/register";
	}
	
	@PostMapping(value = "member/register")
	public String registerProc(MemberDTO member, String confirm, Model model) {
		String msg = service.register(member,confirm);
		model.addAttribute("msg",msg);
		if(msg.equals("회원 가입 성공"))
			return "redirect:index";
		return "member/register";
	}
	
	@GetMapping("member/logout")
	public String logout() {
		session.invalidate();
		return "redirect:index";
	}


	@GetMapping("member/userInfo")
	public String userInfo(Model model) {
		
		String sessionId = (String)session.getAttribute("id");
		
		if(sessionId == null)
			return "redirect:/member/login";
		
		model.addAttribute("member",repository.findById(sessionId));
		
		return "member/userInfo";
	}
	
	
	@GetMapping("member/delete")
	public String delete() {
		if(session.getAttribute("id") == null)
			return "member/login";
		return "member/userDelete";
	}
	
	@PostMapping("member/userDelete")
	public String delete(MemberDTO member, String confirm, Model model) {
		String id = (String)session.getAttribute("id");
		String msg = service.delete(member.getPw(), confirm);
		member.setId(id);
		
		model.addAttribute("msg",msg);
		if(msg.equals("회원 삭제 성공")) {
			repository.userDelete(id);
			session.invalidate();
			return "redirect:/member/index";
		}
		return "model/userDelete";
	}
	
	@GetMapping("member/userUpdate")
	public String update(String id, Model model) {
		String sessionId = (String)session.getAttribute("id");
		if(sessionId == null)
			return "redirect:login";
		if(sessionId.equals(id)) {
			model.addAttribute("member",service.userInfo(id));
			return "member/userUpdate";			
		}
		return "forward:userInfo";
	}
	
	@PostMapping("member/userUpdate")
	public String update(MemberDTO member,String confirm,RedirectAttributes ra) {
		String sessionId = (String)session.getAttribute("id");
		member.setId(sessionId);
		String msg = service.update(member,confirm);
		ra.addFlashAttribute("msg",msg);
		if(msg.equals("회원 정보 수정 완료"))
			return "redirect:userInfo";
		return "redirect:userUpdate?id="+sessionId;
	}
	
	

}
