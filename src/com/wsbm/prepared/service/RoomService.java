package com.wsbm.prepared.service;

import java.util.List;

import com.wsbm.prepared.model.ExamRoom;
import com.wsbm.prepared.model.Ticket;

/**
 * 考场表的业务逻辑处理类-接口
 * @author chen
 *
 */
public interface RoomService {

	List<ExamRoom> getRoom();

	boolean saveRoom(ExamRoom room);

	boolean delRoom(String ids);

	boolean openRoom(String ids);

	ExamRoom getRoomById(int id);

	boolean updateRoom(ExamRoom room);

	List<ExamRoom> getTicket();

	Ticket printTicket(int id, String stuId);

}
