package com.example.demo.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.example.demo.bean.HouseMgrUserBean;

@Repository
public class HouseMgrDao {
	
	@Autowired
	@Qualifier("sqlSessionTemplate") 
	private SqlSession sqlSession;
	
	public Boolean selectHouseMgrUserCheck(HouseMgrUserBean info){
		
		return this.sqlSession.selectOne("selectHouseMgrUserCheck", info);
	}
}