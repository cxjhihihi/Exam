package com.wsbm.information.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wsbm.information.model.Conditions;
import com.wsbm.information.model.Info;
import com.wsbm.information.model.Time;
import com.wsbm.information.service.AdminInfoService;
import com.wsbm.information.service.InfoService;
import com.wsbm.login.model.Admin;

/**
 * 管理员的考生信息处理类
 * @author chen
 *
 */
@Controller
public class AdminInfoController {
	
	private static final Logger log = Logger.getLogger(AdminInfoController.class);
	
	@Autowired
	protected AdminInfoService adminInfoService = null;
	@Autowired
	protected InfoService infoService = null;
	
	/**
	 * 获取所有报考学生信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="allStuInfo")
	public String getAllStu(HttpServletRequest request){
		
		//获取报名时间
		Time time = adminInfoService.getTime();
		time.setId(0);//分页查询偏移量
		//获取所有报名信息
		List<Info> list = infoService.getAllInfo(time);
		//获取所有记录数
		int count = infoService.getCount(time);
		//计算总页码(每页20条数据)
		count = count == 0 ? 1 : count % 20 == 0 ? count / 20 : count / 20 + 1;
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		return "jsp/back/stuList";
		
	}
	
	/**
	 * 分页查询
	 * 
	 * @param request
	 * 						HttpServletRequest
	 * @param condition1
	 * 						审核状态
	 * @param condition2
	 * 						审核人
	 * @param paging
	 * 						页码
	 */
	@RequestMapping(value="pagingQuery")
	public String pagingQuery(HttpServletRequest request, int condition1, String condition2, int paging){
		
		//获取报名时间
		Time time = adminInfoService.getTime();
		//计算分页查询偏移量
		paging = (paging - 1) * 20;
		//将查询条件封装到条件类中
		Conditions conditions = new Conditions(condition1, condition2, time.getStartTime(), time.getEndTime(), paging);
		//获取满足条件的学生信息
		List<Info> list = infoService.getInfoByConditions(conditions);
		//获取满足条件的学生个数
		int count = infoService.getCountByConditions(conditions);
		//计算总页码(每页20条数据)
		count = count == 0 ? 1 : count % 20 == 0 ? count / 20 : count / 20 + 1;
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		request.setAttribute("conditions", conditions);
		return "jsp/back/stuList";
		
	}
	
	/**
	 * 获取报名时间
	 * 	
	 * @param request
	 * 					用户请求
	 * @return
	 */
	@RequestMapping(value="getTime")
	public String getTime(HttpServletRequest request){
		
		Time time = adminInfoService.getTime();
		if(time == null || "".equals(time)){
			request.setAttribute("message", "获取报名时间失败，请重试！");
		}else{
			int count = infoService.getCount(time);
			request.setAttribute("count", count);
		}
		/*
		 * 获取当前时间
		 */
		//设置格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String now = sdf.format(date);
		request.setAttribute("now", now);
		request.setAttribute("time", time);
		return "jsp/back/time";
		
	}
	
	/**
	 * 设置报名时间
	 * 
	 * @param request	
	 * 					请求
	 * @param startTime
	 * 					开始时间
	 * @param endTime
	 * 					结束时间
	 * @return
	 */
	@RequestMapping(value="saveTime")
	public String saveTime(HttpServletRequest request, String startTime, String endTime){
		
		String message = null;
		//封装实体
		Time time = new Time();
		time.setName("报名时间");
		time.setStartTime(startTime);
		time.setEndTime(endTime);
		time.setStatus(0);
		//保存报名时间
		boolean result = adminInfoService.saveTime(time);
		if(result){
			//获取session
			HttpSession session = request.getSession();
			//获取登录信息
			Admin admin = (Admin) session.getAttribute("user");
			log.info(session.getAttribute("role") + "【" + admin.getR_name() + "】设置了报名时间"
					+ "【" + startTime + "】-【" + endTime + "】");
			message = "保存成功！学生可以在该时间段内报名！";
			//获取报名时间
			time = adminInfoService.getTime();
			if(time == null || "".equals(time)){
				request.setAttribute("message", "获取报名时间失败，请重试！");
			}else{
				int count = infoService.getCount(time);
				request.setAttribute("count", count);
			}
			request.setAttribute("time", time);
			/*
			 * 获取当前时间
			 */
			//设置格式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String now = sdf.format(date);
			request.setAttribute("now", now);
		}else{
			message = "保存失败！";
		}
		request.setAttribute("message", message);
		return "jsp/back/time";
		
	}
	
	/**
	 * 修改报名时间
	 * 
	 * @param request
	 * 					请求
	 * @param id
	 * 					主键
	 * @param startTime
	 * 					开始时间
	 * @param endTime
	 * 					结束时间
	 * @return
	 */
	@RequestMapping(value="updateTime")
	public String updateTime(HttpServletRequest request, String id, String startTime,
			String endTime){
		
		String message = null;
		//封装实体
		Time time = new Time();
		if(id == null || "".equals(id)){
			message = "修改失败！请重试！";
		}else{
			time.setId(Integer.parseInt(id));
			time.setStartTime(startTime);
			time.setEndTime(endTime);
			//保存报名时间
			boolean result = adminInfoService.updateTime(time);
			if(result){
				//获取session
				HttpSession session = request.getSession();
				//获取登录信息
				Admin admin = (Admin) session.getAttribute("user");
				log.info(session.getAttribute("role") + "【" + admin.getR_name() + "】修改了报名时间"
						+ "【" + startTime + "】-【" + endTime + "】");
				message = "保存成功！学生可以在该时间段内报名！";
				//获取报名时间
				time = adminInfoService.getTime();
				if(time == null || "".equals(time)){
					request.setAttribute("message", "获取报名时间失败，请重试！");
				}else{
					int count = infoService.getCount(time);
					request.setAttribute("count", count);
				}
				request.setAttribute("time", time);
				/*
				 * 获取当前时间
				 */
				//设置格式
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				String now = sdf.format(date);
				request.setAttribute("now", now);
			}else{
				message = "保存失败！";
			}
			request.setAttribute("message", message);
		}
		return "jsp/back/time";
		
	}
	
	/**
	 * 根据id获取详细信息
	 * 
	 * @param request
	 * 					请求
	 * @param id
	 * 					id
	 * @return
	 */
	@RequestMapping(value="stuInfo")
	public String stuInfo(HttpServletRequest request, String id){
		
		Info info = infoService.getStuInfo(id);
		request.setAttribute("info", info);
		return "jsp/back/stu";
		
	}
	
	/**
	 * 获取将要审核的信息
	 * 
	 * @param request
	 * 					HttpServletRequest
	 * @return
	 */
	@RequestMapping(value="audit")
	public String audit(HttpServletRequest request){
		
		//获取session
		HttpSession session = request.getSession();
		//获取主键
		int id = ((Admin)session.getAttribute("user")).getR_id();
		Info info = infoService.updateAudit(id);
		request.setAttribute("info", info);
		return "jsp/back/audit";
		
	}
	
	/**
	 * 更新审核状态
	 * 
	 * @param request
	 * 					HttpServletRequest	
	 * @param status
	 * 					审核状态
	 * @param id
	 * 					审核id
	 * @return
	 */
	@RequestMapping(value="updateAuditStatus")
	public String updateAuditStatus(HttpServletRequest request, int status, String id){
		
		System.out.println("===="+status+"===="+id);
		//获取session
		HttpSession session = request.getSession();
		//获取主键
		int id2 = ((Admin)session.getAttribute("user")).getR_id();
		boolean result = infoService.updateAuditStatus(status, id, id2);
		if(result){
			request.setAttribute("message", "操作成功！");
		}else{
			request.setAttribute("message", "操作失败！");
		}
		Info info = infoService.updateAudit(id2);
		request.setAttribute("info", info);
		return "jsp/back/audit";
		
	}
	
	/**
	 * 获取所有审核信息
	 * 
	 * @param request
	 * 					HttpServletRequest
	 * @return
	 */
	@RequestMapping(value="allAudit")
	public String allAudit(HttpServletRequest request){
		
		//获取session
		HttpSession session = request.getSession();
		//获取主键
		int id = ((Admin)session.getAttribute("user")).getR_id();
		List<Info> list = infoService.getAllAudit(id);
		request.setAttribute("list", list);
		return "jsp/back/auditList";
		
	}
	
	/**
	 * 条件查询审核信息
	 * 
	 * @param request
	 * 						HttpServletRequest
	 * @param condition1
	 * 						条件一
	 * @param condition2
	 * 						条件二
	 * @return
	 */
	@RequestMapping(value="queryAudit")
	public String queryAudit(HttpServletRequest request, int condition1, String condition2){
		
		//获取session
		HttpSession session = request.getSession();
		//获取主键
		int id = ((Admin)session.getAttribute("user")).getR_id();
		Conditions conditions = new Conditions();
		conditions.setStatus(condition1);
		conditions.setName(condition2);
		conditions.setPaging(id);
		List<Info> list = infoService.queryAudit(conditions);
		request.setAttribute("list", list);
		request.setAttribute("conditions", conditions);
		return "jsp/back/auditList";
		
	}
	
}
