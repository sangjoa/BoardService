package com.care.project.repository;

import org.springframework.stereotype.Repository;

import com.care.project.dto.MemberDTO;

@Repository
public interface MemberRepository {

	public MemberDTO findById(String id);
	public MemberDTO findByNick(String nName);
	public void register(MemberDTO member);
	public void userUpdate(MemberDTO member);
	public void userDelete(String id);

}
