<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
			       通知管理
			        <small>通知列表</small>
			    </h1>
			    <ol class="breadcrumb">
			        <li><a href="javascript:void(0);" target="_self"><i class="glyphicon glyphicon-home"></i>首页</a></li>
			        <li class="active">通知列表</li>
			    </ol>
			</section>
			<section class="content">
				<div class="input-group">
					<span class="input-group-addon" id="basic-addon1">发送时间</span>
					<select class="form-control" placeholder="发送时间" aria-describedby="basic-addon1" id="sendTime">
						<c:if test="${sendTime == 2 or empty sendTime}">
							<option value="2" selected="selected">审核通过时发送</option>
						</c:if>
						<c:if test="${sendTime != 2 and not empty sendTime}">
							<option value="2">审核通过时发送</option>
						</c:if>
						<c:if test="${sendTime == 1 }">
							<option value="1" selected="selected">审核未通过时发送</option>
						</c:if>
						<c:if test="${sendTime != 1 }">
							<option value="1">审核未通过时发送</option>
						</c:if>
					</select>
				</div>
				<div class='row'>
	                <div class='col-md-12'>
	                    <div class='box box-info'>
	                        <div class='box-header'>
	                            <h3 class='box-title'>CK Editor <small>Advanced and full of features</small></h3>
	                       		<!-- tools box -->
                                <div class="pull-right box-tools">
                                    <button class="btn btn-primary" id="saveUpdate" title="保存修改">保存修改</button>
                                </div>
	                        </div><!-- /.box-header -->
	                        <div class='box-body pad'>
	                            <form id="editor">
	                                <textarea id="editor1" name="editor1" rows="10" cols="80">
	                                	${mesMode.message }
	                                </textarea>                        
	                            </form>
	                        </div>
	                    </div><!-- /.box -->
            		</div>
				</div>
			</section>
		</aside>
	</div>
	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script type="text/javascript" src="js/external/jquery-2.1.4.min.js"></script>
	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script type="text/javascript" src="bootstrap/bootstrap.min.js"></script>
	<!-- 引入ckeditor -->
	<script type="text/javascript" src="js/ckeditor/ckeditor.js"></script>
	<!-- 引入notice.js -->
	<script type="text/javascript" src="js/back/notice.js"></script>
	<script type="text/javascript">
        $(function() {
            CKEDITOR.replace('editor1');
            $(".textarea").wysihtml5();
        });
    </script>
</body>
</html>