package com.fridgeCare.fri.juhyun.util;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import com.fridgeCare.fri.juhyun.DAO;
import com.fridgeCare.fri.juhyun.MailSenderService;
import com.fridgeCare.fri.juhyun.vo.AdminVO;
import com.fridgeCare.fri.service.AdminService;

/**
 *스케쥴 Job 실행 클래스
 */
public class JobExecutor{
	
	@Autowired
	DAO aDao;
	
	@Autowired
	MailSenderService mailSender;
	
	@Autowired
	AdminService aSrvc;
	
	@Scheduled(cron="0 03 18 * * ?")
	@Transactional
	public void jobScheduler(){
		// 메일을 보낼 사람들의 아이디 메일을 리스트에 담는다
		System.out.println("#### 스케쥴러 실행 성공 ####");
		System.out.println("member 테이블 조회중");
		List<AdminVO> list = aDao.getLoginInfo();
		
		// 최종 로그인 1년이 지난 사람수 만큼 반복
		for(int i = 0 ; i < list.size(); i++){
			// 반복횟수만큼 mailSender로 메일을 전송
			System.out.println("메일 보내는중");
			mailSender.sendAuthMail(list.get(i).getMail(), list.get(i).getId());
			System.out.println("메일 보내기 완료");
			
			// delinfo 테이블 데이터 생성
			System.out.println("테이블 생성중");
			int cnt = aDao.addDelInfo(list.get(i));
			
		}
		
		// delete 테이블 7일 지난 데이터 조회
		System.out.println("delete 테이블 조회중");
		List<AdminVO> delList = aDao.getDelInfo();
		
		// 삭제대기 7일 이상인 사람수 만큼 반복
		for(int i = 0 ; i < delList.size(); i++) {
			// id로 mno 받아오고
			System.out.println("mno 변환중");
			delList.get(i).setMno( aDao.getMno(delList.get(i).getId()) );
			
			// 'N'인 계정 전부 삭제
			System.out.println("삭제중");
			aSrvc.deleteMemb(delList.get(i));
			
			// delete 테이블 메일 보낸 시간이 7일이상인 데이터 이즈쇼 N으로 변경
			System.out.println("이즈쇼 변경중");
			aDao.setIsshow(delList.get(i).getId());
			
		}
		
	}

}
