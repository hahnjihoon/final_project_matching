package com.test.matching.community.model.service;

import java.util.ArrayList;

import com.test.matching.common.Paging;
import com.test.matching.community.model.vo.Community;

public interface CommunityService {
	int selectListCount();  //총 게시글 갯수 조회용 (페이지 수 계산용)
	ArrayList<Community> selectList(Paging page); //한 페이지에 출력할 게시글 조회용
	Community selectCommunity(int com_num); //해당 번호에 대한 게시글 상세 조회용
	int updateAddReadcount(int com_num);  //상세보기시에 조회수 1증가 처리용
	int insertOriginCommunity(Community community); //원글 등록용
	int updateReplySeq(Community reply);  //댓글 등록시 기존 댓글순번 1증가 처리용
	int updateOrigin(Community board);  //원글 수정용
	int deleteCommunity(Community board);  //게시글 삭제용
}
