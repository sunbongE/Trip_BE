package com.ssafy.board.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.BoardListDto; 


public interface BoardService {

	void registerArticle(BoardDto boardDto);

	BoardListDto searchListAll(Map<String, String> map) throws SQLException;

	List<BoardDto> searchListBySubject(String subject);

	BoardDto viewArticle(int articleNo);

	void modifyArticle(BoardDto boardDto);

	void deleteArticle(int no);

	void updateHit(int articleNo);
	
	void updateRecomment(int articleNo);
}
