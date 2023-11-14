package com.ssafy.user.controller;

import java.sql.SQLException; 
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.user.model.UserDto;
import com.ssafy.user.model.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
//@Controller
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
		UserDto joined = userService.findByUserId(userId);
		
		return new ResponseEntity<>(joined, HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserDto> login(@RequestBody Map<String, String> map) throws SQLException {
//		System.out.println(userDto.getUserId());
		System.out.println("LOGIN REQUEST ID : " + map.get("userId"));
		UserDto userDto = userService.login(map);
		System.out.println("LOGIN RESPONSE ID : " + userDto.getUserId());
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
	public ResponseEntity<?> findUserId(@RequestBody Map<String, String> request) throws SQLException{
		System.out.println("//////////////////////////////////////////////////////////");
		try {
			
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
	public ResponseEntity<?> findUserPwd(@RequestBody Map<String, String> request) throws SQLException{
		try {
			
			System.out.println(request);
			String userPwd = userService.findUserPwd(request);
			if(userPwd != null) {
				return ResponseEntity.status(HttpStatus.OK).body(userPwd);
			}else {
				return new ResponseEntity<>("일치하는 회원이 없습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	
	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
//	
//	// 회원가입 join form 으로 이동시켜준다.
//	@GetMapping("/join")
//	public String mvjoin() {
////		return 
//		return "";
//	}
//	
//	// 회원가입 진행 후 메인 페이지로 이동한다.
//	@PostMapping("/join")
//	public String join(UserDto userDto) throws SQLException {
//		// TODO : join 시 암호화
//		userService.join(userDto);
//		// TODO : 회원가입 완료 후 리턴 매핑- 로그인페이지
//		return "";
//	}
//	
//	@GetMapping("/login")
//	public String mvlogin() {
//		return "index";
//	}
//	
//	@PostMapping("/login")
//	public String login(@RequestParam Map<String, String> map, @RequestParam(name = "saveid", required = false) String saveid, Model model, HttpSession session, HttpServletResponse response) {
//		log.debug("login map : {}", map);
//		try {
//			// TODO : 로그인 시 암호화
//			UserDto userDto = userService.login(map);
//			if(userDto != null) {
//				session.setAttribute("userinfo", userDto);
//				// TODO : 로그인 완료 후 리턴 매핑 - 메인페이지
//				return "";
//			} else {
//				model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
//				return "index";
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			model.addAttribute("msg", "로그인 중 문제 발생!!!");
//			// TODO : 에러페이지 경로 확인
//			return "error";
//		}
//	}
//	
//	@GetMapping("/update")
//	public String mvupdate() {
//		// TODO : 회원 수정 페이지 이동
//		return "";
//	}
//	
//	@PostMapping("/update")
//	public String update(UserDto userDto, HttpServletRequest request, HttpSession session) throws SQLException {
//		int result = userService.update(userDto);
//		
//		if(result == 0) {
//			String msg = "수정하는 과정에서 오류가 발생하였습니다.";
//			request.setAttribute("modifyFailMsg", msg);
//			// TODO : 에러페이지
//			return "";
//		} else {
//			// TODO : 수정완료 후 이동페이지 - 마이페이지
//			String userId = userDto.getUserId();
//			userDto = userService.findByUserId(userId);
//			session.setAttribute("userinfo", userDto);
//			return "";
//		}
//	}
//	
//	@GetMapping("/delete")
//	public String delete(@RequestParam("userid") String userId, HttpSession session) throws SQLException {
//		userService.delete(userId);
//		session.invalidate();
//		return "/index";
//	}
//	
//	@GetMapping("/logout")
//	public String logout(HttpSession session) {
//		session.invalidate();
//		return "/index";
//	}
}
