package com.ssafy.club.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.club.model.ClubStatusDto;
import com.ssafy.club.model.service.ClubStatusService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/club/status")
@CrossOrigin("*")
@Slf4j
public class ClubStatusController {

	private final ClubStatusService clubStatusService;

	public ClubStatusController(ClubStatusService clubStatusService) {
		this.clubStatusService = clubStatusService;
	}

	@PostMapping("/regist")
	public ResponseEntity<?> insertClubStatus(@RequestBody ClubStatusDto dto) throws Exception {
		try {
			log.debug("INSERT MAP : {}", dto);
			clubStatusService.insertClubStatus(dto);
			return new ResponseEntity(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/search/to/{toUserId}")
	public ResponseEntity<List<ClubStatusDto>> searchByToUserId(@PathVariable String toUserId) throws Exception {
		try {
			List<ClubStatusDto> list = clubStatusService.searchByToUserId(toUserId);
			return new ResponseEntity(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/search/from/{fromUserId}")
	public ResponseEntity<List<ClubStatusDto>> searchByFromUserId(@PathVariable String fromUserId) throws Exception {
		try {
			List<ClubStatusDto> list = clubStatusService.searchByFromUserId(fromUserId);
			return new ResponseEntity(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id) throws Exception {
		try {
			clubStatusService.deleteById(id);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	// @param id, answer -> answer는 101 or 102
	@PutMapping("/reply")
	public ResponseEntity<?> replyToAnswer(@RequestBody Map<String, Object> map) throws Exception {
		try {
			clubStatusService.replyToAnswer(map);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
