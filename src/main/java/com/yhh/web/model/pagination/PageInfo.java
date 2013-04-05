package com.yhh.web.model.pagination;

public class PageInfo {
	
	private int startRow = 1;

	private int pageSize = 99999;
	
	private String echo ;

	public PageInfo(int startRow, int pageSize, String echo) {
		super();
		this.startRow = startRow;
		this.pageSize = pageSize;
		this.echo = echo;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getEcho() {
		return echo;
	}

	public void setEcho(String echo) {
		this.echo = echo;
	}

	
}
