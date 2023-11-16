package com.ssafy.plan.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.plan.model.PlanDto;
import com.ssafy.plan.model.PlanInfoDto;
import com.ssafy.plan.model.PlanRegisterRequestDto;
import com.ssafy.plan.model.mapper.PlanMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PlanServiceImpl implements PlanService {

	private final PlanMapper planMapper;

	public PlanServiceImpl(PlanMapper planMapper) {
		this.planMapper = planMapper;
	}

	@Override
	public void regist(PlanRegisterRequestDto dto) throws SQLException {
		// 1. 파싱
		log.debug("\t Regist DTO : {}", dto);
		PlanDto planDto = new PlanDto();
		planDto.setUserId(dto.getUserId());
		planDto.setSubject(dto.getSubject());
		planDto.setDescription(dto.getDescription());
		planMapper.registPlan(planDto);
		
		PlanInfoDto planInfoDto;
		for(int i=0; i<dto.getInfos().size(); i++) {
			planInfoDto = dto.getInfos().get(i);
			planInfoDto.setPlanId(planDto.getId());
			planMapper.registPlanInfo(planInfoDto);
		}
	}

	@Override
	public void deletePlanById(int id) throws SQLException {
		planMapper.deletePlanById(id);
	}

	@Override
	public void updatePlan(PlanRegisterRequestDto dto) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
