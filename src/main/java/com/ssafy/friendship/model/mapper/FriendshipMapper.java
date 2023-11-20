package com.ssafy.friendship.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.friendship.model.FriendshipDto;

@Mapper
public interface FriendshipMapper {

	List<FriendshipDto> getReceived(String userId); // 현재 로그인회원이 받은 친구 요청을 가져온다.
	
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
	List<FriendshipDto> searchByStatus(Map<String ,Object> map);
}
