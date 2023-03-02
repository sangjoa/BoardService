package com.care.project.service;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.project.dto.MemberDTO;
import com.care.project.repository.MemberRepository;

@Service
public class MemberService {

	@Autowired MemberRepository repository;
	@Autowired HttpSession session;
	
	
	public String login(MemberDTO member) {
		if (member.getId() == "" || member.getId() == null) {
			return "아이디를 확인해주세요.";
		}
		if (member.getPw() == "") {
			return "패스워드를 확인해주세요.";
		}
		System.out.println(member.getId());
		MemberDTO chk = repository.findById(member.getId());

		if (chk != null && chk.getPw().equals(member.getPw())) {
			session.setAttribute("id", chk.getId());
			session.setAttribute("name", chk.getUserName());
			session.setAttribute("email", chk.getEmail());
			session.setAttribute("mobile", chk.getMobile());
			return "로그인 성공";
		}
		return "아이디/패스워드를 확인해주세요";
	}


	public String register(MemberDTO member, String confirm) {

		MemberDTO chk = repository.findById(member.getId());

		if (chk == null) {
			repository.register(member);
			return "회원 가입 성공";
		}

		return member.getId() + "는 이미 존재하는 아이디 입니다.";
	}


	public MemberDTO userInfo(String id) {
		return repository.findById(id);
	}

	public String update(MemberDTO member, String confirm) {
		String id = (String) session.getAttribute("id");				
		MemberDTO chk = repository.findById(member.getId());
	
		repository.userUpdate(member);
		return "회원 정보 수정 완료";
	}


	public String delete(String pw, String confirm) {
		if (pw == null || pw.isEmpty())
			return "비밀번호를 확인하세요";
		if (confirm == null || confirm.isEmpty())
			return "비밀번호 확인은 필수 입니다.";
		if (pw.equals(confirm) == false)
			return "비빌번호가 일치하지 않습니다.";
		String id = (String) session.getAttribute("id");
		
		MemberDTO chk = repository.findById(id);
		
		if (chk != null && chk.getPw().equals(pw)) {
			return "회원 삭제 성공";
		}
		return "비밀번호를 확인해주세요";
	}
	
}
