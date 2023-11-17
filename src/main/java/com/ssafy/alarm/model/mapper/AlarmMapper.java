package com.ssafy.alarm.model.mapper;

import java.util.List;

import com.ssafy.alarm.model.AlarmDto;

public interface AlarmMapper {
	
	List<AlarmDto>getAlarm(String userId);
	
	void sendAlarm(AlarmDto alarmDto);
	
	void deleteAlarm(int id);
	
	void readAlarm(int id);
	
}
