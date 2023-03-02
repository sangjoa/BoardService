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
			return "¾ÆÀÌµð¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä";
		
		if(chk == false)
			return "¾ÆÀÌµð¸¦ ¿Ã¹Ù¸£°Ô ÀÔ·ÂÇØÁÖ¼¼¿ä";
		
		MemberDTO member = mRepository.findById(id);
		
		if(member == null)
			return "»ç¿ë °¡´ÉÇÑ ¾ÆÀÌµð ÀÔ´Ï´Ù";
		else
			return "ÀÌ¹Ì Á¸ÀçÇÏ´Â ¾ÆÀÌµð ÀÔ´Ï´Ù";
	}
	
	@ResponseBody
	@PostMapping(value="ajax/nameChk",produces = "text/plain;charset=UTF-8")
	public String nameChk(@RequestBody String uName) {
		
		if(uName.matches("[°¡-ÆR]{2,5}") == false)
			return "ÀÌ¸§À» ¿Ã¹Ù¸£°Ô ÀÔ·ÂÇØÁÖ¼¼¿ä";
		
		return "";
	}
	
	@ResponseBody
	@PostMapping(value="ajax/nickChk",produces = "text/plain;charset=UTF-8")
	public String nickChk(@RequestBody String nName) {
		
		if(nName.matches("[°¡-ÆRa-zA-Z0-9]{2,10}") == false)
			return "»ç¿ë ºÒ°¡´ÉÇÑ ´Ð³×ÀÓ ÀÔ´Ï´Ù";
		
		MemberDTO member = mRepository.findByNick(nName);
		
		if(member == null)
			return "»ç¿ë °¡´ÉÇÑ ´Ð³×ÀÓ ÀÔ´Ï´Ù";
		
		return "ÀÌ¹Ì »ç¿ëÁßÀÎ ´Ð³×ÀÓ ÀÔ´Ï´Ù";
	}
	
	
	@ResponseBody
	@PostMapping(value="ajax/emailChk", produces = "text/plain;charset=UTF-8")
	public String emailChk(@RequestBody String email) {
		return eService.setting(email);
	}
	
	
	
}
