package com.test.matching.notice.model.service;

import java.util.ArrayList;

import com.test.matching.common.SearchDate;
import com.test.matching.notice.model.vo.Notice;

public interface NoticeService {
	ArrayList<Notice> selectAll();
	Notice selectNotice(int notice_num);
	int inserNotice(Notice notice);
	int updateNotice(Notice notice);
	int deleteNotice(int notice_num);
	ArrayList<Notice> selectNewTop3();
	ArrayList<Notice> selectSearchTitle(String keyword);
	ArrayList<Notice> selectSearchWriter(String keyword);
	ArrayList<Notice> selectSearchDate(SearchDate date);
}
