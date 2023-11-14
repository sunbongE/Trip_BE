package com.ssafy.trip.model.service;

import java.util.List;

import com.ssafy.trip.model.AttractionInfoDto;
import com.ssafy.trip.model.GugunDto;
import com.ssafy.trip.model.SidoDto;

public interface AttractionService {

//	List<AttractionInfoDto> attractionList(AttractionInfoDto attractionInfoDto);

	List<SidoDto> sidoList();
	List<GugunDto> gugunList(int sidoCode);
	List<AttractionInfoDto> searchTourinfo(int sidoCode, int gugunCode, int contentTypeId);
	List<AttractionInfoDto> searchByTitle(String title);
}
