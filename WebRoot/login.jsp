<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网上报名系统-考生登录</title>

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" type="text/css" href="bootstrap/bootstrap.min.css">
<!-- 引入日期控件datetimepicker所需css文件 -->
<link rel="stylesheet" type="text/css" href="bootstrap/bootstrap-datetimepicker.min.css">
<!-- 引入login.css -->
<link rel="stylesheet" style="text/css" href="css/foreground/login.css">

<script type="text/javascript">

	if(window != top){
		top.location.href = location.href;
	}

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
<body onload="message()">
	<div class="header">
		<h1 class="headerLogo">
			<img alt="logo" src="images/foreground/logo.gif">
		</h1>
		<!-- <div class="headerNav">
			<a target="" href="javascript:void(0);">关于系统</a>
			<a target="" href="javascript:void(0);">开发团队</a>
			<a target="" href="javascript:void(0);">意见反馈</a>
			<a target="" href="javascript:void(0);"><font color="red">帮助</font></a>
		</div> -->
	</div>

	<div class="banner" style="z-index: 5;">
		<div class="login-aside">
			<div id="o-box-up"></div>
			<div id="o-box-down" style="table-layout: fixed;">
				<div class="error-box"></div>
				<form action="login" method="post" id="loginForm">
					<div class="fm-item">
						<label class="form-label">
							身份证号：
						</label>
						<input type="text" name="userName" id="userName" maxlength="18" class="i-text">
					</div>
					<div class="fm-item">
						<label class="form-label">
							密码：
						</label>
						<input type="password" name="userPwd" id="userPwd" maxlength="30" class="i-text">
					</div>
					<div class="fm-item pos-r">
						<label class="form-label">
							验证码
						</label>
						<input type="text" maxlength="4" name="safeCode" id="safeCode"
							class="i-text yzm" onkeyup="value=value.replace(/[^\w\.\/]/ig, '')">
						<div class="ui-form-explain">
							<img src="safeCode" class="yzm-img" id="yzm" title="点击图片刷新验证码" />
						</div>
					</div>
					<br/>
					没有账号？<a href="javascript:void(0);" data-toggle="modal" data-target="#registerModal"><font color="red">点击注册</font></a>
					<div class="fm-item">
						<label class="form-label"></label>
						<input type="submit" value="" class="btn-login">
					</div>
				</form>
			</div>
		</div>
		<div class="bd">
			<ul>
				<li style="background: url(images/foreground/theme-pic1.jpg) #CCE1F3 center 0 no-repeat;"></li>
				<li style="background: url(images/foreground/theme-pic2.jpg) #BCE0FF center 0 no-repeat;"></li>
			</ul>
		</div>
	</div>
	<div class="banner-shadow"></div>
	<div class="footer">
		<p>
			版权所有	Copyright	cxjhihihi
		</p>
	</div>
	<!-- 用户注册模态框 -->
	<div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  		<div class="modal-dialog" role="document">
    		<div class="modal-content registerModal">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        			<h4 class="modal-title" id="myModalLabel">用户注册
        				<small><font color="green">注意：带有<font color="red">*</font>都为必填项</font></small>
        			</h4>
      			</div>
      			<form action="saveUser" method="post" id="registerForm">
      				<div class="modal-body">
          				<div class="form-group">
            				<label for="id" class="control-label">身份证号:&nbsp;&nbsp;<font color="red">*</font></label>
            				<input type="text" class="form-control" name="id" id="id" maxlength="18" placeholder="身份证号">
          				</div>
          				<div class="form-group">
            				<label for="name" class="control-label">姓名:&nbsp;&nbsp;<font color="red">*</font></label>
            				<input type="text" class="form-control" name="name" id="name" maxlength="10" placeholder="姓名">
          				</div>
          				<div class="form-group">
            				<label for="age" class="control-label">年龄:</label>
            				<input type="text" class="form-control" name="age" id="age" maxlength="2" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="年龄">
          				</div>
          				<div class="form-group">
            				<label for="sex" class="control-label">性别:</label>
            				<select class="form-control" name="sex" id="sex">
            					<option>男</option>
            					<option>女</option>
            				</select>
          				</div>
          				<div class="form-group">
            				<label for="birth" class="control-label">出生日期:</label>
							<div class="input-group date" id="datetimepicker" data-date="dd-mm-yyyy" data-date-format="dd-mm-yyyy">
	                    		<input type="text" class="form-control" name="birth" id="birth" readonly>
	                    		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
								<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
	                		</div>
          				</div>
          				<div class="form-group">
				            <label for="pwd" class="control-label">登录密码:&nbsp;&nbsp;<font color="red">*</font></label>
				            <input type="password" class="form-control" name="pwd" id="pwd" maxlength="30" placeholder="登录密码">
         	 			</div>
         	 			<div class="form-group">
				            <label for="confirmPwd" class="control-label">确认密码:&nbsp;&nbsp;<font color="red">*</font></label>
				            <input type="password" class="form-control" id="confirmPwd" maxlength="30" placeholder="确认密码">
         	 			</div>
      				</div>
	      			<div class="modal-footer">
	        			<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	        			<button type="submit" id="registerButton" class="btn btn-primary">注册</button>
	      			</div>
	      		</form>
    		</div>
  		</div>
	</div>
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script type="text/javascript" src="js/external/jquery-2.1.4.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script type="text/javascript" src="bootstrap/bootstrap.min.js"></script>
	<!-- 获取日期控件datetimepicker所需js文件 -->
	<script type="text/javascript" src="bootstrap/bootstrap-datetimepicker.min.js"></script>
	<!-- 引入日期控件datetimepicker中文语言包 -->
	<script type="text/javascript" src="bootstrap/bootstrap-datetimepicker.zh-CN.js"></script>
	<!-- 引入login.js -->
	<script type="text/javascript" src="js/foreground/login.js"></script>
	<!-- 引入身份证合法性验证的js -->
	<script type="text/javascript" src="js/external/verificationID.js"></script>
</body>
</html>