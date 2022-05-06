package com.test.matching.notice.model.vo;

import java.sql.Date;

public class Notice implements java.io.Serializable {
	
	
	private static final long serialVersionUID = 1365978153616705231L;
	
	private int notice_num;
	private String notice_title;
	private String notice_content;
	private java.sql.Date notice_date;
	private String notice_id;
	
	public Notice() {
		super();
	}

	public Notice(int notice_num, String notice_title, String notice_content, Date notice_date, String notice_id) {
		super();
		this.notice_num = notice_num;
		this.notice_title = notice_title;
		this.notice_content = notice_content;
		this.notice_date = notice_date;
		this.notice_id = notice_id;
	}

	public int getNotice_num() {
		return notice_num;
	}

	public void setNotice_num(int notice_num) {
		this.notice_num = notice_num;
	}

	public String getNotice_title() {
		return notice_title;
	}

	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}

	public String getNotice_content() {
		return notice_content;
	}

	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}

	public java.sql.Date getNotice_date() {
		return notice_date;
	}

	public void setNotice_date(java.sql.Date notice_date) {
		this.notice_date = notice_date;
	}

	public String getNotice_id() {
		return notice_id;
	}

	public void setNotice_id(String notice_id) {
		this.notice_id = notice_id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Notice [notice_num=" + notice_num + ", notice_title=" + notice_title + ", notice_content="
				+ notice_content + ", notice_date=" + notice_date + ", notice_id=" + notice_id + "]";
	}
	
	
	
	
	
}
