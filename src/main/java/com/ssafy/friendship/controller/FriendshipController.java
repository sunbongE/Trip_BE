package com.ssafy.friendship.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.friendship.model.FriendshipDto;
import com.ssafy.friendship.model.service.FriendshipService;

import io.swagger.annotations.ApiOperation;

@RestController // @Controller+Responsebody
@RequestMapping("/friend")
@CrossOrigin("*")
public class FriendshipController {
	
	FriendshipService friendshipService;
	
	
	public FriendshipController(FriendshipService friendshipService) {
		super();
		this.friendshipService = friendshipService;
	}
	
	@ApiOperation(value= "친구요청을 받은 목록", notes = "받은 친구요청 목록을 확인한다.")
	@GetMapping("/{userId}")
	public ResponseEntity<?> getReceived(@PathVariable("userId") String userId) {
		try {
			List<FriendshipDto> receivedList =  friendshipService.getReceived(userId);
			if(receivedList != null && !receivedList.isEmpty()) {
				
				return ResponseEntity.status(HttpStatus.OK).body(receivedList);
			}else {
				return new ResponseEntity<List>(Collections.EMPTY_LIST,HttpStatus.OK);
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
