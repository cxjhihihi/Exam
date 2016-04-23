package com.wsbm.login.service;

import java.util.List;

import com.wsbm.login.model.Admin;

public interface AdminService {

	boolean checkLogin(Admin admin);

	Admin getAdminInfo(Admin admin);

	boolean updatePwd(Admin user);

	List<Admin> getAllRole();

	boolean deleteRole(int id, String ids);

	boolean saveRole(Admin admin);

	boolean updateRole(Admin admin);

	Admin getRoleById(String id);

}
