package com.ssafy.club.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.club.model.ClubDto;
import com.ssafy.club.model.ClubMemberDto;

public interface ClubService {

	void registClub(ClubDto clubDto) throws SQLException;

	void updateClub(ClubDto clubDto) throws SQLException;
	
	void deleteClub(int id) throws SQLException;
	
	List<ClubDto> searchClubAll() throws SQLException;
	
	ClubDto findClubById(int id) throws SQLException;
	
	List<ClubMemberDto> searchClubMemberByClubId(int clubId) throws SQLException;
	
	void deleteClubMemberById(int id) throws SQLException;
	
	List<ClubDto> searchMyClubs(String userId) throws SQLException;
}
