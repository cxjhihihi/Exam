<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网上报名-消息列表</title>
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
			        学校通知
			        <small>通知列表</small>
			    </h1>
			    <ol class="breadcrumb">
			        <li><a href="javascript:void(0);" target="_self"><i class="glyphicon glyphicon-home"></i>首页</a></li>
			        <li class="active">通知列表 </li>
			    </ol>
			</section>
			<section class="content">
				<div class="input-group">
					<span class="input-group-addon">阅读状态</span>
					<select class="form-control" id="readStatus" placeholder="阅读状态" aria-describedby="basic-addon1">
						<c:if test="${status != 1 and status != 2 }">
							<option value="-1" selected="selected">全部</option>
						</c:if>
						<c:if test="${status == 1 or status == 2 }">
							<option value="-1">全部</option>
						</c:if>
						<c:if test="${status == 0 }">
							<option value="0" selected="selected">未读</option>
						</c:if>
						<c:if test="${status != 0 }">
							<option value="0">未读</option>
						</c:if>
						<c:if test="${status == 1 }">
							<option value="1" selected="selected">已读</option>
						</c:if>
						<c:if test="${status != 1 }">
							<option value="1">已读</option>
						</c:if>
					</select>
				</div>
				<div class="box box-primary">
					<div class="box-body chat" id="chat-box">
						<c:forEach var="mes" items="${list }">
							<div class="item">
                                <img src="images/template/news.jpg" alt="user image" class="online"/>
                                <p class="message">
                                    <a href="#" class="name">
                                        <small class="text-muted pull-right"><i class="glyphicon glyphicon-time"></i>${mes.m_time.substring(0, 19) }</small>
                                        发件人：${mes.m_from.r_name }
                                    </a>
                                </p>
                                <div class="attachment">
                                    <h4>消息内容：</h4>
                                    <p class="filename">
                                        ${mes.m_content }
                                    </p>
                                    <c:if test="${mes.m_read == 0 }">
	                                    <div class="pull-right">
	                                        <a href="sign?id=${mes.m_id }"><button class="btn btn-primary btn-sm btn-flat">标记为已读</button></a>
	                                    </div>
	                                </c:if>
                                </div><!-- /.attachment -->
                            </div><!-- /.item -->
						</c:forEach>
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
	<!-- 引入auditList.js -->
	<script type="text/javascript" src="js/foreground/allMessage.js"></script>
</body>
</html>