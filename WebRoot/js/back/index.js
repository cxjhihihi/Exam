$(function(){
	
	var clickNum = 0;//导航栏的隐藏显示
	var dictClickCount = 0;//字典下拉菜单的下拉收起
	var stuClickCount = 0;//招生下拉菜单的下拉收起
	
	//自适应iframe高度
	$("#mainIframe, #mainIframe2").load(function(){
		
		var height = $("body").height() - 50;
		//var thisheight = $(this).contents().find("body").height() + 20;
		$(this).height(height);
		
	});
	
	//iframe宽度自适应侧边栏的隐藏和显示
	$("[data-toggle='offcanvas']").click(function(){
		
		clickNum++;
		if(clickNum % 2 == 0){//显示侧边栏
			$("#mainIframe").css("margin-left", "0px");
			$("#mainIframe").width($("#mainIframe").width() - 220);
			
			$("#mainIframe2").css("margin-left", "0px");
			$("#mainIframe2").width($("#mainIframe2").width() - 220);
		}else{//隐藏侧边栏
			$("#mainIframe").css("margin-left", "-220px");
			$("#mainIframe").width($("#mainIframe").width() + 220);
			
			$("#mainIframe2").css("margin-left", "-220px");
			$("#mainIframe2").width($("#mainIframe2").width() + 220);
		}
		
	});
	
	//设置点击的导航处于活动状态
	$(".nav").click(function(){
		
		$(".nav").each(function(){
			$(this).removeClass("active");
		});
		$(this).addClass("active");
		
	});
	
	//字典下拉框的下拉收起
	$("#dictManager").click(function(){
		
		dictClickCount++;
		if(dictClickCount % 2 == 0){//合并
			$("#dict2").removeClass("glyphicon glyphicon-chevron-up");
			$("#dict2").addClass("glyphicon glyphicon-chevron-down");
		}else{//下拉
			$("#dict2").removeClass("glyphicon glyphicon-chevron-down");
			$("#dict2").addClass("glyphicon glyphicon-chevron-up");
		}
		
	});
	
	//招生下拉框的下拉收起
	$("#stuManager").click(function(){
		
		stuClickCount++;
		if(stuClickCount % 2 == 0){//合并
			$("#stu2").removeClass("glyphicon glyphicon-chevron-up");
			$("#stu2").addClass("glyphicon glyphicon-chevron-down");
		}else{//下拉
			$("#stu2").removeClass("glyphicon glyphicon-chevron-down");
			$("#stu2").addClass("glyphicon glyphicon-chevron-up");
		}
		
	});
	
	//审核下拉框的下拉收起
	$("#auditManager").click(function(){
		
		stuClickCount++;
		if(stuClickCount % 2 == 0){//合并
			$("#audit").removeClass("glyphicon glyphicon-chevron-up");
			$("#audit").addClass("glyphicon glyphicon-chevron-down");
		}else{//下拉
			$("#audit").removeClass("glyphicon glyphicon-chevron-down");
			$("#audit").addClass("glyphicon glyphicon-chevron-up");
		}
		
	});
	
	//设置未读消息的颜色
	var num = $("#news").text();
	if(num < 5){
		$("#newsNum").attr("class", "label label-success");
	}
	if(num >= 5 && num < 10){
		$("#newsNum").attr("class", "label label-warning");
	}
	if(num >= 10){
		$("#newsNum").attr("class", "label label-danger");
	}
	
	//取消未读消息
	$("#news").click(function(){
		
		$("#newsNum").text("");
		
	});
	
});
