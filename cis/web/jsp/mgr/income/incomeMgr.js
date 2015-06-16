var baseProportion;
$(function(){
	setSelectData();
	initPagination();
	$("#level-chosen").chosen({no_results_text: "未找到匹配项"});
	$('#inputIncomeBtn').on('click',function(){
		$('#inputIncomeDialog').modal();
		$('#form-field-income').val('');
		$(".chosen-select").chosen({no_results_text: "未找到匹配项"});
		$(".chosen-select").chosen().change(reloadUserUplines);
		reloadUserUplines();
	});
	$('#form-field-income').on('blur',calculateIncome);
	
	$('.submitIncomeBtn').on('click',function(){
		var now = new Date();
		var y = now.getFullYear();
		var m = now.getMonth();
		if( $('#inputUserIncomeForm .year').val() > y ||
				($('#inputUserIncomeForm .year').val() == y && $('#inputUserIncomeForm .month').val() >m)){
			$.gritter.add({
				title: '操作失败',
				text: '只允许录入当月及之前的收入信息！',
				time:'5700',
				class_name: 'gritter-error gritter-light'
			});
			return;
		}
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
				'year':$('#inputUserIncomeForm .year').val(),
				'month':$('#inputUserIncomeForm .month').val(),
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
	    			$('#inputIncomeDialog').modal('hide');
	    			doQuery();
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
	
	//$('#searchForm .year,#searchForm .month,#level-chosen').on('change',doQuery);
	$('#inputUserIncomeForm .year,#inputUserIncomeForm .month').on('change',reloadUserUplines);
	$('#keyWordSearch').on('keyup',function(e){
		if( e.keyCode == 13){
			doQuery();
		}
	});
});

function calculateIncome(){
	var val = $('#form-field-income').val();
	if(isNaN(val)){
		$.gritter.add({
			title: '输入错误',
			text: '请输入正确的金额',
			time:'5700',
			class_name: 'gritter-error gritter-light'
		});
		$('#form-field-income').val('')[0].focus();
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
}

function setSelectData(){
	/**设置日期**/
	var now = new Date();
	var y = now.getFullYear();
	var m = now.getMonth();
	$('.month').val(m);
	for (var i = 2010; i <= y; i++) {
		$('.year').append('<option value="'+i+'"'+(y==i?'selected="selected"':'')+'>'+i+'</option>');
	}
	
}

function reloadUserUplines(){
	var userId = $(".chosen-select").val();
	var dtime = (new Date()).getTime();
	var data = {
			'dtime':dtime,
			'userId':userId,
			"year":$('#inputUserIncomeForm .year').val(),
			"month":$('#inputUserIncomeForm .month').val()
			};
	$.get(path+'/userIncomeMgr/getUserList', data, function(r){
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
		if( $('#form-field-income').val() ) calculateIncome();
	},'json');
}

function doQuery(currentPage){
	var keyWord = $.trim($('#keyWordSearch').val())?$.trim($('#keyWordSearch').val()):null;
	var dataObj = { 
			"currentPage":currentPage?currentPage:1,
			"pageSize":8,
			"year":$('#searchForm .year').val(),
			"month":$('#searchForm .month').val(),
			"nameOrIdCardLike":keyWord
			}; 
	var lvs = $("#level-chosen").val();
	var lvStr = '';
	if( lvs ){
		for (var i = 0; i < lvs.length; i++) {
			lvStr += lvs[i];
			if(i < lvs.length - 1) lvStr += ',';
		}
		dataObj.levelIn = lvStr;
	}
	$.ajax({
		url:path+'/userIncomeMgr/getUserIncomeList',
		type:"post",
		dataType:"json",
		data:dataObj,
		success:function(r){
			$("#userIncomeTable tbody").empty();
			if(r && r.content.length!=0){
				$('.pagination').show();
				for(var i=0;i<r.content.length;i++){
				$('<tr>'+
				  '  <td title="'+r.content[i].idCard+'">'+
					'	<a href="'+path+'/admin/goUserDetail/'+r.content[i].userId+'" title="点击查看详细">'+r.content[i].name+'</a>'+
					'</td>'+
					'<td class="hidden-480">'+r.content[i].idCard+'</td>'+
					'<td class="hidden-480">'+(r.content[i].sex=='1'?'男':(r.content[i].sex=='2'?'女':'保密'))+'</td>'+
					'<td class="hidden-480">'+r.content[i].level+'级</td>'+
					'<td>'+(r.content[i].income?r.content[i].income:'暂无数据')+'</td>'+
					'<td>'+(r.content[i].performance?r.content[i].performance:'暂无数据')+'</td>'+
				  '</tr>').appendTo('.container table tbody');
				}
				$('.pagination').jqPaginator('option', {
					totalPages: r.totalPages
				});
			}else{
				$('.pagination').hide();
				$('<tr>'+
						'<td colspan="7">暂无数据</td>'+
					  '</tr>').appendTo(".container table tbody");
			}
		},
		error:function(){
			
		}
	});
}
function initPagination(totalPages,current){//初始化分页栏
	$(".pagination").jqPaginator({
		totalPages: totalPages?totalPages:1,
		visiblePages: 5,
		currentPage: current?current:1,
		first: '<li class="first"><a href="javascript:void(0);">首页<\/a><\/li>',
		prev: '<li class="prev"><a href="javascript:void(0);"><i class="arrow arrow2"><\/i>上一页<\/a><\/li>',
		next: '<li class="next"><a href="javascript:void(0);">下一页<i class="arrow arrow3"><\/i><\/a><\/li>',
		last: '<li class="last"><a href="javascript:void(0);">末页<\/a><\/li>',
		page: '<li class="page"><a href="javascript:void(0);">{{page}}<\/a><\/li>',
			onPageChange: function (n) {
				doQuery(n);
			}
		});
	}