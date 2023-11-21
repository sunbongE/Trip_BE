package com.ssafy.trip.model.service;

import java.util.List;
import java.util.Map;

import com.ssafy.trip.model.AttractionInfoDto;
import com.ssafy.trip.model.GugunDto;
import com.ssafy.trip.model.SidoDto;

public interface AttractionService {

//	List<AttractionInfoDto> attractionList(AttractionInfoDto attractionInfoDto);

	List<SidoDto> sidoList();
	List<GugunDto> gugunList(int sidoCode);
	List<AttractionInfoDto> searchTourinfo(int sidoCode, int gugunCode, int contentTypeId);
	List<AttractionInfoDto> searchByTitle(String title);
	List<AttractionInfoDto> searchByPoint(Map<String, Object> map);
	String getDescription(int contentId);
	List<AttractionInfoDto> searchHotel(Map<String, Object> map);
	List<AttractionInfoDto> searchFood(Map<String, Object> map);

}
