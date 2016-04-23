package com.wsbm.prepared.service;

import java.util.List;

import com.wsbm.information.model.Info;
import com.wsbm.information.model.MesModel;
import com.wsbm.prepared.model.Dict;
import com.wsbm.prepared.model.Ticket;

/**
 * 考试前夕准备工作-业务逻辑处理-接口
 * @author chen
 *
 */
public interface PreService {

	List<Dict> getDict(String type);

	boolean saveDict(Dict dict);

	boolean deleteDict(String ids);

	boolean updateDict(Dict dict);

	Dict getDictById(String id);

	MesModel getNotice(String sendTime);

	boolean updateNotice(int sendTime, String content);
	List<Ticket> getTicketsByExamId(int examId);
	
	Info getInfoById(String id);
}
