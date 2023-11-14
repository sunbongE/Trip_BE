package com.ssafy.trip.model;
import lombok.Data;

@Data
public class SidoDto {

	private int sidoCode;
	private String sidoName;


	@Override
	public String toString() {
		return "SidoDto [sidoCode=" + sidoCode + ", sidoName=" + sidoName + "]";
	}

}
