package com.ssafy.qna.model;

import lombok.Builder;
import lombok.Data;

@Data
public class QnaAnswerDto {
	private int id;
	private String userId;
	private int qnaId;
	private String content;
	private String registerTime;
}
