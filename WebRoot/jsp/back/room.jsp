<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
			       考场管理
			        <small>考场列表</small>
			    </h1>
			    <ol class="breadcrumb">
			        <li><a href="javascript:void(0);" target="_self"><i class="glyphicon glyphicon-home"></i>首页</a></li>
			        <li class="active">考场列表 </li>
			    </ol>
			</section>
			<section class="content">
				<div class="btn-group btn-group-lg" role="group" style="float: right;">
					<input type="button" id="delRoomButton" class="btn btn-default" value="删除选中"/>
					<input type="button" id="openRoomButton" class="btn btn-default" value="开放选中"/>
					<input type="button" class="btn btn-default" data-toggle="modal" data-target="#addRoomModal" value="增加考场"/>
				</div>
		        <table id="stu_table" class="table table-bordered table-striped">
					<thead>
						<tr>
							<th width="5%"><input type="checkbox" name="checkAll" id="checkAll"/></th>
							<th width="5%">编号</th>
							<th width="10%">考场名称</th>
							<th width="10%">所在校区</th>
							<th width="8%">容纳人数</th>
							<th width="8%">剩余空位</th>
							<th width="15%">开始时间</th>
							<th width="15%">结束时间</th>
							<th width="8%">考试科目</th>
							<th width="16%">操作</th>
						</tr>
					</thead>
					<tbody>	
						<c:forEach var="bean" items="${list }" varStatus="status">
							<tr>
								<td><input type="checkbox" name="check" value="${bean.e_id }"/></td>
								<td>${status.index + 1 }</td>
								<td>${bean.e_name }</td>
								<td>${bean.e_school.d_name }</td>
								<td>${bean.e_num }</td>
								<td>${bean.e_remain }</td>
								<td>${bean.e_startTime.substring(0, 19) }</td>
								<td>${bean.e_endTime.substring(0, 19) }</td>
								<td>${bean.e_course.d_name }</td>
								<td>
									<a href="javascript:void(0);" onclick="delRoom('${bean.e_id }')" title="删除">
										<span class="glyphicon glyphicon-remove"></span>删除
									</a>
									<c:if test="${bean.e_start == 0 }">
										&nbsp;&nbsp;
										<a href="javascript:void(0);" onclick="updateRoom('${bean.e_id }')" 
											data-toggle="modal" data-target="#updateRoomModal" title="修改">
											<span class="glyphicon glyphicon-pencil"></span>修改
										</a>
										&nbsp;&nbsp;
										<a href="javascript:void(0);" onclick="openRoom('${bean.e_id }')" title="开放">
											<span class="glyphicon glyphicon-play-circle"></span>开放
										</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</section>
		</aside>
		
		<script type="text/javascript">

			//删除考场
			function delRoom(id){
				
				if(confirm("确定删除该考场吗？")){
					window.location.href = "delRoom?ids=" + id;
				}
				
			}
			
			//开放考场
			function openRoom(id){
				
				if(confirm("开放该考场后考生可以开始打印准考证！确定开放该考场吗？")){
					window.location.href="openRoom?ids=" + id;
				}
				
			}
			
			//修改考场
			function updateRoom(id){
				
				$.ajax({
					
					type: "POST",
					url: "getRoomById",
					data: {
						id: id
					},
					dataType: "json",
					success: function(data){
						document.getElementById("e_id2").value = data.e_id;
						document.getElementById("e_name2").value = data.e_name;
						document.getElementById("e_school_id2").value = data.e_school.d_id;
						document.getElementById("e_num2").value = data.e_num;
						document.getElementById("e_startTime2").value = data.e_startTime;
						document.getElementById("e_endTime2").value = data.e_endTime;
						document.getElementById("e_course_id2").value = data.e_course.d_id;
					},
					error: function(data){
						alert("出错了！");
					}
					
				});
				
			}
		
		</script>
		
		<!-- 增加考场模态框 -->
		<div class="modal fade" id="addRoomModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  		<div class="modal-dialog" role="document">
	    		<div class="modal-content">
	      			<div class="modal-header">
	        			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        			<h4 class="modal-title" id="myModalLabel">增加考场</h4>
	      			</div>
	      			<form action="saveRoom" method="post" id="roomForm">
	      				<div class="modal-body">
	          				<div class="form-group">
	            				<label for="e_name" class="control-label">考场名称:&nbsp;<font color="red">*</font></label>
	          					<input type="text" class="form-control" name="e_name" id="e_name" maxlength="10" placeholder="考场名称">
	          				</div>
	          				<div class="form-group">
					            <label for="e_school_id" class="control-label">所在校区:&nbsp;<font color="red">*</font></label>
					            <select class="form-control" name="e_school_id" id="e_school_id">
					            	<c:forEach var="bean" items="${sList }">
					            		<option value="${bean.d_id }">${bean.d_name }</option>
					            	</c:forEach>
					            </select>
	         	 			</div>
	         	 			<div class="form-group">
					            <label for="e_num" class="control-label">总人数:&nbsp;<font color="red">*</font></label>
					            <input type="text" class="form-control" name="e_num" id="e_num" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="考场总人数(只能输入0-999之内的数字)">
	         	 			</div>
	         	 			<div class="form-group">
					            <label for="e_startTime" class="control-label">开始时间:&nbsp;<font color="red">*</font></label>
					            <div class="input-group date datetimepicker">
		                    		<input type="text" class="form-control" name="e_startTime" id="e_startTime" value="" maxlength="19" readonly>
		                    		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                		</div>
	         	 			</div>
	         	 			<div class="form-group">
					            <label for="e_endTime" class="control-label">结束时间:&nbsp;<font color="red">*</font></label>
					            <div class="input-group date datetimepicker">
		                    		<input type="text" class="form-control" name="e_endTime" id="e_endTime" maxlength="19" readonly>
		                    		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                		</div>
	         	 			</div>
	         	 			<div class="form-group">
					            <label for="e_course" class="control-label">考试科目:&nbsp;<font color="red">*</font></label>
					            <select class="form-control" name="e_course_id" id="e_course_id">
					            	<c:forEach var="bean" items="${cList }">
					            		<option value="${bean.d_id }">${bean.d_name }</option>
					            	</c:forEach>
					            </select>
	         	 			</div>
	      				</div>
		      			<div class="modal-footer">
		        			<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        			<button type="submit" class="btn btn-primary">保存</button>
		      			</div>
		      		</form>
	    		</div>
	  		</div>
		</div>
		
		<!-- 修改考场模态框 -->
		<div class="modal fade" id="updateRoomModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  		<div class="modal-dialog" role="document">
	    		<div class="modal-content">
	      			<div class="modal-header">
	        			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        			<h4 class="modal-title" id="myModalLabel">修改考场</h4>
	      			</div>
	      			<form action="updateRoom" method="post" id="roomForm2">
	      				<input type="hidden" name="e_id" id="e_id2"/>
	      				<div class="modal-body">
	          				<div class="form-group">
	            				<label for="e_name" class="control-label">考场名称:&nbsp;<font color="red">*</font></label>
	          					<input type="text" class="form-control" name="e_name" id="e_name2" maxlength="10" placeholder="考场名称">
	          				</div>
	          				<div class="form-group">
					            <label for="e_school_id" class="control-label">所在校区:&nbsp;<font color="red">*</font></label>
					            <select class="form-control" name="e_school_id" id="e_school_id2">
					            	<c:forEach var="bean" items="${sList }">
					            		<option value="${bean.d_id }">${bean.d_name }</option>
					            	</c:forEach>
					            </select>
	         	 			</div>
	         	 			<div class="form-group">
					            <label for="e_num" class="control-label">总人数:&nbsp;<font color="red">*</font></label>
					            <input type="text" class="form-control" name="e_num" id="e_num2" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="考场总人数(只能输入0-999之内的数字)">
	         	 			</div>
	         	 			<div class="form-group">
					            <label for="e_startTime" class="control-label">开始时间:&nbsp;<font color="red">*</font></label>
					            <div class="input-group date datetimepicker">
		                    		<input type="text" class="form-control" name="e_startTime" id="e_startTime2" value="" maxlength="19" readonly>
		                    		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                		</div>
	         	 			</div>
	         	 			<div class="form-group">
					            <label for="e_endTime" class="control-label">结束时间:&nbsp;<font color="red">*</font></label>
					            <div class="input-group date datetimepicker">
		                    		<input type="text" class="form-control" name="e_endTime" id="e_endTime2" maxlength="19" readonly>
		                    		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                		</div>
	         	 			</div>
	         	 			<div class="form-group">
					            <label for="e_course" class="control-label">考试科目:&nbsp;<font color="red">*</font></label>
					            <select class="form-control" name="e_course_id" id="e_course_id2">
					            	<c:forEach var="bean" items="${cList }">
					            		<option value="${bean.d_id }">${bean.d_name }</option>
					            	</c:forEach>
					            </select>
	         	 			</div>
	      				</div>
		      			<div class="modal-footer">
		        			<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		        			<button type="submit" class="btn btn-primary">保存</button>
		      			</div>
		      		</form>
	    		</div>
	  		</div>
		</div>
		
	</div>
	
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script type="text/javascript" src="js/external/jquery-2.1.4.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script type="text/javascript" src="bootstrap/bootstrap.min.js"></script>
	<!-- 引入bootstrap-datatimepicker -->
	<script type="text/javascript" src="bootstrap/bootstrap-datetimepicker.min.js"></script>
	<!-- 引入日期控件datetimepicker中文语言包 -->
	<script type="text/javascript" src="bootstrap/bootstrap-datetimepicker.zh-CN.js"></script>
	<!-- 引入room.js -->
	<script type="text/javascript" src="js/back/room.js"></script>
</body>
</html>