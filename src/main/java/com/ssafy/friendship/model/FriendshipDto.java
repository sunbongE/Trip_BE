package com.ssafy.friendship.model;

import lombok.Data;

@Data
public class FriendshipDto {
	private int id;
	private String fromUserId; 	// 보낸 사람
	private String toUserId;	// 받은 사람
	private int status;			// 요청의 종류 (수락, 거절, 요청, 차단)
	private String sinceDate; 	// 친구가 된 날을 기록.
	
}
