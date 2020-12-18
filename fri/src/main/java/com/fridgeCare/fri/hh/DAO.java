package com.fridgeCare.fri.hh;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.fridgeCare.fri.hh.vo.InputVO;
import com.fridgeCare.fri.hh.vo.LatelyUploadVO;
import com.fridgeCare.fri.hh.vo.SideRankVO;
import com.fridgeCare.fri.hh.vo.ThumbVO;

public class DAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	public int idcheck(String id) {
		return sqlSession.selectOne("hsql.idcheck", id);
	}
	public int mailcheck(String mail) {
		return sqlSession.selectOne("hsql.mailcheck", mail);
	}
	public int joinproc(InputVO ivo) {
		return sqlSession.insert("hsql.insertmember", ivo);
	}
	public int logincheck(InputVO ivo) {
		return sqlSession.selectOne("hsql.logincheck", ivo);
	}
	public String getThumb(String sid) {
		return sqlSession.selectOne("hsql.getThumb", sid);
	}
	public int setAvt(ThumbVO tvo) {
		return sqlSession.insert("hsql.insertavt", tvo);
	}
	public LatelyUploadVO getLUVO() {
		return sqlSession.selectOne("hsql.getLUVO");
	}
	public SideRankVO getWR() {
		return sqlSession.selectOne("hsql.getweekrank");
	}
	public SideRankVO getMR() {
		return sqlSession.selectOne("hsql.getmonthrank");
	}
}
