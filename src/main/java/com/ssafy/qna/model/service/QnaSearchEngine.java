package com.ssafy.qna.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.qna.model.QnaDto;
import com.ssafy.qna.model.mapper.QnaMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum QnaSearchEngine {

	subject {
		@Override
		public List<QnaDto> searchByKey(QnaMapper qnaMapper, String word) throws SQLException {
			log.debug("KEY : SUBJECT \n WORD : {} ", word);
			return qnaMapper.searchBySubject(word);
		}
	},
	userid {
		@Override
		public List<QnaDto> searchByKey(QnaMapper qnaMapper, String word) throws SQLException {
			log.debug("KEY : UserId \n WORD : {} ", word);
			return qnaMapper.searchByUserId(word);
		}
	};

	public abstract List<QnaDto> searchByKey(QnaMapper qnaMapper, String word) throws SQLException;
}
