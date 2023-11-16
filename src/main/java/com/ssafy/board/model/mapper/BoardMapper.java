package com.ssafy.board.model.mapper;

import java.util.List; 

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.board.model.BoardDto;
@Mapper
public interface BoardMapper {

	void registerArticle(BoardDto boardDto);

	List<BoardDto> searchListAll();

	List<BoardDto> searchListBySubject(String subject);

	BoardDto viewArticle(int articleNo);

	void modifyArticle(BoardDto boardDto);

	void deleteArticle(int articleNo);
	
	void updateHit(int articleNo);
	void updateRecomment(int articleNo);
}
