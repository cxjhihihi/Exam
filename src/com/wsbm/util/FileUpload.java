package com.wsbm.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.wsbm.information.model.Info;
import com.wsbm.login.model.User;

/**
 * 文件上传处理类
 * @author chen
 *
 */
public class FileUpload {

	/**
	 * 上传文件处理，封装成一个Info类，便于保存
	 * 
	 * @param request
	 * 					用户请求
	 * @return
	 */
	public static Info uploadFile(HttpServletRequest request){
		
		//获取session
		HttpSession session = request.getSession();
		//获取登录用户信息
		User user = (User) session.getAttribute("user");
		//获取登录用户身份证号
		String id = user.getId();
		//创建一个Map保存所有的输入项的name和value
		Map<String, String> map = new HashMap<String, String>();
		//得到上传文件的保存路径，将上传文件存放于WEB-INF/upload/picture文件夹下面，保证上传文件的安全
		//getRealPath在war包中失效，返回null，所以使用getResource，但是getResource不能自动创建文件夹，没有该文件夹会报空指针
		//String savePath = request.getServletContext().getRealPath("/WEB-INF/upload/picture");
		String savePath = null;
		//上传时生成的临时文件保存目录
		String tempPath = null;
		try {
			savePath = request.getServletContext().getResource("/WEB-INF/upload/picture").toString();
			savePath = savePath.substring(savePath.indexOf("/") + 1);
			tempPath = request.getServletContext().getResource("/WEB-INF/temp").toString();
			tempPath = tempPath.substring(tempPath.indexOf("/") + 1);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		if(savePath == null || "".equals(savePath)
				|| tempPath == null || "".equals(tempPath)){
			return null;
		}
		File tempFile = new File(tempPath);
		if(!tempFile.exists()){
			//创建临时目录
			tempFile.mkdirs();
		}
		try{
			//使用Apache文件 上传组件处理文件上传
			//1.创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//设置工厂的缓冲区大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录中
			factory.setSizeThreshold(1024*100);//设置缓冲区大小为100KB，默认是10KB
			//设置上传时生成的临时文件的保存目录
			factory.setRepository(tempFile);
			//2.创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			//监听文件上传进度
			upload.setProgressListener(new ProgressListener() {
				@Override
				public void update(long arg0, long arg1, int arg2) {
					
					System.out.println("文件大小为：" + arg1 + ",当前已处理：" + arg0);
					
				}
			});
			//解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8");
			//3.判断提交上来的数据是否是上传表单的数据
			if(ServletFileUpload.isMultipartContent(request)){
				//设置单个上传文件的最大值:10MB
				upload.setFileSizeMax(1024*1024*10);
				//设置所有上传文件的最大值:暂不使用
				//upload.setSizeMax(1024*1024*100);
				/*
				 * 4.使用ServletFileUpload解析器上传数据，解析结果返回的是一个List<Item>集合，
				 *	每个FileItem对呀一个Form表单的输入项
				 */
				List<FileItem> list = upload.parseRequest(request);
				for(FileItem item: list){
					//如果FileItem中封装的是普通输入项的数据
					if(item.isFormField()){
						//获取输入项的name值
						String name = item.getFieldName();
						//解决普通输入项的数据的中文乱码问题
						String value = item.getString("UTF-8");
						map.put(name, value);
					}else{//如果FileItem中封装的是普通输入项的数据
						//得到输入项的name
						String name = item.getFieldName();
						//得到上传文件的名字
						String fileName = item.getName();
						if(fileName == null || fileName.trim().equals("")){
							map.put(name, null);
						}else{
							//处理获取到的上传文件的文件名的路径部分，只保留文件名部分(不同浏览器提交的文件名不一样，有的带路径，有的不带)
							fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
							//得到上传文件的后缀名(用于规范只运行上传特定格式的文件，暂不用)
							//String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);
							//获取item中的上传文件的输入流
							InputStream is = item.getInputStream();
							//设置文件保存的名称
							String saveFileName = makeFileName(fileName);
							//得到文件的保存目录
							String realSavePath = makePath(saveFileName, savePath);
							//得到真实路径
							String realPath = realSavePath + "\\" + saveFileName;
							//创建一个文件输出流
							FileOutputStream fos = new FileOutputStream(realPath);
							//创建一个缓冲区
							byte buffer[] = new byte[1024];
							//判断将输入流中的数据是否已经读完的标志
							int len = 0;
							//循环将输入流读入 到缓冲区中
							while((len = is.read(buffer)) > 0){
								//使用FileOutputStream输出流 将缓冲区中的数据写入到指定的目录中
								fos.write(buffer, 0, len);
							}
							map.put(name, realPath.split("picture")[1]);
							//关闭输入流
							is.close();
							//关闭输出流
							fos.close();
							//删除处理文件
							item.delete();
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		Info info = setUser(map);
		info.setAccount(id);//设置该报名信息的提交者
		if(info.getIdCard() == null || "".equals(info.getId())
				|| info.getName() == null || "".equals(info.getName())
				|| info.getHouseholdAddress() == null
				|| "".equals(info.getHouseholdAddress())){//必填字段
			return null;
		}else{
			info.setId(info.getIdCard());
			return info;
		}
		
	}
	
	/**
	 * 为了防止同一目录下出现太多文件，使用hash算法打算存储
	 * 	
	 * @param saveFileName
	 * 						文件的名称
	 * @param savePath
	 * 						文件存储路径
	 * @return
	 */
	private static String makePath(String saveFileName, String savePath) {
		
		//得到文件名的hashcode的值，即为saveFileName对象在内存中的地址
		int hashCode = saveFileName.hashCode();
		int dir1 = hashCode & 0xf;//0--15
		int dir2 = (hashCode & 0xf0) >> 4;//0-5
		//构造新的保存目录
		String dir = savePath + "\\" + dir1 + "\\" + dir2;
		File file = new File(dir);
		//如果目录不存在
		if(!file.exists()){
			//创建目录
			file.mkdirs();
		}
		return dir;
		
	}

	/**
	 * 生成一个唯一的文件名，防止文件同名覆盖的问题
	 * 
	 * @param fileName
	 * 					文件的原始名称
	 * @return
	 */
	private static String makeFileName(String fileName) {

		return UUID.randomUUID().toString() + "_" + fileName;
		
	}
	
	/**
	 * 将一个map封装成一个Info对象
	 * 
	 * @param map
	 * 				属性值的集合
	 * @return
	 */
	private static Info setUser(Map<String, String> map){
		
		Info info = new Info();
		try {
			//1.实例化
			info = info.getClass().newInstance();
			//循环取得所有属性值
			for(String name: map.keySet()){
				//利用java反射机制得到相应的getter和setter方法
				//2.将属性首字母变大写
				String value = map.get(name);
				name = name.substring(0, 1).toUpperCase() + name.substring(1);
				//3.获取getter方法
				Method getMethod = info.getClass().getMethod("get" + name);
				//4.通过getter获取返回值类型
				Class<?> type = getMethod.getReturnType();
				//5.获取setter方法
				Method setMethod = info.getClass().getMethod("set" + name, type);
				//6.根据返回值类型处理数据并利用setter方法赋值
				if("double".equals(type.getSimpleName())){
					double val = 0d;
					if(value != null && !"".equals(value)){
						val = Double.parseDouble(value);
					}
					setMethod.invoke(info, val);
				}else if("int".equals(type.getSimpleName())){
					int val = 0;
					if(value != null && !"".equals(value)){
						val = Integer.parseInt(value);
					}
					setMethod.invoke(info, val);
				}else if("String".equals(type.getSimpleName())){
					setMethod.invoke(info, value);
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		info.setEnter(1);//报名标志
		return info;
		
	}
	
}
