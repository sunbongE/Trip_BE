package com.ssafy.friendship.model;

import lombok.Data;

@Data
public class FriendshipResponseDto {
	private int id;
	private String userId;
	private int status;			// 요청의 종류 (수락, 거절, 요청, 차단)
	private String sinceDate; 	// 친구가 된 날을 기록.
}
