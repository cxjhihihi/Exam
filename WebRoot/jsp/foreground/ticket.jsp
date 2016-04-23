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

<style type="text/css">

	@MEDIA print {
	
		.noprint{
			display: none;
		}
	
	}

</style>

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
	<div class="container-fluid noprint">
		<aside class="right-side">                
			<section class="content-header">
			    <h1>
			       准考证
			        <small>考场列表</small>
			    </h1>
			    <ol class="breadcrumb">
			        <li><a href="javascript:void(0);" target="_self"><i class="glyphicon glyphicon-home"></i>首页</a></li>
			        <li class="active">考场列表 </li>
			    </ol>
			</section>
			<section class="content">
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
									<a href="javascript:void(0);" onclick="printTicket('${bean.e_id}', '${bean.e_remain }')" 
										data-toggle="modal" data-target="#printModal" title="打印">
										<span class="glyphicon glyphicon-print"></span>打印
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</section>
		</aside>
		
		<script type="text/javascript">
		
			//打印准考证
			function printTicket(id, remain){
				
				if(remain > 0){
					
					$.ajax({
						
						type: "POST",
						url: "printTicket",
						data: {
							id: id
						},
						dataType: "json",
						success: function(data){
							if(data == null){
								alert("没有获取到准考证信息！请检查该考场是否还有剩余座位！");
								$("#printModal").modal("hide");
								return false;
							}else if(data.t_room.e_id != id){
								if(confirm("已经打印过该学科的准考证！是否重新打印！")){
									$("#name").val(data.t_user.name);//考生姓名
									$("#id").val(data.t_user.id);//身份证号
									$("#school").val(data.t_room.e_school.d_name);//考试校区
									$("#roomName").val(data.t_room.e_name);//考场
									$("#roomNum").val(data.t_seat);//座位号
									$("#time").val(data.t_room.e_startTime.substring(0, 19)
											+ "至" + data.t_room.e_endTime.substring(0, 19));//考试时间
									$("#course").val(data.t_room.e_course.d_name);//考试科目
									$("#picture").attr("src", "upload/picture/" + data.t_user.picture);//照片
									return true;
								}else{
									$("#printModal").modal("hide");
									return false;
								}
							}else{
								$("#name").val(data.t_user.name);//考生姓名
								$("#id").val(data.t_user.id);//身份证号
								$("#school").val(data.t_room.e_school.d_name);//考试校区
								$("#roomName").val(data.t_room.e_name);//考场
								$("#roomNum").val(data.t_seat);//座位号
								$("#time").val(data.t_room.e_startTime.substring(0, 19)
										+ "至" + data.t_room.e_endTime.substring(0, 19));//考试时间
								$("#course").val(data.t_room.e_course.d_name);//考试科目 
								$("#picture").attr("src", "upload/picture/" + data.t_user.picture);//照片
								return true;
							} 
						},
						error: function(data){
							alert("出错了！");
							$("#printModal").modal("hide");
							return false;
						}
						
					});
				}else{
					alert("该考场没有剩余座位了！请重新选择！");
					return false;
				}
				
			}
		
		</script>
		
	</div>
	<!-- 打印准考证模态框 -->
	<div class="modal fade" id="printModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  		<div class="modal-dialog" role="document">
    		<div class="modal-content">
      			<div class="modal-header">
        			<button type="button" class="close noprint" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        			<h4 class="modal-title" id="myModalLabel" style="text-align: center"><strong>准考证</strong></h4>
      			</div>
      			<div class="row">
      				<div class="col-md-1"></div>
      				<div class="col-md-10">
      					<table class="table table-bordered">
      						<tr>
      							<th width="20%">考生姓名：</th>
      							<td width="50%"><input type="text" name="name" id="name" disabled="disabled"/></td>
	      							<th colspan="2" rowspan="4">
	      								<div>
	      									<img src="" id="picture" style="width: 100%; height: 100%;">
	      								</div>
	      							</th>
	      						</tr>
	      						<tr>
      							<th>身份证号：</th>
      							<td><input type="text" name="id" id="id" disabled="disabled"/></td>
	      						</tr>
	      						<tr>
      							<th>考试校区：</th>
      							<td><input type="text" name="school" id="school" disabled="disabled"/></td>
	      						</tr>
	      						<tr>
      							<th>考场：</th>
      							<td><input type="text" name="roomName" id="roomName" disabled="disabled"/></td>
	      						</tr>
	      						<tr>
      							<th>座位：</th>
      							<td colspan="3"><input type="text" name="roomNum" id="roomNum" disabled="disabled"/></td>
	      						</tr>
	      						<tr>
      							<th>考试时间：</th>
      							<td colspan="3"><input type="text" name="time" id="time" disabled="disabled"/></td>
	      						</tr>
	      						<tr>
      							<th>考试科目：</th>
      							<td colspan="3"><input type="text" name="course" id="course" disabled="disabled"/></td>
	      						</tr>
      					</table>
      				</div>
      				<div class="col-md-1"></div>
      			</div>
      			<div class="modal-footer noprint">
        			<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        			<button type="submit" id="print" onclick="" class="btn btn-primary">打印</button>
      			</div>
    		</div>
  		</div>
	</div>
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script type="text/javascript" src="js/external/jquery-2.1.4.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script type="text/javascript" src="bootstrap/bootstrap.min.js"></script>
	<!-- 引入ticket.js -->
	<script type="text/javascript" src="js/foreground/ticket.js"></script>
</body>
</html>