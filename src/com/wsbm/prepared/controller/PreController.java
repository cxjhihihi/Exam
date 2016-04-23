package com.wsbm.prepared.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wsbm.information.model.MesModel;
import com.wsbm.login.model.Admin;
import com.wsbm.prepared.model.Dict;
import com.wsbm.prepared.service.PreService;

import net.sf.json.JSONObject;

/**
 * 考试前的准备工作控制处理类
 * @author chen
 *
 */
@Controller
public class PreController {
	
	private static final Logger log = Logger.getLogger(PreController.class);
	
	@Autowired
	protected PreService preService = null;
	
	/**
	 * 获取字典
	 * 
	 * @param request
	 * 
	 * @param type
	 * 
	 * @return
	 */
	@RequestMapping(value="getDict")
	public String getDict(HttpServletRequest request, String type){
		
		List<Dict> list = new ArrayList<Dict>();
		if(type != null && !"".equals(type)){
			list = preService.getDict(type);
		}
		request.setAttribute("list", list);
		request.setAttribute("type", type);
		return "jsp/back/dict";
		
	}
	
	/**
	 * 保存字典
	 * 
	 * @param request
	 * 					用户请求
	 * @param dict
	 * 					字典信息
	 * @return
	 */
	@RequestMapping(value="saveDict")
	public String saveDict(HttpServletRequest request, Dict dict){
		
		String message = null;
		if(dict != null && !"".equals(dict)){
			boolean result = preService.saveDict(dict);
			if(result){
				message = "增加成功！";
				//获取session
				HttpSession session = request.getSession();
				//获取登录用户信息
				Admin admin = (Admin) session.getAttribute("user");
				log.info(session.getAttribute("role") + "【" + admin.getR_name() + "】"
						+ "增加了一门学科或校区【" + dict.getD_name() + "】");
			}else{
				message = "增加失败！可能是代码重复！请重试！";
			}
			List<Dict> list = new ArrayList<Dict>();
			if(dict.getD_type() != null && !"".equals(dict.getD_type())){
				list = preService.getDict(dict.getD_type());
			}
			request.setAttribute("list", list);
			request.setAttribute("type", dict.getD_type());
		}else{
			message = "增加失败！未发现信息！";
		}
		request.setAttribute("message", message);
		return "jsp/back/dict";
		
	}
	
	/**
	 * 删除字典
	 * 
	 * @param request
	 * 					请求
	 * @param ids
	 * 					id集合
	 * @param type
	 * 					类型
	 * @return
	 */
	@RequestMapping(value="delDict")
	public String delDict(HttpServletRequest request, String ids, String type){
		
		String message = null;
		//根据id删除字典
		boolean result = preService.deleteDict(ids);
		if(result){
			HttpSession session = request.getSession();
			Admin admin = (Admin) session.getAttribute("user");
			log.info("管理员【" + admin.getR_name() + "】删除了字典【" + ids + "】");
			message = "删除成功！";
		}else{
			message = "删除失败！";
		}
		List<Dict> list = new ArrayList<Dict>();
		if(type != null && !"".equals(type)){
			list = preService.getDict(type);
		}
		request.setAttribute("list", list);
		request.setAttribute("type", type);
		request.setAttribute("message", message);
		return "jsp/back/dict";
		
	}
	
	/**
	 * 根据id获取字典信息
	 * 
	 * @param response
	 * 					响应
	 * @param id
	 * 					主键
	 */
	@RequestMapping(value="getDictById")
	public void getDictById(HttpServletResponse response, String id){
		
		PrintWriter pw = null;
		Dict dict = null;
		try {
			pw = response.getWriter();
			dict = preService.getDictById(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject jsonObject = JSONObject.fromObject(dict);
		pw.write(jsonObject.toString());
		pw.close();
		pw = null;
		
	}
	
	/**
	 * 更新字典信息
	 * 
	 * @param request
	 * 					请求
	 * @param dict
	 * 					字典信息
	 * @return
	 */
	@RequestMapping(value="updateDict")
	public String updateDict(HttpServletRequest request, Dict dict){
		
		String message = null;
		if(dict != null && !"".equals(dict)){
			boolean result = preService.updateDict(dict);//修改字典
			if(result){
				message = "修改成功！";
				//获取session
				HttpSession session = request.getSession();
				//获取登录用户信息
				Admin admin = (Admin) session.getAttribute("user");
				log.info(session.getAttribute("role") + "【" + admin.getR_name() + "】"
						+ "修改了一门学科或校区【" + dict.getD_name() + "】");
			}else{
				message = "修改失败！可能是代码重复！请重试！";
			}
			List<Dict> list = new ArrayList<Dict>();
			if(dict.getD_type() != null && !"".equals(dict.getD_type())){
				list = preService.getDict(dict.getD_type());
			}
			request.setAttribute("list", list);
			request.setAttribute("type", dict.getD_type());
		}else{
			message = "修改失败！未发现信息！";
		}
		request.setAttribute("message", message);
		return "jsp/back/dict";
		
	}
	
	/**
	 * 获取消息内容
	 * 
	 * @param request
	 * 					HttpServletRequest
	 * @param sendTime
	 * 					发送时间
	 * @return
	 */
	@RequestMapping(value="getNotice")
	public String getNotice(HttpServletRequest request, String sendTime){
		
		if(sendTime == null || "".equals(sendTime)){
			sendTime = "2";
		}
		MesModel mesModel = preService.getNotice(sendTime);
		request.setAttribute("mesMode", mesModel);
		request.setAttribute("sendTime", sendTime);
		return "jsp/back/notice";
		
	}
	
	/**
	 * 更新通知模板内容
	 * 
	 * @param request
	 * 					HttpServletRequest
	 * @param sendTime
	 * 					发送时间
	 * @param content
	 * 					通知内容
	 * @return
	 */
	@RequestMapping(value="updateNotice")
	public String updateNotice(HttpServletRequest request, int sendTime, String content){
		
		boolean result = preService.updateNotice(sendTime, content);
		if(result){
			request.setAttribute("message", "修改成功！");
		}else{
			request.setAttribute("message", "修改失败！");
		}
		MesModel mesModel = preService.getNotice(sendTime + "");
		request.setAttribute("mesMode", mesModel);
		request.setAttribute("sendTime", sendTime);
		return "jsp/back/notice";
		
	}

}
