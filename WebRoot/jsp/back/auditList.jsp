<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网上报名-审核列表</title>
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
			        审核管理
			        <small>审核列表</small>
			    </h1>
			    <ol class="breadcrumb">
			        <li><a href="javascript:void(0);" target="_self"><i class="glyphicon glyphicon-home"></i>首页</a></li>
			        <li class="active">审核列表 </li>
			    </ol>
			</section>
			<section class="content">
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1">审核状态</span>
					<select class="form-control" placeholder="审核状态" aria-describedby="basic-addon1" id="condition1">
						<option value="-1">全部</option>
						<c:if test="${conditions.status == 0 }">
							<option value="0" selected="selected">审核未开始</option>
						</c:if>
						<c:if test="${conditions.status == 1 }">
							<option value="1" selected="selected">审核未通过</option>
						</c:if>
						<c:if test="${conditions.status != 1 }">
							<option value="1">审核未通过</option>
						</c:if>
						<c:if test="${conditions.status == 2 }">
							<option value="2" selected="selected">审核通过</option>
						</c:if>
						<c:if test="${conditions.status != 2 }">
							<option value="2">审核通过</option>
						</c:if>
					</select>
					<span class="input-group-addon" id="basic-addon2">姓名</span>
					<input class="form-control" placeholder="请输入学生姓名" aria-describedby="basic-addon2" id="condition2" value="${conditions.name }">
					<span class="input-group-btn">
						<button class="btn btn-primary" type="button" id="queryButton">查询</button>
					</span>
				</div>
				<table id="audit_table" class="table table-bordered table-striped">
					<thead>
						<tr>
							<th width="5%"><input type="checkbox" name="checkAll" id="checkAll"/></th>
							<th width="5%">编号</th>
							<th width="10%">姓名</th>
							<th width="5%">性别</th>
							<th width="15%">身份证号</th>
							<th width="20%">毕业学校</th>
							<th width="20%">户籍所在地</th>
							<th width="10%">审核状态</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="bean" items="${list }" varStatus="status">
							<tr>
								<td><input type="checkbox" name="check" value="${bean.id }"/></td>
								<td>${status.index + 1 }</td>
								<td><a href="stuInfo?id=${bean.idCard }">${bean.name }</a></td>
								<td>${bean.sex }</td>
								<td><a href="stuInfo?id=${bean.idCard }">${bean.idCard }</a></td>
								<td>${bean.school }</td>
								<td>${bean.householdAddress }</td>
								<td>
									<c:if test="${bean.status == 0 }">
										审核未开始
									</c:if>
									<c:if test="${bean.status == 1 }">
										审核未通过
									</c:if>
									<c:if test="${bean.status == 2 }">
										审核通过
									</c:if>
									<c:if test="${bean.status != 0 && bean.status != 1 && bean.status != 2 }">
										其他
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
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
	<!-- 引入auditList.js -->
	<script type="text/javascript" src="js/back/auditList.js"></script>
</body>
</html>