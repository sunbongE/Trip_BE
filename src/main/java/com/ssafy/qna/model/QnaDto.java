package com.ssafy.qna.model;

import lombok.Builder;
import lombok.Data;

@Data
public class QnaDto {

	private int id;
	private String userId;
	private String subject;
	private String content;
	private boolean open;
	private String registerTime;
}
