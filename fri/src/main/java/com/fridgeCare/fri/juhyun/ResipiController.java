package com.fridgeCare.fri.juhyun;


import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.fridgeCare.fri.juhyun.vo.ResipiVO;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
@Controller
@RequestMapping("/juhyun/recipe")
public class ResipiController {
	
	@Autowired
	DAO rDao;
	
	// 레시피 페이지 폼 보기 요청 처리함수
	@RequestMapping(value = "/resipiPage.fri" , method=RequestMethod.GET)
	//@RequestMapping("/resipiPage.fri")
	public ModelAndView resipiPage(ModelAndView mv , PageUtil page, ResipiVO rVO, 
									HttpSession session) {
		rVO.setId((String)session.getAttribute("SID"));
		
		int total = rDao.getAllCnt(rVO.getBno());
		
		page.setTotalCount(total);
		page.setPage();
		
		
		rVO.setPage(page);
		
		
		String ingred = rDao.getIngred(rVO.getBno());
		String[] ingredient = ingred.split("/");
		
		int count = rDao.getReplyCnt(rVO);
		ResipiVO info = rDao.getResipiInfo(rVO);
		List<ResipiVO> list = rDao.getBody(rVO.getBno());
		List<ResipiVO> reply = rDao.getReply(rVO);
		List<ResipiVO> other = rDao.getOther(ingred);
		
		mv.addObject("RESIPI",info);
		mv.addObject("BODY",list);
		mv.addObject("REPLY",reply);
		mv.addObject("INGRED",ingredient);
		mv.addObject("CNT",count);
		mv.addObject("PAGE",page);
		mv.addObject("OTHER",other);
		
		mv.setViewName("juhyun/recipe/resipiPage");
		return mv;
	}
	
	// 레시피 페이지 댓글 추가 요청 처리 함수
	@RequestMapping(value="/resipiReply.fri", params= {"bno","mno"}, method=RequestMethod.POST)
	public ModelAndView resipiReply(ModelAndView mv , RedirectView rd,
									ResipiVO rVO, HttpSession session) {
		int cnt = 0;
		
		System.out.println(rVO.getBno());
		System.out.println(rVO.getMno());
		System.out.println(rVO.getBody());
		
		cnt = rDao.addReply(rVO);
		
		mv.addObject("bno",rVO.getBno());
		rd.setUrl("/fri/juhyun/recipe/resipiPage.fri");
		
		mv.setView(rd);
		return mv;
	}
	
	// 보드 테이블 좋아요 카운트 증가 처리함수
	@RequestMapping(value="/resipiLikeCount.fri", params= {"bno","mno"}, method=RequestMethod.POST)
	public ModelAndView resipiLike(ModelAndView mv, RedirectView rd, ResipiVO rVO, HttpSession session) {
		int cnt = 0;
		int usercnt = 0;
		
		cnt = rDao.addLikeCnt(rVO.getBno());
		usercnt = rDao.addUserLikeCnt(rVO);
		
		mv.addObject("bno",rVO.getBno());
		rd.setUrl("/fri/juhyun/recipe/resipiPage.fri");
		
		mv.setView(rd);
		return mv;
	}
	
	
	
}
/*
 * req.setAttribute("BODY", list); 
 * req.setAttribute("INGRED", ingred);
 * req.setAttribute("REPLY", reply); 
 * req.setAttribute("RESIPI", rVO);
 * req.setAttribute("PAGE", page); 
 * req.setAttribute("CNT", cnt);
 * req.setAttribute("OTHER", other);
 * 
 * 
 */