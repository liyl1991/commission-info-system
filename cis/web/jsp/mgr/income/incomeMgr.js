var baseProportion;
$(function(){
	setSelectData();
	$('#inputIncomeBtn').on('click',function(){
		$('#inputIncomeDialog').modal();
		$('#form-field-income').val('');
		$(".chosen-select").chosen({no_results_text: "未找到匹配项"});
		$(".chosen-select").chosen().change(reloadUserUplines);
		reloadUserUplines();
	});
	$('#form-field-income').on('blur',function(){
		var val = $(this).val();
		if(isNaN(val)){
			$.gritter.add({
				title: '输入错误',
				text: '请输入正确的金额',
				time:'5700',
				class_name: 'gritter-error gritter-light'
			});
			$(this).val('')[0].focus();
			return;
		}
		var currentRule = $('.upline-users').data('currentRule');
		var baseIncome = FloatMul(val, baseProportion);
		var otherProportion = baseProportion;
		$('.upline-users .form-group').each(function(){
			var _user = $(this).data('user');
			if( 'BX' != currentRule.detailContent){
				if( 'B' != _user.level ){
					$(this).find('input:text').val(FloatMul(val, _user.incomeSetting.proportion));
					otherProportion = FloatSub(otherProportion, _user.incomeSetting.proportion);
				} else {
					$(this).find('input:text').val(FloatMul(val, otherProportion));
				}
			} else {
				$(this).find('input:text').val(FloatMul(val, _user.incomeSetting.proportion));
			}
		});
	});
	
	$('.submitIncomeBtn').on('click',function(){
		var val = $('#form-field-income').val();
		if( !$.trim(val) ){
			$.gritter.add({
				title: '操作失败',
				text: '请输入金额',
				time:'5700',
				class_name: 'gritter-error gritter-light'
			});
			return;
		} else if( isNaN(val) ){
			$.gritter.add({
				title: '操作失败',
				text: '请输入正确的金额',
				time:'5700',
				class_name: 'gritter-error gritter-light'
			});
			return;
		}
		var userId = $(".chosen-select").val();
		var currentRule = $('.upline-users').data('currentRule');
		var data = {
				'income':val,
				'userId':userId,
				'year':$('#year').val(),
				'month':$('#month').val(),
				'ruleId':currentRule.ruleId
				};
		$.ajax({
			'url':path+"/userIncomeMgr/doInputUserIncome",
    		'type':'post',
	    	"dataType":"json",
	    	'data':data,
	    	'success': function (r) {
	    		if(r.result){
	    			$.gritter.add({
						title: '录入成功',
						text: '',
						time:'1600',
						class_name: 'gritter-success gritter-light'
					});
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
});

function setSelectData(){
	/**设置日期**/
	var now = new Date();
	var y = now.getFullYear();
	var m = now.getMonth();
	$('#month').val(m);
	for (var i = 2010; i <= y; i++) {
		$('#year').append('<option value="'+i+'"'+(y==i?'selected="selected"':'')+'>'+i+'</option>');
	}
	
}

function reloadUserUplines(){
	var userId = $(".chosen-select").val();
	var dtime = (new Date()).getTime();
	$('#form-field-income').val('');
	$.get(path+'/userIncomeMgr/getUserList',{'dtime':dtime,'userId':userId},function(r){
		if(r.userUplineList){
			$('.upline-users').empty();
			for (var i = 0; i < r.userUplineList.length; i++) {
				var htm = 
					'<div class="form-group">'+
					'	<label class="col-sm-4 control-label no-padding-right">'+r.userUplineList[i].name+'('+r.userUplineList[i].level+')提成</label>'+
					'		<div class="col-sm-8">'+
					'		<input type="text" disabled="disabled" value="0" class="col-xs-10 col-sm-6"/>'+
					'	</div>'+
					'</div>';
				$(htm).data('user',r.userUplineList[i]).appendTo('.upline-users');
			}
		}
		$('.upline-users').data('currentRule', r.currentRule);
		baseProportion = r.baseRuleSetting.proportion;
	},'json');
}