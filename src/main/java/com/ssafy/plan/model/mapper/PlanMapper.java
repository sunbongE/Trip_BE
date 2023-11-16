package com.ssafy.plan.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.plan.model.PlanDto;
import com.ssafy.plan.model.PlanInfoDto;

@Mapper
public interface PlanMapper {
	
	void registPlan(PlanDto planDto) throws SQLException;
	void updatePlanById(PlanDto planDto) throws SQLException;
	void deletePlanById(int id) throws SQLException;
	PlanDto findPlanById (int id) throws SQLException;
	
	void registPlanInfo(PlanInfoDto planInfoDto) throws SQLException;
	void deletePlanInfo(Map<String, String> map) throws SQLException;
	List<PlanInfoDto> searchPlanInfoByPlanId(int planId) throws SQLException;
	
}
