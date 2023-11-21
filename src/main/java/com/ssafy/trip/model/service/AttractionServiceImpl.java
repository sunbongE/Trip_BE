package com.ssafy.trip.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.trip.model.AttractionInfoDto;
import com.ssafy.trip.model.GugunDto;
import com.ssafy.trip.model.SidoDto;
import com.ssafy.trip.model.mapper.AttractionMapper;
@Service
public class AttractionServiceImpl implements AttractionService {
	
	AttractionMapper attractionMapper;
	
	public AttractionServiceImpl(AttractionMapper attractionMapper) {
		super();
		this.attractionMapper = attractionMapper;
	}


	@Override
	public List<AttractionInfoDto> searchByTitle(String title) {
		return attractionMapper.searchByTitle(title);
	}
	
	@Override
	public List<SidoDto> sidoList() {
		return attractionMapper.sidoList();
	}
	
	@Override
	public List<GugunDto> gugunList(int sidoCode) {
		return attractionMapper.gugunList(sidoCode);
	}
	
	@Override
	public List<AttractionInfoDto> searchTourinfo(int sidoCode, int gugunCode, int contentTypeId) {
		return attractionMapper.searchTourinfo(sidoCode, gugunCode, contentTypeId);
	}


	@Override
	public List<AttractionInfoDto> searchByPoint(Map<String, Object> map) {
		return attractionMapper.searchByPoint(map);
	}


	@Override
	public String getDescription(int contentId) {
		return attractionMapper.getDescription(contentId);
	}


	@Override
	public List<AttractionInfoDto> searchHotel(Map<String, Object> map) {
		return attractionMapper.searchHotel(map);
	}


	@Override
	public List<AttractionInfoDto> searchFood(Map<String, Object> map) {
		return attractionMapper.searchFood(map);
	}


	

}
