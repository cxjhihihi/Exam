package com.wsbm.information.mapper;

import com.wsbm.information.model.Time;

/**
 * 数据交互类-接口
 * @author chen
 *
 */
public interface TimeMapper {

	/**
	 * 保存报名时间
	 * 
	 * @param time 
	 * 				报名时间信息
	 * @return
	 */
	boolean saveTime(Time time);

	/**
	 * 获取报名时间
	 * @return
	 */
	Time getTime();

	/**
	 * 修改报名时间
	 * 
	 * @param time
	 * 				报名时间信息
	 * @return
	 */
	boolean updateTime(Time time);

}
