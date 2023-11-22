package com.ssafy.alarm.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.alarm.model.AlarmDto;
import com.ssafy.alarm.model.mapper.AlarmMapper;
@Service
public class AlarmServiceImpl implements AlarmService {

	AlarmMapper alarmMapper;
	
	public AlarmServiceImpl(AlarmMapper alarmMapper) {
		super();
		this.alarmMapper = alarmMapper;
	}

	@Override
	public List<AlarmDto> getAlarm(String userId) {
		return alarmMapper.getAlarm(userId);
	}

	@Override
	public void sendAlarm(AlarmDto alarmDto) {
		alarmMapper.sendAlarm(alarmDto);
	}

	@Override
	public void deleteAlarm(String userId) {
		alarmMapper.deleteAlarm(userId);
	}

	@Override
	public void readAlarm(int id) {
		alarmMapper.readAlarm(id);		
	}

}
