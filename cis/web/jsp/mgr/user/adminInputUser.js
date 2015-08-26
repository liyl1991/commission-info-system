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
	    			location.href = path + '/userMgr/goInputSuccess/a/' + r.user.userId;
	    		}else{
	    			if(r.deletedUser){
	    				for( n in r.deletedUser){
	    					$('#existDeletedUserDlg table td[prop-name="' + n +'"]').text(r.deletedUser[n]);
	    					if('uplineUser' == n && r.deletedUpline){
	    						$('#existDeletedUserDlg table td[prop-name="upline"]').text(r.deletedUpline.name + '(' + r.deletedUpline.level + '级)');
	    					}
	    				}
	    				$('#existDeletedUserDlg').data('userId', r.deletedUser.userId);
	    				$('#existDeletedUserDlg').modal({'backdrop' : 'static'});
	    			}
	    			else if(r.msg){
		    			$.gritter.add({
							title: '新增员工信息出错',
							text: r.msg,
							time:'3700',
							class_name: 'gritter-error gritter-light'
						});
	    			}
	    		}
	    	}
		});
	});
	
	//恢复删除的用户
	$('.recoverUserBtn').on('click', function(){
		$.ajax({
			url: path+"/userMgr/doRecoverUser",
    		type: 'post',
	    	dataType: "json",
	    	data: {userId : $('#existDeletedUserDlg').data('userId')},
	    	success: function (r) {
	    		if(r.result){
	    			location.href = path + '/userMgr/goInputSuccess/r/' + r.user.userId;
	    		} else {
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

