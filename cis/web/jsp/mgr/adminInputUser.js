function InputUser(){
	this.init();
	var defaultBirth = new Date("1970/01/01");
	$('input[name="birthday"]').val(defaultBirth);
}

InputUser.prototype.userId = null;
InputUser.prototype.userInfo = {};
InputUser.prototype.totalPages = 1;

InputUser.prototype.init = function(){
	var _own = this;
	$(".submitBtn").click(function(){
		$("#inputUserForm").ajaxSubmit({
			url:path+"/admin/doInputUser",
    		type:'post',
	    	"dataType":"json",
	    	success: function (r) {
	    		if(r.result){
	    			$.gritter.add({
						title: '新增员工信息成功',
						text: '',
						time:'1600',
						class_name: 'gritter-success gritter-light'
					});
	    		}else{
	    			$.gritter.add({
						title: '新增员工信息出错',
						text: r.msg,
						time:'3700',
						class_name: 'gritter-error gritter-light'
					});
	    		}
	    	}
		});
	});
};

