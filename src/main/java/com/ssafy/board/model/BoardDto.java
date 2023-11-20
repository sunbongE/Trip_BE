package com.ssafy.board.model;

import java.io.Serializable; 
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class BoardDto {

	private int articleNo;
	
	private String userId;
	
	private String subject;
	
	private String content;
	
	private int hit;
	
	private String registerTime;
	
	private int recomment;
	
	private int category;
	
	private int contentId;
	
	@ApiModelProperty(value = "업로드 파일정보")
	private List<FileInfoDto> fileInfos;
}
