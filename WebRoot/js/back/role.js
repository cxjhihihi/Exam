$(function(){
	
	//设置th,td
	$("th, td").each(function(){
		
		$(this).css("text-align", "center");//水平居中
		$(this).css("vertical-align", "middle");//垂直居中
		
	});
	
	//全选和取消全选
	$("input[name='checkAll']").click(function(){
		if($("input[name='checkAll']").is(":checked") == true){//全选
			$("input[name='check']").each(function(){
				this.checked = true;
			});
		}else{//取消全选
			$("input[name='check']").removeAttr("checked");
		}
	});
	
	$("input[name='check']").click(function(){
		var len = $("input[name='check']").length;
		var checklen = $("input[name='check']:checked").length;
		if(checklen < len){//没有被选中的
			$("input[name='checkAll']").removeAttr("checked");
		}else if(checklen == len){//全部都被选中
			$("input[name='checkAll']").each(function(){
				this.checked = true;
			});
		}
	});
	
	//批量删除
	$("#delCheck").click(function(){
		
		var len = $("input[name='check']:checked").length;
		if(len == 0){
			alert("请选择需要删除的记录！");
			return false;
		}else{
			if(confirm("确定要删除这" + len + "条记录吗？")){
				var ids = "";
				$("input[name='check']:checked").each(function(){
					ids += $(this).val() + ",";
				});
				ids = ids.substring(0, ids.length - 1);
				window.location.href="delRole?ids=" + ids;
			}
		}
		
	});
	
	//设置初始密码
	$("#r_account").keyup(function(){
		
		var val = $(this).val();
		$("#r_pwd").val(val);
		
	});
	
	//表单提交验证
	$("#roleForm").submit(function(){
		
		var role = $("#r_role").val().trim();//角色
		var account = $("#r_account").val().trim();//账号
		var pwd =$("#r_pwd").val().trim();//密码
		var name = $("#r_name").val().trim();//所属人
		if(role == null || role == ""){
			alert("请选择角色！");
			return false;
		}else if(account == null || account == ""){
			alert("请输入账号！");
			return false;
		}else if(pwd == null || pwd == ""){
			alert("请输入密码！");
			return false;
		}else if(name == null || name == ""){
			alert("请填写所属人！");
			return false;
		}
		return true;
			
	});
	
	//设置初始密码
	$("#r_account2").keyup(function(){
		
		var val = $(this).val();
		$("#r_pwd2").val(val);
		
	});
	
	//表单提交验证
	$("#roleForm2").submit(function(){
		
		var id = $("#r_id2").val().trim();//主键
		var role = $("#r_role2").val().trim();//角色
		var account = $("#r_account2").val().trim();//账号
		var pwd =$("#r_pwd2").val().trim();//密码
		var name = $("#r_name2").val().trim();//所属人
		if(id == null && id == ""){
			alert("保存失败！未查询到修改对象！");
			return false;
		}else if(role == null || role == ""){
			alert("请选择角色！");
			return false;
		}else if(role != 1){
			alert("该角色不能被修改！");
			return false;
		}else if(account == null || account == ""){
			alert("请输入账号！");
			return false;
		}else if(pwd == null || pwd == ""){
			alert("请输入密码！");
			return false;
		}else if(name == null || name == ""){
			alert("请填写所属人！");
			return false;
		}
		return true;
			
	});
	
});
