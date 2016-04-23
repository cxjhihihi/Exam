package com.wsbm.login.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wsbm.information.mapper.InfoMapper;
import com.wsbm.information.model.Message;
import com.wsbm.login.mapper.UserMapper;
import com.wsbm.login.model.User;
import com.wsbm.login.service.LoginService;

/**
 * 登录业务逻辑类-实现
 * @author chen
 *
 */
@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	protected UserMapper userMapper = null;
	
	@Autowired
	protected InfoMapper InfoMapper = null;
	
	/**
	 * 用户登录
	 */
	@Override
	public boolean checkLogin(User user) {
		
		if(user.getId() == null || "".equals(user.getId())){
			return false;
		}else if(user.getPwd() == null || "".equals(user.getPwd())){
			return false;
		}else{
			return userMapper.checkLogin(user);
		}
		
	}

	/**
	 * 获取用户信息
	 */
	@Override
	public User getUserInfo(String id) {

		if(id == null || "".equals(id)){
			return null;
		}else{
			return userMapper.getUserInfo(id);
		}
		
	}

	/**
	 * 修改登录密码
	 */
	@Override
	public boolean updatePwd(User user) {
		
		if(user.getPwd() != null && !"".equals(user.getPwd()) &&
				user.getId() != null && !"".equals(user.getId())){
			return userMapper.updatePwd(user);
		}
		return false;
		
	}

	/**
	 * 用户注册
	 */
	@Override
	public boolean registerUser(User user) {
		
		if(user.getId() != null && !"".equals(user.getId())
				&& user.getPwd() != null && !"".equals(user.getPwd())){
			return userMapper.registerUser(user);
		}
		return false;
		
	}

	/**
	 * 获取系统消息
	 */
	@Override
	public List<Message> getMessage(String id) {
		
		if(id == null || "".equals(id)){
			return null;
		}
		return InfoMapper.getMessage(id);
		
	}

}
