$(function(){
	
	//用户登录表单提交验证
	$("#adminLoginForm").submit(function(){

		var userName = $("#userName").val().trim();
		var userPwd = $("#userPwd").val().trim();
		var yzm = $("#safeCode").val().trim();
		if(userName == null || userName == ""){
			alert("请输入账号！");
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
	
	//验证码刷新
	$("#yzm").click(function(){
		
		var time = new Date().getTime();
		$(this).attr("src", "safeCode?t=" + time);
		
	});
	
});