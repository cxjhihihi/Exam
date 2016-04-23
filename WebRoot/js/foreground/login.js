//login.jsp页面所需的jQuery
$(function(){
	
	//初始化时间控件
	$("#datetimepicker").datetimepicker({
		format: 'yyyy-mm-dd',//自定义日期格式
	    language:  "zh-CN",//语言
	    weekStart: true,//显示日期
	    todayBtn:  true,//显示今天按钮
		autoclose: true,//选择日期后，自动关闭日期框
		todayHighlight: true,
		startView: 2,//开始视图
		minView: 2,//最小视图
		forceParse: 0
	});
	
	//登录身份证合法性验证
	$("#userName").blur(function(){
		
		var userName = $("#userName").val().trim();
		var result = IdCardValidate(userName);
		if(!result){//不合法
			alert("该身份证号不合法！请重新输入！");
			$("#userName").val("");
			return false;
		}
		return true;
		
	});
	
	//用户登录表单提交验证
	$("#loginForm").submit(function(){

		var userName = $("#userName").val().trim();
		var userPwd = $("#userPwd").val().trim();
		var yzm = $("#safeCode").val().trim();
		if(userName == null || userName == ""){
			alert("请输入身份证号！");
			return false;
		}else if(userPwd == null || userPwd == ""){
			alert("请输入登录密码！");
			return false;
		}else if(yzm == null || yzm == ""){
			alert("请输入验证码！");
			return false;
		}
		return true;
		
	});
	
	//注册身份证合法性验证
	$("#id").blur(function(){
		
		var id = $("#id").val().trim();
		var result = IdCardValidate(id);
		if(!result){//不合法
			alert("该身份证号不合法！请重新输入！");
			$("#id").val("");
			return false;
		}else{
			//获取性别
			var sex = maleOrFemalByIdCard(id);
			$("#sex").val(sex);
			//获取出生年月
			var birth = id.substring(6, 14);
			birth = birth.substring(0, 4) + "-" + birth.substring(4, 6) + "-" + birth.substring(6);
			$("#birth").val(birth);
			return true;
		}
		
	});
	
	//注册表单提交验证
	$("#registerForm").submit(function(){
		
		var id = $("#id").val().trim();//身份证
		var name = $("#name").val().trim();//姓名
		var pwd = $("#pwd").val().trim();//密码
		var confirmPwd = $("#confirmPwd").val().trim();//登录密码
		if(id == null || id == ""){
			alert("身份证号不能为空！");
			return false;
		}else if(name == null || name == ""){
			alert("姓名不能为空！");
			return false;
		}else if(pwd == null || pwd == ""){
			alert("登录密码不能为空！");
			return false;
		}else if(confirmPwd == null || confirmPwd == ""){
			alert("确认密码不能为空！");
			return false;
		}else if(pwd != confirmPwd){
			alert("两次输入密码不一致！请重新输入！");
			$("#pwd").val("");
			$("#confirmPwd").val("");
			return false;
		}
		return true;
		
	});
	
	//验证码刷新
	$("#yzm").click(function(){
		
		var time = new Date().getTime();
		$(this).attr("src", "safeCode?t=" + time);
		
	});
	
});
