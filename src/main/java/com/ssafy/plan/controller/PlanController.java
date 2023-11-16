package com.ssafy.plan.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.plan.model.PlanRegisterRequestDto;
import com.ssafy.plan.model.service.PlanService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/plan")
@Slf4j
public class PlanController {

	private final PlanService planService;
	
	public PlanController(PlanService planService) {
		this.planService = planService;
	}
	
	// 여행 계획 작성
	// 수정 + 순서변경
	// 삭제
	// 동행찾기로 공유
	// 전체보기
	// 상세보기
	
	@PostMapping("/regist")
	public ResponseEntity<?> regist(@RequestBody PlanRegisterRequestDto planRegisterRequestDto) throws Exception{
		log.debug("\t Regist planRegisterRequestDto : {}", planRegisterRequestDto);
		planService.regist(planRegisterRequestDto);
		
		return new ResponseEntity(HttpStatus.CREATED);
	}
}
