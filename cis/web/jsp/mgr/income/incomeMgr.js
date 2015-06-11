var baseProportion;
$(function(){
	$('#inputIncomeBtn').on('click',function(){
		$('#inputIncomeDialog').modal();
		$(".chosen-select").chosen({no_results_text: "未找到匹配项"});
		$(".chosen-select").chosen().change(reloadUserUplines);
		reloadUserUplines();
	});
	$('#form-field-income').on('blur',function(){
		var val = $(this).val();
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
});

function reloadUserUplines(){
	var userId = $(".chosen-select").val();
	var dtime = (new Date()).getTime();
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