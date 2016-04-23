package com.wsbm.information.service;

import java.util.List;

import com.wsbm.information.model.Conditions;
import com.wsbm.information.model.Info;
import com.wsbm.information.model.Message;
import com.wsbm.information.model.Time;

/**
 * 考生信息业务逻辑类-接口
 * @author chen
 *
 */
public interface InfoService {

	Info getInfo(String id);

	boolean saveInfo(Info info);

	boolean updateInfo(Info info, String savePath);

	boolean cancelInfo(String id);
	
    int getCount(Time time);

	List<Info> getAllInfo(Time time);

	Info getStuInfo(String id);

	List<Info> getInfoByConditions(Conditions conditions);
	
	int getCountByConditions(Conditions conditions);

	Info updateAudit(int id);

	boolean updateAuditStatus(int status, String id, int id2);

	List<Info> getAllAudit(int id);

	List<Info> queryAudit(Conditions conditions);

	boolean changeReaded(String id);

	List<Message> getAllMessage(String id);

	boolean changeReadedById(int id);

	List<Message> queryMessage(String id, int status);

}
