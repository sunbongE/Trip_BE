package com.ssafy.friendship.model.service;

import java.util.List; 
import java.util.Map;

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

	@Override
	public void friendRequest(Map<String, String> map) { // insert
		friendshipMapper.friendRequest(map);
		
	}


	@Override
	public void answer(FriendshipDto friendshipDto) {
		int status = friendshipDto.getStatus();
		
		if(status == 201 || status == 204) {
			friendshipMapper.answer(friendshipDto); // 친구 수락, 차단 update
			
		}else if(status == 202) {
			// 삭제
			friendshipMapper.deleteById(friendshipDto.getId());
			
		}
		// 각 status으로 알람 보내기.

	}

	@Override
	public void deleteById(int id) {
		friendshipMapper.deleteById(id);
		
	}

}
