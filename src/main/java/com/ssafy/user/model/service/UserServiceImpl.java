package com.ssafy.user.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.user.model.ProfileInfoDto;
import com.ssafy.user.model.UserDto;
import com.ssafy.user.model.mapper.UserMapper;
import com.ssafy.user.util.UserUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;

	public UserServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
	public void join(UserDto userDto) throws SQLException {
		String password = userDto.getUserPassword();
		// 1. salt 생성
		String salt = UserUtil.generateSalt();
		log.debug("Generated Salt : {} ", salt);
		
		// 2. salt + stretching
		try {
			String hashedPassword = UserUtil.hashingPassword(password, salt);
			log.debug("Hashed Password : {} ", hashedPassword);
			
			// 3. salt 와 hashedPw 할당
			userDto.setSalt(salt);
			userDto.setUserPassword(hashedPassword);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		userMapper.join(userDto);
	}

	@Override
	public UserDto login(Map<String, String> map) throws SQLException, Exception {
		// pw 바꿔서 넣어주기
		String userId = map.get("userId");
		String inputUserPassword = map.get("userPassword");
		log.debug("\t LOGIN USERID : {} ", userId);
		log.debug("\t LOGIN USERPASSWORD : {}", inputUserPassword);

		// 1. id 를 통해 유저정보를 가져와서
		UserDto userDto = userMapper.findByUserId(userId);
		
		// 2. 해당 salt 로 input pw를 해싱 후
		String hashedInputUserPassword = getHashedPasswordWithSalt(inputUserPassword, userDto.getSalt());
		
		// 3. 가져온 정보의 pw 와 비교
		log.debug("\t Stored Password : {}", userDto.getUserPassword());
		log.debug("\t Hashed Input Password : {}", hashedInputUserPassword);
		if(hashedInputUserPassword.equals(userDto.getUserPassword())) {
			map.replace("userPassword", hashedInputUserPassword);
			return userMapper.login(map);
		} else {
			return new UserDto();
		}
	}

	@Override
	public int update(UserDto userDto) throws SQLException {
		// 2. salt + stretching
		try {
			log.debug("REQUEST USERDTO : {}", userDto );
			if( userDto.getUserPassword() != null && !userDto.getUserPassword().equals("")) {
			String password = userDto.getUserPassword();
			// 1. salt 생성
			String salt = UserUtil.generateSalt();
			log.debug("Generated Salt : {} ", salt);
			
			String hashedPassword = UserUtil.hashingPassword(password, salt);
			log.debug("Hashed Password : {} ", hashedPassword);
			
			// 3. salt 와 hashedPw 할당
			userDto.setSalt(salt);
			userDto.setUserPassword(hashedPassword);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		return userMapper.update(userDto);

	}

	@Override
	public void delete(String userId) throws SQLException {
		userMapper.delete(userId);

	}

	@Override
	public boolean checkId(String userId) throws SQLException {
//		System.out.println("넘어가는애 ==> "+userId);
		boolean result = false;
		String cur = userMapper.checkId(userId);
//		System.out.println(cur);
		if(cur == null) { // 중복된경우.
			result=true;
		}
		return result;
	}

	@Override
	public UserDto findByUserId(String userId) throws SQLException {
		return userMapper.findByUserId(userId);
	}

	@Override
	public String findUserId(Map<String, String> map) throws Exception {
		String userId =  userMapper.findUserId(map);
		log.debug("\t FIND User ID : {}" , userId);
		UserDto userDto = userMapper.findByUserId(userId);

		String inputUserPassword = map.get("userPassword");
		System.out.println(userDto.getSalt());
		log.debug("\t Password user inputed : {}" , inputUserPassword);
		// 2. 해당 salt 로 input pw를 해싱 후
		String hashedInputUserPassword = getHashedPasswordWithSalt(inputUserPassword, userDto.getSalt());
		System.out.println(hashedInputUserPassword);
		System.out.println(userDto.getUserPassword());
		if(hashedInputUserPassword.equals(userDto.getUserPassword())) {
			return userId;
		} 
		return ""; // exception
	}

	@Override
	public String findUserPwd(Map<String, String> map) throws Exception {
		return userMapper.findUserPwd(map);
	}

	@Override
	public String generateTempPassword(String userId) throws Exception {
		String tempPassword = UserUtil.generateTempPassword();
		String newSalt = UserUtil.generateSalt();
		String newHashedPwd = UserUtil.hashingPassword(tempPassword, newSalt);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("salt", newSalt);
		map.put("userPassword", newHashedPwd);
		map.put("userId", userId);
		
		userMapper.updatePasswordInfo(map);
		return tempPassword;
	}
	
	private static String getHashedPasswordWithSalt(String password, String salt) throws Exception {
		return UserUtil.hashingPassword(password, salt);
	}

	@Override
	public void saveRefreshToken(String userId, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", refreshToken);
		userMapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userId) throws Exception {
		return userMapper.getRefreshToken(userId);
	}

	@Override
	public void deleRefreshToken(String userId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", userId);
		map.put("token", null);
		userMapper.deleteRefreshToken(map);
	}

	@Override
	public void registerProfile(ProfileInfoDto dto) throws SQLException {
		if(userMapper.findProfileByUserId(dto.getUserId()) != null) {
			userMapper.deleteByUserId(dto.getUserId());
		}
		userMapper.registerProfile(dto);
	}

	@Override
	public void deleteByUserId(String userId) throws SQLException {
		userMapper.deleteByUserId(userId);
	}

	@Override
	public ProfileInfoDto findProfileByUserId(String userId) throws SQLException {
		return userMapper.findProfileByUserId(userId);
	}

	@Override
	public int isCorrectPwd(Map<String, String> map) throws Exception {
		String userId = map.get("userId");
		UserDto userDto = userMapper.findByUserId(userId);

		String inputUserPassword = map.get("userPassword");
		System.out.println(userDto.getSalt());
		log.debug("\t Password user inputed : {}" , inputUserPassword);
		// 2. 해당 salt 로 input pw를 해싱 후
		String hashedInputUserPassword = getHashedPasswordWithSalt(inputUserPassword, userDto.getSalt());
		System.out.println(hashedInputUserPassword);
		System.out.println(userDto.getUserPassword());
		if(hashedInputUserPassword.equals(userDto.getUserPassword())) {
			return 1;
		} 
		return 0; // exception
	}
}
