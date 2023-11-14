package com.ssafy.qna.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.qna.model.QnaAnswerDto;
import com.ssafy.qna.model.mapper.QnaAnswerMapper;

@Service
public class QnaAnswerServiceImpl implements QnaAnswerService {

	private final QnaAnswerMapper qnaAnswerMapper;
	
	public QnaAnswerServiceImpl(QnaAnswerMapper qnaAnswerMapper) {
		this.qnaAnswerMapper = qnaAnswerMapper;
	}

	@Override
	public void register(QnaAnswerDto qnaAnswerDto) throws SQLException {
		qnaAnswerMapper.register(qnaAnswerDto);
	}

	@Override
	public void update(QnaAnswerDto qnaAnswerDto) throws SQLException {
		 qnaAnswerMapper.update(qnaAnswerDto);
	}

	@Override
	public void deleteById(int id) throws SQLException {
		qnaAnswerMapper.deleteById(id);
		
	}

	@Override
	public List<QnaAnswerDto> searchByQnaId(int qnaId) throws SQLException {
		// TODO Auto-generated method stub
		return qnaAnswerMapper.searchByQnaId(qnaId);
	}
}
