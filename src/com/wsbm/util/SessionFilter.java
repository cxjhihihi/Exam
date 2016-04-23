package com.wsbm.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 全局过滤器-过滤所有未登录的请求
 * @author chen
 *
 */
public class SessionFilter implements Filter {

	@Override
	public void destroy() {
		// TODO 暂不使用

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {
		
		//获取request和response
		HttpServletRequest request = (HttpServletRequest)arg0;
		HttpServletResponse response = (HttpServletResponse)arg1;
		System.out.println(request.getRequestURI());
		String uri=request.getRequestURI();
		if(uri.startsWith("/Exam/app")||uri.endsWith("test.jsp")){
			arg2.doFilter(arg0, arg1);
		}else{
			//获取session
			HttpSession session = request.getSession();
			//获取请求
			String url = request.getServletPath();
			//获取登录用户信息
			Object user = (Object) session.getAttribute("user");
			if(user != null && !"".equals(user)){//用户已登录
				arg2.doFilter(arg0, arg1);//不拦截
				return;
			}else{//用户未登录
				//允许样式通过(即css、js、images、bootstrap)
				if(url.indexOf("css/") > -1 || url.indexOf("images/") > -1 
						|| url.indexOf("js/") > -1 || url.indexOf("bootstrap/") > -1
						|| url.indexOf("fonts/") > -1){
					arg2.doFilter(arg0, arg1);//不拦截
					return;
				}
				//允许登录和注册
				if("/login".equals(url) || "/login.jsp".equals(url) || "/".equals(url)
						|| "/saveUser".equals(url) || "/admin.jsp".equals(url) 
						|| "/wsbm".equals(url) || "/adminLogin".equals(url) 
						|| "/safeCode".equals(url)){
					arg2.doFilter(arg0, arg1);//不拦截
					return;
				}else{
					response.sendRedirect("/Exam/login.jsp");//重定向到登录界面
					return;
				}
			}
		}
		

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO 暂不使用

	}

}
