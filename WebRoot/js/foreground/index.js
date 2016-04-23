////index.jsp页面所需的jQuery
$(function(){
	
	var clickNum = 0;
	
	//自适应iframe高度
	$("#mainIframe").load(function(){
		
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
		}else{//隐藏侧边栏
			$("#mainIframe").css("margin-left", "-220px");
			$("#mainIframe").width($("#mainIframe").width() + 220);
		}
		
	});
	
	//设置点击的导航处于活动状态
	$(".nav").click(function(){
		
		$(".nav").each(function(){
			$(this).removeClass("active");
		});
		$(this).addClass("active");
		
	});
	
	//将未读消息设置为已读消息
	$("#message").click(function(){
		
		$("#mesNum").text("");
		$.ajax({
			
			type: "POST",
			url: "changeReaded",
			dataType: "json",
			success: function(data){
				
			},
			error: function(data){
				alert("出错了！！！");
			}
		});
		
	})
	
});
