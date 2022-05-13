package com.test.matching.common;

public class Paging {
	private int startRow;
	private int endRow;
	private String userid;
	
	public Paging() {}
	
	public Paging(int startRow, int endRow) {
		super();
		this.startRow = startRow;
		this.endRow = endRow;
	}

	public Paging(int startRow, int endRow, String userid) {
		super();
		this.startRow = startRow;
		this.endRow = endRow;
		this.userid = userid;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "Paging [startRow=" + startRow + ", endRow=" + endRow + ", userid=" + userid + "]";
	}


}