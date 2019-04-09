package com.company.project.common.core;

import java.io.Serializable;

public class QueryRequest implements Serializable {

	private static final long serialVersionUID = 6695134677863490635L;

	private int pageSize;
	private int pageNum;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

}
