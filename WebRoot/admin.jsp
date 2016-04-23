<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网上报名系统-管理员登录</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" type="text/css" href="bootstrap/bootstrap.min.css">
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
	</div>

	<div class="banner">
		<div class="login-aside">
			<div id="o-box-up"></div>
			<div id="o-box-down" style="table-layout: fixed;">
				<div class="error-box"></div>
				<form action="adminLogin" method="post" id="adminLoginForm">
					<div class="fm-item">
						<label class="form-label">
							账号：
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
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script type="text/javascript" src="js/external/jquery-2.1.4.min.js"></script>
	<!-- 引入login.js -->
	<script type="text/javascript" src="js/back/admin.js"></script>
</body>
</html>