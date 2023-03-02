package com.care.project.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.care.project.dto.BoardDTO;

@Repository
public interface BoardRepository {
	
	void write(BoardDTO board);
	
	int boardCount(Map<String, Object> map);
	
	List<BoardDTO> boardList(
			@Param("b")int begin,
			@Param("e") int end, 
			@Param("sel") String select,
			@Param("search") String search);
}
