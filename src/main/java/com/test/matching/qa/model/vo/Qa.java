package com.test.matching.qa.model.vo;

import java.sql.Date;

public class Qa implements java.io.Serializable {
	private static final long serialVersionUID = -6476172893148042519L;
	
	private int qa_num;
	private String qa_id;
	private String qa_title;
	private String qa_content;
	private int qa_ref;
	private int qa_reply_ref;
	private int qa_lev;
	private int qa_reply_seq;
	private int qa_readcount;
	private Date qa_date;
	
	public Qa() {
		super();
	}

	public Qa(int qa_num, String qa_id, String qa_title, String qa_content, int qa_ref, int qa_reply_ref, int qa_lev,
			int qa_reply_seq, int qa_readcount, Date qa_date) {
		super();
		this.qa_num = qa_num;
		this.qa_id = qa_id;
		this.qa_title = qa_title;
		this.qa_content = qa_content;
		this.qa_ref = qa_ref;
		this.qa_reply_ref = qa_reply_ref;
		this.qa_lev = qa_lev;
		this.qa_reply_seq = qa_reply_seq;
		this.qa_readcount = qa_readcount;
		this.qa_date = qa_date;
	}

	public int getQa_num() {
		return qa_num;
	}

	public void setQa_num(int qa_num) {
		this.qa_num = qa_num;
	}

	public String getQa_id() {
		return qa_id;
	}

	public void setQa_id(String qa_id) {
		this.qa_id = qa_id;
	}

	public String getQa_title() {
		return qa_title;
	}

	public void setQa_title(String qa_title) {
		this.qa_title = qa_title;
	}

	public String getQa_content() {
		return qa_content;
	}

	public void setQa_content(String qa_content) {
		this.qa_content = qa_content;
	}

	public int getQa_ref() {
		return qa_ref;
	}

	public void setQa_ref(int qa_ref) {
		this.qa_ref = qa_ref;
	}

	public int getQa_reply_ref() {
		return qa_reply_ref;
	}

	public void setQa_reply_ref(int qa_reply_ref) {
		this.qa_reply_ref = qa_reply_ref;
	}

	public int getQa_lev() {
		return qa_lev;
	}

	public void setQa_lev(int qa_lev) {
		this.qa_lev = qa_lev;
	}

	public int getQa_reply_seq() {
		return qa_reply_seq;
	}

	public void setQa_reply_seq(int qa_reply_seq) {
		this.qa_reply_seq = qa_reply_seq;
	}

	public int getQa_readcount() {
		return qa_readcount;
	}

	public void setQa_readcount(int qa_readcount) {
		this.qa_readcount = qa_readcount;
	}

	public Date getQa_date() {
		return qa_date;
	}

	public void setQa_date(Date qa_date) {
		this.qa_date = qa_date;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Qa [qa_num=" + qa_num + ", qa_id=" + qa_id + ", qa_title=" + qa_title + ", qa_content=" + qa_content
				+ ", qa_ref=" + qa_ref + ", qa_reply_ref=" + qa_reply_ref + ", qa_lev=" + qa_lev + ", qa_reply_seq="
				+ qa_reply_seq + ", qa_readcount=" + qa_readcount + ", qa_date=" + qa_date + "]";
	}
	
	
	
}
