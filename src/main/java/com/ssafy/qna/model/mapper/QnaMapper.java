package com.ssafy.qna.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.qna.model.QnaDto;

@Mapper
public interface QnaMapper {
	void register(QnaDto qnaDto) throws SQLException;
	void update(QnaDto qnaDto) throws SQLException;
	void deleteById(int id) throws SQLException;
	
	QnaDto findById(int id) throws SQLException;
	List<QnaDto> searchAll() throws SQLException;
	List<QnaDto> searchByUserId(String userId) throws SQLException;
	List<QnaDto> searchBySubject(String subject) throws SQLException;

}
