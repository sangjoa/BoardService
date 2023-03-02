package com.care.project.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.care.project.dto.MemberDTO;
import com.care.project.repository.MemberRepository;
import com.care.project.service.emailService;

@Controller
public class AjaxController {

	@Autowired private MemberRepository mRepository;
	@Autowired private emailService eService;
	
	
	@ResponseBody
	@PostMapping(value="ajax/idChk", produces = "text/plain;charset=UTF-8")
	public String idChk(@RequestBody String id) {
		
		boolean chk =id.matches("[a-zA-Z0-9_-]{5,20}");
		
		if(id== "" || id == null || id.isEmpty())
			return "���̵� �Է����ּ���";
		
		if(chk == false)
			return "���̵� �ùٸ��� �Է����ּ���";
		
		MemberDTO member = mRepository.findById(id);
		
		if(member == null)
			return "��� ������ ���̵� �Դϴ�";
		else
			return "�̹� �����ϴ� ���̵� �Դϴ�";
	}
	
	@ResponseBody
	@PostMapping(value="ajax/nameChk",produces = "text/plain;charset=UTF-8")
	public String nameChk(@RequestBody String uName) {
		
		if(uName.matches("[��-�R]{2,5}") == false)
			return "�̸��� �ùٸ��� �Է����ּ���";
		
		return "";
	}
	
	@ResponseBody
	@PostMapping(value="ajax/nickChk",produces = "text/plain;charset=UTF-8")
	public String nickChk(@RequestBody String nName) {
		
		if(nName.matches("[��-�Ra-zA-Z0-9]{2,10}") == false)
			return "��� �Ұ����� �г��� �Դϴ�";
		
		MemberDTO member = mRepository.findByNick(nName);
		
		if(member == null)
			return "��� ������ �г��� �Դϴ�";
		
		return "�̹� ������� �г��� �Դϴ�";
	}
	
	
	@ResponseBody
	@PostMapping(value="ajax/emailChk", produces = "text/plain;charset=UTF-8")
	public String emailChk(@RequestBody String email) {
		return eService.setting(email);
	}
	
	
	
}
