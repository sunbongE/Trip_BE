package com.ssafy.plan.model;

import java.util.List;

import lombok.Data;

@Data
public class PlanRegisterRequestDto {

	private String userId;
	private String subject;
	private String description;
	private List<PlanInfoDto> infos;
}
