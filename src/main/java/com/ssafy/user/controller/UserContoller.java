package com.ssafy.user.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.ssafy.util.JWTUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

//@Controller
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@Slf4j
public class UserContoller {

	private final UserService userService;
	private JWTUtil jwtUtil;
	
	public UserContoller(UserService userService, JWTUtil jwtUtil) {
		this.userService = userService;
		this.jwtUtil = jwtUtil;
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
	public ResponseEntity<Map<String, Object>> login(@RequestBody Map<String, String> map) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		
		try {
			UserDto loginUser = userService.login(map);
			System.out.println("////////////////////////////////////////");
			System.out.println(map);
			System.out.println(loginUser);
			System.out.println("////////////////////////////////////////");
			if(loginUser != null) {
				System.out.println(loginUser+" /?????");
				String accessToken = jwtUtil.createAccessToken(loginUser.getUserId());
				String refreshToken = jwtUtil.createRefreshToken(loginUser.getUserId());
				log.debug("access token : {}", accessToken);
				log.debug("refresh token : {}", refreshToken);
				
//				발급받은 refresh token을 DB에 저장.
				System.out.println(loginUser.getUserId());
				System.out.println("////////////////////////////////////////");
				userService.saveRefreshToken(loginUser.getUserId(), refreshToken);
				
//				JSON으로 token 전달.
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				status = HttpStatus.CREATED;
			} else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해주세요.");
				status = HttpStatus.UNAUTHORIZED;
			} 
			
		} catch (Exception e) {
			log.debug("로그인 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userId") @ApiParam(value = "인증할 회원의 아이디.", required = true) String userId,
			HttpServletRequest request) {
//		logger.debug("userId : {} ", userId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
			log.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				UserDto userDto = userService.findByUserId(userId);
				resultMap.put("userDto", userDto);
				status = HttpStatus.OK;
			} catch (Exception e) {
				log.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			log.error("사용 불가능 토큰!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> removeToken(@PathVariable ("userId") @ApiParam(value = "로그아웃할 회원의 아이디.", required = true) String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			userService.deleRefreshToken(userId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}
	
	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody UserDto userDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");
		log.debug("token : {}, memberDto : {}", token, userDto);
		if (jwtUtil.checkToken(token)) {
			if (token.equals(userService.getRefreshToken(userDto.getUserId()))) {
				String accessToken = jwtUtil.createAccessToken(userDto.getUserId());
				log.debug("token : {}", accessToken);
				log.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				status = HttpStatus.CREATED;
			}
		} else {
			log.debug("리프레쉬토큰도 사용불가!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
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
	
	@ApiOperation(value = "아이디 중복확인.", notes = "회원가입 시 아이디 중복확인에 사용.")
	@PostMapping("/checkId")
	public ResponseEntity<?> checkId(@RequestBody String userId){
		try {
			boolean notValid = userService.checkId(userId);
			if(notValid) {
				return ResponseEntity.status(HttpStatus.OK).body(false); 
			}else {
				return ResponseEntity.status(HttpStatus.OK).body(true); 
				
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
