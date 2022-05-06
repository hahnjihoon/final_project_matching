package com.test.matching.community.model.vo;

import java.util.Date;

public class Community implements java.io.Serializable{
	private static final long serialVersionUID = 2750645469282558994L;

	private int com_num;
	private String com_content;
	private Date com_date;
	private String com_original_file;
	private String com_rename_file;
	private String com_writer;
	private int com_readcount;
	
	public Community() {}

	@Override
	public String toString() {
		return "Community [com_num=" + com_num + ", com_content=" + com_content + ", com_date=" + com_date
				+ ", com_original_file=" + com_original_file + ", com_rename_file=" + com_rename_file + ", com_writer="
				+ com_writer + ", com_readcount=" + com_readcount + "]";
	}

	public int getCom_num() {
		return com_num;
	}

	public void setCom_num(int com_num) {
		this.com_num = com_num;
	}

	public String getCom_content() {
		return com_content;
	}

	public void setCom_content(String com_content) {
		this.com_content = com_content;
	}

	public Date getCom_date() {
		return com_date;
	}

	public void setCom_date(Date com_date) {
		this.com_date = com_date;
	}

	public String getCom_original_file() {
		return com_original_file;
	}

	public void setCom_original_file(String com_original_file) {
		this.com_original_file = com_original_file;
	}

	public String getCom_rename_file() {
		return com_rename_file;
	}

	public void setCom_rename_file(String com_rename_file) {
		this.com_rename_file = com_rename_file;
	}

	public String getCom_writer() {
		return com_writer;
	}

	public void setCom_writer(String com_writer) {
		this.com_writer = com_writer;
	}

	public int getCom_readcount() {
		return com_readcount;
	}

	public void setCom_readcount(int com_readcount) {
		this.com_readcount = com_readcount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Community(int com_num, String com_content, Date com_date, String com_original_file, String com_rename_file,
			String com_writer, int com_readcount) {
		super();
		this.com_num = com_num;
		this.com_content = com_content;
		this.com_date = com_date;
		this.com_original_file = com_original_file;
		this.com_rename_file = com_rename_file;
		this.com_writer = com_writer;
		this.com_readcount = com_readcount;
	}

	
	
	
}
