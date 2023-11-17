package com.ssafy.user.model.mapper;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.user.model.UserDto;

@Mapper
public interface UserMapper {

	void join(UserDto userDto) throws SQLException;
	
	UserDto login(Map<String, String> map) throws SQLException;
	
	int update(UserDto userDto) throws SQLException;
	
	void delete(String userId) throws SQLException;
	
	boolean checkId(String userId) throws SQLException;
	
	UserDto findByUserId(String userId) throws SQLException;
	
	String findUserId(Map<String, String> map);
	
	String findUserPwd(Map<String, String> map);
	
	int updatePasswordInfo(Map<String, String> map) throws Exception;
	
	void saveRefreshToken(Map<String, String> map) throws SQLException;
	
	Object getRefreshToken(String userid) throws SQLException;
	
	void deleteRefreshToken(Map<String, String> map) throws SQLException;
}
