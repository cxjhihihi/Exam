package com.wsbm.login.model;

public class Admin {

	private int r_id;//主键
	private String r_account;//账号
	private String r_pwd;//密码
	private String r_name;//所属人
	private int r_role;//角色
	
	public int getR_id() {
		return r_id;
	}
	public void setR_id(int r_id) {
		this.r_id = r_id;
	}
	public String getR_account() {
		return r_account;
	}
	public void setR_account(String r_account) {
		this.r_account = r_account;
	}
	public String getR_pwd() {
		return r_pwd;
	}
	public void setR_pwd(String r_pwd) {
		this.r_pwd = r_pwd;
	}
	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}
	public int getR_role() {
		return r_role;
	}
	public void setR_role(int r_role) {
		this.r_role = r_role;
	}
	
}
