package com.ssafy.board.model.mapper;

import java.sql.SQLException; 
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.board.model.BoardDto;
@Mapper
public interface BoardMapper {

	void registerArticle(BoardDto boardDto);

	List<BoardDto> searchListAll(Map<String, Object> param);
	// 페이지네이션 관련
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;

	List<BoardDto> searchListBySubject(String subject);

	BoardDto viewArticle(int articleNo);

	void modifyArticle(BoardDto boardDto);

	void deleteArticle(int articleNo);
	
	void updateHit(int articleNo);
	void updateRecomment(int articleNo);
}
