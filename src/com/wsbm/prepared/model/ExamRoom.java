package com.wsbm.prepared.model;

/**
 * 考场表对应的实体类
 * @author chen
 *
 */
public class ExamRoom {

	private int e_id;//主键
	private String e_name;//考场名称
	private Dict e_school;//考场所在校区
	private int e_school_id;//考场主键
	private int e_num;//考场容纳人数
	private int e_remain;//考场剩余空位
	private String e_startTime;//考试开始时间
	private String e_endTime;//考试结束时间
	private Dict e_course;//考试科目
	private int e_course_id;//科目主键
	private int e_start;//考场状态
	
	private String e_school_name;
	private String e_course_name;
	
	
	public String getE_school_name() {
		return e_school_name;
	}
	public void setE_school_name(String e_school_name) {
		this.e_school_name = e_school_name;
	}
	public String getE_course_name() {
		return e_course_name;
	}
	public void setE_course_name(String e_course_name) {
		this.e_course_name = e_course_name;
	}
	public int getE_start() {
		return e_start;
	}
	public void setE_start(int e_start) {
		this.e_start = e_start;
	}
	public int getE_school_id() {
		return e_school_id;
	}
	public void setE_school_id(int e_school_id) {
		this.e_school_id = e_school_id;
	}
	public int getE_course_id() {
		return e_course_id;
	}
	public void setE_course_id(int e_course_id) {
		this.e_course_id = e_course_id;
	}
	public int getE_id() {
		return e_id;
	}
	public void setE_id(int e_id) {
		this.e_id = e_id;
	}
	public String getE_name() {
		return e_name;
	}
	public void setE_name(String e_name) {
		this.e_name = e_name;
	}
	public Dict getE_school() {
		return e_school;
	}
	public void setE_school(Dict e_school) {
		this.e_school = e_school;
	}
	public int getE_num() {
		return e_num;
	}
	public void setE_num(int e_num) {
		this.e_num = e_num;
	}
	public int getE_remain() {
		return e_remain;
	}
	public void setE_remain(int e_remain) {
		this.e_remain = e_remain;
	}
	public String getE_startTime() {
		return e_startTime;
	}
	public void setE_startTime(String e_startTime) {
		this.e_startTime = e_startTime;
	}
	public String getE_endTime() {
		return e_endTime;
	}
	public void setE_endTime(String e_endTime) {
		this.e_endTime = e_endTime;
	}
	public Dict getE_course() {
		return e_course;
	}
	public void setE_course(Dict e_course) {
		this.e_course = e_course;
	}
	
}
