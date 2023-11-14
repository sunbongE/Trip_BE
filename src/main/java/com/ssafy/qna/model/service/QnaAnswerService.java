package com.ssafy.qna.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.qna.model.QnaAnswerDto;

public interface QnaAnswerService {
	
	void register(QnaAnswerDto qnaAnswerDto) throws SQLException;
	void update(QnaAnswerDto qnaAnswerDto) throws SQLException;
	void deleteById(int id) throws SQLException;
	List<QnaAnswerDto> searchByQnaId(int qnaId) throws SQLException;
}
