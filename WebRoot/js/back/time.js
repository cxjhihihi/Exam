$(function(){
	
	//初始化时间控件
	$(".datetimepicker").datetimepicker({
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
	
	//提交验证
	$("#timeForm").submit(function(){
		
		var st = $("#startTime").val().trim();//开始时间
		var et = $("#endTime").val().trim();//结束时间
		if(st == null || st == ""){
			alert("请选择开始时间！");
			return false;
		}else if(et == null || et == ""){
			alert("请选择结束时间！");
			return false;
		}else if(st > et){
			alert("时间段错误，请重新选择！");
			return false;
		}
		return true;
		
	});
	
	//保存报名时间
	$("#saveButton").click(function(){
		
		$("#timeForm").attr("action", "saveTime");
		$("#timeForm").submit();
		
	});
	
	//修改报名时间
	$("#updateButton").click(function(){
		
		if(confirm("修改报名时间后，可能存在部分已报名考生不能显示，确定修改吗？")){
			$("#timeForm").attr("action", "updateTime");
			$("#timeForm").submit();
		}
		
	});
	
});