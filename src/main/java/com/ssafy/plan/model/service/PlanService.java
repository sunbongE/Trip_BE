package com.ssafy.plan.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.plan.model.PlanDto;
import com.ssafy.plan.model.PlanInfoDto;
import com.ssafy.plan.model.PlanRegisterRequestDto;
import com.ssafy.plan.model.PlanSearchResponseDto;
import com.ssafy.plan.model.PlanUpdateRequestDto;

public interface PlanService {

	// 저장하는 녀석
	void regist(PlanRegisterRequestDto dto) throws SQLException;
	void deletePlanById(int id) throws SQLException;
	void updatePlan(PlanUpdateRequestDto dto) throws SQLException;
	List<PlanSearchResponseDto> searchAll() throws SQLException;
	PlanSearchResponseDto searchPlanById(int id) throws SQLException;
	List<PlanDto> searchPlanByUserId(String userId) throws SQLException;
}
