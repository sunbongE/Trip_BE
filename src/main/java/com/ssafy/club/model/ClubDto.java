package com.ssafy.club.model;

import lombok.Data;

@Data
public class ClubDto {

	private int id;
	private String subject;
	private String content;
	private String userId;
	private int planId;
	private int status;
	private String registerTime;
	private int totalCnt;
	private int people;
}
