package com.wsbm.information.model;

/**
 * 分页查询条件类
 * 
 * @author chen
 *
 */
public class Conditions {

	private int status;//审核状态
	private String name;//审核人
	private String startTime;//开始时间
	private String endTime;//结束时间
	private int paging;//偏移量
	
	public Conditions() {
	}

	public Conditions(int status, String name, String startTime, String endTime, int paging) {
		super();
		this.status = status;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.paging = paging;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getPaging() {
		return paging;
	}
	public void setPaging(int paging) {
		this.paging = paging;
	}
	
}
