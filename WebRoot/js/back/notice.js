$(function(){
	
	//发送时间改变
	$("#sendTime").change(function(){
		
		var sendTime = $("#sendTime").val().trim();
		window.location.href="getNotice?sendTime=" + sendTime;
		
	});
	
	$("#saveUpdate").click(function(){
		
		var sendTime = $("#sendTime").val().trim();
		var oEditor = CKEDITOR.instances.editor1; 
		var content = oEditor.getData(); 
		window.location.href="updateNotice?sendTime=" + sendTime + "&content=" + content;
		
	});
	
});