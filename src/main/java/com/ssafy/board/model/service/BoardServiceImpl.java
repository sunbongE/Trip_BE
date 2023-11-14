package com.ssafy.board.model.service;

import java.util.List; 

import org.springframework.stereotype.Service;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.mapper.BoardMapper; 
@Service
public class BoardServiceImpl implements BoardService {

	BoardMapper boardMapper ;
	
	public BoardServiceImpl(BoardMapper boardMapper) {
		super();
		this.boardMapper = boardMapper;
	}

	@Override
	public void registerArticle(BoardDto boardDto) {
		boardMapper.registerArticle(boardDto);
	}

	@Override
	public List<BoardDto> searchListAll() {
		// TODO Auto-generated method stub
		return boardMapper.searchListAll();
	}

	@Override
	public List<BoardDto> searchListBySubject(String subject) {
		// TODO Auto-generated method stub
		return boardMapper.searchListBySubject(subject);
	}

	@Override
	public BoardDto viewArticle(int articleNo) {
		return boardMapper.viewArticle(articleNo);
	}

	@Override
	public void modifyArticle(BoardDto boardDto) {
		boardMapper.modifyArticle(boardDto);		
	}

	@Override
	public void deleteArticle(int articleNo) {
		boardMapper.deleteArticle(articleNo);		
	}

	@Override
	public void updateHit(int articleNo) {
		boardMapper.updateHit(articleNo);		
	}

	

}
