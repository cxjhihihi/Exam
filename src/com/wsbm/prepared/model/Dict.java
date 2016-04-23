package com.wsbm.prepared.model;

/**
 * 字典表(dict)对应的实体类
 * @author chen
 *
 */
public class Dict {

	private int d_id;//主键
	private String d_type;//字典类型
	private String d_code;//字典编号
	private String d_name;//中文含义
	
	public int getD_id() {
		return d_id;
	}
	public void setD_id(int d_id) {
		this.d_id = d_id;
	}
	public String getD_type() {
		return d_type;
	}
	public void setD_type(String d_type) {
		this.d_type = d_type;
	}
	public String getD_code() {
		return d_code;
	}
	public void setD_code(String d_code) {
		this.d_code = d_code;
	}
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	
}
