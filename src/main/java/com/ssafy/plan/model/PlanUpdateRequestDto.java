package com.ssafy.plan.model;

import java.util.List;

import lombok.Data;

@Data
public class PlanUpdateRequestDto {

	private int id; // plan의 pk id
	private String userId;
	private String subject;
	private String description;
	private List<PlanInfoDto> infos;
}
