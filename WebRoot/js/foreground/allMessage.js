$(function(){
	
	$("#readStatus").change(function(){
		
		var status = $("#readStatus").val().trim();
		window.location.href = "queryMessage?status=" + status;
		
	});
	
});