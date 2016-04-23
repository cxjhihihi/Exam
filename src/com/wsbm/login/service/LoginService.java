package com.wsbm.login.service;

import java.util.List;

import com.wsbm.information.model.Message;
import com.wsbm.login.model.User;

/**
 * 登录业务逻辑类-接口
 * @author chen
 *
 */
public interface LoginService {

	boolean checkLogin(User user);

	User getUserInfo(String id);

	boolean updatePwd(User user);

	boolean registerUser(User user);

	List<Message> getMessage(String id);

}
