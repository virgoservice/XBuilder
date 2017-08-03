package com.ramostear.xbuilder.kit;

import java.io.Serializable;
import java.util.List;

public class PageDto<T> implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long totalSize;
	
	private Integer pageNo;
	
	private Integer pageSize;
	
	private List<T> items;
	
	@SuppressWarnings("unused")
	private String pagination;
	
	
	public PageDto() {
		super();
	}

	public PageDto(Long totalSize,Integer pageNo,Integer pageSize,List<T> items) {
		this.items = items;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalSize = totalSize;
	}

	public Long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Long totalSize) {
		this.totalSize = totalSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public String getPagination() {
		Integer totalPage = (int) (totalSize % pageSize > 0 ? (totalSize
				/ pageSize + 1) : (totalSize / pageSize));
		Integer prePage = pageNo - 1 > 0 ? pageNo - 1 : 1;
		Integer nextPage = pageNo + 1 > totalPage ? totalPage : pageNo + 1;
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("<ul class=\"pagination pagination-sm no-margin pull-right\">");
		if (pageNo == 1) {
			stringBuffer
					.append("");
		} else {
			stringBuffer.append("<li><a href=\"#\" onclick=\"query(" + prePage
					+ "," + pageSize + ")\">Previous</a></li>");
		}
		for (int i = pageNo-5; i < pageNo+5; i++) {
			if (i<1||i>totalPage) {
				continue;
			}
			if (i==pageNo) {
				stringBuffer
				.append("<li class=\"paginate_button active\"><a href=\"#\"  onclick=\"query("
							+ i + "," + pageSize + ")\">"+i+"</a></li>");
			}else {
				stringBuffer
				.append("<li><a href=\"#\"  onclick=\"query("
							+ i + "," + pageSize + ")\">"+i+"</a></li>");
			}
		}
		if (pageNo == totalPage) {
			stringBuffer
					.append("");
		} else {
			stringBuffer
			.append("<li><a href=\"#\" onclick=\"query("
					+ nextPage + "," + pageSize + ")\">Next</a></li>");
		}
		stringBuffer.append("</ul>");
		return stringBuffer.toString();
	}

	public void setPagination(String pagination) {
		this.pagination = pagination;
	}
	
	
	
}
