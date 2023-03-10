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
			return "아이디를 입력해주세요";
		
		if(chk == false)
			return "아이디를 올바르게 입력해주세요";
		
		MemberDTO member = mRepository.findById(id);
		
		if(member == null)
			return "사용 가능한 아이디 입니다";
		else
			return "이미 존재하는 아이디 입니다";
	}
	
	@ResponseBody
	@PostMapping(value="ajax/nameChk",produces = "text/plain;charset=UTF-8")
	public String nameChk(@RequestBody String uName) {
		
		if(uName.matches("[가-힣]{2,5}") == false)
			return "이름을 올바르게 입력해주세요";
		
		return "";
	}
	
	@ResponseBody
	@PostMapping(value="ajax/nickChk",produces = "text/plain;charset=UTF-8")
	public String nickChk(@RequestBody String nName) {
		
		if(nName.matches("[가-힣a-zA-Z0-9]{2,10}") == false)
			return "사용 불가능한 닉네임 입니다";
		
		MemberDTO member = mRepository.findByNick(nName);
		
		if(member == null)
			return "사용 가능한 닉네임 입니다";
		
		return "이미 사용중인 닉네임 입니다";
	}
	
	
	@ResponseBody
	@PostMapping(value="ajax/emailChk", produces = "text/plain;charset=UTF-8")
	public String emailChk(@RequestBody String email) {
		return eService.setting(email);
	}
	
	
	
}
