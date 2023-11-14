package com.ssafy.user.model.service;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.user.model.UserDto;
import com.ssafy.user.model.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	private final UserMapper userMapper;
	
	public UserServiceImpl(UserMapper userMapper) {
		this.userMapper = userMapper;
	}
	
	@Override
	public void join(UserDto userDto) throws SQLException {
		userMapper.join(userDto);
	}

	@Override
	public UserDto login(Map<String, String> map) throws SQLException {
		return userMapper.login(map);
	}

	@Override
	public int update(UserDto userDto) throws SQLException {
		return userMapper.update(userDto);
		
	}

	@Override
	public void delete(String userId) throws SQLException {
		userMapper.delete(userId);
		
	}

	@Override
	public boolean checkId(String userId) throws SQLException {
		return userMapper.checkId(userId);
	}

	@Override
	public UserDto findByUserId(String userId) throws SQLException {
		return userMapper.findByUserId(userId);
	}

	@Override
	public String findUserId(Map<String, String> map) {
		return userMapper.findUserId(map);
	}

	@Override
	public String findUserPwd(Map<String, String> map) {
		return userMapper.findUserPwd(map);
	}

}
