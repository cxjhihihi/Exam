package com.wsbm.login.mapper;

import java.util.List;

import com.wsbm.login.model.Admin;

public interface RoleMapper {

	/**
	 * 登录验证
	 * 
	 * @param admin
	 * 				登录信息
	 * @return
	 */
	boolean checkLogin(Admin admin);

	/**
	 * 获取登录用户信息
	 * 
	 * @param admin
	 * 				登录信息
	 * @return
	 */
	Admin getAdminInfo(Admin admin);

	/**
	 * 修改密码
	 * 
	 * @param user
	 * 				密码信息
	 * @return
	 */
	boolean updatePwd(Admin user);

	/**
	 * 获取该系统的所有角色
	 * 
	 * @return
	 */
	List<Admin> getAllRole();

	/**
	 * 根据id删除角色
	 * 
	 * @param intIdArray
	 * 						id数组
	 * @return
	 */
	int deleteRole(int[] intIdArray);

	/**
	 * 增加角色
	 * 
	 * @param admin
	 * 					角色信息
	 * @return
	 */
	boolean saveRole(Admin admin);

	/**
	 * 根据id修改角色信息
	 * 
	 * @param admin
	 * 					角色信息
	 * @return
	 */
	boolean updateRole(Admin admin);

	/**
	 * 根据id查询角色信息
	 * 
	 * @param id
	 * 				主键
	 * @return
	 */
	Admin getRoleById(int id);

}
