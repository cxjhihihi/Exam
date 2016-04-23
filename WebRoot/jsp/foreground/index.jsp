<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
                     <li class="dropdown messages-menu">
                        <a href="javascript:void(0);" id="message" class="dropdown-toggle" data-toggle="dropdown" style="height: 50px;">
	                   		<i class="glyphicon glyphicon-envelope"></i>
	                   		<c:if test="${fn:length(mes) > 0 }">
	                    		<span id="mesNum" class="label label-success">${fn:length(mes) }</span>
                			</c:if>
                		</a>
		                <ul class="dropdown-menu">
		                    <li class="header">未读消息</li>
		                    <li>
		                        <ul class="menu">
		                        	<c:if test="${empty mes }">
		                        		<li>暂无未读消息</li>
		                        	</c:if>
		                        	<c:forEach var="mes" items="${mes }">
			                            <li>
			                                <a href="javascript:void(0);">
			                                    <div class="pull-left">
			                                        <img src="images/template/news.jpg" class="img-circle" alt="User Image"/>
			                                    </div>
			                                    <h4>
			                                       	 系统消息
			                                       	 <small><i class="glyphicon glyphicon-time"></i> ${mes.m_time.substring(0, 19) }</small>
			                                    </h4>
			                                    <p>${mes.m_content }</p>
			                                </a>
			                            </li>
		                            </c:forEach>
		                        </ul>
                    		</li>
                    		<li class="footer"><a href="allMessage" target="mainIframe">查看所有系统消息</a></li>
                		</ul>
            		</li>
		            <li class="dropdown user user-menu">
		                <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" style="height: 50px;">
		                    <i class="glyphicon glyphicon-user"></i>
		                    <span>${sessionScope.user.name }<i class="caret"></i></span>
		                </a>
		                <ul class="dropdown-menu">
		                    <li class="user-header bg-light-blue">
		                    	<c:if test="${sessionScope.user.sex == '男' }">
		                    		<img src="images/template/avatar.png" class="img-circle" alt="User Image" />
		                    	</c:if>
		                        <c:if test="${sessionScope.user.sex == '女' }">
		                        	<img src="images/template/avatar2.png" class="img-circle" alt="User Image" />
		                        </c:if>
		                        <p>
		                            ${sessionScope.user.name } - 学生
		                            <small>${sessionScope.user.birth }</small>
		                        </p>
		                    </li>
		                    <li class="user-footer">
		                        <div class="pull-left">
		                            <a href="javascript:void(0);" id="jumpModel" class="btn btn-default btn-flat" data-toggle="modal" data-target="#pwdModal">修改密码</a>
		                        </div>
		                        <div class="pull-right">
		                            <a href="logout" class="btn btn-default btn-flat">退出</a>
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
	                    <c:if test="${sessionScope.user.sex == '男' }">
	                   		<img src="images/template/avatar.png" class="img-circle" alt="User Image" />
	                   	</c:if>
	                    <c:if test="${sessionScope.user.sex == '女' }">
	                    	<img src="images/template/avatar2.png" class="img-circle" alt="User Image" />
	                    </c:if>
	                </div>
	                <div class="pull-left info">
	                    <p>你好, ${sessionScope.user.name }</p>
	
	                    <a href="javascript:void(0);"><i class="fa fa-circle text-success"></i> 在线</a>
	                </div>
	            </div>
	            <ul class="sidebar-menu">
	                <li class="active nav">
	                    <a href="information" target="mainIframe">
	                        <i class="glyphicon glyphicon-book"></i> <span>报名信息</span>
	                    </a>
	                </li>
	                <c:if test="${status == 2 }">
		                <li class="nav">
		                    <a href="ticket" target="mainIframe">
		                        <i class="glyphicon glyphicon-print"></i> <span>准考证</span>
		                    </a>
		                </li>
		            </c:if>
	                <li class="nav">
	                    <a href="allMessage" target="mainIframe">
	                        <i class="glyphicon glyphicon-comment"></i> <span>学校通知</span>
	                    </a>
	                </li>
	            </ul>
	        </section>
	    </aside>
   		<iframe src="information" name="mainIframe" id="mainIframe" width="100%" frameborder="0"></iframe>              
	</div>
	<!-- 修改密码模态框 -->
	<div class="modal fade" id="pwdModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  		<div class="modal-dialog" role="document">
    		<div class="modal-content">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        			<h4 class="modal-title" id="myModalLabel">修改密码</h4>
      			</div>
      			<form action="updatePwd" method="post" onsubmit="return validation();">
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
	        			<button type="submit" id="updatePwd" class="btn btn-primary">保存</button>
	      			</div>
	      		</form>
	      		<script type="text/javascript">
	      		
	      			function validation(){
	      				
	      				var truePwd = "${sessionScope.user.pwd}";//正确密码
						var initPwd = document.getElementById("initPwd").value;//原始密码
						var newPwd = document.getElementById("newPwd").value;//新密码
						var confirmPwd = document.getElementById("confirmPwd").value;//确认密码
						
						if(initPwd == null || initPwd == ""){
							alert("请输入原始密码！")
							return false;
						}else if(truePwd != initPwd){
							alert("原始密码错误，请重新输入！");
							return false;
						}else if(newPwd == null || newPwd == ""){
							alert("请输入新密码！");
							return false;
						}else if(confirmPwd == null || confrimPwd == ""){
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
	<!-- 引入index.js文件 -->
	<script type="text/javascript" src="js/foreground/index.js"></script>
</body>
</html>