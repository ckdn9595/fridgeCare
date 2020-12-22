package com.fridgeCare.fri.joo;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fridgeCare.fri.joo.vo.JooVO;
import com.fridgeCare.fri.joo.vo.JRecipeVO;
import com.fridgeCare.fri.joo.vo.JTumbVO;

@Controller
public class JooController {
	@Autowired
	JooDao jDao;
	@Autowired
	JooService jSrvc;
	
	@RequestMapping("/joo/recipeAdd.fri")
	public ModelAndView addRecipe(ModelAndView mv) {
		List<String> list = jDao.getInList();
		mv.addObject("LIST", list);
		mv.setViewName("joo/recipeAdd");
		return mv;
	}
	
	@RequestMapping("/joo/recipeIngred.fri")
	public ModelAndView addIngredProc(ModelAndView mv, JooVO jVO) {
		String str = jSrvc.editInbox(jVO.getInboxList());
		jVO.setInbox(str);
		return mv;
	}
	
	@RequestMapping("/joo/recipeAddProc.fri")
	public ModelAndView addRecipeProc(ModelAndView mv, JooVO jVO, JTumbVO jtVO) {
		//System.out.println("########################jVO id" + jVO.getId());
		jSrvc.addAll(mv, jVO, jtVO);
		return mv;
	}
}
