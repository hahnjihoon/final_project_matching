package com.test.matching.qa.model.dao;

import java.util.ArrayList;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.matching.common.Paging;
import com.test.matching.qa.model.vo.Qa;

@Repository("QaDao")
public class QaDao {
	@Autowired
	private SqlSessionTemplate session;

	public ArrayList<Qa> selectTop3() {
		List<Qa> list = session.selectList("QaMapper.selectTop3");
		return (ArrayList<Qa>)list;
	}

	public int selectListCount() {
		return session.selectOne("QaMapper.getListCount");
	}

	public ArrayList<Qa> selectList(Paging page) {
		List<Qa> list = session.selectList("QaMapper.selectList", page);
		return (ArrayList<Qa>)list;
	}
	
  
	
	public Qa selectBoard(int Qa_num) {
		return session.selectOne("QaMapper.selectBoard", Qa_num);
	}

	public int updateAddReadcount(int Qa_num) {
		return session.update("QaMapper.addReadCount", Qa_num);
	}

	public int insertOriginBoard(Qa qa) {
		return session.insert("QaMapper.insertOriginBoard", qa);
	}

	public int insertReply(Qa reply) {
		int result=0;
		
		if(reply.getQa_lev()==2) { //�뙎湲��씠硫�
			result = session.insert("QaMapper.insertReply1", reply);
		}
		if(reply.getQa_lev()==3) { //���뙎湲��씠硫�
			result = session.insert("QaMapper.insertReply2", reply);
		}
		
		return result;
	}

	public int updateReplySeq(Qa reply) {
		int result=0;
		
		if(reply.getQa_lev()==2) { //�뙎湲��씠硫�
			result = session.update("QaMapper.updateReplySeq1", reply);
		}
		if(reply.getQa_lev()==3) { //���뙎湲��씠硫�
			result = session.update("QaMapper.updateReplySeq2", reply);
		}
		
		return result;
	}

	public int updateOrigin(Qa qa) {
		return session.update("QaMapper.updateOrigin", qa);
	}
	
	public int updateReply(Qa reply) {
		return session.update("QaMapper.updateReply", reply);
	}

	public int deleteBoard(Qa qa) {
		return session.delete("QaMapper.deleteBoard", qa);
	}
}
