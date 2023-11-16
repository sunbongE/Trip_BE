package com.ssafy.friendship.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.friendship.model.FriendshipDto;

@Mapper
public interface FriendshipMapper {

	List<FriendshipDto> getReceived(String userId); // 현재 로그인회원이 받은 친구 요청을 가져온다.
}
