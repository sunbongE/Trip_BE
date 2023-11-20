package com.ssafy.friendship.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.friendship.model.FriendshipDto;
import com.ssafy.friendship.model.FriendshipResponseDto;

public interface FriendshipService {
	List<FriendshipDto> getReceived(String userId);
	
	// 친구 요청.
	void friendRequest(Map<String, String> map);
//	// 수락
//	void acceptById(int id);
//	// 거부
//	void refusalById(int id);
//	// 차단
//	void cutById(int id);
	
	void deleteById(int id);
	void answer(FriendshipDto friendshipDto);
	
	List<FriendshipResponseDto> searchByStatus(Map<String ,Object> map) throws Exception;
}
