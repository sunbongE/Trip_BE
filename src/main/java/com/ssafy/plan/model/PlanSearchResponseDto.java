package com.ssafy.plan.model;

import java.util.List;

import lombok.Data;

@Data
public class PlanSearchResponseDto {

	private int userId;
	private String description;
	private List<PlanInfoDto> infos;
}
