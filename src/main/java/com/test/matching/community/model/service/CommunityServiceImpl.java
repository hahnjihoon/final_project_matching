package com.test.matching.community.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.matching.common.Paging;
import com.test.matching.community.model.dao.CommunityDao;
import com.test.matching.community.model.vo.Community;

@Service("communityService")
public class CommunityServiceImpl implements CommunityService{

	@Autowired
	private CommunityDao communityDao;

	@Override
	public int selectListCount() {
		return communityDao.selectListCount();
	}

	@Override
	public ArrayList<Community> selectList(Paging page) {
		// TODO Auto-generated method stub
		return communityDao.selectList(page);
	}

	@Override
	public Community selectCommunity(int com_num) {
		// TODO Auto-generated method stub
		return communityDao.selectCommunity(com_num);
	}

	@Override
	public int updateAddReadcount(int com_num) {
		// TODO Auto-generated method stub
		return communityDao.updateAddReadcount(com_num);
	}

	@Override
	public int updateOrigin(Community community) {
		// TODO Auto-generated method stub
		return communityDao.updateOrigin(community);
	}

	@Override
	public int deleteCommunity(Community community) {
		// TODO Auto-generated method stub
		return communityDao.deleteCommunity(community);
	}

	@Override
	public int insertOriginCommunity(Community community) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateReplySeq(Community reply) {
		// TODO Auto-generated method stub
		return 0;
	}

}
