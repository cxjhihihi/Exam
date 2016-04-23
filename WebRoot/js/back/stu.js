$(function(){
	
	//设置td
	$("td").each(function(){
		
		var val = $(this).text();
		if(val == 0){
			$(this).text("");
		}
		
	});
	
	//设置th和th
	$("th, td").each(function(){
		
		$(this).css("text-align", "center");
		$(this).css("vertical-align", "middle");
		
	});
	
	//返回
	$("#backButton").click(function(){
		window.history.back(-1);
	})
	
})