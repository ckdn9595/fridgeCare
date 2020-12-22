package com.fridgeCare.fri.joo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.fridgeCare.fri.joo.vo.*;

public class JooService {
	@Autowired
	JooDao jDao;
	@Autowired
	JFileUtil jfUtil;
	
	//1+2+3
	@Transactional
	public void addAll(ModelAndView mv, JooVO jVO, JTumbVO jtVO) {
		try {
			
			jVO.setInbox(editInbox(jVO.getInboxList()));
			
			jtVO = jfUtil.saveProc(jtVO.getThumb(), "/img/thumb");

			jtVO.setId(jVO.getId());
			//System.out.println("jtVO id : " + jtVO.getId());

			jDao.addThumb(jtVO);
			jVO.setTno(jtVO.getTno());
			jDao.addRecipe(jVO);

			ArrayList<JRecipeVO> list = jfUtil.saveProc(jVO.getImage(), "/img/upload");
			
			//System.out.println("### list size : " + list.size());
			
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setBody(jVO.getBody()[i]);
				jDao.addRecipeDetail(list.get(i));
			}
			
			mv.setViewName("joo/recipeAddClear");
		} catch (Exception e) {
			mv.setViewName("joo/recipeAddError");
			
			e.printStackTrace();
		}
		
		
	}
	//재료 문자열 정리 전담 함수
	public String editInbox(String[] arrInbox) {
		String str = arrInbox[0];
		for(int i = 1 ; i < arrInbox.length ;i++) {
			str += ("/" + arrInbox[i]);
		}
		return str;
	}
}
