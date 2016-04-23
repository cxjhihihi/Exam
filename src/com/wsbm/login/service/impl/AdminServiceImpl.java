package com.wsbm.login.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wsbm.login.mapper.RoleMapper;
import com.wsbm.login.model.Admin;
import com.wsbm.login.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	protected RoleMapper roleMapper = null;

	/**
	 * 登录验证
	 */
	@Override
	public boolean checkLogin(Admin admin) {
		if(admin != null && !"".equals(admin)){
			return roleMapper.checkLogin(admin);
		}
		return false;
	}

	/**
	 * 获取登录用户信息
	 */
	@Override
	public Admin getAdminInfo(Admin admin) {
		if(admin != null && !"".equals(admin)){
			return roleMapper.getAdminInfo(admin);
		}
		return null;
	}

	/**
	 * 修改密码
	 */
	@Override
	public boolean updatePwd(Admin user) {

		if(user != null && !"".equals(user)){
			return roleMapper.updatePwd(user);
		}
		return false;
		
	}

	/**
	 * 获取所有角色
	 */
	@Override
	public List<Admin> getAllRole() {
		
		return roleMapper.getAllRole();
		
	}

	/**
	 * 根据id删除角色
	 */
	@Override
	public boolean deleteRole(int id, String ids) {

		if(ids != null && !"".equals(ids)){
			//分割ids
			String[] idArray = ids.split(",");
			int[] intIdArray = new int[idArray.length];
			for(int i = 0; i < idArray.length; i++){
				intIdArray[i] = Integer.parseInt(idArray[i]);
				if(intIdArray[i] == id){
					return false;
				}
			}
			int num = roleMapper.deleteRole(intIdArray);
			if(num == idArray.length){
				return true;
			}
			return false;
		}
		return false;
		
	}

	/**
	 * 增加角色
	 */
	@Override
	public boolean saveRole(Admin admin) {
		
		if(admin != null && !"".equals(admin)){
			if(admin.getR_account() == null || "".equals(admin.getR_account()) ||
					admin.getR_pwd() == null || "".equals(admin.getR_pwd()) ||
					admin.getR_name() == null || "".equals(admin.getR_name()) ||
					admin.getR_role() != 1){
				return false;
			}
			List<Admin> list = roleMapper.getAllRole();
			for(Admin bean: list){
				if(bean.getR_account().equals(admin.getR_account())){
					return false;
				}
			}
			return roleMapper.saveRole(admin);
		}
		return false;
		
	}

	/**
	 * 根据id修改角色信息
	 */
	@Override
	public boolean updateRole(Admin admin) {

		if(admin != null && !"".equals(admin)){
			if(admin.getR_id() > 0 && admin.getR_role() == 1
					&& admin.getR_account() != null && !"".equals(admin.getR_account())
					&& admin.getR_pwd() != null && !"".equals(admin.getR_pwd())
					&& admin.getR_name() != null && !"".equals(admin.getR_name())){
				List<Admin> list = roleMapper.getAllRole();
				for(Admin bean: list){
					if(bean.getR_account().equals(admin.getR_account())
							&& bean.getR_id() != admin.getR_id()){
						return false;
					}
				}
				return roleMapper.updateRole(admin);
			}
			return false;
		}
		return false;
		
	}

	/**
	 * 根据id获取角色信息
	 */
	@Override
	public Admin getRoleById(String id) {

		if(id == null || id == ""){
			return null;
		}
		return roleMapper.getRoleById(Integer.parseInt(id));
		
	}

}
