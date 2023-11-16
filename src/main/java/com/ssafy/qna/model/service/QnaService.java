package com.ssafy.qna.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.qna.model.QnaDto;
import com.ssafy.qna.model.QnaListDto;

public interface QnaService {
	void register(QnaDto qnaDto) throws SQLException;

	void update(QnaDto qnaDto) throws SQLException;

	void deleteById(int id) throws SQLException;

	QnaDto findById(int id) throws SQLException;

	QnaListDto searchAll(Map<String, String> map) throws SQLException;

	List<QnaDto> searchByKey(String key, String word) throws SQLException;
}
