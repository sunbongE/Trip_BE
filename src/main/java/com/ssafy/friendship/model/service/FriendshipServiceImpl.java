package com.ssafy.friendship.model.service;

import java.util.List; 
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.alarm.model.AlarmDto;
import com.ssafy.alarm.model.mapper.AlarmMapper;
import com.ssafy.friendship.model.FriendshipDto;
import com.ssafy.friendship.model.mapper.FriendshipMapper;

@Service
public class FriendshipServiceImpl implements FriendshipService{
	FriendshipMapper friendshipMapper;
	AlarmMapper alramMapper;
	public FriendshipServiceImpl(FriendshipMapper friendshipMapper ,AlarmMapper alramMapper) {
		super();
		this.friendshipMapper = friendshipMapper;
		this.alramMapper= alramMapper;
	}

	@Override
	public List<FriendshipDto> getReceived(String userId) {
		return friendshipMapper.getReceived(userId);
	}

	@Override
	public void friendRequest(Map<String, String> map) { // insert
		friendshipMapper.friendRequest(map);
		// 친구 요청시 알람 발생
		AlarmDto alarmDto = new AlarmDto();
		alarmDto.setToUserId(map.get("fromUserId")); // 받은 사람이 보낸사람한테 보냄
		alarmDto.setFromUserId(map.get("toUserId"));
		alarmDto.setType(203);
		alramMapper.sendAlarm(alarmDto);
	}


	@Override
	public void answer(FriendshipDto friendshipDto) {
		int status = friendshipDto.getStatus();

		// 알람 객체
		if(status == 201 || status == 204) {
			friendshipMapper.answer(friendshipDto); // 친구 수락, 차단 update
			
		}else if(status == 202) {
			// 삭제
			friendshipMapper.deleteById(friendshipDto.getId());
			
		}

		// 각 status으로 알람 보내기.
		AlarmDto alarmDto = new AlarmDto();
		alarmDto.setToUserId(friendshipDto.getFromUserId()); // 받은 사람이 보낸사람한테 보냄
		alarmDto.setFromUserId(friendshipDto.getToUserId());
		alarmDto.setType(status);
		alramMapper.sendAlarm(alarmDto);

	}

	@Override
	public void deleteById(int id) {
		friendshipMapper.deleteById(id);
		
	}

}
