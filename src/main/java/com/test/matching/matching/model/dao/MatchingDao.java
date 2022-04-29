package com.test.matching.matching.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.matching.user.model.vo.User;

@Repository("matchingDao")
public class MatchingDao {
	@Autowired
	private SqlSessionTemplate session;
	
	
	public ArrayList<User> selectMatch(String userid) {
		List<User> list = session.selectList("userMapper.selectMatching", userid);
		return (ArrayList<User>)list;
	}
	
	public ArrayList<User> selectAppeal(String userid) {
		List<User> list = session.selectList("userMapper.selectAppeal", userid);
		return (ArrayList<User>)list;
	}
	

}
