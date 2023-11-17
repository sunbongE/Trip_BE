package com.ssafy.alarm.model;

import lombok.Data;

@Data
public class AlarmDto {
	private int id;
	private int type;
	private String fromUserId;
	private String toUserId;
	private String registerTime;
	private boolean isRead;
}
