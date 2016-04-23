package com.wsbm.prepared.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.wsbm.login.model.Admin;
import com.wsbm.login.model.User;
import com.wsbm.prepared.model.Dict;
import com.wsbm.prepared.model.ExamRoom;
import com.wsbm.prepared.model.Ticket;
import com.wsbm.prepared.model.TicketsShow;
import com.wsbm.prepared.service.PreService;
import com.wsbm.prepared.service.RoomService;
import com.wsbm.util.Utils;

import net.sf.json.JSONObject;

/**
 * 考场表的控制处理类
 * @author chen
 *
 */
@Controller
public class RoomController {
	
	private static final Logger log = Logger.getLogger(PreController.class);
	
	@Autowired
	protected RoomService roomService = null;
	
	@Autowired
	protected PreService preService = null;
	
	/**
	 * 获取所有的考场信息
	 * 
	 * @param request
	 * 					请求
	 * @return
	 */
	@RequestMapping(value="getRoom")
	public String getRoom(HttpServletRequest request){
		
		List<ExamRoom> list = roomService.getRoom();
		List<ExamRoom> list2 = new ArrayList<ExamRoom>();
		for(ExamRoom examRoom:list){
			int examId=examRoom.getE_course_id();
			int schoolId=examRoom.getE_school_id();
			examRoom.setE_school(preService.getDictById(String.valueOf(schoolId)));
			examRoom.setE_course(preService.getDictById(String.valueOf(examId)));
			list2.add(examRoom);
		}
		List<Dict> sList = preService.getDict("school");//校区字典
		List<Dict> cList = preService.getDict("course");//课程字典
		request.setAttribute("list", list2);
		request.setAttribute("sList", sList);
		request.setAttribute("cList", cList);
		return "jsp/back/room";
		
	}
	
	/**
	 * 保存考场信息
	 * 
	 * @param request
	 * 					请求
	 * @param room
	 * 					考场管理
	 * @return
	 */
	@RequestMapping(value="saveRoom")
	public String saveRoom(HttpServletRequest request, ExamRoom room){
		
		String message = "";
		if(room != null && !"".equals(room)){
			//保存考场信息
			boolean result = roomService.saveRoom(room);
			if(result){
				message = "保存成功！开放考场后考生可以打印准考证！";
				HttpSession session = request.getSession();
				Admin admin = (Admin) session.getAttribute("user");
				log.info("管理员【" + admin.getR_name() + "】增加了考场【" + room.getE_name() + "】");
			}else{
				message = "保存失败！可能是考试时间不合理！";
			}
		}else{
			message = "保存失败！考场信息为空！";
		}
		List<ExamRoom> list = roomService.getRoom();
		List<Dict> sList = preService.getDict("school");//校区字典
		List<Dict> cList = preService.getDict("course");//课程字典
		request.setAttribute("list", list);
		request.setAttribute("sList", sList);
		request.setAttribute("cList", cList);
		request.setAttribute("message", message);
		return "jsp/back/room";
		
	}
	
	/**
	 * 删除考场
	 * 
	 * @param request
	 * 					请求
	 * @param ids
	 * 					id集合
	 * @return
	 */
	@RequestMapping(value="delRoom")
	public String delRoom(HttpServletRequest request, String ids){
		
		String message = null;
		boolean result = roomService.delRoom(ids);
		if(result){
			HttpSession session = request.getSession();
			Admin admin = (Admin) session.getAttribute("user");
			log.info("管理员【" + admin.getR_name() + "】删除了考场【" + ids + "】");
			message = "删除成功！";
		}else{
			message = "删除失败！开放考场后在考试结束前考场不允许被删除！";
		}
		List<ExamRoom> list = roomService.getRoom();
		List<Dict> sList = preService.getDict("school");//校区字典
		List<Dict> cList = preService.getDict("course");//课程字典
		request.setAttribute("list", list);
		request.setAttribute("sList", sList);
		request.setAttribute("cList", cList);
		request.setAttribute("message", message);
		return "jsp/back/room";
		
	}
	
	/**
	 * 开放考场
	 * 
	 * @param request
	 * 					请求
	 * @param ids
	 * 					id集合
	 * @return
	 */
	@RequestMapping(value="openRoom")
	public String openRoom(HttpServletRequest request, String ids){
		
		String message = null;
		boolean result = roomService.openRoom(ids);
		if(result){
			HttpSession session = request.getSession();
			Admin admin = (Admin) session.getAttribute("user");
			log.info("管理员【" + admin.getR_name() + "】开放了考场【" + ids + "】");
			message = "考场开放成功！考生可以开始打印准考证！";
		}else{
			message = "开放考场失败！请重试！";
		}
		List<ExamRoom> list = roomService.getRoom();
		List<Dict> sList = preService.getDict("school");//校区字典
		List<Dict> cList = preService.getDict("course");//课程字典
		request.setAttribute("list", list);
		request.setAttribute("sList", sList);
		request.setAttribute("cList", cList);
		request.setAttribute("message", message);
		return "jsp/back/room";
		
	}
	
	/**
	 * 根据id获取考场信息
	 * 
	 * @param response
	 * 					响应
	 * @param id	
	 * 					主键
	 */
	@RequestMapping(value="getRoomById")
	public void getRoomById(HttpServletResponse response, int id){
		
		PrintWriter pw = null;
		ExamRoom room = null;
		try {
			pw = response.getWriter();
			room = roomService.getRoomById(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = JSONObject.fromObject(room);
		pw.write(jsonObject.toString());
		pw.close();
		pw = null;
		
	}
	
	/**
	 * 更新考场信息
	 * 
	 * @param request
	 * 					请求
	 * @param room
	 * 					考场信息
	 * @return
	 */
	@RequestMapping(value="updateRoom")
	public String updateRoom(HttpServletRequest request, ExamRoom room){
		
		String message = "";
		if(room != null && !"".equals(room)){
			boolean result = roomService.updateRoom(room);
			if(result){
				message = "修改成功！开放考场后考生可以打印准考证！";
				HttpSession session = request.getSession();
				Admin admin = (Admin)session.getAttribute("user");
				log.info("管理员【" + admin.getR_name() + "】修改了考场信息【" + room.getE_id() + "】");
			}else{
				message = "修改失败！可能是考试时间设置不合理！";
			}
		}else{
			message = "修改失败！未获取到修改信息！";
		}
		List<ExamRoom> list = roomService.getRoom();
		List<Dict> sList = preService.getDict("school");//校区字典
		List<Dict> cList = preService.getDict("course");//课程字典
		request.setAttribute("list", list);
		request.setAttribute("sList", sList);
		request.setAttribute("cList", cList);
		request.setAttribute("message", message);
		return "jsp/back/room";
		
	}
	
	/**
	 * 获取所有有用的考场信息
	 * 
	 * @param request
	 * 					请求
	 * @return
	 */
	@RequestMapping(value="ticket")
	public String ticket(HttpServletRequest request){
		
		List<ExamRoom> list = roomService.getTicket();
		request.setAttribute("list", list);
		return "jsp/foreground/ticket";
		
	}
	
	/**
	 * 打印准考证
	 * @param response
	 * @param id
	 */
	@RequestMapping(value="printTicket")
	public void printTicket(HttpServletRequest request, HttpServletResponse response, int id){
		
		PrintWriter pw = null;
		Ticket ticket = null;
		try {
			pw = response.getWriter();
			//获取session
			HttpSession session = request.getSession();
			//获取登录用户信息
			User user = (User) session.getAttribute("user");
			ticket = roomService.printTicket(id, user.getId());
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = JSONObject.fromObject(ticket);
		pw.write(jsonObject.toString());
		pw.close();
		pw = null;
		
	}
	
	/**
	 * 获取考场信息
	 */
	@RequestMapping("/app/appGetExamInfo")
	public void getExamRoomInfo(HttpServletRequest request,HttpServletResponse response){
		com.alibaba.fastjson.JSONObject jv = new com.alibaba.fastjson.JSONObject();
		List<ExamRoom> examroomList=roomService.getRoom();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sysTime=sdf.format(new Date());
		List <ExamRoom> retExamRoom = new ArrayList<ExamRoom>();
		for(ExamRoom examRoom:examroomList){
			if(examRoom.getE_endTime().compareTo(sysTime)<0){
				continue;
			}
			int examId=examRoom.getE_course_id();
			int schoolId=examRoom.getE_school_id();
			examRoom.setE_school(preService.getDictById(String.valueOf(schoolId)));
			examRoom.setE_course(preService.getDictById(String.valueOf(examId)));
			retExamRoom.add(examRoom);
		}
		jv.put("res", retExamRoom);
		Utils.writeBack(request, response, jv);
	}
	
	@RequestMapping("/app/appGetExamRoomInfo")
	public void getExamRoomInfos(HttpServletRequest request,HttpServletResponse response,int examId){
		List<Ticket> tickets = preService.getTicketsByExamId(examId);
		List<TicketsShow> ticketsShowList =new ArrayList<TicketsShow>();
		for(Ticket ticket:tickets){
			TicketsShow ticketShow = new TicketsShow();
			ticket.setT_examId(ticket.getT_room().getE_id());
			ticketShow.setTicket(ticket);
			ticketShow.setInfo(preService.getInfoById(ticket.getT_stuId()));
			ticketsShowList.add(ticketShow);
		}
		
		
		com.alibaba.fastjson.JSONObject jv= new com.alibaba.fastjson.JSONObject();
		jv.put("res", ticketsShowList);
		Utils.writeBack(request, response, jv);
	}

}
