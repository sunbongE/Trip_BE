package com.ssafy.alarm.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.alarm.model.AlarmDto;
@Mapper
public interface AlarmMapper {
	
	List<AlarmDto>getAlarm(String userId);
	
	void sendAlarm(AlarmDto alarmDto);
	
	void deleteAlarm(int id);
	
	void readAlarm(int id);
	
}
