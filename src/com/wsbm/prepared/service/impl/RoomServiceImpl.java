package com.wsbm.prepared.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wsbm.information.mapper.InfoMapper;
import com.wsbm.information.model.Info;
import com.wsbm.prepared.mapper.PreMapper;
import com.wsbm.prepared.model.ExamRoom;
import com.wsbm.prepared.model.Ticket;
import com.wsbm.prepared.service.RoomService;

/**
 * 考场表的业务逻辑处理类-接口实现类
 * @author chen
 *
 */
@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	protected PreMapper preMapper = null;
	
	@Autowired
	protected InfoMapper infoMapper = null;
	
	/**
	 * 获取所有考场信息
	 */
	@Override
	public List<ExamRoom> getRoom() {
		
		return preMapper.getRoom();
		
	}

	/**
	 * 保存考场信息
	 */
	@Override
	public boolean saveRoom(ExamRoom room) {

		if(room != null && !"".equals(room)){
			if((room.getE_startTime()).compareTo(room.getE_endTime()) >= 0){
				return false;
			}
			return preMapper.saveRoom(room);
		}
		return false;
		
	}

	/**
	 * 根据id删除考场信息
	 */
	@Override
	public boolean delRoom(String ids) {

		if(ids != null && !"".equals(ids)){
			//格式化时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
			//获取当前时间
			Date date = new Date();
			//当前时间
			String time = sdf.format(date);
			String[] idArray = ids.split(",");
			int[] intIdArray = new int[idArray.length];
			for(int i = 0; i < idArray.length; i++){
				intIdArray[i] = Integer.parseInt(idArray[i]);
				//根据id获取考场信息
				ExamRoom er = preMapper.getRoomById(intIdArray[i]);
				if(time.compareTo((er.getE_endTime()).substring(0, 19)) < 0
						&& er.getE_start() == 1){
					return false;
				}
			}
			int num = preMapper.deleteRoom(intIdArray);
			if(num == idArray.length){
				return true;
			}
			return false;
		}
		return false;
		
	}

	/**
	 * 开放考场
	 */
	@Override
	public boolean openRoom(String ids) {

		if(ids != null && !"".equals(ids)){
			String[] idArray = ids.split(",");
			int[] intIdArray = new int[idArray.length];
			for(int i = 0; i < idArray.length; i++){
				intIdArray[i] = Integer.parseInt(idArray[i]);
			}
			int num = preMapper.openRoom(intIdArray);
			if(num == idArray.length){
				return true;
			}
			return false;
		}
		return false;
		
	}

	/**
	 * 根据id获取考场信息
	 */
	@Override
	public ExamRoom getRoomById(int id) {
		
		if(id > 0){
			return preMapper.getRoomById(id);
		}
		return null;
	}

	/**
	 * 根据id修改考场信息
	 */
	@Override
	public boolean updateRoom(ExamRoom room) {

		if(room != null && !"".equals(room)){
			if((room.getE_startTime()).compareTo(room.getE_endTime()) >= 0){
				return false;
			}
			return preMapper.updateRoom(room);
		}
		return false;
		
	}

	/**
	 * 获取有用的考场信息
	 */
	@Override
	public List<ExamRoom> getTicket() {

		//格式化时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:00.0");
		//获取当前时间
		Date date = new Date();
		//当前时间
		String time = sdf.format(date);
		return preMapper.getTicket(time);
		
	}

	/**
	 * 打印准考证
	 */
	@Override
	public Ticket printTicket(int id, String stuId) {
		
		if(id > 0){
			//通过登录用户id查询报名学生的信息
			Info info = infoMapper.getInfo(stuId);
			System.out.println("====="+info.getStatus());
			if(info.getStatus() == 2){//通过审核
				//考生id
				stuId = info.getId();
				//获取该考生已经打印过的准考证
				List<Ticket> list = preMapper.getPrintTicket(stuId);
				//根据id获取考场信息
				ExamRoom room = preMapper.getRoomById(id);
				for(Ticket ticket: list){
					//已经打印过该科目的准考证，只能打印已存在的
					if((ticket.getT_room().getE_course().getD_name())
							.equals(room.getE_course().getD_name())){
						return ticket;
					}
				}
				//没有打印过准考证，获取剩余座位数
				int remain = room.getE_remain();
				if(remain <= 0){//没有剩余座位
					return null;
				}
				//有剩余座位，分配座位
				int seat = room.getE_num() - room.getE_remain() + 1;
				//封装成对象
				Ticket ticket = new Ticket();
				ticket.setT_examId(id);
				ticket.setT_stuId(stuId);
				ticket.setT_seat(seat);
				//保存准考证信息
				boolean result1 = preMapper.saveTicket(ticket);
				//更新考场剩余人数
				ExamRoom er = new ExamRoom();
				er.setE_id(id);
				er.setE_remain(room.getE_remain() - 1);
				boolean result2 = preMapper.updateRemain(er);
				//保存成功
				if(result1 & result2){
					Ticket bean = preMapper.getTicketById(ticket.getT_id());
					return bean;
				}else{
					return null;//保存失败
				}
			}else{//未通过审核
				return null;
			}
		}else{
			return null;
		}
		
	}
	
}
