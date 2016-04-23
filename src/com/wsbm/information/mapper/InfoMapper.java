package com.wsbm.information.mapper;

import java.util.List;

import com.wsbm.information.model.Conditions;
import com.wsbm.information.model.Info;
import com.wsbm.information.model.MesModel;
import com.wsbm.information.model.Message;
import com.wsbm.information.model.Time;

/**
 * 考生信息表数据交互接口
 * @author chen
 *
 */
public interface InfoMapper {

	/**
	 * 获取报名信息
	 * 
	 * @param id
	 * 				登录用户身份证号
	 * @return
	 */
	public Info getInfo(String id);

	/**
	 * 保存报名信息
	 * 
	 * @param info
	 * 				报名信息
	 * @return
	 */
	public boolean saveInfo(Info info);

	/**
	 * 删除报名信息
	 * 
	 * @param account
	 * 				登录用户身份证号	
	 * @return
	 */
	public boolean deleteInfo(String account);

	/**
	 * 取消报名
	 * 
	 * @param id
	 * 				报名学生的身份证号
	 * @return
	 */
	public boolean cancelInfo(String id);

	/**
	 * 获取报名人员总数
	 * 
	 * @param time 
	 * 				时间信息
	 * @return
	 */
	public int getCount(Time time);

	/**
	 * 获取所有报名人员信息
	 * 
	 * @param time
	 * 				时间信息
	 * @return
	 */
	public List<Info> getAllInfo(Time time);

	/**
	 * 根据id获取详细信息
	 * 
	 * @param id
	 * 				id
	 * @return
	 */
	public Info getStuInfo(String id);

	/**
	 * 根据查询条件获取详细信息
	 * 
	 * @param conditions
	 * 						查询条件
	 * @return
	 */
	public List<Info> getInfoByConditions(Conditions conditions);
	
	/**
	 * 根据查询条件获取个数
	 * 
	 * @param conditions
	 * 						查询条件
	 * @return
	 */
	public int getCountByConditions(Conditions conditions);

	/**
	 * 获取审核信息
	 * 
	 * @return
	 */
	public Info getAudit();

	/**
	 * 获取上次未审核信息
	 * 
	 * @param id 
	 * 				审核人主键
	 * @return
	 */
	public Info getLastAudit(int id);

	/**
	 * 更改信息的审核人
	 * 	
	 * @param time
	 * 				更改条件
	 * @return
	 */
	public boolean updateAudit(Time time);

	/**
	 * 更改审核信息
	 * 
	 * @param time
	 * 				审核状态
	 * @return
	 */
	public boolean updateAuditStatus(Time time);

	/**
	 * 获取所有审核信息
	 * 
	 * @param id
	 * 				审核人id
	 * @return
	 */
	public List<Info> getAllAudit(int id);

	/***
	 * 条件查询审核信息
	 * 
	 * @param conditions
	 * 						条件
	 * @return
	 */
	public List<Info> queryAudit(Conditions conditions);

	/**
	 * 获取消息内容
	 * 
	 * @param status
	 * 					状态码
	 * @return
	 */
	public MesModel getMessageModel(int status);

	/**
	 * 发送消息
	 * @param message
	 */
	public void sendMessage(Message message);

	/**
	 * 获取系统消息
	 * 
	 * @param id
	 * 				接收人id
	 * @return
	 */
	public List<Message> getMessage(String id);

	/**
	 * 将未读消息设置为已读消息
	 * 
	 * @param id
	 * 				主键
	 * @return 
	 */
	public boolean changeReaded(String id);

	/**
	 * 获取所有消息
	 * 
	 * @param id
	 * 				主键
	 * @return
	 */
	public List<Message> getAllMessage(String id);

	/**
	 * 根据id修改阅读状态
	 * 
	 * @param id
	 * 				主键
	 * @return
	 */
	public boolean changeReaderById(int id);

	/**
	 * 条件查询消息
	 * 
	 * @param conditions
	 * 						条件
	 * @return
	 */
	public List<Message> queryMessage(Conditions conditions);

	/**
	 * 获取消息模型
	 * 
	 * @param sendTime
	 * 					发送时间
	 * @return
	 */
	public MesModel getNotice(String sendTime);

	/**
	 * 修改消息模型
	 * 
	 * @param mesModel
	 * 					模型信息
	 * @return
	 */
	public boolean updateMessageModel(MesModel mesModel);

}
