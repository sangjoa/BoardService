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
import com.care.project.repository.BoardRepository;

@Service
public class BoardService {
	
	@Autowired HttpSession session;
	@Autowired BoardRepository repository;
	
	
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
		String fileName = "";
		
		if(mFile != null && mFile.getSize() != 0) {
			fileName = mFile.getOriginalFilename();
			String path = "C:\\springs15\\upload\\" + id;
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

}
