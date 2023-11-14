package com.ssafy.trip.model;
import lombok.Data;

@Data
public class AttractionDescriptionDto {

	private int contentId;
	private String homepage;
	private String overview;
	private String telname;


	@Override
	public String toString() {
		return "AttractionDescriptionDto [contentId=" + contentId + ", homepage=" + homepage + ", overview=" + overview
				+ ", telname=" + telname + "]";
	}

}
