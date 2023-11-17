package com.ssafy.user.model.service;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.user.model.UserDto;

public interface UserService {

	void join(UserDto userDto) throws SQLException;
	UserDto login(Map<String, String> map) throws SQLException, Exception;
	int update(UserDto userDto) throws SQLException;
	void delete(String userId) throws SQLException;
	boolean checkId(String userId) throws SQLException;
	UserDto findByUserId(String userId) throws SQLException;
	String findUserId(Map<String, String> map)throws Exception;
	String findUserPwd(Map<String, String> map) throws Exception;
	
	String generateTempPassword(String userId) throws Exception;

	void saveRefreshToken(String userId, String refreshToken) throws Exception;
	Object getRefreshToken(String userId) throws Exception;
	void deleRefreshToken(String userId) throws Exception;
	
}
