package com.fridgeCare.fri.juhyun;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/juhyun/admin")
public class adminController {
	
	@Autowired
	DAO aDao;
	
	// 관리자 계정 로그인 폼 보기 함수
	@RequestMapping("/admin.fri")
	public ModelAndView adminLogin(ModelAndView mv) {
		
		mv.setViewName("juhyun/admin/admin");
		return mv;
	}
}
