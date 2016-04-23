<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网上报名系统-招生时间</title>

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" type="text/css" href="bootstrap/bootstrap.min.css">
<!-- 引入模板的css文件 -->
<link rel="stylesheet" type="text/css" href="css/template/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/template/ionicons.min.css">
<link rel="stylesheet" type="text/css" href="css/template/AdminLTE.css">
<!-- 引入日期控件datetimepicker所需css文件 -->
<link rel="stylesheet" type="text/css" href="bootstrap/bootstrap-datetimepicker.min.css">

<script type="text/javascript">

	//结果返回,因为外部js文件不能获取request或session中的值，所以必须放在jsp中
	//因为jsp本身就是一个servlet
	function message(){
		
		var message = "${message}";
		if(message != null && message != ""){
			alert(message);
		}
		
	}
	
</script>

</head>
<body onload="message()">
	<div class="container-fluid">
		<aside class="right-side">                
			<section class="content-header">
			    <h1>
			        招生管理
			        <small>招生时间</small>
			    </h1>
			    <ol class="breadcrumb">
			        <li><a href="javascript:void(0);" target="_self"><i class="glyphicon glyphicon-home"></i>首页</a></li>
			        <li class="active">招生时间 </li>
			    </ol>
			</section>
			<section class="content">
				<div class="row">
	                <div class="col-md-12">
	                    <div class="box box-primary">
	                        <div class="box-header">
	                            <h3 class="box-title">招生时间</h3>
	                        </div>
	                        <div class="box-body">
	                            <div class="row margin">
	                            	<form action="javascript:void(0);" id="timeForm" method="post">
	                            		<input type="hidden" value="${time.id }" name="id" id="id"/>
	                            		<table>
	                            			<tr>
	                            				<td class="col-md-2">
	                            					招生时间：
	                            				</td>
	                            				<td class="col-md-4">
	                            					<div class="input-group date datetimepicker">
							                    		<input type="text" class="form-control" value="${time.startTime.substring(0, 10) }" name="startTime" id="startTime" readonly>
							                    		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
														<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
							                		</div>
	                            				</td>
	                            				<td class="col-md-1">
	                            					——
	                            				</td>
	                            				<td class="col-md-4">
	                            					<div class="input-group date datetimepicker">
							                    		<input type="text" class="form-control" value="${time.endTime.substring(0, 10) }" name="endTime" id="endTime" readonly>
							                    		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
														<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
							                		</div>
	                            				</td>
	                            				<td class="col-md-1">
	                            					<c:if test="${empty time }">
	                            						<button type="button" id="saveButton" data-toggle="tooltip" data-placement="top" class="btn btn-primary" title="点击后，在该时间段内考生可以报名">确定</button>
	                            					</c:if>
	                            					<c:if test="${not empty time }">
	                            						<button type="button" id="updateButton" data-toggle="tooltip" data-placement="top" class="btn btn-primary" title="点击后，在该时间段内考生可以报名">确定修改</button>
	                            					</c:if>
	                            				</td>
	                            			</tr>
			                        	</table>
	                                </form>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            <div class="row">                        
	                <div class="col-md-12">
	                    <ul class="timeline">
	                        <li class="time-label">
	                            <span class="bg-red">
	                            	<!-- gt:大于	lt:小于	ge(gt eq):大于等于	le(lt eq):小于等于	ne:不等于		eq:等于 -->
	                            	<c:if test="${(time.startTime).substring(0, 10) gt now || empty time }">
	                            		尚未开始
	                            	</c:if>
	                            	<c:if test="${(time.startTime).substring(0, 10) le now }">
	                               		${time.startTime.substring(0, 10) }
	                               	</c:if>
								</span>
	                        </li>
	                        <c:if test="${(time.startTime).substring(0, 10) gt now || empty time }">
		                        <li>
		                            <i class="fa fa-comments bg-green"></i>
		                            <div class="timeline-item">
		                                <span class="time"><i class="fa fa-clock-o"></i>${now }</span>
		                                <h3 class="timeline-header"><a href="javascript:void(0);">尚未开始</a></h3>
		                                <div class="timeline-body">
		                                	尚未开始报名....
		                                </div>
		                            </div>
		                        </li>
		                    </c:if>
		                    <c:if test="${(time.startTime).substring(0, 10) le now }">
		                        <li>
		                            <i class="fa fa-envelope bg-blue"></i>
		                            <div class="timeline-item">
		                                <span class="time"><i class="fa fa-clock-o"></i>${time.startTime.substring(0, 10) }</span>
		                                <h3 class="timeline-header"><a href="javascript:void(0);">开始报名</a></h3>
		                                <div class="timeline-body">
		                                	【${user.r_name }】在${time.startTime.substring(0, 10) }设置报名时间，报名开始
		                                </div>
		                            </div>
		                        </li>
		                        <li>
		                            <i class="fa fa-user bg-aqua"></i>
		                            <div class="timeline-item">
		                                <span class="time"><i class="fa fa-clock-o"></i>...</span>
		                                <h3 class="timeline-header no-border"><a href="javascript:void(0);">报名进行中...</a></h3>
		                            	<div class="timeline-body">
		                                	截止到目前为止，已有<font color="red">${count }</font>人提交报名信息...
		                                </div>
		                            </div>
		                        </li>
		                    </c:if>
		                    <c:if test="${(time.endTime).substring(0, 10) lt now }">
		                        <li>
		                            <i class="fa fa-comments bg-yellow"></i>
		                            <div class="timeline-item">
		                                <span class="time"><i class="fa fa-clock-o"></i>${time.endTime.substring(0, 10) }</span>
		                                <h3 class="timeline-header"><a href="javascript:void(0);">报名结束</a>可以开始进行审核准备...</h3>
		                                <div class="timeline-body">
		                                    	报名已经结束！<br/>
		                                    	从开始到结束共有<font color="red">${count }</font>提交报名信息！
		                                    	请及时进行审核....
		                                </div>
		                                <div class='timeline-footer'>
		                                    <a class="btn btn-warning btn-flat btn-xs">结束</a>
		                                </div>
		                            </div>
		                        </li>
		                    </c:if>
	                        <li>
	                            <i class="fa fa-clock-o"></i>
	                        </li>
	                    </ul>
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
	<!-- 引入time.js -->
	<script type="text/javascript" src="js/back/time.js"></script>
</body>
</html>