package com.ssafy.trip.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.trip.model.AttractionInfoDto;
import com.ssafy.trip.model.GugunDto;
import com.ssafy.trip.model.SidoDto;
import com.ssafy.trip.model.service.AttractionService;

@RestController // @Controller+Responsebody
@RequestMapping("/trip")
@CrossOrigin("*")
public class TripController extends HttpServlet {
	
	AttractionService attractionService;
	
	
	public TripController(AttractionService attractionService) {
		super();
		this.attractionService = attractionService;
	}

	@GetMapping("/sidoList") // 여행 정보 불러오기.
	protected ResponseEntity<?> tourinfo()  {
		List<SidoDto> list = new ArrayList<SidoDto>(); // 시, 도 정보를 담은 리스트
		try {
			list = attractionService.sidoList();
			if (list != null && !list.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(list);
			} else {
				return new ResponseEntity<List>(Collections.EMPTY_LIST, HttpStatus.OK);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/gugunList/{sidoCode}")
	protected  ResponseEntity<?> gugunList(@PathVariable int sidoCode)  {

		List<GugunDto> list = new ArrayList<GugunDto>();
		try {
			list = attractionService.gugunList(sidoCode);
			if (list != null && !list.isEmpty()) { // 리스트가 비어있지 않은 경우,,
				return ResponseEntity.status(HttpStatus.OK).body(list);
			} else {// 비어있으면 
//				return ResponseEntity.status(HttpStatus.OK).body(list); // 빈목록 그대로 전달
				return new ResponseEntity<List>(Collections.EMPTY_LIST, HttpStatus.OK);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/searchTourinfo/{sidoCode}/{gugunCode}/{contentTypeId}")
	protected ResponseEntity<?> searchTourinfo(@PathVariable int sidoCode, @PathVariable int gugunCode, @PathVariable int contentTypeId) {
	    // sidoCode, gugunCode, content 변수를 사용하여 로직을 처리
		try {
			List<AttractionInfoDto> list = attractionService.searchTourinfo(sidoCode, gugunCode, contentTypeId);
			if(list!=null && !list.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(list); 
			}else {
				return new ResponseEntity<List>(Collections.EMPTY_LIST, HttpStatus.OK);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/searchTourinfoByKeyword/{title}")
	protected ResponseEntity<?> searchTourinfoByKeyword(@PathVariable String title) throws ServletException, IOException {
		List<AttractionInfoDto> list = new ArrayList<AttractionInfoDto>();
		try {
			list = attractionService.searchByTitle(title);
			if(list!=null && !list.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(list); 
			}else {
				return new ResponseEntity<List>(Collections.EMPTY_LIST, HttpStatus.OK);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping("/searchByPoint")
	protected ResponseEntity<?> searchByPoint(@RequestBody Map<String, Object> map) throws ServletException, IOException {
		List<AttractionInfoDto> list = new ArrayList<AttractionInfoDto>();
		try {
			list = attractionService.searchByPoint(map);
			if(list!=null && !list.isEmpty()) {
				return ResponseEntity.status(HttpStatus.OK).body(list); 
			}else {
				return new ResponseEntity<List>(Collections.EMPTY_LIST, HttpStatus.OK);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
