package com.test.matching.community.model.service;

import java.util.ArrayList;

import com.test.matching.common.Paging;
import com.test.matching.community.model.vo.Community;

public interface CommunityService {
	int selectListCount();  //�� �Խñ� ���� ��ȸ�� (������ �� ����)
	ArrayList<Community> selectList(Paging page); //�� �������� ����� �Խñ� ��ȸ��
	Community selectCommunity(int com_num); //�ش� ��ȣ�� ���� �Խñ� �� ��ȸ��
	int updateAddReadcount(int com_num);  //�󼼺���ÿ� ��ȸ�� 1���� ó����
	int insertOriginCommunity(Community community); //���� ��Ͽ�
//	int updateReplySeq(Community reply);  //��� ��Ͻ� ���� ��ۼ��� 1���� ó����
	int updateOrigin(Community community);  //���� ������
	int deleteCommunity(Community community);  //�Խñ� ������
}
