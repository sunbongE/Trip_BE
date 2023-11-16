package com.ssafy.board.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.BoardListDto;
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
	public BoardListDto searchListAll(Map<String, String> map) throws SQLException {
//		return boardMapper.searchListAll(map);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("word", map.get("word") == null ? "" : map.get("word"));
		int currentPage = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
		int sizePerPage = Integer.parseInt(map.get("spp") == null ? "20" : map.get("spp"));
		int start = currentPage * sizePerPage - sizePerPage;
		param.put("start", start);
		param.put("listsize", sizePerPage);

		String key = map.get("key");
		param.put("key", key == null ? "" : key);
		if ("user_id".equals(key))
			param.put("key", key == null ? "" : "b.user_id");
		List<BoardDto> list = boardMapper.searchListAll(param);

		if ("user_id".equals(key))
			param.put("key", key == null ? "" : "user_id");
		int totalArticleCount = boardMapper.getTotalArticleCount(param);
		int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;

		BoardListDto boardListDto = new BoardListDto();
		boardListDto.setArticles(list);
		boardListDto.setCurrentPage(currentPage);
		boardListDto.setTotalPageCount(totalPageCount);

		return boardListDto;
	}

	@Override
	public List<BoardDto> searchListBySubject(String subject) {
		// TODO Auto-generated method stub
		return boardMapper.searchListBySubject(subject);
	}

	@Override
	public BoardDto viewArticle(int articleNo) {
		boardMapper.updateHit(articleNo);
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

	@Override
	public void updateRecomment(int articleNo) {
		boardMapper.updateRecomment(articleNo);
	}

	

}
