package com.ssafy.trip.model.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.trip.model.AttractionInfoDto;
import com.ssafy.trip.model.GugunDto;
import com.ssafy.trip.model.SidoDto;

@Mapper
public interface AttractionMapper {

//	List<AttractionInfoDto> attractionList(AttractionInfoDto attractionInfoDto);

//	List<AttractionInfoDto> searchByTitle(String title, int sidoCode);
	String findUserId(Map<String, String> map);

	String findUserPwd(Map<String, String> map);

	// 시도 불러오기
	List<SidoDto> sidoList();

	// 구군 불러오기
	List<GugunDto> gugunList(int sidoCode);

	List<AttractionInfoDto> searchTourinfo(@Param("sidoCode") int sidoCode, @Param("gugunCode") int gugunCode, @Param("contentTypeId") int contentTypeId);

	List<AttractionInfoDto> searchByTitle(String title);
}
