$(function(){
	
	//查询
	$("#queryButton").click(function(){
		
		var condition1 = $("#condition1").val().trim();
		var condition2 = $("#condition2").val().trim();
		window.location.href = "queryAudit?condition1=" + condition1 +
			"&condition2=" + condition2;
		
	});
	
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
	
});