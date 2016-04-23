package com.wsbm.prepared.model;

import com.wsbm.information.model.Info;

/**
 * 与准考证表(ticket)关联的实体类
 * @author chen
 *
 */
public class Ticket {
	
	private int t_id;//主键
	private String t_stuId;//学生主键
	private Info t_user;//学生外键
	private int t_examId;//考场主键
	private ExamRoom t_room;//考场外键
	private int t_seat;//座位号
	
	public int getT_id() {
		return t_id;
	}
	public void setT_id(int t_id) {
		this.t_id = t_id;
	}
	public String getT_stuId() {
		return t_stuId;
	}
	public void setT_stuId(String t_stuId) {
		this.t_stuId = t_stuId;
	}
	public Info getT_user() {
		return t_user;
	}
	public void setT_user(Info t_user) {
		this.t_user = t_user;
	}
	public int getT_examId() {
		return t_examId;
	}
	public void setT_examId(int t_examId) {
		this.t_examId = t_examId;
	}
	public ExamRoom getT_room() {
		return t_room;
	}
	public void setT_room(ExamRoom t_room) {
		this.t_room = t_room;
	}
	public int getT_seat() {
		return t_seat;
	}
	public void setT_seat(int t_seat) {
		this.t_seat = t_seat;
	}
	
}
