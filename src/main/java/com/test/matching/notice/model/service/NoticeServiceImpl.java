package com.test.matching.notice.model.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.matching.common.SearchDate;
import com.test.matching.notice.model.dao.NoticeDao;
import com.test.matching.notice.model.vo.Notice;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDao noticeDao;
	
	@Override
	public ArrayList<Notice> selectAll() {
		return noticeDao.selectList();
	}

	@Override
	public Notice selectNotice(int notice_num) {
		return noticeDao.selectOne(notice_num);
	}

	@Override
	public int inserNotice(Notice notice) {
		return noticeDao.insertNotice(notice);
	}

	@Override
	public int updateNotice(Notice notice) {
		return noticeDao.updateNotice(notice);
	}

	@Override
	public int deleteNotice(int notice_num) {
		return noticeDao.deleteNotice(notice_num);
	}

	@Override
	public ArrayList<Notice> selectNewTop3() {
		return noticeDao.selectNewTop3();
	}

	@Override
	public ArrayList<Notice> selectSearchTitle(String keyword) {
		return noticeDao.selectSearchTitle(keyword);
	}

	@Override
	public ArrayList<Notice> selectSearchWriter(String keyword) {
		return noticeDao.selectSearchWriter(keyword);
	}

	@Override
	public ArrayList<Notice> selectSearchDate(SearchDate date) {
		return noticeDao.selectSearchDate(date);
	}
	
}
