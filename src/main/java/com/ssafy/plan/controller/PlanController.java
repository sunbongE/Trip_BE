package com.ssafy.plan.controller;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.plan.model.MyPlanInfoDto;
import com.ssafy.plan.model.PlanDto;
import com.ssafy.plan.model.PlanRegisterRequestDto;
import com.ssafy.plan.model.PlanSearchResponseDto;
import com.ssafy.plan.model.PlanUpdateRequestDto;
import com.ssafy.plan.model.service.PlanService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/plan")
@CrossOrigin("*")
@Slf4j
public class PlanController {

	private final PlanService planService;
	
	public PlanController(PlanService planService) {
		this.planService = planService;
	}
	
	@PostMapping("/regist")
	public ResponseEntity<?> regist(@RequestBody PlanRegisterRequestDto planRegisterRequestDto) throws Exception{
		log.debug("\t Regist planRegisterRequestDto : {}", planRegisterRequestDto);
		planService.regist(planRegisterRequestDto);
		
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updatePlan(@RequestBody PlanUpdateRequestDto planUpdateRequestDto)throws Exception{
		log.debug("\t UPDATE planUpdateRequestDto : {}", planUpdateRequestDto.toString());
		planService.updatePlan(planUpdateRequestDto);
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@GetMapping("/searchAll")
	public ResponseEntity<?> searchAllPlan() throws Exception {
		List<PlanSearchResponseDto> list= planService.searchAll();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		return ResponseEntity.ok().headers(header).body(list);
	}
	
	@GetMapping("/search/{id}")
	public ResponseEntity<?> searchPlanById(@PathVariable int id) throws Exception {
		PlanSearchResponseDto dto = planService.searchPlanById(id);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		return ResponseEntity.ok().headers(header).body(dto);
	}
	
	@GetMapping("/search/my/{userId}")
	public ResponseEntity<?> searchPlanByUserId(@PathVariable String userId) throws Exception {
		List<PlanDto> list = planService.searchPlanByUserId(userId);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		return ResponseEntity.ok().headers(header).body(list);
	}
	
	@DeleteMapping("/delte/{id}")
	public ResponseEntity<?> deletePlanById(@PathVariable int id) throws Exception {
		try {
		planService.deletePlanById(id);
		
		return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getMyTourInfo/{planId}")
	public ResponseEntity<?> searchTourInfoByPlanId(@PathVariable int planId) throws Exception{
		try {
			List<MyPlanInfoDto>  list = planService.getMyTourInfo(planId);
			return new ResponseEntity<List<MyPlanInfoDto> >(list,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/deletePlanInfoByPlanId/{planId}")
	public ResponseEntity<?> deletePlanInfoByPlanId(@PathVariable int planId) throws Exception{
		try {
			planService.deletePlanInfoByPlanId(planId);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}
}
