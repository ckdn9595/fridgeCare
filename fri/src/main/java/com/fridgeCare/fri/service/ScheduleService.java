package com.fridgeCare.fri.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fridgeCare.fri.juhyun.vo.AdminVO;

@Service("scheduleService")
public interface ScheduleService {
	public void getLastLogin();
}
