package com.wsbm.information.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wsbm.information.mapper.InfoMapper;
import com.wsbm.information.model.Conditions;
import com.wsbm.information.model.Info;
import com.wsbm.information.model.MesModel;
import com.wsbm.information.model.Message;
import com.wsbm.information.model.Time;
import com.wsbm.information.service.InfoService;

/**
 * 考生信息业务逻辑类-实现
 * @author chen
 *
 */
@Service
public class InfoServiceImpl implements InfoService {

	@Autowired
	protected InfoMapper infoMapper = null;
	
	/**
	 * 获取报名信息
	 */
	@Override
	public Info getInfo(String id) {
		
		if(id == null || "".equals(id)){
			return null;
		}else{
			return infoMapper.getInfo(id);
		}
		
	}

	/**
	 * 保存报名信息
	 */
	@Override
	public boolean saveInfo(Info info) {
		
		if(info == null || "".equals(info)){
			return false;
		}
		return infoMapper.saveInfo(info);
		
	}

	/**
	 * 修改报名信息或者重新报名
	 */
	@Override
	public boolean updateInfo(Info info, String savePath) {
		System.out.println("info"+info.toString());
		if(info == null || "".equals(info)){
			return false;
		}
		//获取操作用户身份证号
		String account = info.getAccount();
		System.out.println("account"+account);
		//查看该用户是否已经报名
		Info bean = infoMapper.getInfo(account);
		//已经报名
		if(bean != null && !"".equals(bean)){
			boolean delResult = infoMapper.deleteInfo(account);//删除原来的报名信息
			boolean saveResult = infoMapper.saveInfo(info);//保存新的报名信息
			if(delResult & saveResult){
				String suffixPath = bean.getPicture();//后面的路径
				if(suffixPath != null && !"".equals(suffixPath)){
					String realPath = savePath + suffixPath.replace("\\", "/");//目标文件路径
					File targetFile = new File(realPath);//指定文件
					if(targetFile.exists() && targetFile.isFile()){//如果文件存在
						targetFile.delete();//删除该文件
					}
				}
				List<File> list = getAllNullDirectory(new File(savePath));
				removeAllNullDirectory(list, savePath);
			}
			return delResult & saveResult;
		}else{
			return infoMapper.saveInfo(info);
		}
		
	}

	/**
	 * 递归得到根目录下的最深层的空文件夹的绝对路径，存储至list
	 * 
	 * @param savePath
	 * 					根目录
	 * @param suffixPath
	 * 					目标文件目录
	 */
	private List<File> getAllNullDirectory(File rootDirectory) {

		List<File> list = new ArrayList<File>();//存储最深层空文件夹
		File[] dirs = rootDirectory.listFiles();//得到根目录下所有的文件
		if(dirs != null){
			for(int i = 0; i < dirs.length; i++){//得到下级的所有文件
				//下级文件是目录并且没有下级文件了，则该目录是最深层的空目录
				if(dirs[i].isDirectory() && dirs[i].listFiles().length == 0){
					list.add(dirs[i]);
				}
				list.addAll(getAllNullDirectory(dirs[i]));
			}
		}
		return list;
	
	}
	
	/**
	 * 从最深层的空文件夹，向上递归，删除空文件夹
	 * @param list
	 * @param savePath
	 */
	public void removeAllNullDirectory(List<File> list, String savePath){
		
		//不存在空文件夹
		if(list == null || list.size() == 0){
			return;
		}
		//存储父级目录
		List<File> parentList = new ArrayList<File>();
		//循环所有空目录
		for(int i = 0; i < list.size(); i++){
			File temp = list.get(i);//空目录
			//该文件是目录并且下级没有文件
			if(temp.isDirectory() && temp.listFiles().length == 0){
				temp.delete();
			}
			//得到该目录的父级目录
			File parentFile = temp.getParentFile();
			System.out.println((parentFile.getPath() + "\\") + "==" + savePath);
			//父级目录就是根目录
			if((parentFile.getPath() + "\\").equals(savePath.replace("/", "\\"))){
				continue;
			}
			//父级目录不在父级目录的列表中
			if(!parentList.contains(parentFile)){
				parentList.add(parentFile);
			}
		}
		removeAllNullDirectory(parentList, savePath);
		
	}

	/**
	 * 取消报名
	 */
	@Override
	public boolean cancelInfo(String id) {

		if(id == null || "".equals(id)){
			return false;
		}
		return infoMapper.cancelInfo(id);
		
	}

	/**
	 * 获取报名人数
	 */
	@Override
	public int getCount(Time time) {
		
		if(time != null && !"".equals(time)){
			if(time.getStartTime() != null && !"".equals(time.getStartTime())
					&& time.getEndTime() != null && !"".equals(time.getEndTime())){
				time.setStartTime(time.getStartTime().substring(0, 10));
				time.setEndTime(time.getEndTime().substring(0, 10));
				return infoMapper.getCount(time);
			}
			return 0;
		}
		return 0;
		
	}

	/**
	 * 获取所有报名人员的信息
	 */
	@Override
	public List<Info> getAllInfo(Time time) {

		if(time != null && !"".equals(time)){
			if(time.getStartTime() != null && !"".equals(time.getStartTime())
					&& time.getEndTime() != null && !"".equals(time.getEndTime())){
				time.setStartTime(time.getStartTime().substring(0, 10));
				time.setEndTime(time.getEndTime().substring(0, 10));
				return infoMapper.getAllInfo(time);
			}
			return null;
		}
		return null;
		
	}

	/**
	 * 根据id获取详细信息
	 */
	@Override
	public Info getStuInfo(String id) {

		if(id != null && !"".equals(id)){
			return infoMapper.getStuInfo(id);
		}
		return null;
		
	}

	/**
	 * 获取满足条件的考生信息
	 */
	@Override
	public List<Info> getInfoByConditions(Conditions conditions) {
		
		if(conditions != null && !"".equals(conditions)){
			return infoMapper.getInfoByConditions(conditions);
		}
		return null;
		
	}
	
	/**
	 * 获取满足条件的记录个数
	 */
	@Override
	public int getCountByConditions(Conditions conditions){
		
		if(conditions != null && !"".equals(conditions)){
			return infoMapper.getCountByConditions(conditions);
		}
		return 0;
		
	}

	/**
	 * 获取审核信息
	 */
	@Override
	public Info updateAudit(int id) {

		//获取上次已经分配但未审核的信息
		Info info = infoMapper.getLastAudit(id);
		if(info == null || "".equals(info)){
			//若上次没有未审核信息，则重新分配
			info = infoMapper.getAudit();
			if(info != null && !"".equals(info)){
				//重新分配后将该记录的审核人更新
				String id2 = info.getId();
				Time t = new Time();//用时间类充当条件类
				t.setId(id);
				t.setName(id2);
				boolean result = infoMapper.updateAudit(t);
				if(!result){
					return null;
				}
			}else{
				return null;
			}
		}
		return info;
		
	}

	/**
	 * 更新审核状态并向考生发布通知
	 */
	@Override
	public boolean updateAuditStatus(int status, String id, int id2) {
		
		
		if(status > 0 && id != null && !"".equals(id)){
			Time time = new Time();
			time.setId(status);
			time.setName(id);
			boolean result = infoMapper.updateAuditStatus(time);
			System.out.println("result"+result);
			if(result){
				//获取消息内容
				MesModel model = infoMapper.getMessageModel(status);
				Message message = new Message();
				message.setM_content(model.getMessage());//消息内容
				message.setM_to2(id);//接收人
				message.setM_from2(id2);//发送人
				message.setM_read(0);//未读
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
				Date date = new Date();
				message.setM_time(sdf.format(date));//发送时间
				//保存
				infoMapper.sendMessage(message);
			}
			return result;
		}
		return false;
	}

	/**
	 * 获取所有审核信息
	 */
	@Override
	public List<Info> getAllAudit(int id) {
		if(id > 0){
			return infoMapper.getAllAudit(id);
		}
		return null;
	}

	/**
	 * 条件查询审核信息
	 */
	@Override
	public List<Info> queryAudit(Conditions conditions) {
		
		if(conditions == null || "".equals(conditions)){
			return null;
		}
		return infoMapper.queryAudit(conditions);
		
	}

	/**
	 * 将未读消息设置为已读消息
	 */
	@Override
	public boolean changeReaded(String id) {
		
		if(id != null && !"".equals(id)){
			return infoMapper.changeReaded(id);
		}
		return false;
		
	}

	/**
	 * 获取所有消息
	 */
	@Override
	public List<Message> getAllMessage(String id) {

		if(id != null && !"".equals(id)){
			return infoMapper.getAllMessage(id);
		}
		return null;
		
	}

	/**
	 * 根据id修改阅读状态
	 */
	@Override
	public boolean changeReadedById(int id) {

		if(id > 0){
			return infoMapper.changeReaderById(id);
		}
		return false;
		
	}

	/**
	 * 条件查询消息
	 */
	@Override
	public List<Message> queryMessage(String id, int status) {

		if(id != null && !"".equals(id)){
			Conditions conditions = new Conditions();
			conditions.setStatus(status);
			conditions.setName(id);
			return infoMapper.queryMessage(conditions);
		}
		return null;
		
	}

}
