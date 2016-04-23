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
	
	//初始化时间控件
	$("#datetimepicker").datetimepicker({
		format: 'yyyy',//自定义日期格式
	    language:  "zh-CN",//语言
	    weekStart: true,//显示日期
	    todayBtn:  true,//显示今天按钮
		autoclose: true,//选择日期后，自动关闭日期框
		todayHighlight: true,
		startView: 4,//开始视图
		minView: 4,//最小视图
		forceParse: 0
	});
	
	//分页
	$(".paging, #queryButton").click(function(){
		
		var condition1 = $("#condition1").val().trim();//审核状态
		var condition2 = $("#condition2").val().trim();//审核人
		var paging = $(this).index();//页码
		if(paging == 0){
			paging = 1;
		}
		window.location.href="pagingQuery?condition1=" + condition1 + 
			"&condition2=" + condition2 + "&paging=" + paging;
		
	});
	
	//导出
	$("#exportButton").click(function(){
		
		var condition1 = $("#condition1").val().trim();//审核状态
		var condition2 = $("#condition2").val().trim();//审核人
		var textCondition1 = $("#condition1 option:eq(" + (parseInt(condition1) + 1) + ")").text().trim();
		if(condition2 == null || condition2 == ""){
			if(confirm("确定导出" + textCondition1 + "考生的基本信息吗？")){
				alert("该功能尚未实现!");
			}
		}else{
			if(confirm("确定导出审核人为" + condition2 + "的" + textCondition1 + "考生的基本信息吗？")){
				alert("该功能尚未实现！");
			}
		}
		
	});
	
});