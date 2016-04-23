$(function(){
	
	//设置th、td
	$("th, td").each(function(){
		
		$(this).css("text-align", "center");//水平居中
		$(this).css("vertical-align", "middle")//垂直居中
		
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
	
	//设置input框的背景色
	$("input[type='text']").each(function(){
		
		$(this).css("border", "0px");//隐藏边框
		$(this).css("background-color", "transparent")
		$(this).width("100%");//长度跟td的长度一致 
		$(this).height("100%");//长度跟td的高度一致 
		
	});
	
	//打印准考证
	$("#print").click(function(){
		
		window.print();
		
	});
	
});