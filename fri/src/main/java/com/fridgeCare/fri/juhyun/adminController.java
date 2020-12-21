package com.fridgeCare.fri.juhyun;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fridgeCare.fri.juhyun.vo.AdminVO;
import com.fridgeCare.fri.service.AdminService;

@Controller
@RequestMapping("/juhyun/admin")
public class adminController {
	
	@Autowired
	DAO aDao;
	@Autowired
	AdminService aSrvc;
	
	// 관리자 계정 로그인 폼 보기 함수
	@RequestMapping("/admin.fri")
	public ModelAndView adminLogin(ModelAndView mv) {
		mv.setViewName("juhyun/admin/admin");
		return mv;
	}
	
	@RequestMapping("/waitingPage.fri")
	public ModelAndView waitingPage(ModelAndView mv) {
		mv.setViewName("juhyun/admin/waitingPage");
		return mv;
	}
	
	@RequestMapping("/wordCencerPage.fri")
	public ModelAndView wordCencerPage(ModelAndView mv) {
		mv.setViewName("juhyun/admin/wordCencerPage");
		return mv;
	}
	
	@RequestMapping("/schedulerPage.fri")
	public ModelAndView schedulerPage(ModelAndView mv) {
		mv.setViewName("juhyun/admin/schedulerPage");
		return mv;
	}
	
	@RequestMapping("/deletePage.fri")
	public ModelAndView deletePage(ModelAndView mv) {
		mv.setViewName("juhyun/admin/deletePage");
		return mv;
	}
	
	@RequestMapping("/deletePageProc.fri")
	public ModelAndView deletePageProc(ModelAndView mv, HttpSession session, AdminVO aVO) {
		
		AdminVO info = aDao.getMemb(aVO.getId());
		
		mv.addObject("INFO",info);
		
		mv.setViewName("juhyun/admin/deletePage");
		return mv;
	}
	
	@RequestMapping("/delete.fri")
	public ModelAndView delete(ModelAndView mv,AdminVO aVO) {
		
		
		//aVO.setMno(aDao.getMno(aVO.getId()));
		aVO.setMno(aDao.getMno("jiwoo"));
		
		System.out.println("mno : " + aVO.getMno());
		
		aSrvc.deleteMemb(mv, aVO, aDao);
		
		mv.setViewName("juhyun/admin/deletePage");
		return mv;
	}
}
