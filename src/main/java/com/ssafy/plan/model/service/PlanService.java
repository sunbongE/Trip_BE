package com.ssafy.plan.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.plan.model.PlanDto;
import com.ssafy.plan.model.PlanInfoDto;
import com.ssafy.plan.model.PlanRegisterRequestDto;

public interface PlanService {

	// 저장하는 녀석
	void regist(PlanRegisterRequestDto dto) throws SQLException;
	void deletePlanById(int id) throws SQLException;
	void updatePlan(PlanRegisterRequestDto dto) throws SQLException;
	
}
