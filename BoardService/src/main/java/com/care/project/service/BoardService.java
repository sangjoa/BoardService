package com.care.project.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.project.dto.BoardDTO;
import com.care.project.dto.MemberDTO;
import com.care.project.repository.BoardRepository;
import com.care.project.repository.MemberRepository;

@Service
public class BoardService {
	
	@Autowired HttpSession session;
	@Autowired BoardRepository repository;
	@Autowired MemberRepository mrepository;
	
	
	public void boardList(Model model, int currentPage, String search, String select, HttpServletRequest req) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("select", select);
				
		int totalCount = repository.boardCount(map);
		
		System.out.println();
		
		int pageBlock = 5;
		int end = currentPage * pageBlock;
		int begin = end+1 - pageBlock;

		List<BoardDTO> boardList = repository.boardList(begin, end, select, search);
		model.addAttribute("boardList", boardList);

		String url = "/board/boardForm?";
		if(select != null) {
			url+="select="+select+"&";
			url+="search="+search+"&";
		}
			url+="currentPage=";
		model.addAttribute("page", PageService.getNavi(currentPage, pageBlock, totalCount, url));
	}
	
	


	public String write(MultipartHttpServletRequest multi) {
		String writer = multi.getParameter("writer");
		String title = multi.getParameter("title");
		String content = multi.getParameter("content");
		MultipartFile mFile = multi.getFile("fName");
		
		String id = (String)session.getAttribute("id");
		
		
		if(writer == null || writer.isEmpty())
			return "작성자를 입력하세요";
		if(title == null || title.isEmpty())
			return "제목을 입력하세요";
		
		BoardDTO board = new BoardDTO();
		board.setWriter(writer);
		board.setTitle(title);
		board.setContent(content);
		board.setvCount(0);
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		board.setcDate(sdf.format(date));
		board.setmDate(sdf.format(date));
		String fileName = "";
		
		if(mFile != null && mFile.getSize() != 0) {
			fileName = mFile.getOriginalFilename();
			String path = "C:\\springs15\\upload\\" + writer;
			File file = new File(path);
			if(file.exists() == false)
				file.mkdir();
			
			sdf = new SimpleDateFormat("yyyyMMddHHmmss-");
			Calendar cal = Calendar.getInstance();
			fileName = sdf.format(cal.getTime()) + fileName;
			
			path = path + "\\" + fileName;
			file = new File(path);
			
			try {
				mFile.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		board.setfName(fileName);
		repository.write(board);
		return "게시글 작성 완료";
	}




	public BoardDTO view(String boardId) {
		
		if(boardId == null || boardId.isEmpty())
			return null;
		int bI = 0;
		
		try {
			bI = Integer.parseInt(boardId);
		} catch (Exception e) {
			return null;
		}
		
		return repository.boardView(bI);
	}
	
	public void vCountInc(String bn) {
		repository.vCountInc(Integer.parseInt(bn));
	}




	public String boardDelete(String pw, String confirm, String fName, String bI) {
		
		BoardDTO board = repository.boardView(Integer.parseInt(bI));
		MemberDTO chk = mrepository.findByNick(board.getWriter());
		
		if(chk.getPw().equals(pw) == false)
			return "비밀번호가 일치하지 않습니다";
		if(pw == null || pw.isEmpty())
			return "비밀번호를 입력해주세요";
		if(confirm == null || confirm.isEmpty())
			return "비밀번호를 입력해주세요";
		if(pw.equals(confirm) == false)
			return "비밀번호가 일치하지 않습니다";
		
		
		if(chk != null && chk.getPw().equals(pw)) {
			repository.boardDelete(Integer.parseInt(bI));
			
			String path = "C:\\Users\\dhdlt\\Desktop\\Springs15\\upload" + board.getWriter();
			path = path + "\\" + fName;
			File file = new File(path);
			
			if(file.exists()) {
				file.delete();
			}			
			return "게시글 삭제 완료";
		}
		
		return "비밀번호를 확인해주세요";
	}


	public String boardUpdate(BoardDTO board) {
		if(board.getTitle() == null || board.getTitle().isEmpty())
			return "제목을 입력해주세요";
		String id = (String)session.getAttribute("id");
		
		MemberDTO member = mrepository.findById(id);
		
		int rowCount = repository.boardUpdate(board);
		if(rowCount == 0)
			return "게시글 수정 실패";
		
		return "게시글 수정 성공";
	}	

	
	
	
}
