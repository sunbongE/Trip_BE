package com.ssafy.trip.model;
import lombok.Data;

@Data
public class GugunDto {

	private int gugunCode;
	private String gugunName;
	private int sidoCode;


	@Override
	public String toString() {
		return "GugunDto [gugunCode=" + gugunCode + ", gugunName=" + gugunName + ", sidoCode=" + sidoCode + "]";
	}

}
