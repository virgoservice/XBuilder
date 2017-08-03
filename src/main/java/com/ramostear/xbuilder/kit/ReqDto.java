package com.ramostear.xbuilder.kit;

import java.io.Serializable;

public class ReqDto implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer pageNo;
	private Integer pageSize;
	public ReqDto() {
		super();
	}
	public ReqDto(Integer pageNo,Integer pageSize){
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}
	
	public Integer getPageNo(){
		return pageNo == null ? 1 : pageNo;
	}
	
	public void setPageNo(Integer pageNo){
		this.pageNo = pageNo;
	}
	
	public Integer getPageSize(){
		return pageSize == null ? 10 : pageSize;
	}
	
	public void setPageSize(Integer pageSize){
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "ReqDto [pageNo=" + pageNo + ", pageSize=" + pageSize + "]";
	}
	
}
