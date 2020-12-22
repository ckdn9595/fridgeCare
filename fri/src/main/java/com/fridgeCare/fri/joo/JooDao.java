package com.fridgeCare.fri.joo;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fridgeCare.fri.joo.vo.JRecipeVO;
import com.fridgeCare.fri.joo.vo.JTumbVO;
import com.fridgeCare.fri.joo.vo.JooVO;

public class JooDao {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	//재료리스트뽑기
	public List<String> getInList(){
		return sqlSession.selectList("jSQL.getInList");
	}
	
	//1번 보드
	public int addRecipe(JooVO jVO){
		return sqlSession.insert("addRecipe", jVO);
	}
	
	//2번 썸네일
	public int addThumb(JTumbVO jtVO) {
		return sqlSession.insert("addThumb", jtVO);
	}
	
	//3번 상세레시피
	public int addRecipeDetail(JRecipeVO jrVO) {
		return sqlSession.insert("addRecipeDetail", jrVO);
	}
	

}
