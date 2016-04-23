package com.wsbm.login.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wsbm.information.model.Info;
import com.wsbm.information.model.Message;
import com.wsbm.information.service.InfoService;
import com.wsbm.login.model.User;
import com.wsbm.login.service.LoginService;

/**
 * 登录请求处理类
 * @author chen
 *
 */
@Controller
public class LoginController {

	private static final Logger log = Logger.getLogger(LoginController.class);
	
	@Autowired
	protected LoginService loginService = null;
	
	@Autowired
	protected InfoService infoService = null;
	
	/**
	 * 实现验证码
	 * 
	 * @param request
	 * 					HttpServletRequest
	 * @param response
	 * 					HttpServletResponse
	 * 
	 * @throws IOException 
	 */
	@RequestMapping(value="safeCode")
	public void SafeCode(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		//创建一个高37，宽120的图像缓冲
		BufferedImage bi = new BufferedImage(110, 35, BufferedImage.TYPE_INT_RGB);
		//获取一个图像对象
		Graphics g = bi.getGraphics();
		//创建颜色对象
		Color c = new Color(176, 224, 230);
		//给图像设置颜色
		g.setColor(c);
		//给图像设置边框
		g.fillRect(0, 0, 110, 35);
		//给图像设置字体
		g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		
		//设置验证码所需字符
		char[] ch = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
		//随机对象
		Random r = new Random();
		int len = ch.length;//获取验证码所需所有字符的长度
		int index;//下标
		//存储验证码对象
		StringBuffer sb = new StringBuffer();
		//验证码长度为4，循环获取随机字符
		for(int i = 0; i < 4; i++){
			index = r.nextInt(len);//获取随机下标，0-len内
			//设置图像颜色
			g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
			//字符所在图像位置
			g.drawString(ch[index] + "", i * 25 + 5, 30);
			sb.append(ch[index]);
		}
		//绘制干扰线
		for(int i = 0; i < 50; i++){
			int x1 = r.nextInt(110);
			int y1 = r.nextInt(35);
			int x2 = r.nextInt(6) + 1;
			int y2 = r.nextInt(12 + 1);
			g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
			g.drawLine(x1, y1, x1 + x2, y1 + y2);
		}
		//绘制干扰线
		for(int i = 0; i < 50; i++){
			int x1 = r.nextInt(110);
			int y1 = r.nextInt(35);
			int x2 = r.nextInt(6) + 1;
			int y2 = r.nextInt(12 + 1);
			g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
			g.drawLine(x1, y1, x1 - x2, y1 - y2);
		}
		//获取session
		HttpSession session = request.getSession();
		//将验证码存入session中
		session.setAttribute("safeCode", sb.toString());
		//以jpg的格式输出图像
		ImageIO.write(bi, "JPG", response.getOutputStream());
		
	}
	
	/**
	 * 用户注册
	 * 
	 * @param request
	 * 					用户请求
	 * @param user
	 * 					注册信息
	 * @return
	 */
	@RequestMapping(value="saveUser")
	public String registerUser(HttpServletRequest request, User user){
		
		String message = null;
		if(user != null && !"".equals(user)){
			boolean result = loginService.registerUser(user);
			if(result){
				message = "注册成功！请登录！";
				log.info("用户【" + user.getName() + "】注册成功！");
			}else{
				message = "该用户已存在！请直接登录！";
			}
		}else{
			message = "请先填写注册信息！";
		}
		request.setAttribute("message", message);
		return "login";
	}
	
	/**
	 * 用户登录请求
	 * 
	 * @param request
	 * 					请求
	 * @param response
	 * 					响应
	 * @param userName
	 * 					用户名（即身份证号）
	 * @param userPwd
	 * 					登录密码
	 * @param safeCode
	 * 					验证码
	 * @return
	 */
	@RequestMapping(value="login")
	public String userLogin(HttpServletRequest request, HttpServletResponse response
			, String userName, String userPwd, String safeCode) {
		
		//获取前台参数-登录
		User user = new User();
		user.setId(userName);
		user.setPwd(userPwd);
		System.out.println(safeCode);
		System.out.println(request.getSession());
		if(safeCode == null || "".equals(safeCode)
				|| request.getSession() == null){
			request.setAttribute("message", "登录失败,验证码错误！");
			return "login";
		}else if(safeCode.toUpperCase().equals(request.getSession().getAttribute("safeCode"))){
			//是否允许登录
			boolean result = loginService.checkLogin(user);
			if(result){
				//获取用户信息
				user = loginService.getUserInfo(userName);
				//获取session
				HttpSession session = request.getSession();
				Info info = infoService.getInfo(user.getId());
				if(info == null || "".equals(info)){
					request.setAttribute("status", 0);
				}else{
					int status = info.getStatus();
					request.setAttribute("status", status);
				}
				//获取系统消息
				List<Message> message = loginService.getMessage(user.getId());
				request.setAttribute("mes", message);
				session.setAttribute("user", user);
				log.info("用户【" + userName + "】登录成功！");
				return "jsp/foreground/index";
			}else{
				request.setAttribute("message", "登录失败,用户名或密码错误！");
				return "login";
			}
		}else{
			request.setAttribute("message", "登录失败,验证码错误！");
			return "login";
		}
		
	}
	
	/**
	 * 用户退出登录请求
	 * 
	 * @param request
	 * 					请求
	 * @param response
	 * 					相应
	 * @return
	 */
	@RequestMapping(value="logout")
	public String userLogout(HttpServletRequest request, HttpServletResponse response){
		
		//获取session
		HttpSession session = request.getSession();
		//获取登录用户信息
		User user = (User) session.getAttribute("user");
		//获取用户姓名
		String name = user.getName();
		
		session.invalidate();//关闭会话，清除session
		log.info("用户【" + name + "】退出登录！");
		return "login";
		
	}
	
	/**
	 * 修改密码
	 * 
	 * @param request
	 * 					用户请求
	 * @param initPwd
	 * 					初始密码
	 * @param newPwd
	 * 					新密码
	 * @return
	 */
	@RequestMapping(value="updatePwd")
	public String updatePwd(HttpServletRequest request, String initPwd, String newPwd){
		
		String message = null;
		//获取session
		HttpSession session = request.getSession();
		//获取 用户信息
		User user = (User) session.getAttribute("user");
		user.setName(initPwd);//存储原始密码，修改时验证所需
		user.setPwd(newPwd);
		boolean result = loginService.updatePwd(user);
		if(result){
			message = "密码修改成功！请重新登录！";
			log.info("用户【" + user.getName() + "】修改密码成功！");
			request.setAttribute("message", message);
			return "login";
		}else{
			return "jsp/foreground/index";
		}
		
	}
	
}
