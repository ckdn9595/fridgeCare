package com.fridgeCare.fri.hyunuk;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fridgeCare.fri.hyunuk.vo.GetVO;
import com.fridgeCare.fri.hyunuk.vo.IngredVO;
import com.fridgeCare.fri.hyunuk.vo.SearchVO;


@Controller
@RequestMapping("/hyunuk")
public class HyunukController {
	
	@Autowired
	SearchDao sDao;
	
	// 페이지 요청
	@RequestMapping("/search.fri")
	public ModelAndView getList(ModelAndView mv, SearchVO sVO, IngredVO inVO, HttpSession session) {
		mv.setViewName("hyunuk/search");
				
		List<IngredVO> ingred = sDao.getIngredient(inVO);
		mv.addObject("LIST", ingred);
		return mv;
	}
	
	// 선택된 값 요청
	@RequestMapping(value="/selected.fri", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> SelectedIngred(ModelAndView mv, String[] Situat, String[] Category, String SelIng) {
		
		String situ = "";
		String CAT = "";
		
		if(Situat == null || Situat.length == 0) {
			situ = "'SELF','DRINK','SIDE','FULL'";
		}else {
			for(int i = 0; i < Situat.length; i++) {
				situ += "'"+(String)Situat[i] + "',";
			}
			situ = situ.substring(0,situ.length()-1);
		}
		
		if(Category == null || Category.length == 0) {
			CAT = "'KR','CH','JP','WE','FU'";
		}else {
			for(int i = 0; i < Category.length; i++) {
				CAT += "'"+(String)Category[i] + "',";
			}
			CAT = CAT.substring(0,CAT.length()-1);
		}
		
		if(SelIng == "") {
			SelIng = "|";
		}
		
		GetVO gVO = new GetVO();
		gVO.setCat(CAT);
		gVO.setSitu(situ);
		gVO.setSeling(SelIng);
		
		System.out.println("aaaaaaaaaaaaaaaaaaaaaa");
		System.out.println(gVO.getCat());
		System.out.println(gVO.getSitu());
		System.out.println(gVO.getSeling());
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("CAT", CAT);
		map.put("situ", situ);
		map.put("SelIng", SelIng);
		
		// List<SearchVO> sLIST = sDao.getRecipelist(gVO);
		List<SearchVO> sLIST = sDao.getRecipelist(map);
		
		System.out.println(sLIST.size());
		
		Map<String,Object> map2 = new HashMap<String, Object>();
		map2.put("sLIST",sLIST);
		return map2;
	}
	
}
