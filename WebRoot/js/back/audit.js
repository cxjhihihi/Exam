function updateAuditStatus(status, id){
	
	if(status == 1){
		if(confirm("确定使该学生不通过审核吗？")){
			window.location.href="updateAuditStatus?status=1&id=" + id;
		}
	}else if(status == 2){
		if(confirm("确定使该学生通过审核吗？")){
			window.location.href="updateAuditStatus?status=2&id=" + id;
		}
	}else{
		alert("操作有误！");
	}
	
}
