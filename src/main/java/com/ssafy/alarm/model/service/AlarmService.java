package com.ssafy.alarm.model.service;

import java.util.List;

import com.ssafy.alarm.model.AlarmDto;

public interface AlarmService {
	List<AlarmDto>getAlarm(String userId);
	
	void sendAlarm(AlarmDto alarmDto);
	
	void deleteAlarm(String userId);
	
	void readAlarm(int id);
}
