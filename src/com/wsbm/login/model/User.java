package com.wsbm.login.model;

/**
 * 与用户表(user)关联实体类
 * @author chen
 *
 */
public class User {

	private String id;//身份证号
	private String name;//考生姓名
	private int age;//考生年龄
	private String sex;//考生性别
	private String pwd;//登录密码
	private String picture;//考生头像
	private String birth;//考生出生日期
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}

}
