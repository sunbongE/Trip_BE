package com.ssafy.qna.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.qna.model.QnaDto;
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
	public List<QnaDto> searchAll() throws SQLException {
		return qnaMapper.searchAll();
	}

	@Override
	public List<QnaDto> searchByKey(String key, String word) throws SQLException {		
		return QnaSearchEngine.valueOf(key).searchByKey(qnaMapper, word);
	}

}
