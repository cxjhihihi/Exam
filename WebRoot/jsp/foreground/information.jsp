<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网上报名系统-报名信息</title>

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" type="text/css" href="bootstrap/bootstrap.min.css">
<!-- 引入模板的css文件 -->
<link rel="stylesheet" type="text/css" href="css/template/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="css/template/ionicons.min.css">
<link rel="stylesheet" type="text/css" href="css/template/AdminLTE.css">
<!-- 引入information.css文件 -->
<link rel="stylesheet" type="text/css" href="css/foreground/information.css">
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
		
	};
	
</script>

</head>
<body onload="message()">
	<div class="container-fluid">
		<aside class="right-side">                
			<section class="content-header">
			    <h1>
			        报名信息
			        <small>控制面板</small>
			    </h1>
			    <ol class="breadcrumb">
			        <li><a href="javascript:void(0);" target="_self"><i class="glyphicon glyphicon-home"></i>首页</a></li>
			        <li class="active">报名信息</li>
			    </ol>
			</section>
			<form action="javascript:void(0);" method="post" id="infoForm" enctype="multipart/form-data">
				<section class="content">
					<table id="info_table" class="table table-bordered table-striped row">
				  		<tr>
				  			<th>姓名&nbsp;<font color="red">*</font></th>
				  			<td>
				  				<input type="text" name="name" id="name" value="${info.name }" maxlength="10"/>
				  			</td>
				  			<th>性别</th>
				  			<td>
				  				<select name="sex" id="sex" class="sex">
				  					<c:if test="${empty info }">
					  					<c:if test="${sessionScope.user.sex == '男' }">
					  						<option selected="selected">男</option>
					  						<option>女</option>
					  					</c:if>
					  					<c:if test="${sessionScope.user.sex == '女' }">
					  						<option>男</option>
					  						<option selected="selected">女</option>
					  					</c:if>
					  				</c:if>
					  				<c:if test="${not empty info }">
					  					<c:if test="${info.sex == '男' }">
					  						<option selected="selected">男</option>
					  						<option>女</option>
					  					</c:if>
					  					<c:if test="${info.sex == '女' }">
					  						<option>男</option>
					  						<option selected="selected">女</option>
					  					</c:if>
					  				</c:if>
				  				</select>
				  			</td>
				  			<th colspan="2">出生年月</th>
				  			<td colspan="2">
								<div class="input-group date" id="datetimepicker" data-date="dd-mm-yyyy" data-date-format="dd-mm-yyyy">
		                    		<input type="text" class="form-control" value=
									    	"<c:if test='${empty info }'>${sessionScope.user.birth }</c:if><c:if test='${not empty info }'>${info.birth }</c:if>" 
									    	name="birth" id="birth" readonly>
		                    		<span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
									<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
		                		</div>
				  			</td>
				  			<th colspan="2">照片</th>
				  		</tr>
				  		<tr>
				  			<th>身份证号&nbsp;<font color="red">*</font></th>
				  			<td colspan="3">
				  				<input type="text" name="idCard" id="idCard" value=
				  					"<c:if test='${empty info }'>${sessionScope.user.id }</c:if><c:if test='${not empty info }'>${info.idCard }</c:if>" maxlength="18"/>
				  			</td>
				  			<th colspan="2">联系电话</th>
				  			<td colspan="2">
				  				<input type="text" name="phone_1" id="phone_1" value="${info.phone_1 }" maxlength="25"/>
				  			</td>
				  			<td rowspan="4" colspan="2">
				  				<input type="file" name="picture" id="picture" data-toggle="tooltip" data-placement="top" 
				  					title="每次修改都需重新上传照片">
				  				<div id="result_picture">
				  					<c:if test="${not empty info.picture }">
				  						<img alt="${info.picture }" src="upload/picture/${info.picture }"
				  							style="width: 100%; height: 100%;">
				  					</c:if>
				  				</div>
		  						<script type="text/javascript">

				  					//图片预览
					  				var file_input = document.getElementById("picture");
					  				var file_view = document.getElementById("result_picture");
					  				if(typeof FileReader == "undefined"){
					  					file_view.innerHTML = "抱歉，您的浏览器不支持预览功能！";
					  					file_input.setAttribute("disabled", "disabled");
					  				}else{
					  					file_input.addEventListener("change", readFile, false);
					  				}
		
					  				function readFile(){
					  					var file = this.files[0];
					  					if(!/image\/\w+/.test(file.type)){
					  						alert("文件必须为图片！");
					  						file_input.value = "";
					  						file_view.innerHTML = "";
					  						return false;
					  					}
					  					var reader = new FileReader();
					  					reader.readAsDataURL(file);
					  					reader.onload = function(e){
					  						file_view.innerHTML = "<img src='" + this.result + "' alt='' width='100%' height='100%'/>"
					  					}
					  					
					  				} 
				  				</script>
				  			</td>
				  		</tr>
				  		<tr>
				  			<th>学校所在地</th>
				  			<td colspan="3">
				  				<input type="text" name="schoolAddress" id="schoolAddress" value="${info.schoolAddress }" maxlength="125"/>
				  			</td>
				  			<th>毕业学校</th>
				  			<td colspan="3">
				  				<input type="text" name="school" id="school" value="${info.school }" maxlength="50"/>
				  			</td>
				  		</tr>
				  		<tr>
				  			<th>户籍所在地&nbsp;<font color="red">*</font></th>
				  			<td colspan="3">
				  				<input type="text" name="householdAddress" id="householdAddress" value="${info.householdAddress }" maxlength="125"/>
				  			</td>
				  			<th colspan="2">班级</th>
				  			<td colspan="2">
				  				<input type="text" name="sclass" id="sclass" value="${info.sclass }" maxlength="25"/>
				  			</td>
				  		</tr>
				  		<tr>
				  			<th>家庭住址</th>
				  			<td colspan="3">
				  				<input type="text" name="homeAddress" id="homeAddress" value="${info.homeAddress }" maxlength="125"/>
				  			</td>
				  			<th colspan="2">电话</th>
				  			<td colspan="2">
				  				<input type="text" name="phone_2" id="phone_2" value="${info.phone_2 }" maxlength="25"/>
				  			</td>
				  		</tr>
					</table>
					<font color="green">注意：带<font color="red">*</font>字符的为必填项</font>
					<div align="center">
						<div class="btn-group btn-group-lg" role="group">
							<!-- 报名结束 -->
							<c:if test="${(time.endTime).substring(0, 10) lt now }">
								<button type="button" class="btn btn-primary" data-toggle="tooltip" data-placement="top" title="报名已结束">报名已结束</button>
							</c:if>
							<!-- 报名未开始 -->
							<c:if test="${empty info && (time.startTime).substring(0, 10) gt now }">
								<button type="button" id="saveButton1" class="btn btn-primary" data-toggle="tooltip" data-placement="top" title="点击后保存该信息但是不提交">保存</button>
							</c:if>
							<!-- 报名时间内 -->
							<c:if test="${(empty info || info.enter == 0) && (time.startTime).substring(0, 10) le now && (time.endTime).substring(0, 10) ge now }">
								<button type="button" id="saveButton2" class="btn btn-primary" data-toggle="tooltip" data-placement="top" title="点击后报名信息提交到学校">报名</button>
							</c:if>
							<!-- 报名时间内 -->
							<c:if test="${not empty info && info.enter == 1 && (time.startTime).substring(0, 10) le now && (time.endTime).substring(0, 10) ge now }">
								<button type="button" id="updateButton1" class="btn btn-primary" data-toggle="tooltip" data-placement="top" title="点击后保存修改并提交">保存修改</button>
								<button type="button" id="cancelButton" class="btn btn-primary" data-toggle="tooltip" data-placement="top" title="点击后从学校取消报名">取消报名</button>
							</c:if>
							<!-- 报名未开始 -->
							<c:if test="${not empty info && info.enter == 0 && (time.startTime).substring(0, 10) gt now }">
								<button type="button" id="updateButton2" class="btn btn-primary" data-toggle="tooltip" data-placement="top" title="点击后保存修改但是不提交">保存修改</button>
							</c:if>
							<c:if test="${not empty info && info.enter == 2 && (time.startTime).substring(0, 10) le now && (time.endTime).substring(0, 10) ge now }">
								<button type="button" id="againSaveButton" class="btn btn-primary" data-toggle="tooltip" data-placement="top" title="点击后报名信息再次提交到学校">重新报名</button>
							</c:if>
						</div>
					</div>
				</section>
			</form>
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
	<!-- 引入身份证合法性验证的js -->
	<script type="text/javascript" src="js/external/verificationID.js"></script>
	<!-- 获取informatino.jsp所需jsp文件 -->
	<script type="text/javascript" src="js/foreground/information.js"></script>
</body>
</html>