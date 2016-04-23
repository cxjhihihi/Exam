package com.wsbm.information.model;

import com.wsbm.login.model.Admin;
import com.wsbm.login.model.User;

/**
 * 与消息表(message)相对应的实体类
 * @author chen
 *
 */
public class Message {
	
	private int m_id;//主键
	private String m_content;//消息内容
	private User m_to;//接收人实体
	private Admin m_from;//发送人实体
	private String m_to2;//接收人主键
	private int m_from2;//发送人主键
	private int m_globale;//全局消息
	private String m_time;//发送时间
	private int m_read;//是否被阅读
	
	public int getM_id() {
		return m_id;
	}
	public void setM_id(int m_id) {
		this.m_id = m_id;
	}
	public String getM_content() {
		return m_content;
	}
	public void setM_content(String m_content) {
		this.m_content = m_content;
	}
	public User getM_to() {
		return m_to;
	}
	public void setM_to(User m_to) {
		this.m_to = m_to;
	}
	public Admin getM_from() {
		return m_from;
	}
	public void setM_from(Admin m_from) {
		this.m_from = m_from;
	}
	public String getM_to2() {
		return m_to2;
	}
	public void setM_to2(String m_to2) {
		this.m_to2 = m_to2;
	}
	public int getM_from2() {
		return m_from2;
	}
	public void setM_from2(int m_from2) {
		this.m_from2 = m_from2;
	}
	public int getM_globale() {
		return m_globale;
	}
	public void setM_globale(int m_globale) {
		this.m_globale = m_globale;
	}
	public String getM_time() {
		return m_time;
	}
	public void setM_time(String m_time) {
		this.m_time = m_time;
	}
	public int getM_read() {
		return m_read;
	}
	public void setM_read(int m_read) {
		this.m_read = m_read;
	}

}
