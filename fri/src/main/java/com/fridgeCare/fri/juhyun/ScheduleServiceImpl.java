package com.fridgeCare.fri.juhyun;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fridgeCare.fri.juhyun.vo.AdminVO;
import com.fridgeCare.fri.service.ScheduleService;

@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {
	@Autowired
	DAO aDao;
	
	@Override
	public void getLastLogin(){
		System.out.println("test test");
	}
}
