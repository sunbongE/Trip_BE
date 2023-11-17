package com.ssafy.club.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.club.model.ClubDto;
import com.ssafy.club.model.ClubMemberDto;
import com.ssafy.club.model.mapper.ClubMapper;

@Service
public class ClubServiceImpl implements ClubService{

	private final ClubMapper clubMapper;
	
	public ClubServiceImpl(ClubMapper clubMapper) {
		this.clubMapper = clubMapper;
	}
	
	@Override
	public void registClub(ClubDto clubDto) throws SQLException {
		clubMapper.registClub(clubDto);
		
		int clubId = clubDto.getId();
		String userId = clubDto.getUserId();
		Map<String, Object> map = new HashMap<>();
		map.put("clubId", clubId);
		map.put("userId", userId);
		clubMapper.initClubMember(map);
	}
	
	@Override
	public void updateClub(ClubDto clubDto) throws SQLException {
		clubMapper.updateClub(clubDto);
	}

	@Override
	public void deleteClub(int id) throws SQLException {
		clubMapper.deleteClub(id);
	}

	@Override
	public List<ClubDto> searchClubAll() throws SQLException {
		return clubMapper.searchClubAll();
	}

	@Override
	public ClubDto findClubById(int id) throws SQLException {
		return clubMapper.findClubById(id);
	}

	@Override
	public List<ClubMemberDto> searchClubMemberByClubId(int clubId) throws SQLException {
		return clubMapper.searchClubMemberByClubId(clubId);
	}

	@Override
	public void deleteClubMemberById(int id) throws SQLException {
		clubMapper.deleteClubMemberById(id);
	}

	@Override
	public List<ClubDto> searchMyClubs(String userId) throws SQLException {
		return clubMapper.searchMyClubs(userId);
	}
}

	
