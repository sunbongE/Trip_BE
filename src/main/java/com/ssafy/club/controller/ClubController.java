package com.ssafy.club.controller;

import java.sql.SQLException;
import java.util.List;

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

import com.ssafy.club.model.ClubDto;
import com.ssafy.club.model.ClubMemberDto;
import com.ssafy.club.model.service.ClubService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/club")
@CrossOrigin("*")
@Slf4j
public class ClubController {

	private final ClubService clubService;

	public ClubController(ClubService clubService) {
		this.clubService = clubService;
	}

	@PostMapping("/regist")
	public ResponseEntity<?> registClub(@RequestBody ClubDto clubDto) throws Exception {
		try {
			clubService.registClub(clubDto);
			return new ResponseEntity(HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	// subject, content, status 변경 가능
	// 바꿔줄 값만 넣어서 dto 보내주면 변경된다.
	@PutMapping("/update")
	public ResponseEntity<?> updateClub(@RequestBody ClubDto clubDto) throws Exception {
		try {
			clubService.updateClub(clubDto);
			return new ResponseEntity(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteClub(@PathVariable int id) throws SQLException {
		try {
			clubService.deleteClub(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	// pagination
	@GetMapping("/search")
	public ResponseEntity<List<ClubDto>> searchClubAll() throws SQLException {
		try {
			List<ClubDto> list = clubService.searchClubAll();
			return new ResponseEntity<List<ClubDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ClubDto>>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/search/{id}")
	public ResponseEntity<ClubDto> findClubById(@PathVariable int id) throws SQLException {
		try {
			ClubDto dto = clubService.findClubById(id);
			return new ResponseEntity<ClubDto>(dto, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ClubDto>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/search/member/{clubId}")
	public ResponseEntity<List<ClubMemberDto>> searchClubMemberByClubId(@PathVariable int clubId) throws SQLException {
		try {
			List<ClubMemberDto> list = clubService.searchClubMemberByClubId(clubId);
			return new ResponseEntity<List<ClubMemberDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ClubMemberDto>>(HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/delete/member/{id}")
	public ResponseEntity<?> deleteClubMemberByid(@PathVariable int id) throws Exception {
		try {
			clubService.deleteClubMemberById(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/search/my/{userId}")
	public ResponseEntity<List<ClubDto>> searchMyClubs(@PathVariable String userId) throws SQLException {
		try {
			List<ClubDto> list= clubService.searchMyClubs(userId);
			return new ResponseEntity<List<ClubDto>>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<List<ClubDto>>(HttpStatus.BAD_REQUEST);
		}
	}

}
