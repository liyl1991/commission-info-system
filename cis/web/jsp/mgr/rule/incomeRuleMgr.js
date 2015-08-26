var downTotal = 0;
var baseProportion;
$(function(){
	eventInit();
	buildRuleEditForm();
});
function eventInit(){
	var winHei = $(window).height();
	$('.tab-content .tab-pane').height(winHei-168);
	$('.user-select-list').height($('#specialRuleTab').height() - 50);
	$(window).resize(function(){
		$('.tab-content .tab-pane').height(winHei-168);
		$('.user-select-list').height($('#specialRuleTab').height() - 50);
	});
	$('#updateRuleForm').on('change','#ruleId',function(){
		buildRuleEditForm();
	});
	$("#updateRuleForm .submitUpdateBtn").click(function(){
		var hasError = false;
		if(!validateIncomeSetting()) return;
		$('#updateRuleForm .rule-setting').each(function(idx){
			if(!$.trim($(this).find('input:text').val())) hasError = true;
		});
		if(hasError){
			$.gritter.add({
				title: '修改出错',
				text: '请填写所有内容',
				time:'3700',
				class_name: 'gritter-error gritter-light'
			});
			return;
		}
		$("#updateRuleForm").ajaxSubmit({
			url:path+"/incomeRuleMgr/doUpdateIncomeRule",
    		type:'post',
	    	"dataType":"json",
	    	success: function (r) {
	    		if(r.result){
	    			if(r.updatedCnt != 0){
	    				var usingFlag = $('#updateRuleForm input[name="usingFlag"]:checked').val();
	    				var txt = '您成功修改当前主线各级提成比例，将于';
	    				if(usingFlag == 1){
	    					txt += '本月开始生效';
	    				} else {
	    					txt += '于下月开始生效';
	    				}
		    			$.gritter.add({
							title: '操作成功',
							text: txt,
							time:'3600',
							class_name: 'gritter-success gritter-light'
						});
		    		}else{
		    			$.gritter.add({
							title: '操作失败',
							text: '您没有修改任何数据！',
							time:'3600',
							class_name: 'gritter-warning gritter-light'
						});
		    		}
	    		}else{
	    			$.gritter.add({
						title: '操作失败',
						text: r.msg,
						time:'5700',
						class_name: 'gritter-error gritter-light'
					});
	    		}
	    	}
		});
	});
	$("#updateReachForm .submitUpdateBtn").click(function(){
		if(!($('input[name="reachC"]').val()
				&&$('input[name="reachD"]').val()
				&&$('input[name="reachE"]').val())){
			$.gritter.add({
				title: '修改出错',
				text: '请填写所有内容',
				time:'3700',
				class_name: 'gritter-error gritter-light'
			});
			return;
		}
		$("#updateReachForm").ajaxSubmit({
			url:path+"/incomeRuleMgr/doUpdateReach",
    		type:'post',
	    	"dataType":"json",
	    	success: function (r) {
	    		if(r.result){
	    			if(r.updatedList.length != 0){
	    				var lvl = '';
	    				for (var i = 0; i < r.updatedList.length; i++) {
							lvl += r.updatedList[i].settingLevel;
							if(i < r.updatedList.length-1) lvl += ",";
						}
	    				var usingFlag = $('#updateReachForm input[name="usingFlag"]:checked').val();
	    				var txt = '您成功修改'+lvl+'级别的达标业绩值，将于';
	    				if(usingFlag == 1){
	    					txt += '本月开始生效';
	    				} else {
	    					txt += '于下月开始生效';
	    				}
		    			$.gritter.add({
							title: '操作成功',
							text: txt,
							time:'3600',
							class_name: 'gritter-success gritter-light'
						});
		    		}else{
		    			$.gritter.add({
							title: '操作失败',
							text: '您没有修改任何数据！',
							time:'3600',
							class_name: 'gritter-warning gritter-light'
						});
		    		}
	    		}else{
	    			$.gritter.add({
						title: '操作失败',
						text: r.msg,
						time:'5700',
						class_name: 'gritter-error gritter-light'
					});
	    		}
	    	}
		});
	});
	$("#updateBaseRuleForm .submitUpdateBtn").click(function(){
		var v = $("#updateBaseRuleForm input:text").val();
		var msg = '';
		if(isNaN(v)){
			msg = '比例请填写数字！';
		}
		else if( parseFloat(v) > 100){
			msg = '比例设置不得大于100';
		}
		if(msg){	
			$.gritter.add({
				title: '输入数据出错',
				text: msg,
				time:'5700',
				class_name: 'gritter-error gritter-light'
			});
			return;
		}
		$("#updateBaseRuleForm").ajaxSubmit({
			url:path+"/incomeRuleMgr/doUpdateBaseRule",
    		type:'post',
	    	"dataType":"json",
	    	success: function (r) {
	    		if(r.result){
	    			if(r.updatedFlag){
	    				var usingFlag = $('#updateBaseRuleForm input[name="usingFlag"]:checked').val();
	    				var txt = '';
	    				if(usingFlag == 1){
	    					txt = '您成功基础提成比例，将于本月开始生效';
	    				} else {
	    					txt = '您成功基础提成比例，将于下月开始生效';
	    				}
		    			$.gritter.add({
							title: '操作成功',
							text: txt,
							time:'3600',
							class_name: 'gritter-success gritter-light'
						});
		    		}else{
		    			$.gritter.add({
							title: '操作失败',
							text: '您没有修改任何数据！',
							time:'3600',
							class_name: 'gritter-warning gritter-light'
						});
		    		}
	    		}else{
	    			$.gritter.add({
						title: '操作失败',
						text: r.msg,
						time:'5700',
						class_name: 'gritter-error gritter-light'
					});
	    		}
	    	}
		});
	});
	$('#myTab4 li').eq(0).click(function(){
		buildRuleEditForm();
	});
	$('#myTab4 li').eq(3).click(function(){
		reLoadUsers();
	});
	
	$('#userNameSearch').on('keyup',function(e){
		if(e.keyCode == 13) reLoadUsers();
	});
	
	$('#updateRuleForm').on('keyup', 'input:text', function(){
		var v = $(this).val();
		validateIncomeSetting();
		if(!$.trim(v)){
			$(this).val(0);
		}
		else if(isNaN(v)){
			v += '';
			$(this).val(v.substring(0, v.length - 1));
		}
	});
	
	
	$('#updateRuleForm').on('blur',' .rule-setting input:text',function(){
		var rule = $('#ruleId').find("option:selected").text();
		if(!validateIncomeSetting())
			$("#updateRuleForm .submitUpdateBtn").prop('disabled',true);
		else
			$("#updateRuleForm .submitUpdateBtn").prop('disabled',false);
		
	});
	
	$('#specialRuleTab .user-select-list').on('click','ul li',function(){
		$('#specialRuleTab .user-select-list li').removeClass('active');
		$(this).addClass('active');
		var userId = $(this).attr('user-id');
		var dtime = (new Date()).getTime();
		$.ajax({
			url:path+"/incomeRuleMgr/getUserIncomeSetting/"+userId,
    		type:'get',
	    	"dataType":"json",
	    	data:{'dtime':dtime},
	    	success: function (r) {
	    		buildSettingAccordion(r);
	    	}
		});
	});
	
	$('#settingAccordion').on('click','.panel-heading .accordion-toggle',function(){
		var userId = $('#specialRuleTab .user-select-list li.active').attr("user-id");
		var ruleId = $(this).attr("rule-id");
		var _own = this;
		$.ajax({
			url:path+"/incomeRuleMgr/getIncomeSettingLine",
			type:'post',
			data:{'ruleId':ruleId,'userId':userId},
	    	"dataType":"json",
	    	success: function (r) {
	    		buildSpecialSettingForm(r,_own);
	    	}
		});
	});
	
	$('#settingAccordion').on('blur','.special-setting-input',function(e){
		spcialSettingValidate($(this));
	});
	
	$('#settingAccordion').on('click','.do-set-btn',function(){
		var $inputObj = $(this).parent().prev('input');
		if( !spcialSettingValidate($inputObj) ){
			var settingVal = $inputObj.val();
			var ruleId = $inputObj.attr('rule-id');
			var userId = $('#specialRuleTab .user-select-list li.active').attr("user-id");
			$.ajax({
				url:path+"/incomeRuleMgr/doUpdateUserSpecialSetting/",
	    		type:'post',
		    	"dataType":"json",
		    	data:{'userId':userId,'ruleId':ruleId,'newSetting':settingVal},
		    	success: function (r) {
		    		if(r.result){
		    			$.gritter.add({
		    				title: '操作成功',
		    				text: '',
		    				time:'3000',
		    				class_name: 'gritter-success gritter-light'
		    			});
		    		}
		    	},
				error:function(){
					
				}
			});
		} else {
			$.gritter.add({
				title: '操作失败',
				text: spcialSettingValidate($inputObj),
				time:'5000',
				class_name: 'gritter-error gritter-light'
			});
		}
	});
	
	$('#settingAccordion').on('click','.do-del-btn',function(){
		var $inputObj = $(this).parent().prev('input');
		var ruleId = $inputObj.attr('rule-id');
		var userId = $('#specialRuleTab .user-select-list li.active').attr("user-id");
		$.ajax({
			url:path+"/incomeRuleMgr/doDeleteUserSpecialSetting/",
    		type:'post',
	    	"dataType":"json",
	    	data:{'userId':userId,'ruleId':ruleId},
	    	success: function (r) {
	    		if(r.result){
	    			$.gritter.add({
	    				title: '操作成功',
	    				text: '',
	    				time:'3000',
	    				class_name: 'gritter-success gritter-light'
	    			});
	    			$inputObj.val('');
	    		}
	    	},
			error:function(){
				
			}
		});
	});
}

function spcialSettingValidate($inputObj){
	var msg = null;
	var settingVal = $inputObj.val();
	var otherTotal = FloatMul($inputObj.attr('other-total'), 100);
	var userLevel = $('#specialRuleTab .user-select-list li.active').attr("user-level");
	var $row = $inputObj.closest('.panel-body');
	var total = FloatMul(baseProportion, 100);
	if( userLevel != 'B'){
		if( settingVal && !isNaN(settingVal) ){
			var lvBSetting = FloatSub(total ,FloatAdd(otherTotal, settingVal));
			if( lvBSetting <= 0){
				msg = 'B级的提成必须大于0！';
			}
			$row.find('.lvb-proportion span').text(lvBSetting);
		} else if( isNaN(settingVal) ){
			msg = '比例请填写数字！';
		}
	} else {
		if( settingVal && !isNaN(settingVal) ){
			if( total < settingVal){
				msg = 'B级的提成不得大于总提成！';
			}
		} else if( isNaN(settingVal) ){
			msg = '比例请填写数字！';
		}
	}
	return msg;
}

function buildSpecialSettingForm(data,own){
	var $body = $(own).parent('.panel-title').parent('.panel-heading').next('.panel-collapse').find('.panel-body');
	var currentRule = data.currentRule;
	var otherTotal = 0;
	var currentSetting = 0;
	var formHtm = 
			'<div class="row">'+
			'	<blockquote class="col-sm-3 panel-primary">总提成：<span>'+FloatMul(data.baseRuleSetting.proportion,100)+'</span>%</blockquote>';
	if( currentRule.detailContent != 'BX'){
		for (var i = 0; i < data.commonSettings.length; i++) {
			var specialStr = 'max' + data.commonSettings[i].settingLevel;
			var forAdd = 0;
			if(data[specialStr] && data[specialStr] > data.commonSettings[i].proportion){
				forAdd = data[specialStr];
			} else if( data.currentUser.level == data.commonSettings[i].settingLevel 
					&& data.currentUserSetting 
					&& data.currentUserSetting.specialSetting > data.currentUserSetting.commonSetting){
				forAdd = data.currentUserSetting.specialSetting;
				currentSetting = data.currentUserSetting.specialSetting;
			} else if( data.currentUser.level == data.commonSettings[i].settingLevel ){
				currentSetting = data.commonSettings[i].proportion;
				forAdd = data.commonSettings[i].proportion;
			} else {
				forAdd = data.commonSettings[i].proportion;
			}
			otherTotal = FloatAdd(forAdd, otherTotal);
		}
		var lvBpro = FloatSub(data.baseRuleSetting.proportion, otherTotal);
		formHtm +='	<blockquote class="col-sm-3 panel-primary lvb-proportion">B最低提成：<span>'+FloatMul(lvBpro,100)+'</span>%</blockquote>';
	}
	for (var i = 0; i < data.commonSettings.length; i++) {
		var specialStr = 'max' + data.commonSettings[i].settingLevel;
		formHtm +=' <blockquote class="col-sm-3 '+(data.currentUser.level==data.commonSettings[i].settingLevel?'panel-success':'panel-primary') +'">'+data.commonSettings[i].settingLevel+
						'提成：<span>'+FloatMul(data.commonSettings[i].proportion,100)+'</span>%'+
						(data[specialStr]?'(最高：<span>'+FloatMul(data[specialStr],100)+'</span>%)':'')+
						'</blockquote>';
	}
	otherTotal = FloatSub(otherTotal, currentSetting);
	formHtm +=
			'</div>'+
			'<div class="form-group">'+
			'	<label class="col-sm-2">特殊设置(%)：</label>'+
			'	<input type="text" class="col-sm-5 special-setting-input" rule-id="'+currentRule.ruleId+'" other-total="'+otherTotal+
					'" value="'+ (data.currentUserSetting?FloatMul(data.currentUserSetting.specialSetting, 100):'') +'"/>'+
			'	<span class="col-sm-3">'+
			'		<button class="btn btn-sm btn-success do-set-btn">设置</button>'+
			'		<button class="btn btn-sm btn-success do-del-btn">删除特殊配置</button>'+
			'	</span>'+
			'</div>';
	$body.empty();
	$body.append(formHtm);
}

function buildRuleEditForm(){
	var ruleId = $('#ruleId').val();
	var rule = $('#ruleId').find("option:selected").text();
	if(ruleId){
		$.ajax({
			url:path+"/incomeRuleMgr/getIncomeSetting/"+ruleId,
    		type:'get',
	    	"dataType":"json",
	    	success: function (r) {
    			$('#updateRuleForm .rule-setting,#updateRuleForm .rule-base-lvlb').remove();
    			$('#updateRuleForm input[name="baseRule"]').val(FloatMul(r.baseRuleSetting.proportion, 100));
    			baseProportion = r.baseRuleSetting.proportion;
    			downTotal = 0;
	    		for(var i = 0 ; i < r.settings.length ; i++){
	    			
	    			var htm = 
	    				'<div class="form-group rule-setting" style="height: 31px">'+
						'	<label class="col-sm-3 control-label no-padding-right" for="form-field-icmset' + r.settings[i].settingLevel + '">' + r.settings[i].settingLevel + '级</label>'+
						'	<div class="col-sm-9">'+
						'		<input type="text" name="settings['+i+'].proportion" value="' + FloatMul(r.settings[i].proportion, 100 )+ '" id="form-field-icmset' + r.settings[i].settingLevel + '" class="col-xs-10 col-sm-5" />'+
						'		<input type="hidden" name="settings['+i+'].settingLevel" value="' + r.settings[i].settingLevel + '"/>'+
						'	</div>'+
						'</div>';
					$('#updateRuleForm .use-time').before(htm);
					downTotal += r.settings[i].proportion;
	    		}
    			if( rule != 'BX' ){
    				var htm = 
	    				'<div class="form-group rule-base-lvlb" style="height: 31px">'+
						'	<label class="col-sm-3 control-label no-padding-right">B级</label>'+
						'	<div class="col-sm-9">'+
						'		<input type="text" readonly value="' +FloatMul(FloatSub(baseProportion,downTotal), 100)+ '" class="col-xs-10 col-sm-5" />'+
						'	</div>'+
						'</div>';
    				$('#updateRuleForm .rule-setting').eq(0).before(htm);
    			}
	    			
	    	}
		});
	}
}


function buildSettingAccordion(data){
	var settings = data.userSettings;
	$('#settingAccordion').empty();
	for (var i = 0; i < settings.length; i++) {
		var htm = 
			'<div class="panel panel-default">'+
			'	<div class="panel-heading">'+
			'		<h4 class="panel-title">'+
			'			<a rule-id="'+settings[i].ruleId+'" class="accordion-toggle" data-toggle="collapse" data-parent="#settingAccordion" href="#collapse'+settings[i].ruleId+'">'+
			'				<i class="icon-angle-right bigger-110" data-icon-hide="icon-angle-down" data-icon-show="icon-angle-right"></i>'+
			'&nbsp;主线 ' + settings[i].detailContent + ' 设置'+				
			'			</a>'+
			'		</h4>'+
			'	</div>'+
			'	<div class="panel-collapse collapse" id="collapse'+settings[i].ruleId+'">'+
			'		<div class="panel-body">'+
			'			'+
			'		</div>'+
			'	</div>'+
			'</div>';
		$('#settingAccordion').append(htm);
	}
	$('.panel-heading .accordion-toggle').eq(0).click();
}

function validateIncomeSetting(){
	var newTotal = 0;
	var flag = true; 
	var rule = $('#ruleId').find("option:selected").text();
	$('#updateRuleForm .rule-setting input:text').each(function(idx){
		if( isNaN($(this).val()) ){
			$("#updateRuleForm .submitUpdateBtn").prop('disabled',true);
			if(flag){
				$.gritter.add({
					title: '输入数据出错',
					text: '比例请填写数字！',
					time:'5700',
					class_name: 'gritter-error gritter-light'
				});
				flag = false;
				return;
			}
		}else{
			newTotal = FloatAdd( newTotal ,parseFloat($(this).val()));
		}
	});
	$('#updateRuleForm .rule-base-lvlb input:text').val( FloatSub(FloatMul(baseProportion, 100), newTotal) );
	if((newTotal >= FloatMul(baseProportion,100) && rule !='BX')||(newTotal > FloatMul(baseProportion,100) && rule =='BX')){
		if(flag){
			$.gritter.add({
				title: '输入数据出错',
				text: '总提成比例不能为大于基础提成比例，且【B级】所剩提成比例不能为0！',
				time:'5700',
				class_name: 'gritter-error gritter-light'
			});
		}
		flag = false;
	} 
	return flag;
}
function reLoadUsers(){
	$('.user-select-list ul').empty();
	var username = $('#userNameSearch').val();
	$.ajax({
		url:path+"/incomeRuleMgr/getUserList",
		type:'post',
		data:{'nameOrIdCardLike':username},
    	"dataType":"json",
    	success: function (r) {
    		for (var i = 0; i < r.users.length; i++) {
    			var htm = '<li title="身份证：'+ r.users[i].idCard +'" user-id="'+ r.users[i].userId +'" user-level="'+ r.users[i].level +'">'+ 
    					  r.users[i].name +'<span>'+ r.users[i].level +
    					  '级</span></li>'
				$(htm).appendTo('.user-select-list ul');
			}
    		$('.user-select-list ul li').eq(0).trigger('click');
    	}
	});
}