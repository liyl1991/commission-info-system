function UserDetail(userId){
	if(userId){
		this.userId = userId;
		//this.loadUserDetail();
	}
	this.init();
}

UserDetail.prototype.userId = null;
UserDetail.prototype.userInfo = {};
UserDetail.prototype.totalPages = 1;
//事件初始化
UserDetail.prototype.init = function(){
	var _own = this;
	$(".submitUpdateBtn").click(function(){
		$("#updateUserForm").ajaxSubmit({
			url:path+"/admin/doUpdateUser",
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
	$("#profile4 .pagination").jqPaginator({
		'totalPages': _own.totalPages,
		visiblePages: 5,
		currentPage: 1,
		first: '<li class="first"><a href="javascript:void(0);">首页<\/a><\/li>',
		prev: '<li class="prev"><a href="javascript:void(0);"><i class="arrow arrow2"><\/i>上一页<\/a><\/li>',
		next: '<li class="next"><a href="javascript:void(0);">下一页<i class="arrow arrow3"><\/i><\/a><\/li>',
		last: '<li class="last"><a href="javascript:void(0);">末页<\/a><\/li>',
		page: '<li class="page"><a href="javascript:void(0);">{{page}}<\/a><\/li>',
		onPageChange: function (n) {
			_own.doQueryUserdownlines(n);
		}
	});
	$("#dropdown14 .pagination").jqPaginator({
		totalPages: 1,
		visiblePages: 5,
		currentPage: 1,
		first: '<li class="first"><a href="javascript:void(0);">首页<\/a><\/li>',
		prev: '<li class="prev"><a href="javascript:void(0);"><i class="arrow arrow2"><\/i>上一页<\/a><\/li>',
		next: '<li class="next"><a href="javascript:void(0);">下一页<i class="arrow arrow3"><\/i><\/a><\/li>',
		last: '<li class="last"><a href="javascript:void(0);">末页<\/a><\/li>',
		page: '<li class="page"><a href="javascript:void(0);">{{page}}<\/a><\/li>',
		onPageChange: function (n) {
			_own.doQueryUserIncome(n);
		}
	});
	//提交收入信息按钮事件
	$('.submitIncomeBtn').click(function(){
		if(isNaN($('input[name="income"]').val())||$.trim($('input[name="income"]').val()).length==0){
			$.gritter.add({
				title: '添加收入信息出错',
				text: '请正确输入收入信息，必须为数值。',
				time:'3700',
				class_name: 'gritter-error gritter-light'
			});
			return;
		}
		if(_own.userInfo.level!='X'&&(isNaN($('input[name="performance"]').val())||$.trim($('input[name="performance"]').val()).length==0)){
			$.gritter.add({
				title: '添加收入信息出错',
				text: '请正确输入业绩信息，必须为数值。',
				time:'3700',
				class_name: 'gritter-error gritter-light'
			});
			return;
		}
		if($.trim($('input[name="incomeDate"]').val()).length==0){
			$.gritter.add({
				title: '添加收入信息出错',
				text: '请正选择收入的日期。',
				time:'3700',
				class_name: 'gritter-error gritter-light'
			});
			return;
		}
		$("#inputUserIncomeForm").ajaxSubmit({
			url:path+"/admin/doInputUserIncome",
    		type:'post',
	    	"dataType":"json",
	    	success: function (r) {
	    		if(r.result){
	    			$("#modal-form").modal('hide');
	    			_own.doQueryUserIncome(1);
	    			$.gritter.add({
						title: '添加员工收入信息成功',
						text: '',
						time:'1600',
						class_name: 'gritter-success gritter-light'
					});
	    		}else{
	    			$.gritter.add({
						title: '添加员工收入出错',
						text: r.msg,
						time:'3700',
						class_name: 'gritter-error gritter-light'
					});
	    		}
	    	}
		});
	});
};
//查询用户下线
UserDetail.prototype.doQueryUserdownlines = function(pageNo){
	var _own = this;
	var queryObj = {
		uplineUser:_own.userId,
		"currentPage":pageNo?pageNo:1,
		pageSize:8
	};
	$.ajax({
		url:path+'/admin/getUserDownlines',
		type:"get",
		dataType:"json",
		data:queryObj,
		success:function(r){
			$(".downline-table tbody tr").remove();
			if(r.downlines.content.length!=0){
				$('#profile4 .pagination').show();
				for(var i=0;i<r.downlines.content.length;i++){
				$('<tr>'+
				  '  <td>'+
					'	<a href="'+path+'/admin/goUserDetail/'+r.downlines.content[i].userId+'" title="点击查看详细">'+r.downlines.content[i].name+'</a>'+
					'</td>'+
					'<td>'+(r.downlines.content[i].sex=='1'?'男':(r.downlines.content[i].sex=='2'?'女':'保密'))+'</td>'+
					'<td class="hidden-480">'+r.downlines.content[i].level+'级</td>'+
					'<td>'+formatDate(r.downlines.content[i].incomeDate)+'</td>'+
					'<td>'+(r.downlines.content[i].income?r.downlines.content[i].income:'暂无数据')+'</td>'+
					'<td>'+(r.downlines.content[i].performance?r.downlines.content[i].performance:'暂无数据')+'</td>'+
					'<td class="hidden-480">'+
					'	<span>'+r.downlines.content[i].career+'</span>'+
					'</td>'+
				  '</tr>').appendTo('.downline-table tbody');
				}
			}else{
				$('#profile4 .pagination').hide();
				$('<tr>'+
						'<td colspan="7">暂无数据</td>'+
					  '</tr>').appendTo(".downline-table tbody");
			}
				
			$('#profile4 .pagination').jqPaginator('option', {
				totalPages: r.downlines.totalPages
			});
		},
		error:function(){
			
		}
	});
};
//查询员工收入
UserDetail.prototype.doQueryUserIncome = function(pageNo){
	var _own = this;
	var dataObj = { 
			"currentPage":pageNo?pageNo:1,
			"pageSize":8,
			"userId":_own.userId}; 
	$.ajax({
		url:path+"/admin/getIncomeInfo",
		type:"post",
		dataType:"json",
		data:dataObj,
		success:function(r){
			$(".income-table tbody tr").remove();
			if(r.incomeList.content.length!=0){
				$('#dropdown14 .pagination').show();
				for(var i=0;i<r.incomeList.content.length;i++){
					var isEnough = r.incomeList.content[i].isEnough==1?'ok':'remove';
					var statusClass = r.incomeList.content[i].isEnough==1?'success':'danger';
					$('<tr class="'+statusClass+'">'+
						'<td>'+formatDate(r.incomeList.content[i].incomeDate)+'</td>'+
						'<td>'+(r.incomeList.content[i].income?("￥"+r.incomeList.content[i].income):'暂无数据')+'</td>'+
						'<td>'+(r.incomeList.content[i].performance?("￥"+r.incomeList.content[i].performance):'暂无数据')+'</td>'+
						'<td><i class="icon-'+isEnough+'"></i></td>'+
						//'<td><a class="btn btn-minier btn-danger" href="javascript:doDelete('+r.incomeList.content[i].+')" title="删除该员工">删除</a></td>'+
					  '</tr>').appendTo(".income-table tbody");
				}
			}
			else{
				$('#dropdown14 .pagination').hide();
				$('<tr class="'+statusClass+'">'+
						'<td colspan="4">暂无数据</td>'+
					  '</tr>').appendTo(".income-table tbody");
			}
			//$(".pre-income,.pre-performance").remove infobox-green
			if(r.preIncome){
				$(".pre-income .money").text(r.preIncome.preMonthIncome?("￥"+r.preIncome.preMonthIncome):'暂无数据');
				$(".pre-performance .money").text(r.preIncome.preMonthPerformance?("￥"+r.preIncome.preMonthPerformance):'暂无数据');
				if(r.preIncome.isEnough==1) $(".pre-performance").addClass("infobox-green");
				else $(".pre-performance").addClass("infobox-red");
			}
			if(r.incomeSum){
				$(".sum-income .money").text(r.incomeSum.income?("￥"+r.incomeSum.income):'暂无数据');
				$(".sum-performance .money").text(r.incomeSum.performance?("￥"+r.incomeSum.performance):'暂无数据');
			}
			_own.userInfo = r.userInfo;
			$('#dropdown14 .pagination').jqPaginator('option', {
				totalPages: r.incomeList.totalPages
			});
		},
		error:function(){}
	});
};

UserDetail.prototype.getDate = function(){
	if(str){
		var dt = new Date(str);
	  	var yy = dt.getFullYear();
	  	var mm= dt.getMonth()+1<10?'0'+(dt.getMonth()+1):(dt.getMonth()+1);
	  	/*var dd = dt.getDate()<10?'0'+dt.getDate():dt.getDate();
	  	 var hh = dt.getHours()<10?'0'+dt.getHours():dt.getHours();
	  	var mi = dt.getMinutes()<10?'0'+dt.getMinutes():dt.getMinutes(); */
	  	return yy+"年"+mm+"月";
	}else{
		return '';
	}
}


