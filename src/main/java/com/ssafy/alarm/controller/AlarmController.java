package com.ssafy.alarm.controller;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
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

import com.ssafy.alarm.model.AlarmDto;
import com.ssafy.alarm.model.service.AlarmService;

import lombok.extern.slf4j.Slf4j;

@RestController // @Controller+Responsebody
@RequestMapping("/alarm")
@CrossOrigin("*")
@Slf4j
public class AlarmController {

	AlarmService alarmService;
	
	
	public AlarmController(AlarmService alarmService) {
		super();
		this.alarmService = alarmService;
	}


	@GetMapping("/{userId}")
	public ResponseEntity<?> getAlarm(@PathVariable("userId") String userId){
		try {
			List<AlarmDto> alarmList = alarmService.getAlarm(userId);
			return ResponseEntity.status(HttpStatus.OK).body(alarmList);
			
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/send")
	public ResponseEntity<?> sendAlarm(@RequestBody AlarmDto alarmDto){
		try {
			alarmService.sendAlarm(alarmDto);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/deleteAlarm/{id}")
	public ResponseEntity<?> deleteAlarm(@PathVariable("id") int id){
		try {
			alarmService.deleteAlarm(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/readAlarm")
	public ResponseEntity<?> readAlarm(@RequestBody int id){
	    log.debug("id - {}", id);
	    try {
	        alarmService.readAlarm(id);
	        return new ResponseEntity<Void>(HttpStatus.OK);
	    } catch (Exception e) {
	        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	    }
	}

//	private ResponseEntity<String> exceptionHandling(Exception e) {
//		e.printStackTrace();
//		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
}
