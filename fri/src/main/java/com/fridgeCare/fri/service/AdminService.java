package com.fridgeCare.fri.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Bean;
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
			
			System.out.println("### 계정 삭제 성공 ###");
		} catch(Exception e) {
			e.printStackTrace();
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
	
	@Transactional
	public boolean cencerAll() {
		try {
			List<AdminVO> list = aDao.getCencerList();
			/*
			 * List<AdminVO> board = aDao.getCencerBoard(); List<AdminVO> boardpart =
			 * aDao.getCencerBoardPart(); List<AdminVO> reply = aDao.getCencerReply();
			 */
			
			for(int i = 0 ; i < list.size(); i++ ) {
				//int cnt = aDao.getCencerCnt(list.get(i).getBody());
			}
			System.out.println("모든 게시글 검열 성공");
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("모든 게시글 검열 실패");
			return false;
		}
	}
	
	@Transactional
	public boolean cencerAll(String body) {
		/*
		 * List<AdminVO> board = aDao.getCencerBoard(); List<AdminVO> boardpart =
		 * aDao.getCencerBoardPart(); List<AdminVO> reply = aDao.getCencerReply();
		 */
		
		// 중복 확인
		System.out.println("넘어온 검열 단어 : " + body);
		int cnt = aDao.getCencerCnt(body);
		if(cnt == 1) {
			// 단어 중복 
			System.out.println("이미 등록되어있는 단어입니다.");
			return false;
		} else {
			// 단어 생성 가능
			
			System.out.println("검열단어로 게시글 검색중...");
			List<Integer> blist = aDao.getCencerBoard(body);
			for(int i = 0; i < blist.size(); i++) {
				System.out.println("검열된 board : " + blist.get(i));
				
				int mno = aDao.getBoardMno(blist.get(i));
				
				aDao.deleteUserLike(mno);
				aDao.deleteBoardPart(blist.get(i));
				aDao.deleteBoardReply(blist.get(i));
				int tno = aDao.getBnoThumb(blist.get(i));
				aDao.deleteBoardBno(blist.get(i));
				aDao.deleteBoardThumb(tno);
			}
			List<Integer> bplist = aDao.getCencerBoardPart(body);
			for(int i = 0 ; i < bplist.size(); i++) {
				System.out.println("검열된 boardpart : " + bplist.get(i));
				
				aDao.deleteCencerBoardPart(bplist.get(i));
			}
			
			System.out.println("검열단어로 댓글 검색중...");
			List<Integer> rlist = aDao.getCencerReply(body);
			for(int i = 0 ; i < rlist.size(); i++) {
				System.out.println("검열된 reply : " + rlist.get(i));
				
				aDao.deleteCencerReply(rlist.get(i));
			}
			
			System.out.println("검열단어로 회원ID 검색중...");
			List<AdminVO> mlist = aDao.getCencerMember(body);
			for(int i = 0; i < mlist.size(); i++) {
				System.out.println("검열된 id : " + mlist.get(i));
				deleteMemb(mlist.get(i));
			}
			
			System.out.println("해당 단어 검열 성공");
			
			
			aDao.addCencer(body);
			System.out.println("단어 생성 완료.");
			
			return true;
		}
	}
	
	/*
	 * @Bean public List<AdminVO> cencerList(){ List<AdminVO> list =
	 * aDao.getCencerList(); return list; }
	 */
}
