package com.fridgeCare.fri.juhyun;

import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fridgeCare.fri.juhyun.vo.PartnerVO;
import com.fridgeCare.fri.juhyun.vo.ResipiVO;

public class DAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	
	@Autowired
	FileUtil futil;
	
	// 레시피 정보 가져오기 전담 처리 함수
	public ResipiVO getResipiInfo(ResipiVO rVO) {
		return sqlSession.selectOne("rSQL.getResipiInfo", rVO);
	}
	// 재료 설명 가져오기 전담 처리함수
	public List<ResipiVO> getBody(int bno){
		return sqlSession.selectList("rSQL.getBody", bno);
	}
	// 댓글 가져오기 전담 처리 함수
	public List<ResipiVO> getReply(ResipiVO rVO){
		return sqlSession.selectList("rSQL.getReply", rVO);
	}
	// 게시글 수 조회 전담 처리함수
	public int getAllCnt(int bno) {
		return sqlSession.selectOne("rSQL.getAllCnt", bno);
	}
	// 유저 댓글 확인 전담 처리 함수
	public int getReplyCnt(ResipiVO rVO) {
		return sqlSession.selectOne("rSQL.getReplyCnt", rVO);
	}
	// 선택 재료로 가능한 레시피 불러오기 전담 처리 함수
	public List<ResipiVO> getOther(String ingred){
		return sqlSession.selectList("rSQL.getOther", ingred);
	}
	// 보드 테이블 좋아요 카운트 증가 처리 함수
	public int addLikeCnt(int bno) {
		return sqlSession.update("rSQL.addLikeCnt", bno);
	}
	// 유저라이크 테이블 데이터 추가 처리 함수
	public int addUserLikeCnt(ResipiVO rVO) {
		return sqlSession.insert("rSQL.addUserLikeCnt", rVO);
	}
	// 댓글 저장 전담 처리 함수
	public int addReply(ResipiVO rVO) {
		return sqlSession.insert("rSQL.addReply", rVO);
	}
	
	public String getIngred(int bno) {
		return sqlSession.selectOne("rSQL.getIngred", bno);
	}
	
	// 파트너 보드 리스트 전담 처리 함수
	public List<PartnerVO> getBoardInfo(PageUtil page){
		return sqlSession.selectList("pSQL.getBoardInfo", page);
	}
	
	// 보드 리스트 갯수 전담 처리 함수
	public int getBoardCnt() {
		return sqlSession.selectOne("pSQL.getBoardCnt");
	}
	
	// 보드 조회수 추가 전담 처리 함수
	public int addCnt(int bno) {
		return sqlSession.update("pSQL.addCnt", bno);
	}
}
