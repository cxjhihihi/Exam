package com.wsbm.information.model;

/**
 * 与消息模型表(mes_model)相关联的实体类类
 * @author chen
 *
 */
public class MesModel {
	
	private int id;//主键
	private String message;//消息内容
	private int type;//消息类型
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

}
