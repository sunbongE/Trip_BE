package com.ssafy.qna.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.qna.model.QnaDto;

public interface QnaService {
	void register(QnaDto qnaDto) throws SQLException;

	void update(QnaDto qnaDto) throws SQLException;

	void deleteById(int id) throws SQLException;

	QnaDto findById(int id) throws SQLException;

	List<QnaDto> searchAll() throws SQLException;

	List<QnaDto> searchByKey(String key, String word) throws SQLException;
}
