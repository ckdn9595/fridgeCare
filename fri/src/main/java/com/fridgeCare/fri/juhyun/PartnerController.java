package com.fridgeCare.fri.juhyun;


import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.fridgeCare.fri.juhyun.vo.PartnerVO;
import com.fridgeCare.fri.juhyun.vo.ResipiVO;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
@Controller
@RequestMapping("/juhyun/partner")
public class PartnerController {
	
	@Autowired
	DAO pDao;
	
	// 보드 리스트 전담 처리 함수
	@RequestMapping(value="/partner.fri", method=RequestMethod.GET)
	public ModelAndView partnerBoard(ModelAndView mv, PageUtil page) {
		int total = pDao.getBoardCnt();
		
		page.setTotalCount(total);
		page.setPage();
		
		System.out.println("start: " + page.getStartCont() + " endcont: " + page.getEndCont());
		System.out.println(page.getNowPage());
		List<PartnerVO> board = pDao.getBoardInfo(page);
		
		mv.addObject("BOARD",board);
		mv.addObject("PAGE", page);
		
		mv.setViewName("juhyun/partner/partner");
		return mv;
	}
	
	// 조회수 증가 전담 처리 함수
	@RequestMapping(value="/partnerCnt.fri", params={"bno","nowPage"}, method=RequestMethod.POST)
	public ModelAndView partnerCnt(ModelAndView mv, RedirectView rd,HttpSession session, PartnerVO pVO, PageUtil page) {
		int cnt = pDao.addCnt(pVO.getBno());
		
		if(cnt == 1) {
			rd.setUrl("/fri/juhyun/recipe/resipiPage.fri");
		}
		mv.addObject("bno", pVO.getBno());
		mv.setView(rd);
		return mv;
	}
	
}
/*
 * 	req.setAttribute("BOARD", board);
	req.setAttribute("pagelist", pagelist);
	req.setAttribute("pagination", page);
 */