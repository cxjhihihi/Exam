<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网上报名系统-角色管理</title>

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
			       角色管理
			        <small>角色列表</small>
			    </h1>
			    <ol class="breadcrumb">
			        <li><a href="javascript:void(0);" target="_self"><i class="glyphicon glyphicon-home"></i>首页</a></li>
			        <li class="active">角色列表 </li>
			    </ol>
			</section>
			<section class="content">
				<div class="btn-group btn-group-lg" role="group" style="float: right;">
					<input type="button" id="delCheck" class="btn btn-default" value="删除选中"/>
					<input type="button" class="btn btn-default" data-toggle="modal" data-target="#addRoleModal" value="增加角色"/>
				</div>
		        <table id="stu_table" class="table table-bordered table-striped">
					<thead>
						<tr>
							<th width="5%"><input type="checkbox" name="checkAll" id="checkAll"/></th>
							<th width="5%">编号</th>
							<th width="20%">账号</th>
							<th width="20%">所属人</th>
							<th width="20%">角色</th>
							<th width="30%">操作</th>
						</tr>
					</thead>
					<tbody>	
						<c:forEach var="bean" items="${list }" varStatus="status">
							<tr>
								<td><input type="checkbox" name="check" value="${bean.r_id }"/></td>
								<td>${status.index + 1 }</td>
								<td>${bean.r_account }</td>
								<td>${bean.r_name }</td>
								<td>
									<c:if test="${bean.r_role == 0 }">管理员</c:if>
									<c:if test="${bean.r_role == 1 }">审核人</c:if>
									<c:if test="${bean.r_role == 2 }">阅卷人</c:if>
									<c:if test="${bean.r_role == 3 }">校区管理员</c:if>
								</td>
								<td>
									<a href="javascript:void(0);" onclick="delRole('${bean.r_id}')" title="删除">
										<span class="glyphicon glyphicon-remove"></span>删除
									</a>&nbsp;&nbsp;
									<c:if test="${bean.r_role != 0 }">
										<a href="javascript:void(0);" data-toggle="modal" data-target="#updateRoleModal"
											onclick="updateRole1('${bean.r_id}')"  title="修改">
											<span class="glyphicon glyphicon-pencil"></span>修改
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
		
			//删除角色
			function delRole(id){
	
				if(confirm("确认删除该账号吗？")){
					window.location.href="delRole?ids=" + id;
				}
				
			}
	
			//根据id查询信息并赋值
			function updateRole1(id){
				
				$.ajax({
					
					type: "POST",
					url: "getRoleById",
					data: {
						id: id
					},
					dataType: "json",
					success: function(data){
						document.getElementById("r_id2").value = data.r_id;
						document.getElementById("r_role2").value = data.r_role;
						document.getElementById("r_account2").value = data.r_account;
						document.getElementById("r_pwd2").value = data.r_pwd;
						document.getElementById("r_name2").value = data.r_name;
					},
					error: function(data){
						alert("出错了！");
					}
					
				}); 
				
			}
				
		</script>
		
		<!-- 增加角色模态框 -->
		<div class="modal fade" id="addRoleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
	  		<div class="modal-dialog" role="document">
	    		<div class="modal-content">
	      			<div class="modal-header">
	        			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        			<h4 class="modal-title" id="myModalLabel">增加角色</h4>
	      			</div>
	      			<form action="saveRole" method="post" id="roleForm">
	      				<div class="modal-body">
	          				<div class="form-group">
	            				<label for="r_role" class="control-label">角色:&nbsp;<font color="red">*</font></label>
	          					<select class="form-control" name="r_role" id="r_role">
	          						<option value="1">审核人</option>
	          					</select>
	          				</div>
	          				<div class="form-group">
					            <label for="r_account" class="control-label">账号:&nbsp;<font color="red">*</font></label>
					            <input type="text" class="form-control" name="r_account" id="r_account" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="账号(只能输入数字)" autocomplete="off">
	         	 			</div>
	         	 			<div class="form-group">
					            <label for="r_pwd" class="control-label">密码:&nbsp;<font color="red">*</font></label>
					            <input type="text" class="form-control" name="r_pwd" id="r_pwd" maxlength="20" placeholder="密码(初始密码跟账号相同)" readonly="readonly">
	         	 			</div>
	         	 			<div class="form-group">
					            <label for="r_name" class="control-label">所属人:&nbsp;<font color="red">*</font></label>
					            <input type="text" class="form-control" name="r_name" id="r_name" maxlength="10" placeholder="所属人姓名">
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
	
	<!-- 修改角色模态框 -->
	<div class="modal fade" id="updateRoleModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  		<div class="modal-dialog" role="document">
    		<div class="modal-content">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        			<h4 class="modal-title" id="myModalLabel">修改角色</h4>
      			</div>
      			<form action="updateRole" method="post" id="roleForm2">
      				<input type="hidden" name="r_id" id="r_id2"/>
      				<div class="modal-body">
          				<div class="form-group">
            				<label for="r_role" class="control-label">角色:&nbsp;<font color="red">*</font></label>
          					<select class="form-control" name="r_role" id="r_role2">
          						<option value="1">审核人</option>
          					</select>
          				</div>
          				<div class="form-group">
				            <label for="r_account" class="control-label">账号:&nbsp;<font color="red">*</font></label>
				            <input type="text" class="form-control" name="r_account" id="r_account2" maxlength="20" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="账号(只能输入数字)">
         	 			</div>
         	 			<div class="form-group">
				            <label for="r_pwd" class="control-label">密码:&nbsp;<font color="red">*</font></label>
				            <input type="text" class="form-control" name="r_pwd" id="r_pwd2" maxlength="20" placeholder="密码(初始密码跟账号相同)" readonly="readonly">
         	 			</div>
         	 			<div class="form-group">
				            <label for="r_name" class="control-label">所属人:&nbsp;<font color="red">*</font></label>
				            <input type="text" class="form-control" name="r_name" id="r_name2" maxlength="10" placeholder="所属人姓名">
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
	
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script type="text/javascript" src="js/external/jquery-2.1.4.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script type="text/javascript" src="bootstrap/bootstrap.min.js"></script>
	<!-- 引入role.js -->
	<script type="text/javascript" src="js/back/role.js"></script>
</body>
</html>