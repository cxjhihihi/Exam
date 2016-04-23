package com.wsbm.information.model;

import com.wsbm.login.model.Admin;

//import com.wsbm.login.model.Admin;

/**
 * 与考生信息表(info)关联实体类
 * @author chen
 *
 */
public class Info {
	
	private String id;//主键
	private String name;//考生姓名
	private String sex;//考生性别
	private String birth;//考生出生年月
	private String picture;//考生照片路径
	private String idCard;//考生身份证号
	private String phone_1;//考生联系电话一
	private String schoolAddress;//考生所在学校地址
	private String householdAddress;//考生户籍所在地
	private String school;//考生毕业学校
	private String sclass;//考生所在班级
	private String homeAddress;//考生家庭住址
	private String phone_2;//考生联系电话二
	
	private String account;//操作账户
	private int enter;//考生是否报名
	private int status;//考生审核状态
	private Admin admin;//审核老师
	private int teacher;//审核老师
	private String time;//报名时间
	
	
	public int getTeacher() {
		return teacher;
	}
	public void setTeacher(int teacher) {
		this.teacher = teacher;
	}
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getSex() {
		return sex;
	}
	public String getBirth() {
		return birth;
	}
	public String getPicture() {
		return picture;
	}
	public String getIdCard() {
		return idCard;
	}
	public String getPhone_1() {
		return phone_1;
	}
	public String getSchoolAddress() {
		return schoolAddress;
	}
	public String getHouseholdAddress() {
		return householdAddress;
	}
	public String getSchool() {
		return school;
	}
	public String getSclass() {
		return sclass;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public String getPhone_2() {
		return phone_2;
	}
	public String getAccount() {
		return account;
	}
	public int getEnter() {
		return enter;
	}
	public int getStatus() {
		return status;
	}
	public Admin getAdmin() {
		return admin;
	}
	/*public int getTeacher() {
		return teacher;
	}*/
	public String getTime(){
		return time;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public void setPhone_1(String phone_1) {
		this.phone_1 = phone_1;
	}
	public void setSchoolAddress(String schoolAddress) {
		this.schoolAddress = schoolAddress;
	}
	public void setHouseholdAddress(String householdAddress) {
		this.householdAddress = householdAddress;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public void setSclass(String sclass) {
		this.sclass = sclass;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public void setPhone_2(String phone_2) {
		this.phone_2 = phone_2;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setEnter(int enter) {
		this.enter = enter;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public void setTeacher(Admin admin) {
		this.admin = admin;
	}
	/*public void setTeacher(int teacher) {
		this.teacher = teacher;
	}*/
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Info [id=" + id + ", name=" + name + ", sex=" + sex
				+ ", birth=" + birth + ", picture=" + picture + ", idCard="
				+ idCard + ", phone_1=" + phone_1 + ", schoolAddress="
				+ schoolAddress + ", householdAddress=" + householdAddress
				+ ", school=" + school + ", sclass=" + sclass
				+ ", homeAddress=" + homeAddress + ", phone_2=" + phone_2
				+ ", relationship_1=" + ", account=" + account + ", enter="
				+ enter + ", status=" + status + ", admin=" + admin
				+ ", teacher=" + teacher + ", time=" + time + "]";
	}


}
