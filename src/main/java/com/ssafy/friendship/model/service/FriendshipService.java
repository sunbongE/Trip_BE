package com.ssafy.friendship.model.service;

import java.util.List;

import com.ssafy.friendship.model.FriendshipDto;

public interface FriendshipService {
	List<FriendshipDto> getReceived(String userId);
}
