package com.ssafy.friendship.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.friendship.model.FriendshipDto;
import com.ssafy.friendship.model.mapper.FriendshipMapper;

@Service
public class FriendshipServiceImpl implements FriendshipService{
	FriendshipMapper friendshipMapper;
	
	public FriendshipServiceImpl(FriendshipMapper friendshipMapper) {
		super();
		this.friendshipMapper = friendshipMapper;
	}

	@Override
	public List<FriendshipDto> getReceived(String userId) {
		return friendshipMapper.getReceived(userId);
	}

}
