$(function(){
	
	//初始化时间控件
	$(".datetimepicker").datetimepicker({
		format: 'yyyy-mm-dd hh:ii:00',//自定义日期格式
	    language:  "zh-CN",//语言
	    weekStart: true,//显示日期
	    todayBtn:  true,//显示今天按钮
		autoclose: true,//选择日期后，自动关闭日期框
		todayHighlight: true,
		startView: 0,//开始视图
		minView: 0,//最小视图
		pickerPosition: "top-left",//方向
		forceParse: 0
	});
	
	//设置th和td的样式
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
	
	//保存的提交验证
	$("#roomForm").submit(function(){

		var name = $("#e_name").val().trim();//考场名称
		var school = $("#e_school_id").val().trim();//所在校区
		var num = $("#e_num").val().trim();//考场人数
		var startTime = $("#e_startTime").val().trim();//开始时间
		var endTime = $("#e_endTime").val().trim();//结束时间
		var course = $("#e_course_id").val().trim();//考试科目
		if(name == null || name == ""){
			alert("请输入考场名称！");
			return false;
		}else if(school == null || school == ""){
			alert("请选择所在校区！");
			return false;
		}else if(num == null || num == ""){
			alert("请输入考场人数！");
			return false;
		}else if(num == 0){
			alert("考场人数不能为0！");
			return false;
		}else if(startTime == null || startTime == ""){
			alert("请选择考试开始时间！");
			return false;
		}else if(endTime == null || endTime == ""){
			alert("请选择考试结束时间！");
			return false;
		}else if(course == null || course == ""){
			alert("请选择考试科目！");
			return false;
		}
		return true;
		
	});
	
	//批量删除
	$("#delRoomButton").click(function(){
		
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
				window.location.href="delRoom?ids=" + ids;
			}
		}
		
	});
	
	//开放考场
	$("#openRoomButton").click(function(){
		
		var len = $("input[name='check']:checked").length;
		if(len == 0){
			alert("请先选中考场！");
			return false;
		}else{
			if(confirm("开放考场后考生可以开始打印准考证！确定要开放这" + len + "个考场吗？")){
				var ids = "";
				$("input[name='check']:checked").each(function(){
					ids += $(this).val() + ",";
				});
				ids = ids.substring(0, ids.length - 1);
				window.location.href="openRoom?ids=" + ids;
			}
		}
		
	});
	
	//修改考场信息
	$("#roomForm2").submit(function(){
		
		var id = $("#e_id2").val().trim();//主键
		var name = $("#e_name2").val().trim();//考场名称
		var school = $("#e_school_id2").val().trim();//所在校区
		var num = $("#e_num2").val().trim();//考场人数
		var startTime = $("#e_startTime2").val().trim();//开始时间
		var endTime = $("#e_endTime2").val().trim();//结束时间
		var course = $("#e_course_id2").val().trim();//考试科目
		if(id == null || id == "" || id <= 0){
			alert("修改失败！未查询到相关信息！");
			return false;
		}else if(name == null || name == ""){
			alert("请输入考场名称！");
			return false;
		}else if(school == null || school == ""){
			alert("请选择所在校区！");
			return false;
		}else if(num == null || num == ""){
			alert("请输入考场人数！");
			return false;
		}else if(num == 0){
			alert("考场人数不能为0！");
			return false;
		}else if(startTime == null || startTime == ""){
			alert("请选择考试开始时间！");
			return false;
		}else if(endTime == null || endTime == ""){
			alert("请选择考试结束时间！");
			return false;
		}else if(course == null || course == ""){
			alert("请选择考试科目！");
			return false;
		}
		return true;
		
	});
	
});