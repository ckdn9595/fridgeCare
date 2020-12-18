package com.fridgeCare.fri.joo;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class DAO {
	@Autowired
	SqlSessionTemplate sqlSession;
	
}
