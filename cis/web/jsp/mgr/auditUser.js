$(function(){
	initPagination();
});
function doQuery(currentPage){
	var dataObj = { 
			"currentPage":currentPage?currentPage:1,
	"pageSize":8}; 
	$.ajax({
		url:path+'/auditInfo/getAuditList',
		type:"post",
		dataType:"json",
		data:dataObj,
		success:function(r){
			$("#vauditUserTabel tbody tr").remove();
			if(r.vauditUsers.content.length!=0){
				$('.pagination').show();
				for(var i=0;i<r.vauditUsers.content.length;i++){
					var str = '<tr>'+
					  '  <td>'+
						r.vauditUsers.content[i].idCard+
						'</td>'+
						'<td>'+r.vauditUsers.content[i].name+'</td>'+
						'<td>'+(r.vauditUsers.content[i].sex=='1'?'男':(r.vauditUsers.content[i].sex=='2'?'女':'保密'))+'</td>'+
						'<td class="hidden-480">'+(r.vauditUsers.content[i].birthday?formatDate(r.vauditUsers.content[i].birthday):'暂无数据')+'</td>'+
						'<td class="hidden-480">'+
						'	<span>'+(r.vauditUsers.content[i].career?r.vauditUsers.content[i].career:'暂无数据')+'</span>'+
						'</td>'+
						'<td class="hidden-480">'+(r.vauditUsers.content[i].address?r.vauditUsers.content[i].address:'暂无数据')+'</td>'+
						'<td>';
					if(r.vauditUsers.content[i].auditStatus==1){
						str += '<span class="green">已通过</span></td><td></td>';
					}
					else if(r.vauditUsers.content[i].auditStatus==2){
						str +='<span class="red">已驳回</span></td>';
						str += 
							'<td>'+
							'	<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
							'		<a class="btn btn-minier btn-warning" href="'+path+'/auditInfo/goUpdateAndReaudit/'+r.vauditUsers.content[i].userId+'" title="让该用户通过审核">修改重审</a>'+
							'	</div>'+
							'</td>';
					}
					else if(r.vauditUsers.content[i].auditStatus==3){
						str +='待审核</td>';
						str += '<td>'+
							'	<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
							'		<a class="btn btn-minier btn-success" href="javascript:doPassAudit('+r.vauditUsers.content[i].userId+',\''+r.vauditUsers.content[i].name+'\')" title="让该用户通过审核">通过</a>'+
							'		<a class="btn btn-minier btn-danger" href="javascript:doUnpassAudit('+r.vauditUsers.content[i].userId+',\''+r.vauditUsers.content[i].name+'\')" title="该用户不通过审核">驳回</a>'+
							'	</div>'+
							'</td>';
					}
					str += '</tr>';
					$(str).appendTo('#vauditUserTabel tbody');
				}
			}else{
				$('.pagination').hide();
				$('<tr>'+
						'<td colspan="8">暂无数据</td>'+
					  '</tr>').appendTo("#vauditUserTabel tbody");
			}
				
			$('.pagination').jqPaginator('option', {
				totalPages: r.vauditUsers.totalPages
			});
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
function doPassAudit(userId,userName){
	if(confirm("您确认要让"+userName+"通过审核？通过后用户默认为E级，且可以登陆到系统。")){
		$.ajax({
			url:path+'/auditInfo/doPassAudit/'+userId,
			type:"post",
			dataType:"json",
			success:function(r){
				if(r.result){
					doQuery();
					reCountUnaudit();
					$.gritter.add({
						title: '操作成功',
						text: userName+'已通过审核。',
						class_name: 'gritter-success gritter-light'
					});
				}else{
					$.gritter.add({
						title: '操作失败',
						text: r.msg,
						class_name: 'gritter-error gritter-light'
						});
				}
			},
			error:function(){
					
			}
		});
	}
}
function doUnpassAudit(userId,userName){
	if(confirm("您确认驳回"+userName+"的申请？")){
		$.ajax({
			url:path+'/auditInfo/doUnpassAudit/'+userId,
			type:"post",
			dataType:"json",
			success:function(r){
				if(r.result){
					doQuery();
					reCountUnaudit();
					$.gritter.add({
						title: '操作成功',
						text: userName+'已申请未能通过，已被驳回。',
						class_name: 'gritter-warning gritter-light'
					});
				}else{
					$.gritter.add({
						title: '操作失败',
						text: r.msg,
						class_name: 'gritter-error gritter-light'
						});
				}
			},
			error:function(){
					
			}
		});
	}
}
function reCountUnaudit(){
	$.ajax({
		url:path+'/auditInfo/getUnAtuditCount',
		type:"post",
		dataType:"json",
		success:function(r){
			$('.unaudit-count').text(r.unAtuditCount);
		},
		error:function(){
			
		}
	});
}