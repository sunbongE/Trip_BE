package com.ssafy.plan.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.plan.model.PlanDto;
import com.ssafy.plan.model.PlanInfoDto;
import com.ssafy.plan.model.PlanRegisterRequestDto;
import com.ssafy.plan.model.PlanSearchResponseDto;
import com.ssafy.plan.model.PlanUpdateRequestDto;
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
	public void updatePlan(PlanUpdateRequestDto dto) throws SQLException {
		planMapper.deletePlanInfoByPlanId(dto.getId());
		
		PlanDto planDto = new PlanDto();
		planDto.setSubject(dto.getSubject());
		planDto.setDescription(dto.getDescription());
		planDto.setId(dto.getId());
		
		planMapper.updatePlanById(planDto);
		for(int i=0; i<dto.getInfos().size(); i++) {
			PlanInfoDto planInfoDto = new PlanInfoDto();
			planInfoDto = dto.getInfos().get(i);
			planInfoDto.setPlanId(planDto.getId());
			planMapper.registPlanInfo(planInfoDto);
		}
	}

	@Override
	public List<PlanSearchResponseDto> searchAll() throws SQLException {
		// TODO Auto-generated method stub
		List<PlanDto> planList = planMapper.selectPlanAll();
		
		List<PlanSearchResponseDto> list = new ArrayList<>();
		for(int index=0; index < planList.size() ; index++) {
			PlanSearchResponseDto dto = new PlanSearchResponseDto();
			List<PlanInfoDto> infoDtos = planMapper.searchPlanInfoByPlanId(planList.get(index).getId());;
			dto.setId(planList.get(index).getId());
			dto.setSubject(planList.get(index).getSubject());
			dto.setDescription(planList.get(index).getDescription());
			dto.setUserId(planList.get(index).getUserId());
			dto.setInfos(infoDtos);
			list.add(dto);
		}
		return list;
	}
	
	@Override
	public PlanSearchResponseDto searchPlanById(int id) throws SQLException {
		PlanSearchResponseDto dto = new PlanSearchResponseDto();
		PlanDto planDto = planMapper.findPlanById(id);
		List<PlanInfoDto> infoDtos = planMapper.searchPlanInfoByPlanId(id);
		
		dto.setId(planDto.getId());
		dto.setSubject(planDto.getSubject());
		dto.setDescription(planDto.getDescription());
		dto.setUserId(planDto.getUserId());
		dto.setInfos(infoDtos);
		
		return dto;
	}

	@Override
	public List<PlanDto> searchPlanByUserId(String userId) throws SQLException {
		return planMapper.searchPlanByUserId(userId);
	}

	/*
	 * public ReturnType 동행찾기로_슛(PlanTo동행슛DTO){
	 * 	동행서비스.sharePlanTo동행(dto)
	 * ...
	 * } => 이후의 로직은 동행에서 처리한다.
	 * 아니면 id만 그냥 보내도 괜찮을듯..? 어차피 front 에서 url 호출로 data 상세 접근이 가능하기 때문 !
	 * 애초에 동행으로 슛메서드 자체가 여기 없어도 괜찮을 듯 하다.
	 * */
}
