function AuditUser(){
	this.initBtnEvent();
}

AuditUser.prototype.initBtnEvent = function(){
	$(".submitUpdateBtn").click(function(){
		$("#updateUserForm").ajaxSubmit({
			url:path+"/auditInfo/doUpdateUser",
    		type:'post',
	    	"dataType":"json",
	    	success: function (r) {
	    		if(r.result){
	    			$.gritter.add({
						title: '修改员工信息成功',
						text: '',
						time:'1600',
						class_name: 'gritter-success gritter-light'
					});
	    		}else{
	    			$.gritter.add({
						title: '修改员工信息出错',
						text: r.msg,
						time:'3700',
						class_name: 'gritter-error gritter-light'
					});
	    		}
	    	}
		});
	});
	$(".submitUpdateAndPassBtn").click(function(){
		$("#updateUserForm").ajaxSubmit({
			url:path+"/auditInfo/doUpdateAndPass",
    		type:'post',
	    	"dataType":"json",
	    	success: function (r) {
	    		if(r.result){
	    			location.href = path + '/auditInfo/goAuditUser';
	    		}else{
	    			$.gritter.add({
						title: '操作失败',
						text: r.msg,
						time:'3700',
						class_name: 'gritter-error gritter-light'
					});
	    		}
	    	}
		});
	});
}