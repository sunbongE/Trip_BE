package com.ssafy.plan.model;

import java.util.List;

import lombok.Data;

@Data
public class PlanSearchResponseDto {

	private int id;
	private String userId;
	private String subject;
	private String description;
	private List<PlanInfoDto> infos;
}
