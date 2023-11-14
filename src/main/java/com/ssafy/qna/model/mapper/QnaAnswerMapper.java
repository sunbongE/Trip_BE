package com.ssafy.qna.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.qna.model.QnaAnswerDto;

@Mapper
public interface QnaAnswerMapper {
	void register(QnaAnswerDto qnaAnswerDto) throws SQLException;
	void update(QnaAnswerDto qnaAnswerDto) throws SQLException;
	void deleteById(int id) throws SQLException;
	List<QnaAnswerDto> searchByQnaId(int qnaId) throws SQLException;
}
