<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网上报名系统-考生信息</title>

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" type="text/css" href="bootstrap/bootstrap.min.css">
<!-- 引入模板的css文件 -->
<link rel="stylesheet" type="text/css" href="css/template/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/template/ionicons.min.css">
<link rel="stylesheet" type="text/css" href="css/template/AdminLTE.css">
<!-- 引入日期控件datetimepicker所需css文件 -->
<link rel="stylesheet" type="text/css" href="bootstrap/bootstrap-datetimepicker.min.css">

</head>
<body>
	<div class="container-fluid">
		<aside class="right-side">                
			<section class="content-header">
			    <h1>
			       招生管理
			        <small>考生信息</small>
			    </h1>
			    <ol class="breadcrumb">
			        <li><a href="javascript:void(0);" target="_self"><i class="glyphicon glyphicon-home"></i>首页</a></li>
			        <li class="active">考生信息</li>
			    </ol>
			</section>
			<section class="content">
				<table id="info_table" class="table table-bordered table-striped row">
			  		<tr>
			  			<th>姓名&nbsp;<font color="red">*</font></th>
			  			<td>${info.name }</td>
			  			<th>性别</th>
			  			<td>${info.sex }</td>
			  			<th colspan="2">出生年月</th>
			  			<td colspan="2">${info.birth }</td>
			  			<th colspan="2">照片</th>
			  		</tr>
			  		<tr>
			  			<th>身份证号&nbsp;<font color="red">*</font></th>
			  			<td colspan="3">${info.idCard }</td>
			  			<th colspan="2">联系电话</th>
			  			<td colspan="2">${info.phone_1 }</td>
			  			<td rowspan="4" colspan="2">
		  					${info.name }
		  					<div>
			  					<c:if test="${not empty info.picture }">
			  						<img alt="${info.picture }" src="upload/picture${info.picture }"
			  							width="90%" height="90%">
			  					</c:if>
			  				</div>
			  			</td>
			  		</tr>
			  		<tr>
			  			<th>学校所在地</th>
			  			<td colspan="3">${info.schoolAddress }</td>
			  			<th>毕业学校</th>
			  			<td colspan="3">${info.school }</td>
			  		</tr>
			  		<tr>
			  			<th>户籍所在地&nbsp;<font color="red">*</font></th>
			  			<td colspan="3">${info.householdAddress }</td>
			  			<th colspan="2">班级</th>
			  			<td colspan="2">${info.sclass }</td>
			  		</tr>
			  		<tr>
			  			<th>家庭住址</th>
			  			<td colspan="3">${info.homeAddress }</td>
			  			<th colspan="2">电话</th>
			  			<td colspan="2">${info.phone_2 }</td>
			  		</tr>
				</table>
				<font color="green">注意：带<font color="red">*</font>字符的为必填项</font>
				<div align="center">
					<div class="btn-group btn-group-lg" role="group">
						<button type="button" id="backButton" class="btn btn-primary" data-toggle="tooltip" data-placement="top" title="点击后返回上一页面">返回</button>
					</div>
				</div>
			</section>
	    </aside>
	</div>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script type="text/javascript" src="js/external/jquery-2.1.4.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script type="text/javascript" src="bootstrap/bootstrap.min.js"></script>
	<!-- 引入bootstrap-datatimepicker -->
	<script type="text/javascript" src="bootstrap/bootstrap-datetimepicker.min.js"></script>
	<!-- 引入日期控件datetimepicker中文语言包 -->
	<script type="text/javascript" src="bootstrap/bootstrap-datetimepicker.zh-CN.js"></script>
	<!-- 获取informatino.jsp所需jsp文件 -->
	<script type="text/javascript" src="js/back/stu.js"></script>
</body>
</html>