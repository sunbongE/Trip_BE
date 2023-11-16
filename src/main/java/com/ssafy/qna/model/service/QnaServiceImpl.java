package com.ssafy.qna.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.board.model.BoardDto;
import com.ssafy.qna.model.QnaDto;
import com.ssafy.qna.model.QnaListDto;
import com.ssafy.qna.model.mapper.QnaMapper;

@Service
public class QnaServiceImpl implements QnaService {

	private final QnaMapper qnaMapper;
	
	public QnaServiceImpl(QnaMapper qnaMapper) {
		this.qnaMapper = qnaMapper;
	}
	
	@Override
	public void register(QnaDto qnaDto) throws SQLException {
		qnaMapper.register(qnaDto);
	}

	@Override
	public void update(QnaDto qnaDto) throws SQLException {
		qnaMapper.update(qnaDto);
	}

	@Override
	public void deleteById(int id) throws SQLException {
		qnaMapper.deleteById(id);
	}

	@Override
	public QnaDto findById(int id) throws SQLException {
		return qnaMapper.findById(id);
	}

	@Override
	public QnaListDto searchAll(Map<String, String> map) throws SQLException {
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
		List<QnaDto> list = qnaMapper.searchAll(param);

		if ("user_id".equals(key))
			param.put("key", key == null ? "" : "user_id");
		int totalArticleCount = qnaMapper.getTotalArticleCount(param);
		int totalPageCount = (totalArticleCount - 1) / sizePerPage + 1;
		
		QnaListDto qnalist = new QnaListDto();
		qnalist.setQnalist(list);
		qnalist.setCurrentPage(currentPage);
		qnalist.setTotalPageCount(totalPageCount);
		
		return qnalist;
	}

	@Override
	public List<QnaDto> searchByKey(String key, String word) throws SQLException {		
		return QnaSearchEngine.valueOf(key).searchByKey(qnaMapper, word);
	}

}
