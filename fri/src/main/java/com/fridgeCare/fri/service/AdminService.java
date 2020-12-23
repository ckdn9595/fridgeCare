package com.fridgeCare.fri.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.ModelAndView;

import com.fridgeCare.fri.juhyun.DAO;
import com.fridgeCare.fri.juhyun.vo.AdminVO;

public class AdminService {
	@Autowired
	DAO bDao;
	
	@Autowired
	DAO aDao;
	
	// 게시글 등록 처리함수
	@Transactional
	public void deleteMemb(ModelAndView mv, AdminVO aVO, DAO aDao) {
		try{
			
			System.out.println("mno : " + aVO.getMno());
			aDao.deleteThumb(aVO.getMno());
			aDao.deleteReply(aVO.getMno());
			List<AdminVO> list = aDao.getBnoList(aVO.getMno());
			
			aDao.deleteUserLike(aVO.getMno());
			
			for(int i = 0 ; i < list.size(); i++) {
				System.out.println("list i bno : " + list.get(i).getBno());
				
				aDao.deleteBoardPart(list.get(i).getBno());
				aDao.deleteBoardReply(list.get(i).getBno());
				aDao.deleteLikeBno(list.get(i).getBno());
			}
			List<AdminVO> thumb = aDao.getBoardThumb(aVO.getMno());
			aDao.deleteBoard(aVO.getMno());
			for(int i = 0; i < thumb.size(); i++) {
				System.out.println("삭제 tno : " + thumb.get(i).getTno());
				aDao.deleteBoardThumb(thumb.get(i).getTno());
			}
			aDao.deleteMemb(aVO.getMno());
			
			mv.addObject("VIEW", "/fri/juhyun/admin/deletePage.fri");
			System.out.println("### 계정 삭제 성공 ###");
		} catch(Exception e) {
			e.printStackTrace();
			mv.addObject("VIEW", "/fri/juhyun/admin/deletePage.fri");
			System.out.println("### 계정 삭제 실패 ####");
		}
		
		mv.setViewName("juhyun/admin/deletePageProc");
		
		return;
	}
	
	@Transactional
	public void deleteMemb(AdminVO aVO) {
		try{
			
			System.out.println("mno : " + aVO.getMno());
			aDao.deleteThumb(aVO.getMno());
			aDao.deleteReply(aVO.getMno());
			List<AdminVO> list = aDao.getBnoList(aVO.getMno());
			
			aDao.deleteUserLike(aVO.getMno());
			
			for(int i = 0 ; i < list.size(); i++) {
				System.out.println("list i bno : " + list.get(i).getBno());
				
				aDao.deleteBoardPart(list.get(i).getBno());
				aDao.deleteBoardReply(list.get(i).getBno());
				aDao.deleteLikeBno(list.get(i).getBno());
			}
			aDao.deleteBoard(aVO.getMno());
			List<AdminVO> thumb = aDao.getBoardThumb(aVO.getMno());
			for(int i = 0; i < thumb.size(); i++) {
				System.out.println("삭제 tno : " + thumb.get(i).getTno());
				aDao.deleteBoardThumb(thumb.get(i).getTno());
			}
			aDao.deleteMemb(aVO.getMno());
			
			System.out.println("### 계정 삭제 성공 ###");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("### 계정 삭제 실패 ####");
		}
		
		
		return;
	}
	
	public void cencerBoard(List<AdminVO> list) {
		try {
			
			System.out.println("게시글 검열 성공");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("게시글 검열 실패");
		}
	}
}
