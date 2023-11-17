package com.ssafy.club.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.club.model.ClubDto;
import com.ssafy.club.model.ClubStatusDto;
import com.ssafy.club.model.mapper.ClubMapper;
import com.ssafy.club.model.mapper.ClubStatusMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClubStatusServiceImpl implements ClubStatusService {

	private final ClubStatusMapper mapper;
	private final ClubMapper clubMapper;

	public ClubStatusServiceImpl(ClubStatusMapper mapper, ClubMapper clubMapper) {
		this.mapper = mapper;
		this.clubMapper = clubMapper;
	}

	@Override
	public void insertClubStatus(ClubStatusDto dto) throws Exception {
		int clubId = dto.getClubId();
		Map<String, Object> map = mapper.checkTotalCnt(clubId);

		Integer totalCount = (Integer) map.get("total_cnt");
		Long people = (Long) map.get("people");

		// 들어갈 수 있다면
		if (people + 1 <= totalCount) {
			mapper.insertClubStatus(dto);
			if (totalCount - people == 1) {
				ClubDto clubDto = new ClubDto();
				clubDto.setId(clubId);
				clubDto.setStatus(2);

				clubMapper.updateClub(clubDto);
			}
		} else {
			throw new Exception("요청 불가");
		}
	}

	@Override
	public List<ClubStatusDto> searchByToUserId(String toUserId) throws SQLException {
		return mapper.searchByToUserId(toUserId);
	}

	@Override
	public List<ClubStatusDto> searchByFromUserId(String fromUserId) throws SQLException {
		return mapper.searchByFromUserId(fromUserId);
	}

	@Override
	public void deleteById(int id) throws SQLException {
		mapper.deleteById(id);
	}

	@Override
	public void replyToAnswer(Map<String, Object> map) throws SQLException {
		// 102, 101 에 따라서 member에 넣어주는거 변경 필요
		mapper.replyToAnswer(map);
		ClubStatusDto statusDto = mapper.findById((int) map.get("id"));
		int answer = (int) map.get("answer");
		switch (answer) {
		case 101: // 수락
			// 수락 알람 넣기
			int clubId = statusDto.getClubId();
			String userId = statusDto.getFromUserId();
			Map<String, Object> mem = new HashMap<>();
			mem.put("clubId", clubId);
			mem.put("userId", userId);
			clubMapper.initClubMember(mem);
			// 사람다찼는데 승낙하는거막아야함
			break;
		case 102: // 거부
			// 거부알람 넣기
			break;
		default:
			break;
		}
	}
}
