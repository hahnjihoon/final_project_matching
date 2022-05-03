package com.test.matching.community.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.matching.common.Paging;
import com.test.matching.community.model.vo.Community;

@Repository("CommunityDao")
public class CommunityDao {
	@Autowired
	private SqlSessionTemplate session;

	public int selectListCount() {
		return session.selectOne("communityMapper.getListCount");
	}

	public ArrayList<Community> selectList(Paging page) {
		List<Community> list = session.selectList("communityMapper.selectList", page);
		return (ArrayList<Community>)list;
	}

	public Community selectCommunity(int com_num) {
		return session.selectOne("communityMapper.selectCommunity", com_num);
	}

	public int updateAddReadcount(int com_num) {
		return session.update("communityMapper.addReadCount", com_num);
	}

	public int insertOriginCommunity(Community community) {
		return session.insert("communityMapper.insertOriginCommunity", community);
	}

	public int updateOrigin(Community community) {
		return session.update("communityMapper.updateOrigin", community);
	}

	public int updateReply(Community reply) {
		return session.update("communityMapper.updateReply", reply);
	}

	public int deleteCommunity(Community community) {
		// TODO Auto-generated method stub
		return session.delete("communityMapper.deleteCommunity", community);
	}



}