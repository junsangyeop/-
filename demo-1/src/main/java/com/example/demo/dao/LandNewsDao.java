package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.LandNewsBean;

@Repository
public class LandNewsDao {
	
	@Autowired
	@Qualifier("sqlSessionTemplate") 
	private SqlSession sqlSession;
	
	public List<LandNewsBean> selectLandNews(String news){
		return this.sqlSession.selectList("selectNewsList", news);
	}
}
