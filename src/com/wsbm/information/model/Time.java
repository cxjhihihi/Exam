package com.wsbm.information.model;

/**
 * 与时间表（time）对应的实体类
 * 
 * @author chen
 *
 */
public class Time {

	private int id;//主键或充当分页查询时的偏移量(从0开始)
	private String name;//名称
	private String startTime;//开始时间
	private String endTime;//结束时间
	private int status;//状态
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
