//information.jsp所需的jQuery
$(function(){
	
	//设置th
	$("th").each(function(){
		
		$(this).css("text-align", "center");//水平居中
		$(this).css("vertical-align", "middle");//垂直居中
		
	});
	
	//设置select
	$("select").each(function(){
		
		$(this).css("border", "0px");//隐藏边框
		$(this).css("background-color", "transparent")
		$(this).width("100%");//长度跟td的长度一致 
		$(this).height("100%");//长度跟td的高度一致 
		
	});
	
	//设置input框
	$("input").each(function(){
		
		var val = $(this).val();//获取所有input框的值
		if(val == 0){//因为成绩、人数等在数据库是int或double类型，默认值为0
			$(this).val("");//若input值为0，则将值设为空
		}
		$(this).css("border", "0px");//隐藏边框
		$(this).css("background-color", "transparent")
		$(this).width("100%");//长度跟td的长度一致 
		$(this).height("100%");//长度跟td的高度一致 
		
	});
	
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
	
	//初始化工具提示
	$(function () {
		
		$('[data-toggle="tooltip"]').tooltip();
		
	});
	
	//验证身份证的合法性并自动获取出生年月和性别
	$("#idCard").blur(function(){
		
		var idCard = $("#idCard").val();
		var result = IdCardValidate(idCard);
		if(!result){//不合法
			alert("该身份证号不合法！请重新输入！");
			$("#idCard").val("");
			return false;
		}else{//合法
			//获取性别
			var sex = maleOrFemalByIdCard(idCard);
			$("#sex").val(sex);
			//获取出生年月
			var birth = idCard.substring(6, 14);
			birth = birth.substring(0, 4) + "-" + birth.substring(4, 6) + "-" + birth.substring(6);
			$("#birth").val(birth);
			return true;
		}
		
	});
	
	//form表单提交验证
	$("#infoForm").submit(function(){
		
		var name = $("#name").val().trim();//姓名
		var idCard = $("#idCard").val().trim();//身份证号
		//var phone_1 = $("#phone_1").val().trim();//联系电话
		var householdAddress = $("#householdAddress").val().trim();//户籍所在地
		
		if(name == null || name == ""){
			alert("学生姓名不能为空！");
			return false;
		}else if(idCard == null || idCard == ""){
			alert("学生身份证号不能为空！");
			return false;
		}/*else if(phone_1 == null || phone_1 == ""){
			alert("学生联系电话不能为空！");
			return false;
		}*/else if(householdAddress == null || householdAddress == ""){
			alert("学生户籍所在地不能为空！");
			return false;
		}else if(householdAddress.indexOf("新乡") < 0){
			alert("本校只招收户籍在新乡的学生！");
			return false;
		}
		return true;
		
	});
	
	//保存报名信息
	$("#saveButton1, #saveButton2").click(function(){
		
		var id = $(this).attr("id");
		if(id == "saveButton1"){
			if(confirm("报名尚未开始，保存后不会提交到学校，确认保存？")){
				//设置提交的action
				$("#infoForm").attr("action", "saveInfo");
				//提交表单
				$("#infoForm").submit();
			}
		}else if(id == "saveButton2"){
			if(confirm("保存后信息会提交到学校，确认保存？")){
				//设置提交的action
				$("#infoForm").attr("action", "saveInfo");
				//提交表单
				$("#infoForm").submit();
			}
		}
		
	});
	
	//修改报名信息或者重新报名
	$("#updateButton1, #updateButton2, #againSaveButton").click(function(){
		
		var id = $(this).attr("id");
		if(id == "updateButton1"){
			if(confirm("保存修改后，信息会同步提交到学校，确定保存修改？")){
				//设置提交的action
				$("#infoForm").attr("action", "updateInfo");
				//提交表单
				$("#infoForm").submit();
			}
		}else if(id == "updateButton2"){
			if(confirm("报名尚未开始，保存后修改信息不会同步到学校，确定保存修改？")){
				//设置提交的action
				$("#infoForm").attr("action", "updateInfo");
				//提交表单
				$("#infoForm").submit();
			}
		}else if(id == "againSaveButton"){
			if(confirm("确认重新提交报名信息？")){
				//设置提交的action
				$("#infoForm").attr("action", "updateInfo");
				//提交表单
				$("#infoForm").submit();
			}
		}
		
		
	});
	
	//取消报名
	$("#cancelButton").click(function(){
		
		if(confirm("确认取消报名？")){
			//设置提交的action
			$("#infoForm").attr("action", "cancelInfo");
			//提交表单
			$("#infoForm").submit();
		}
		
	});
	
});
