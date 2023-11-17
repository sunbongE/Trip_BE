package com.ssafy.user.controller;

import java.sql.SQLException;  
import java.util.Map;

import javax.servlet.http.HttpSession;

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

import com.ssafy.user.model.UserDto;
import com.ssafy.user.model.service.UserService;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

//@Controller
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@Slf4j
public class UserContoller {

	private final UserService userService;
	
	public UserContoller(UserService userService) {
		this.userService = userService;
	}
//	
//	@GetMapping("/test")
//	public ResponseEntity<String> test() {
//		return new ResponseEntity<>("success", HttpStatus.OK);
//	}
//	
//	@PostMapping(value = "/posttest", headers = { "Content-type=application/json" })
//	public ResponseEntity<String> postTest(@RequestBody String test){
//		System.out.println(test);
//		return new ResponseEntity<String>(test, HttpStatus.OK);
//		
//	}
	
	@PostMapping("/join")
	public ResponseEntity<UserDto> join(@RequestBody UserDto userDto) throws SQLException {
//		System.out.println(userDto.getUserId());
		userService.join(userDto);
		
		String userId = userDto.getUserId();
		log.debug("JOINED ID {} ", userId);
		UserDto joined = userService.findByUserId(userId);
		
		return new ResponseEntity<>(joined, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserDto> login(@RequestBody Map<String, String> map) throws Exception {
		UserDto userDto = userService.login(map);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody UserDto userDto, HttpSession session) throws SQLException {
		int result = userService.update(userDto);
		String userId = userDto.getUserId();
		if(result == 1) {
			UserDto updated = userService.findByUserId(userId);
			session.setAttribute("userinfo", updated);
			return new ResponseEntity<>(updated, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("수정에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
//	@DeleteMapping("/delete/{userId}")
//	public ResponseEntity<?> delete(@PathVariable("userId") String userId) throws SQLException{
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody Map<String, String> request) throws SQLException{
		String userId = request.get("userId");
		System.out.println("삭제할 userId : "+userId);
		userService.delete(userId);
		
		return new ResponseEntity<>("삭제 완료", HttpStatus.OK);
	}
	
	// 회원 아이디 찾기.
	@PostMapping("/findUserId")
	public ResponseEntity<?> findUserId(@RequestBody Map<String, String> request) throws Exception{
		System.out.println("//////////////////////////////////////////////////////////");
		try {
			// 아이디 찾는 과정에서 비밀번호 해싱 필요
			String userId = userService.findUserId(request);
			if(userId != null) {
			return ResponseEntity.status(HttpStatus.OK).body(userId);
			}else {
				return new ResponseEntity<>("일치하는 회원이 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	// 회원 비번 찾기.
	@PostMapping("/findUserPwd")
	public ResponseEntity<?> findUserPwd(@RequestBody Map<String, String> request) throws Exception{
		try {
			// 조건에 따른 PWD 찾기
			String userPwd = userService.findUserPwd(request);
			
			if(userPwd != null) {
				// null 이 아니라면 조건을 통과하였으므로, 임시 비밀번호를 생성한 후 리턴한다.
				userPwd = userService.generateTempPassword(request.get("userId"));
				return ResponseEntity.status(HttpStatus.OK).body(userPwd);
			}else {
				return new ResponseEntity<>("일치하는 회원이 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "회원페이지로 이동.", notes = "선택한 회원의 프로필을 확인하기위한 정보를 가져온다.")
	@GetMapping("/{userId}")
	public ResponseEntity<?> findByUserId(@PathVariable("userId") String userId){
		try {
			UserDto userDto = userService.findByUserId(userId);
			if(userDto==null) {
				return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
			}else {
				return ResponseEntity.status(HttpStatus.OK).body(userDto);
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
