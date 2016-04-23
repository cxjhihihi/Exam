package com.wsbm.information.service;

import com.wsbm.information.model.Time;

/**
 * 管理员操作考生信息业务处理类-接口
 * @author chen
 *
 */
public interface AdminInfoService {

	boolean saveTime(Time time);

	Time getTime();

	boolean updateTime(Time time);

}
