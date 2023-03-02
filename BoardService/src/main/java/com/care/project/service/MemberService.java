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
			return "���̵� Ȯ�����ּ���.";
		}
		if (member.getPw() == "") {
			return "�н����带 Ȯ�����ּ���.";
		}
		System.out.println(member.getId());
		MemberDTO chk = repository.findById(member.getId());

		if (chk != null && chk.getPw().equals(member.getPw())) {
			session.setAttribute("id", chk.getId());
			session.setAttribute("name", chk.getUserName());
			session.setAttribute("email", chk.getEmail());
			session.setAttribute("mobile", chk.getMobile());
			return "�α��� ����";
		}
		return "���̵�/�н����带 Ȯ�����ּ���";
	}


	public String register(MemberDTO member, String confirm) {

		MemberDTO chk = repository.findById(member.getId());

		if (chk == null) {
			repository.register(member);
			return "ȸ�� ���� ����";
		}

		return member.getId() + "�� �̹� �����ϴ� ���̵� �Դϴ�.";
	}


	public MemberDTO userInfo(String id) {
		return repository.findById(id);
	}

	public String update(MemberDTO member, String confirm) {
		String id = (String) session.getAttribute("id");				
		MemberDTO chk = repository.findById(member.getId());
	
		repository.userUpdate(member);
		return "ȸ�� ���� ���� �Ϸ�";
	}


	public String delete(String pw, String confirm) {
		if (pw == null || pw.isEmpty())
			return "��й�ȣ�� Ȯ���ϼ���";
		if (confirm == null || confirm.isEmpty())
			return "��й�ȣ Ȯ���� �ʼ� �Դϴ�.";
		if (pw.equals(confirm) == false)
			return "�����ȣ�� ��ġ���� �ʽ��ϴ�.";
		String id = (String) session.getAttribute("id");
		
		MemberDTO chk = repository.findById(id);
		
		if (chk != null && chk.getPw().equals(pw)) {
			return "ȸ�� ���� ����";
		}
		return "��й�ȣ�� Ȯ�����ּ���";
	}
	
}
