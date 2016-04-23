<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
<title>网上报名系统-首页</title>

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" type="text/css" href="bootstrap/bootstrap.min.css">
<!-- 引入模板的css文件 -->
<link rel="stylesheet" type="text/css" href="css/template/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/template/ionicons.min.css">
<link rel="stylesheet" type="text/css" href="css/template/AdminLTE.css">

<script type="text/javascript">

	//结果返回,因为外部js文件不能获取request或session中的值，所以必须放在jsp中
	//因为jsp本身就是一个servlet
	function message(){
		
		var message = "${message}";
		if(message != null && message != ""){
			alert(message);
		}
		
	};
	
</script>

</head>
<body onload="message()" class="skin-blue">
	<header class="header">
		<a href="javascript:void(0);" class="logo">网上报名系统</a>
		<nav class="navbar navbar-static-top" role="navigation">
       		<a href="javascript:void(0);" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
             	<span class="sr-only">Toggle navigation</span>
                 <span class="icon-bar"></span>
                 <span class="icon-bar"></span>
                 <span class="icon-bar"></span>
             </a>
	         <div class="navbar-right">
             	<ul class="nav navbar-nav">
             		<c:if test="${sessionScope.user.r_role == 0 }">
	                     <li class="dropdown messages-menu">
	                        <a href="javascript:void(0);" id="news" class="dropdown-toggle" data-toggle="dropdown" style="height: 50px;">
		                   		<i class="glyphicon glyphicon-envelope"></i>
		                   		<c:if test="${count > 0 }">
		           	         		<span id="newsNum" class="label label-success">${count }</span>
	                			</c:if>
	                		</a>
			                <ul class="dropdown-menu">
			                    <li class="header">所有消息</li>
			                    <li>
			                        <ul class="menu">
			                            <li>
			                                <a href="javascript:void(0);">
			                                    <div class="pull-left">
			                                        <img src="images/template/news.jpg" class="img-circle" alt="User Image"/>
			                                    </div>
			                                    <h4>
			                                       	 报名消息
			                                    </h4>
			                                    <p>截止到目前，已有${count }名考生报考本学校</p>
			                                </a>
			                            </li>
			                        </ul>
	                    		</li>
	                    		<li class="footer"><a href="allStuInfo" target="mainIframe">查看所有报考学生信息</a></li>
	                		</ul>
            			</li>
            		</c:if>
		            <li class="dropdown user user-menu">
		                <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" style="height: 50px;">
		                    <i class="glyphicon glyphicon-user"></i>
		                    <span>${sessionScope.user.r_name }<i class="caret"></i></span>
		                </a>
		                <ul class="dropdown-menu">
		                    <li class="user-header bg-light-blue">
	                    		<img src="images/template/avatar.png" class="img-circle" alt="User Image" />
		                        <p>
		                            ${sessionScope.user.r_name } - ${sessionScope.role }
		                        </p>
		                    </li>
		                    <li class="user-footer">
		                        <div class="pull-left">
		                            <a href="javascript:void(0);" id="jumpModel" class="btn btn-default btn-flat" data-toggle="modal" data-target="#pwdModal">修改密码</a>
		                        </div>
		                        <div class="pull-right">
		                            <a href="adminLogout" class="btn btn-default btn-flat">退出</a>
		                        </div>
		                    </li>
		                </ul>
		            </li>
		        </ul>
		    </div>
		</nav>
	</header>
	<div class="wrapper row-offcanvas row-offcanvas-left">
	    <aside class="left-side sidebar-offcanvas">                
	        <section class="sidebar">
	            <div class="user-panel">
	                <div class="pull-left image">
                   		<img src="images/template/avatar.png" class="img-circle" alt="User Image" />
	                </div>
	                <div class="pull-left info">
	                    <p>你好, ${sessionScope.user.r_name }</p>
	
	                    <a href="javascript:void(0);"><i class="fa fa-circle text-success"></i> 在线</a>
	                </div>
	            </div>
	            <c:if test="${sessionScope.user.r_role == 0 }">
		            <ul class="sidebar-menu">
		                <li class="treeview">
	                        <a href="javascript:void(0);" id="stuManager">
	                            <i class="glyphicon glyphicon-user"></i>
	                            <span>招生管理</span>
	                            <i id="stu2" class="glyphicon glyphicon-chevron-down" style="float: right;"></i>
	                        </a>
	                        <ul class="treeview-menu">
	                            <li class="nav"><a href="getTime" target="mainIframe"><i class="glyphicon glyphicon-time"></i><span>招生时间</span></a></li>
	                            <li class="nav"><a href="allStuInfo" target="mainIframe"><i class="glyphicon glyphicon-th-list"></i><span>招生列表</span></a></li>
	                        </ul>
	                    </li>
		                <li class="nav">
		                    <a href="getRole" target="mainIframe">
		                        <i class="glyphicon glyphicon-flag"></i> <span>角色管理</span>
		                    </a>
		                </li>
		                <li class="nav">
		                    <a href="getRoom" target="mainIframe">
		                        <i class="glyphicon glyphicon-home"></i> <span>考场管理</span>
		                    </a>
		                </li>
		                <li class="treeview">
	                        <a href="javascript:void(0);" id="dictManager">
	                            <i class="glyphicon glyphicon-book"></i>
	                            <span>字典管理</span>
	                            <i id="dict2" class="glyphicon glyphicon-chevron-down" style="float: right;"></i>
	                        </a>
	                        <ul class="treeview-menu">
	                            <li class="nav"><a href="getDict?type=course" target="mainIframe"><i class="glyphicon glyphicon-tags"></i><span>学科字典</span></a></li>
	                            <li class="nav"><a href="getDict?type=school" target="mainIframe"><i class="glyphicon glyphicon-bookmark"></i><span>校区字典</span></a></li>
	                        </ul>
	                    </li>
	                    <li class="nav">
		                    <a href="getNotice" target="mainIframe">
		                        <i class="glyphicon glyphicon-comment"></i> <span>通知管理</span>
		                    </a>
		                </li>
		            </ul>
		    	</c:if>
		    	<c:if test="${sessionScope.user.r_role == 1 }">
		    		<ul class="sidebar-menu">
			    		<li class="treeview">
	                        <a href="javascript:void(0);" id="auditManager">
	                            <i class="glyphicon glyphicon-book"></i>
	                            <span>审核管理</span>
	                            <i id="audit" class="glyphicon glyphicon-chevron-down" style="float: right;"></i>
	                        </a>
	                        <ul class="treeview-menu">
	                            <li class="nav"><a href="audit" target="mainIframe2"><i class="glyphicon glyphicon-tags"></i><span>开始审核</span></a></li>
	                            <li class="nav"><a href="allAudit" target="mainIframe2"><i class="glyphicon glyphicon-bookmark"></i><span>审核列表</span></a></li>
	                        </ul>
	                    </li>
	            	</ul>
		    	</c:if>
	        </section>
	    </aside>
	    <c:if test="${sessionScope.user.r_role == 0 }">
   			<iframe src="allStuInfo" name="mainIframe" id="mainIframe" width="100%" frameborder="0"></iframe>              
		</c:if>
		<c:if test="${sessionScope.user.r_role == 1 }">
			<iframe src="audit" name="mainIframe2" id="mainIframe2" width="100%" frameborder="0"></iframe>
		</c:if>
	</div>
	<!-- 修改密码模态框 -->
	<div class="modal fade" id="pwdModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  		<div class="modal-dialog" role="document">
    		<div class="modal-content">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        			<h4 class="modal-title" id="myModalLabel">修改密码</h4>
      			</div>
      			<form action="updateAdminPwd" method="post" id="updateAdminPwd" onsubmit="return validation();">
      				<div class="modal-body">
          				<div class="form-group">
            				<label for="initPwd" class="control-label">原始密码:</label>
            				<input type="password" class="form-control" name="initPwd" id="initPwd" maxlength="30" placeholder="原始密码">
          				</div>
          				<div class="form-group">
				            <label for="newPwd" class="control-label">新密码:</label>
				            <input type="password" class="form-control" name="newPwd" id="newPwd" maxlength="30" placeholder="新密码">
         	 			</div>
         	 			<div class="form-group">
				            <label for="confirmPwd" class="control-label">确认密码:</label>
				            <input type="password" class="form-control" name="confirmPwd" id="confirmPwd" maxlength="30" placeholder="确认密码">
         	 			</div>
      				</div>
	      			<div class="modal-footer">
	        			<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        			<button type="submit" class="btn btn-primary">保存</button>
	      			</div>
	      		</form>
	      		<script type="text/javascript">
	      		
	      			function validation(){
	      				
	      				var truePwd = "${sessionScope.user.r_pwd}";//正确密码
						var initPwd = document.getElementById("initPwd").value.trim();//原始密码
						var newPwd = document.getElementById("newPwd").value.trim();//新密码
						var confirmPwd = document.getElementById("confirmPwd").value.trim();//确认密码
						
						if(initPwd == null || initPwd == ""){
							alert("请输入原始密码！")
							return false;
						}else if(truePwd != initPwd){
							alert("原始密码错误，请重新输入！");
							return false;
						}else if(newPwd == null || newPwd == ""){
							alert("请输入新密码！");
							return false;
						}else if(confirmPwd == null || confirmPwd == ""){
							alert("请输入确认密码！");
							return false;
						}else if(confirmPwd != newPwd){
							alert("两次密码输入不一致，请重新输入！");
							return false;
						}
						return true;
							
	      			}
	      		
	      		</script>
    		</div>
  		</div>
	</div>
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script type="text/javascript" src="js/external/jquery-2.1.4.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script type="text/javascript" src="bootstrap/bootstrap.min.js"></script>
	<!-- 引入模板的js文件 -->
	<script type="text/javascript" src="js/template/app.js"></script>
	<!-- 引入index.js -->
	<script type="text/javascript" src="js/back/index.js"></script>
</body>
</html>