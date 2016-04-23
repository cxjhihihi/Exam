package com.wsbm.login.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.sun.xml.internal.ws.resources.HttpserverMessages;
import com.wsbm.information.model.Time;
import com.wsbm.information.service.AdminInfoService;
import com.wsbm.information.service.InfoService;
import com.wsbm.login.model.Admin;
import com.wsbm.login.service.AdminService;
import com.wsbm.util.Utils;

/**
 * 管理员登录处理类
 * 
 * @author chen
 * 
 */
@Controller
public class AdminLoginController {

	private static final Logger log = Logger
			.getLogger(AdminLoginController.class);

	@Autowired
	protected AdminService adminService = null;

	@Autowired
	protected InfoService infoService = null;

	@Autowired
	protected AdminInfoService adminInfoService = null;

	/**
	 * 管理员登录
	 * 
	 * @param request
	 *            请求
	 * @param userName
	 *            用户名
	 * @param userPwd
	 *            密码
	 * @return
	 */
	@RequestMapping(value = "adminLogin")
	public String adminLogin(HttpServletRequest request, String userName,
			String userPwd, String safeCode) {

		String message = null;
		// 封装
		Admin admin = new Admin();
		admin.setR_account(userName);
		admin.setR_pwd(userPwd);
		if (safeCode == null || "".equals(safeCode)
				|| request.getSession() == null) {
			request.setAttribute("message", "登录失败,验证码错误！");
			return "admin";
		} else if (safeCode.toUpperCase().equals(
				request.getSession().getAttribute("safeCode"))) {
			// 是否允许登录
			boolean result = adminService.checkLogin(admin);
			if (result) {
				// 获取登录用户信息
				admin = adminService.getAdminInfo(admin);
				int roleCode = admin.getR_role();
				String realRole = null;
				if (roleCode == 0) {
					realRole = "管理员";
				} else if (roleCode == 1) {
					realRole = "审核人";
				} else if (roleCode == 2) {
					realRole = "阅卷人";
				} else if (roleCode == 3) {
					realRole = "校区管理员";
				}
				log.info(realRole + "【" + admin.getR_name() + "】登录成功！");
				// 获取session
				HttpSession session = request.getSession();
				Time time = adminInfoService.getTime();
				int count = infoService.getCount(time);
				request.setAttribute("count", count);
				session.setAttribute("user", admin);
				session.setAttribute("role", realRole);
				return "jsp/back/index";
			} else {
				message = "登录失败！用户名或密码错误！";
				request.setAttribute("message", message);
				return "admin";
			}
		} else {
			request.setAttribute("message", "登录失败,验证码错误！");
			return "admin";
		}

	}

	@RequestMapping(value = "/app/appAdminLogin")
	public void appAdminLogin(HttpServletRequest request,
			HttpServletResponse response, String userName, String userPwd) {
		System.out.println("dasdsadsadas");
		JSONObject jv = new JSONObject();

		// 封装
		Admin admin = new Admin();
		admin.setR_account(userName);
		admin.setR_pwd(userPwd);
		boolean result = adminService.checkLogin(admin);
		if (result) {
			// 获取登录用户信息
			admin = adminService.getAdminInfo(admin);
			int roleCode = admin.getR_role();
			String realRole = null;
			if (roleCode == 0) {
				realRole = "管理员";
			} else if (roleCode == 1) {
				realRole = "审核人";
			} else if (roleCode == 2) {
				realRole = "阅卷人";
			} else if (roleCode == 3) {
				realRole = "校区管理员";
			}
			log.info(realRole + "【" + admin.getR_name() + "】登录成功！");
			// 获取session
			HttpSession session = request.getSession();
			Time time = adminInfoService.getTime();
			int count = infoService.getCount(time);
			request.setAttribute("count", count);
			session.setAttribute("user", admin);
			session.setAttribute("role", realRole);
			jv.put("result", "true");

		} else {
			jv.put("result", "false");
			jv.put("msg", "username or password error");

		}
		Utils.writeBack(request, response, jv);

	}

	/**
	 * 修改密码
	 * 
	 * @param request
	 *            请求
	 * @param initPwd
	 *            初始密码
	 * @param newPwd
	 *            新密码
	 * @return
	 */
	@RequestMapping(value = "updateAdminPwd")
	public String updateAdminPwd(HttpServletRequest request, String initPwd,
			String newPwd, String confirmPwd) {

		String message = null;
		// 获取session
		HttpSession session = request.getSession();
		if (!newPwd.equals(confirmPwd)) {
			message = "密码修改失败！两次密码输入不一致！";
			request.setAttribute("message", message);
			return "jsp/back/index";
		} else {
			// 获取 用户信息
			Admin user = (Admin) session.getAttribute("user");
			user.setR_name(initPwd);// 存储原始密码，修改时验证所需
			user.setR_pwd(newPwd);// 新密码
			boolean result = adminService.updatePwd(user);
			if (result) {
				message = "密码修改成功！请重新登录！";
				log.info(session.getAttribute("role") + "【" + user.getR_name()
						+ "】修改密码成功！");
				request.setAttribute("message", message);
				return "admin";
			} else {
				message = "密码修改失败！原始密码错误！";
				request.setAttribute("message", message);
				return "jsp/back/index";
			}
		}

	}

	/**
	 * 管理员注销
	 * 
	 * @param request
	 *            请求
	 * @return
	 */
	@RequestMapping(value = "adminLogout")
	public String adminLogout(HttpServletRequest request) {

		// 获取session
		HttpSession session = request.getSession();
		// 获取登录用户信息
		Admin user = (Admin) session.getAttribute("user");
		// 获取用户姓名
		String name = user.getR_name();
		// 获取角色
		String role = (String) session.getAttribute("role");

		session.invalidate();// 关闭会话，清除session
		log.info(role + "【" + name + "】退出登录！");
		return "admin";

	}

	/**
	 * 获取所有角色
	 * 
	 * @param request
	 *            用户请求
	 * @return
	 */
	@RequestMapping(value = "getRole")
	public String getRole(HttpServletRequest request) {

		List<Admin> list = adminService.getAllRole();
		request.setAttribute("list", list);
		return "jsp/back/role";

	}

	/**
	 * 根据id删除角色
	 * 
	 * @param request
	 *            请求
	 * @param ids
	 *            id集合
	 * @return
	 */
	@RequestMapping(value = "delRole")
	public String delRole(HttpServletRequest request, String ids) {

		String message = null;
		// 获取session
		HttpSession session = request.getSession();
		// 获取用户信息
		Admin admin = (Admin) session.getAttribute("user");
		// 获取登录用户id
		int id = admin.getR_id();
		// 根据id删除角色
		boolean result = adminService.deleteRole(id, ids);
		if (result) {
			message = "删除成功！";
		} else {
			message = "删除失败！不可删除自己！";
		}
		List<Admin> list = adminService.getAllRole();
		request.setAttribute("list", list);
		request.setAttribute("message", message);
		return "jsp/back/role";

	}

	/**
	 * 增加角色
	 * 
	 * @param request
	 *            请求
	 * @param admin
	 *            角色信息
	 * @return
	 */
	@RequestMapping(value = "saveRole")
	public String saveRole(HttpServletRequest request, Admin admin) {

		String message = null;
		if (admin != null && !"".equals(admin)) {
			boolean result = adminService.saveRole(admin);
			if (result) {
				message = "角色增加成功！";
			} else {
				message = "角色增加失败！可能是该账号已存在！";
			}
		}
		List<Admin> list = adminService.getAllRole();
		request.setAttribute("list", list);
		request.setAttribute("message", message);
		return "jsp/back/role";

	}

	/**
	 * 根据id获取角色信息
	 * 
	 * @param response
	 *            响应
	 * @param id
	 *            主键
	 */
	@RequestMapping(value = "getRoleById")
	public void getRoleById(HttpServletResponse response, String id) {

		PrintWriter pw = null;
		Admin admin = null;
		try {
			pw = response.getWriter();
			admin = adminService.getRoleById(id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		net.sf.json.JSONObject jsonObject = net.sf.json.JSONObject
				.fromObject(admin);
		pw.write(jsonObject.toString());
		pw.close();
		pw = null;

	}

	/**
	 * 根据id修改角色信息
	 * 
	 * @param request
	 *            请求
	 * @param admin
	 *            角色信息
	 * @return
	 */
	@RequestMapping(value = "updateRole")
	public String updateRole(HttpServletRequest request, Admin admin) {

		String message = null;
		if (admin != null && !"".equals(admin)) {
			boolean result = adminService.updateRole(admin);
			if (result) {
				message = "角色修改成功！";
			} else {
				message = "角色修改失败！可能是该账号已存在！";
			}
		}
		List<Admin> list = adminService.getAllRole();
		request.setAttribute("list", list);
		request.setAttribute("message", message);
		return "jsp/back/role";

	}

	/**
	 * 上传文件
	 * @throws IOException 
	 * @throws IllegalStateException 
	 */
	@RequestMapping("/app/uploadFile")
	public void uploadFile(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "file") MultipartFile file,
			@RequestParam(value="stuId")String stuId) throws IllegalStateException, IOException {
		File localfile=new File("E://Test//"+stuId+".txt");
		file.transferTo(localfile);
		JSONObject jv= new JSONObject();
		jv.put("code",200);
		Utils.writeBack(request, response, jv);
	}
	@RequestMapping("/app/downloadFile")
	public void downloadFile(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value="stuId")String stuId){
		String fileName=stuId+".txt";
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName="
                + fileName);
        try {
/*            String path = Thread.currentThread().getContextClassLoader()
                    .getResource("").getPath()
                    + "download";//这个download目录为啥建立在classes下的
*/          
        	String path="E://Test";
        	File file= new File(path+File.separator + fileName);
        	if(file.exists()){
            	InputStream inputStream = new FileInputStream(new File(path
                        + File.separator + fileName));
     
                OutputStream os = response.getOutputStream();
                byte[] b = new byte[2048];
                int length;
                while ((length = inputStream.read(b)) > 0) {
                    os.write(b, 0, length);
                }
                 // 这里主要关闭。
                os.close();
                inputStream.close();
        	}else{
        	}
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
