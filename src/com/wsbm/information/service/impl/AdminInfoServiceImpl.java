package com.wsbm.information.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wsbm.information.mapper.TimeMapper;
import com.wsbm.information.model.Time;
import com.wsbm.information.service.AdminInfoService;

/**
 * 管理员操作考生信息业务处理类-接口实现
 * @author chen
 *
 */
@Service
public class AdminInfoServiceImpl implements AdminInfoService{

	@Autowired
	protected TimeMapper timeMapper = null;
	
	/**
	 * 保存报名时间
	 */
	@Override
	public boolean saveTime(Time time) {

		if(time != null && !"".equals(time)){
			if(time.getStartTime() != null && !"".equals(time.getStartTime())
					&& time.getEndTime() != null && !"".equals(time.getEndTime())){
				return timeMapper.saveTime(time);
			}
			return false;
		}
		return false;
		
	}

	/**
	 * 获取报名时间
	 */
	@Override
	public Time getTime() {

		return timeMapper.getTime();
		
	}

	/**
	 * 修改报名时间
	 */
	@Override
	public boolean updateTime(Time time) {

		if(time != null && !"".equals(time)){
			if(time.getStartTime() != null && !"".equals(time.getStartTime())
					&& time.getEndTime() != null && !"".equals(time.getEndTime())
					&& time.getId() != 0){
				return timeMapper.updateTime(time);
			}
			return false;
		}
		return false;
		
	}

}
