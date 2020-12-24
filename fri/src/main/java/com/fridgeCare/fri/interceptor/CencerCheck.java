package com.fridgeCare.fri.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fridgeCare.fri.service.AdminService;

public class CencerCheck implements HandlerInterceptor {
	
	@Autowired
	AdminService aSrvc;
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler)
			throws Exception {
		// 요청 보낸 주소 받아오고
		String now_address = req.getRequestURI();
		System.out.println("now_address : " + now_address);
		
		if(now_address.equals("/fri/juhyun/admin/addCencerProc.fri")) {
			// 검열단어 생성 컨트롤러
			boolean bool = searchWord(req.getParameter("body"));
			
			if(bool == false) {
				resp.sendRedirect("/fri/juhyun/admin/wordCencerPage.fri");
			}else {
				return true;
			}
			
			
		}else if(now_address.equals("/fri/joo/recipeAddProc.fri")){
			//보드와 보드파트 생성 컨트롤러
			
		/*
			int cnt = searchWord(req.getParameter("body"));
			if(cnt == 0) {
				resp.sendRedirect("/fri/juhyun/admin/wordCencerPage.fri");
			}else {
				return true;
			}
		 */
			
			
		}else if(now_address.equals("/fri/hh/joinproc.fri")){
			// 계정 생성 컨트롤러

		/*
			int cnt = searchWord(req.getParameter("body"));
			if(cnt == 0) {
				resp.sendRedirect("/fri/juhyun/admin/wordCencerPage.fri");
			}else {
				return true;
			}
		 */
			
			
		}else if(now_address.equals("/fri/juhyun/resipiReply.fri")) {
			// 댓글 생성 컨트롤러

			boolean bool = searchWord(req.getParameter("body"));
			if(bool == false) {
				resp.sendRedirect("/fri/juhyun/admin/wordCencerPage.fri");
			}else {
				return true;
			}
			
			
		}else {
			resp.sendRedirect("/fri/hh/main.fri");
		}
		
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse resp, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse resp, Object handler, Exception ex)
			throws Exception {

	}
	
	
	//<mapping path="/**/addCencerProc.fri"/> 요청에대한 인터셉터 처리함수
	public boolean searchWord(String body) {
		boolean bool = aSrvc.cencerAll(body);
		return bool;
	}
	// 게시글 제목에 대한 인터셉터 처리 함수
	public int searchTitle(String title) {
		int cnt = 0;
		
		return cnt;
	}
	// 게시글 내용에 대한 인터셉터 처리 함수
	public int searchBoardPart(String body) {
		int cnt = 0;
		
		return cnt;
	}
	// 댓글 내용에 대한 인터셉터 처리 함수
	public int searchReply(String body) {
		int cnt = 0;
		
		return cnt;
	}
	// 회원가입 아이디에 대한 인터셉터 처리 함수
	public int searchId(String id) {
		int cnt = 0;
		
		return cnt;
	}
}