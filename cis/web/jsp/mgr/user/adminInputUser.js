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
			url:path+"/userMgr/doInputUser",
    		type:'post',
	    	"dataType":"json",
	    	success: function (r) {
	    		if(r.result){
	    			location.href = path + '/userMgr/goInputSuccess/' + r.user.userId;
	    			/*$.gritter.add({
						title: '新增员工信息成功',
						text: '',
						time:'1600',
						class_name: 'gritter-success gritter-light'
					});*/
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
	//修改等级事件
	$('#form-field-level').on('change',function(){
		var lvl = $(this).val();
		var dtime = (new Date()).getTime();
		var data = {'level':lvl, 'dtime':dtime};
		$('.chosen-select').html('');
		$('.chosen-select').chosen('destroy');
		$.get(path + '/userMgr/getListorUpSelect', data, function(r){
			for(var i = 0 ; i < r.length; i++){
				var option = '<option value="'+ r[i].userId +'">'+
								r[i].name + '(' + r[i].level +')'+
							 '</option>';
				$(option).appendTo('.chosen-select');
			}
			$('.chosen-select').chosen({no_results_text: "未找到匹配项"});
		}, 'json');
	});
};

