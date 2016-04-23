package com.wsbm.login.mapper;

import com.wsbm.login.model.User;

/**
 * 用户表数据交互接口
 * @author chen
 *
 */
public interface UserMapper{

	/**
	 * 检查是否允许登录
	 * 
	 * @param user
	 * 				登录信息
	 * @return
	 */
	public boolean checkLogin(User user);

	/**
	 * 获取登录用户信息
	 * 
	 * @param id
	 * 				登录用户ID（身份证号）
	 * @return
	 */
	public User getUserInfo(String id);

	/**
	 * 修改登录密码
	 * 
	 * @param newPwd
	 * 					新密码
	 * @param id
	 * 					用户身份证号
	 * @return
	 */
	public boolean updatePwd(User user);

	/**
	 * 用户注册
	 * 
	 * @param user
	 * 				注册信息
	 * @return
	 */
	public boolean registerUser(User user);

}
