package com.wsbm.information.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wsbm.information.model.Info;
import com.wsbm.information.model.Message;
import com.wsbm.information.model.Time;
import com.wsbm.information.service.AdminInfoService;
import com.wsbm.information.service.InfoService;
import com.wsbm.login.model.User;
import com.wsbm.util.FileUpload;

import net.sf.json.JSONObject;

/**
 * 考生信息处理类
 * @author chen
 *
 */
@Controller
public class InfoController {

	private static final Logger log = Logger.getLogger(InfoController.class);
	
	@Autowired
	protected InfoService infoService = null;
	
	@Autowired
	protected AdminInfoService adminInfoService = null;
	
	/**
	 * 获取学生的报名信息
	 * 
	 * @param request
	 * 					用户请求
	 * @return
	 */
	@RequestMapping(value="information")
	public String getInfo(HttpServletRequest request){
		
		//获取session
		HttpSession session = request.getSession();
		//获取登录用户信息
		User user = (User) session.getAttribute("user");
		//获取登录用户身份证号
		String id = user.getId();
		//获取考生信息
		Info info = infoService.getInfo(id);
		//获取报名时间
		Time enterTime = adminInfoService.getTime();
		/*
		 * 获取当前时间
		 */
		//设置格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String time = sdf.format(date);
		request.setAttribute("info", info);
		request.setAttribute("now", time);
		request.setAttribute("time", enterTime);
		return "jsp/foreground/information";
		
	}
	
	/**
	 * 保存考生报名信息
	 * 
	 * @param request
	 * 					用户请求
	 * @return
	 */
	@RequestMapping(value="saveInfo")
	public String saveInfo(HttpServletRequest request){
		
		String message = null;
		Info info = FileUpload.uploadFile(request);
		/*
		 * 获取当前时间
		 */
		//设置格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String time = sdf.format(date);
		//获取报名时间
		Time enterTime = adminInfoService.getTime();
		String startTime = enterTime.getStartTime().substring(0, 10);//开始时间
		String endTime = enterTime.getEndTime().substring(0, 10);//结束时间
		if(info != null && !"".equals(info)){
			//设置审核状态
			info.setStatus(0);
			//设置报名时间
			info.setTime(time);
			boolean result = false;
			if(time.compareTo(startTime) >= 0 && 
					time.compareTo(endTime) <= 0){//报名时间内
				System.out.println("in reg");
				info.setEnter(1);//报名
				//得到文件保存路径，便于删除上传的图片
				String savePath = null;
				try {
					savePath = request.getServletContext().getResource("/WEB-INF/upload/picture").toString();
					savePath = savePath.substring(savePath.indexOf("/") + 1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				result = infoService.updateInfo(info, savePath);
				message = "报名成功！学校将可以查看您的报名信息！";
			}else if(time.compareTo(startTime) < 0){//尚未开始报名
				System.out.println("not reg");
				info.setEnter(0);
				result = infoService.saveInfo(info);
				message = "保存成功！请在报名开始后提交您的报名信息！";
			}else if(time.compareTo(endTime) > 0){//报名结束
				System.out.println("end reg");
				message = "报名已结束！";
				request.setAttribute("message", message);
				request.setAttribute("time", enterTime);
				request.setAttribute("now", time);
				return "jsp/foreground/information";
			}
		
			if(result){
				log.info("用户【" + info.getName() + "】报名成功！");
				request.setAttribute("info", info);
			}else{
				message = "报名失败！请稍后重试！";
			}
		}else{
			message = "报名失败！提交的报名信息为空！请检查填写内容！";
		}
		request.setAttribute("message", message);
		request.setAttribute("time", enterTime);
		request.setAttribute("now", time);
		return "jsp/foreground/information";
		
	}
	
	/**
	 * 修改考生报名信息
	 * 
	 * @param request
	 * 					用户请求
	 * @return
	 */
	@RequestMapping(value="updateInfo")
	public String updateInfo(HttpServletRequest request){
		
		String message = null;
		Info info = FileUpload.uploadFile(request);
		/*
		 * 获取当前时间
		 */
		//设置格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String time = sdf.format(date);
		//获取报名时间
		Time enterTime = adminInfoService.getTime();
		String startTime = enterTime.getStartTime().substring(0, 10);//开始时间
		String endTime = enterTime.getEndTime().substring(0, 10);//结束时间
		if(info != null && !"".equals(info)){
			//得到文件保存路径，便于删除上传的图片
			String savePath = null;
			try {
				savePath = request.getServletContext().getResource("/WEB-INF/upload/picture").toString();
				savePath = savePath.substring(savePath.indexOf("/") + 1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//设置审核状态
			info.setStatus(0);
			//设置报名时间
			info.setTime(time);
			if(time.compareTo(startTime) >= 0 && 
					time.compareTo(endTime) <= 0){
				info.setEnter(1);//报名
				message = "修改成功！学校将可以查看您的最新信息！";
			}else if(time.compareTo(startTime) < 0){//报名未开始
				info.setEnter(0);//不报名
				message = "修改成功！请在报名开始后提交报名信息！";
			}else if(time.compareTo(endTime) > 0){//报名已结束
				message = "报名已结束！";
				request.setAttribute("message", message);
				request.setAttribute("time", enterTime);
				request.setAttribute("now", time);
				return "jsp/foreground/information";
			}
			boolean result = infoService.updateInfo(info, savePath);
			if(result){
				log.info("用户【" + info.getName() + "】修改报名信息！");
				request.setAttribute("info", info);
			}else{
				message = "修改失败！请稍后重试！";
			}
		}else{
			message = "修改失败！提交的修改信息为空！请检查填写内容！";
		}
		request.setAttribute("message", message);
		request.setAttribute("time", enterTime);
		request.setAttribute("now", time);
		return "jsp/foreground/information";
		
	}
	
	/**
	 * 取消报名
	 * 
	 * @param request
	 * 					请求
	 * @return
	 */
	@RequestMapping(value="cancelInfo")
	public String cancelInfo(HttpServletRequest request){
		
		String message = null;//提示信息
		//获取报考信息
		Info info = FileUpload.uploadFile(request);
		/*
		 * 获取当前时间
		 */
		//设置格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String time = sdf.format(date);
		//获取报名时间
		Time enterTime = adminInfoService.getTime();
		String startTime = enterTime.getStartTime().substring(0, 10);//开始时间
		String endTime = enterTime.getEndTime().substring(0, 10);//结束时间
		if(time.compareTo(startTime) >= 0 && 
				time.compareTo(endTime) <= 0){//报名时间内
			info.setEnter(2);//取消报名标志
			if(info != null && !"".equals(info)){
				String id = info.getIdCard();//获取报名用户身份证号
				boolean result = infoService.cancelInfo(id);
				if(result){
					message = "取消成功！学校将不能查看您的报名信息！";
					log.info("用户【" + info.getName() + "】取消报名！");
					request.setAttribute("info", info);
				}else{
					message = "取消失败！没有查询到报名信息！";
				}
			}else{
				message = "取消失败！没有查询到报名信息！";
			}
		}else{//报名结束
			message = "取消失败！报名已结束，不允许取消！";
		}
		
		request.setAttribute("message", message);
		request.setAttribute("time", enterTime);
		request.setAttribute("now", time);
		return "jsp/foreground/information";
		
	}
	
	/**
	 * 将未读消息设置为已读消息
	 * 
	 * @param request
	 * 					HttpServletRequest
	 * @param response
	 * 					HttpServletResponse
	 */
	@RequestMapping(value="changeReaded")
	public void changeReaded(HttpServletRequest request, HttpServletResponse response){
		
		PrintWriter pw = null;
		try {
			pw = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//获取session
		HttpSession session = request.getSession();
		//获取id
		String id = ((User)session.getAttribute("user")).getId();
		boolean result = infoService.changeReaded(id);
		JSONObject jsonObject = JSONObject.fromObject(result);
		pw.write(jsonObject.toString());
		pw.close();
		pw = null;
		
	}
	
	/**
	 * 获取所有信息
	 * 
	 * @param request
	 * 					HttpServletRequest
	 * @return
	 */
	@RequestMapping(value="allMessage")
	public String allMessage(HttpServletRequest request){
		
		//获取session
		HttpSession session = request.getSession();
		//获取id
		String id = ((User)session.getAttribute("user")).getId();
		List<Message> list = infoService.getAllMessage(id);
		request.setAttribute("list", list);
		return "jsp/foreground/allMessage";
		
	}
	
	/**
	 * 更改阅读状态
	 * 	
	 * @param request
	 * 					HttpServletRequest
	 * @param id
	 * 					主键
	 * @return
	 */
	@RequestMapping(value="sign")
	public String sign(HttpServletRequest request, int id){
		
		boolean result = infoService.changeReadedById(id);
		if(result){
			//获取session
			HttpSession session = request.getSession();
			//获取id
			String id2 = ((User)session.getAttribute("user")).getId();
			List<Message> list = infoService.getAllMessage(id2);
			request.setAttribute("list", list);
		}
		return "jsp/foreground/allMessage";
		
	}
	
	/**
	 * 条件查询消息
	 * 
	 * @param request
	 * 					HttpServletRequest
	 * @param status
	 * 					阅读状态
	 * @return
	 */
	@RequestMapping(value="queryMessage")
	public String queryMessage(HttpServletRequest request, int status){
		
		//获取session
		HttpSession session = request.getSession();
		//获取id
		String id = ((User)session.getAttribute("user")).getId();
		List<Message> list = infoService.queryMessage(id, status);
		request.setAttribute("list", list);
		request.setAttribute("status", status);
		return "jsp/foreground/allMessage";
		
	}
	
}
