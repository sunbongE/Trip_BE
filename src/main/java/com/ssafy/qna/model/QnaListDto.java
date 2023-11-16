package com.ssafy.qna.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@ApiModel(value = "QnaListDto : Qna 목록 & 페이지 정보", description = "qna 목록과 현재 페이지와 전체 페이지 정보를 나타낸다.")
public class QnaListDto {
	@ApiModelProperty(value = "문의글 목록")
	private List<QnaDto> qnalist;
	@ApiModelProperty(value = "현재 페이지번호")
	private int currentPage;
	@ApiModelProperty(value = "전체 페이지 수")
	private int totalPageCount;

}
