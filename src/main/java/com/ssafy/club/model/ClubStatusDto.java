package com.ssafy.club.model;

import lombok.Data;

@Data
public class ClubStatusDto {

	private int id;
	private String fromUserId;
	private String toUserId;
	private int clubId;
	private int answer;
}
