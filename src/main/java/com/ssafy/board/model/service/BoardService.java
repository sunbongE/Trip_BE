package com.ssafy.board.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.BoardListDto;
import com.ssafy.board.model.FileInfoDto; 


public interface BoardService {

	void registerArticle(BoardDto boardDto);
//	void registerOnlyArticle(BoardDto boardDto);
	BoardListDto searchListAll(Map<String, String> map) throws SQLException;

	List<BoardDto> searchListBySubject(String subject);

	BoardDto viewArticle(int articleNo);

	void modifyArticle(BoardDto boardDto);

	void deleteArticle(int no);

	void updateHit(int articleNo);
	
	void updateRecomment(int articleNo);
	
	List<FileInfoDto> fileInfoList(int articleNo) throws Exception;
}
