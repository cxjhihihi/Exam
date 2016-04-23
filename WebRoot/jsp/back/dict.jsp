<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网上报名系统-学科管理</title>
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
		
	}
	
</script>
</head>
<body onload="message()">
	<div class="container-fluid">
		<aside class="right-side">                
			<section class="content-header">
			    <h1>
			       字典管理
			        <small>
						<c:if test="${type == 'course' }">学科管理</c:if>
						<c:if test="${type == 'school' }">校区管理</c:if>
					</small>
			    </h1>
			    <ol class="breadcrumb">
			        <li><a href="javascript:void(0);" target="_self"><i class="glyphicon glyphicon-home"></i>首页</a></li>
			        <li class="active">
						<c:if test="${type == 'course' }">学科管理</c:if>
						<c:if test="${type == 'school' }">校区管理</c:if>
					</li>
			    </ol>
			</section>
			<div class="col-md-2"></div>
			<section class="content col-md-8">
				<div class="btn-group btn-group-lg" role="group" style="float: right;">
					<input type="button" id="delCheck" class="btn btn-default" value="删除选中"/>
					<input type="button" id="addButton" class="btn btn-default" data-toggle="modal" data-target="#addDictModal" 
						value="<c:if test="${type == 'course' }">增加学科</c:if><c:if test="${type == 'school' }">增加校区</c:if>"/>
				</div>
		        <table id="stu_table" class="table table-bordered table-striped">
					<thead>
						<tr>
							<th width="10%"><input type="checkbox" name="checkAll" id="checkAll"/></th>
							<th width="10%">编号</th>
							<th width="20%">
								<c:if test="${type == 'course' }">学科代码</c:if>
								<c:if test="${type == 'school' }">校区代码</c:if>
							</th>
							<th width="30%">
								<c:if test="${type == 'course' }">学科名称</c:if>
								<c:if test="${type == 'school' }">校区名称</c:if>
							</th>
							<th width="30%">操作</th>
						</tr>
					</thead>
					<tbody>	
						<c:forEach var="bean" items="${list }" varStatus="status">
							<tr>
								<td><input type="checkbox" name="check" value="${bean.d_id }"/></td>
								<td>${status.index + 1 }</td>
								<td>${bean.d_code }</td>
								<td>${bean.d_name }</td>
								<td>
									<a href="javascript:void(0);" onclick="delDict('${bean.d_id}', '${type }')" title="删除">
										<span class="glyphicon glyphicon-remove"></span>删除
									</a>&nbsp;&nbsp;
									<a href="javascript:void(0);" onclick="modifyDict('${bean.d_id}')"
										 title="修改" data-toggle="modal" data-target="#updateDictModal">
										<span class="glyphicon glyphicon-pencil"></span>修改
									</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</section>
		</aside>
		<div class="col-md-2"></div>
		<script type="text/javascript">
			
			//修改
			function modifyDict(id){
				
				$.ajax({
					
					type: "POST",
					url: "getDictById",
					data: {
						id: id
					},
					dataType: "json",
					success: function(data){
						document.getElementById("d_id2").value = data.d_id;
						document.getElementById("d_code2").value = data.d_code;
						document.getElementById("d_name2").value = data.d_name;
					},
					error: function(data){
						alert("出错了！");
					}
					
				});
				
			}
		
		</script>
		
		<!-- 增加角色模态框 -->
		<div class="modal fade" id="addDictModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  		<div class="modal-dialog" role="document">
	    		<div class="modal-content">
	      			<div class="modal-header">
	        			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        			<h4 class="modal-title" id="myModalLabel">
	        				<c:if test="${type == 'course' }">增加学科</c:if>
							<c:if test="${type == 'school' }">增加校区</c:if>
	        			</h4>
	      			</div>
	      			<form action="saveDict" method="post" id="dictForm">
	      				<input type="hidden" name="d_type" id="d_type" value="${type }"/>
	      				<div class="modal-body">
	          				<div class="form-group">
	            				<label for="d_code" class="control-label">
	            					<c:if test="${type == 'course' }">学科代码</c:if>
									<c:if test="${type == 'school' }">校区代码</c:if>
	            					&nbsp;<font color="red">*</font>
	            				</label>
	          					<input type="text" class="form-control" name="d_code" id="d_code" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="学科或校区代码，不可重复(只能输入数字)">
	          				</div>
	          				<div class="form-group">
					            <label for="d_name" class="control-label">
									<c:if test="${type == 'course' }">学科名称</c:if>
									<c:if test="${type == 'school' }">校区名称</c:if>
	            					&nbsp;<font color="red">*</font>
								</label>
					            <input type="text" class="form-control" name="d_name" id="d_name" maxlength="50" placeholder="学科或校区名称（50字以内）">
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
		
		<!-- 修改角色模态框 -->
		<div class="modal fade" id="updateDictModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  		<div class="modal-dialog" role="document">
	    		<div class="modal-content">
	      			<div class="modal-header">
	        			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        			<h4 class="modal-title" id="myModalLabel">
	        				<c:if test="${type == 'course' }">修改学科</c:if>
							<c:if test="${type == 'school' }">修改校区</c:if>
	        			</h4>
	      			</div>
	      			<form action="updateDict" method="post" id="dictForm2">
	      				<input type="hidden" name="d_id" id="d_id2"/>
	      				<input type="hidden" name="d_type" id="d_type2" value="${type }"/>
	      				<div class="modal-body">
	          				<div class="form-group">
	            				<label for="d_code" class="control-label">
	            					<c:if test="${type == 'course' }">学科代码</c:if>
									<c:if test="${type == 'school' }">校区代码</c:if>
	            					&nbsp;<font color="red">*</font>
	            				</label>
	          					<input type="text" class="form-control" name="d_code" id="d_code2" maxlength="3" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="学科或校区代码，不可重复(只能输入数字)">
	          				</div>
	          				<div class="form-group">
					            <label for="d_name" class="control-label">
									<c:if test="${type == 'course' }">学科名称</c:if>
									<c:if test="${type == 'school' }">校区名称</c:if>
	            					&nbsp;<font color="red">*</font>
								</label>
					            <input type="text" class="form-control" name="d_name" id="d_name2" maxlength="50" placeholder="学科或校区名称（50字以内）">
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
	<!-- 引入role.js -->
	<script type="text/javascript" src="js/back/dict.js"></script>
</body>
</html>