package com.fridgeCare.fri.juhyun;

import java.util.List;

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
	@RequestMapping("/addCencerProc.fri")
	public ModelAndView addCencerProc(ModelAndView mv,AdminVO aVO) {
		
		System.out.println("body : " + aVO.getBody());
		aDao.addCencer(aVO.getBody());
		
		aSrvc.cencerAll();
		mv.setViewName("juhyun/admin/wordCencerPage");
		return mv;
	}
	
	
	
	
	@RequestMapping("/deletePage.fri")
	public ModelAndView deletePage(ModelAndView mv) {
		mv.setViewName("juhyun/admin/deletePage");
		return mv;
	}
	
	@RequestMapping("/deletePageProc.fri")
	public ModelAndView deletePageProc(ModelAndView mv, HttpSession session, AdminVO aVO) {
		
		System.out.println("id : " + aVO.getId());
		AdminVO info = aDao.getMemb(aVO.getId());
		mv.addObject("INFO",info);
		
		mv.setViewName("juhyun/admin/deletePage");
		return mv;
	}
	
	@RequestMapping("/delete.fri")
	public ModelAndView delete(ModelAndView mv,AdminVO aVO) {
		try {
			aVO.setMno(aDao.getMno(aVO.getId()));
			
			System.out.println("mno : " + aVO.getMno());
			
			aSrvc.deleteMemb(mv, aVO, aDao);
		}catch(Exception e) {}
		mv.setViewName("juhyun/admin/deletePage");
		return mv;
	}
}
