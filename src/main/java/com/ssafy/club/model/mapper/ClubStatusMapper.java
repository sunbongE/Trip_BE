package com.ssafy.club.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.club.model.ClubStatusDto;

@Mapper
public interface ClubStatusMapper {

	// 요청 생성
	void insertClubStatus(ClubStatusDto dto) throws SQLException;

	// 본인에게 온 요청 조회
	List<ClubStatusDto> searchByToUserId(String toUserId) throws SQLException;

	// 본인이 보낸 요청 조회
	List<ClubStatusDto> searchByFromUserId(String fromUserId) throws SQLException;

	// 요청 삭제
	void deleteById(int id) throws SQLException;

	ClubStatusDto findById(int id) throws SQLException;
	// 요청에 대한 응답
	// 요청 상태 변경
	void replyToAnswer(Map<String, Object> map) throws SQLException;
	
	Map<String, Object> checkTotalCnt(int id) throws SQLException;
}
