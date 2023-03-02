package com.care.project.repository;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.care.project.dto.BoardDTO;

@Repository
public interface BoardRepository {

	public ArrayList<BoardDTO> BoardList();
	public void write(BoardDTO board);

}
