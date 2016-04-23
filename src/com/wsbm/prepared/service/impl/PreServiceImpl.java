package com.wsbm.prepared.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wsbm.information.mapper.InfoMapper;
import com.wsbm.information.model.Info;
import com.wsbm.information.model.MesModel;
import com.wsbm.prepared.mapper.PreMapper;
import com.wsbm.prepared.model.Dict;
import com.wsbm.prepared.model.Ticket;
import com.wsbm.prepared.service.PreService;

/**
 * 考试前夕准备工作-业务逻辑处理-接口实现
 * @author chen
 *
 */
@Service
public class PreServiceImpl implements PreService {

	@Autowired
	protected PreMapper preMapper = null;
	@Autowired
	protected InfoMapper infoMapper = null;
	
	/**
	 * 获取字典信息
	 */
	@Override
	public List<Dict> getDict(String type) {

		if(type == null || "".equals(type)){
			return null;
		}
		return preMapper.getDict(type);
		
	}

	/**
	 * 保存字典信息
	 */
	@Override
	public boolean saveDict(Dict dict) {

		if(dict != null && !"".equals(dict)){
			if(dict.getD_type() != null && !"".equals(dict.getD_type())
					&& dict.getD_code() != null && !"".equals(dict.getD_code())
					&& dict.getD_name() != null && !"".equals(dict.getD_name())){
				List<Dict> list = preMapper.getDict(dict.getD_type());
				for(Dict bean: list){
					if((bean.getD_code()).equals(dict.getD_code())){
						return false;
					}
				}
				return preMapper.saveDict(dict);
			}
		}
		return false;
		
	}

	/**
	 * 根据id删除字典信息
	 */
	@Override
	public boolean deleteDict(String ids) {

		if(ids != null && !"".equals(ids)){
			String[] idArray = ids.split(",");
			int[] intIdArray = new int[idArray.length];
			for(int i = 0; i < idArray.length; i++){
				intIdArray[i] = Integer.parseInt(idArray[i]);
			}
			int num = preMapper.deleteDict(intIdArray);
			if(num == idArray.length){
				return true;
			}
			return false;
		}
		return false;
		
	}

	/**
	 * 修改字典信息
	 */
	@Override
	public boolean updateDict(Dict dict) {

		if(dict != null && !"".equals(dict)){
			if(dict.getD_type() != null && !"".equals(dict.getD_type())
					&& dict.getD_code() != null && !"".equals(dict.getD_code())
					&& dict.getD_name() != null && !"".equals(dict.getD_name())
					&& dict.getD_id() > 0){
				List<Dict> list = preMapper.getDict(dict.getD_type());
				for(Dict bean: list){
					if((bean.getD_code()).equals(dict.getD_code())
							&& bean.getD_id() != dict.getD_id()){
						return false;
					}
				}
				return preMapper.updateDict(dict);
			}
		}
		return false;
		
	}

	/**
	 * 根据id查询字典信息
	 */
	@Override
	public Dict getDictById(String id) {

		if(id == null || id == ""){
			return null;
		}
		return preMapper.getDictById(Integer.parseInt(id));
		
	}

	/**
	 * 获取消息模型
	 */
	@Override
	public MesModel getNotice(String sendTime) {

		return infoMapper.getMessageModel(Integer.parseInt(sendTime));
		
	}

	/**
	 * 修改消息模型
	 */
	@Override
	public boolean updateNotice(int sendTime, String content) {

		if(sendTime > 0 && content != null && !"".equals(content)){
			MesModel mesModel = new MesModel();
			mesModel.setMessage(content);
			mesModel.setType(sendTime);
			return infoMapper.updateMessageModel(mesModel);
		}
		return false;
		
	}
	
	public List<Ticket> getTicketsByExamId(int examId){
		return preMapper.getTicketsByExamId(examId);
	}
	public Info getInfoById(String id){
		return preMapper.getInfoById(id);
	}

}
