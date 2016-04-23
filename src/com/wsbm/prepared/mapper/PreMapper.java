package com.wsbm.prepared.mapper;

import java.util.List;

import com.wsbm.information.model.Info;
import com.wsbm.prepared.model.Dict;
import com.wsbm.prepared.model.ExamRoom;
import com.wsbm.prepared.model.Ticket;

/**
 * 考试前夕准备工作-数据交互接口
 * @author chen
 *
 */
public interface PreMapper {

	/**
	 * 获取字典信息
	 * 
	 * @param type
	 * 				字典类型
	 * @return
	 */
	List<Dict> getDict(String type);

	/**
	 * 保存字典信息
	 * 
	 * @param dict
	 * 				字典信息
	 * @return
	 */
	boolean saveDict(Dict dict);

	/**
	 * 根据id删除字典信息
	 * 
	 * @param idArray
	 * 					id数组
	 * @return
	 */
	int deleteDict(int[] intIdArray);

	/**
	 * 根据id修改字典信息
	 * 
	 * @param dict
	 * 				修改后的字典信息
	 * @return
	 */
	boolean updateDict(Dict dict);
	
	/**
	 * 获取所有考场信息
	 * 
	 * @return
	 */
	List<ExamRoom> getRoom();

	/**
	 * 根据id查询字典信息
	 * 
	 * @param id
	 * 				主键
	 * @return
	 */
	Dict getDictById(int id);

	/**
	 * 保存考场信息
	 * 
	 * @param room
	 * 				考场信息
	 * @return
	 */
	boolean saveRoom(ExamRoom room);

	/**
	 * 根据id获取考场信息
	 * 
	 * @param id
	 * 				考场id
	 * @return
	 */
	ExamRoom getRoomById(int id);

	/**
	 * 根据id删除考场信息
	 * 
	 * @param ids
	 * 				id集合
	 * @return
	 */
	int deleteRoom(int[] ids);

	/**
	 * 开放考场
	 * 
	 * @param ids
	 * 					id集合
	 * @return
	 */
	int openRoom(int[] ids);

	/**
	 * 根据id修改考场信息
	 * 
	 * @param room
	 * 				考场信息
	 * @return
	 */
	boolean updateRoom(ExamRoom room);

	/**
	 * 获取有用的考场信息
	 * 
	 * @param time
	 * 				当前时间
	 * @return
	 */
	List<ExamRoom> getTicket(String time);

	/**
	 * 根据考生id获取该考生已经打印过的准考证
	 * 
	 * @param stuId
	 * 					考生主键
	 * @return
	 */
	List<Ticket> getPrintTicket(String stuId);

	/**
	 * 保存准考证信息
	 * 
	 * @param ticket
	 * 					准考证信息
	 * @return
	 */
	boolean saveTicket(Ticket ticket);

	/**
	 * 更新考场剩余人数
	 * 
	 * @param er 
	 * 					考场信息
	 * 
	 * @return
	 */
	boolean updateRemain(ExamRoom er);

	/**
	 * 根据主键获取准考证信息
	 * 
	 * @param id
	 * 					准考证主键
	 * @return
	 */
	Ticket getTicketById(int id);
	
	/**
	 * 
	 */
	List<Ticket> getTicketsByExamId(int ExamId);
	
	Info getInfoById(String id);

}
