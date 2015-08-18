$(function(){
	initPagination();
	$("#level-chosen").chosen({no_results_text: "未找到匹配项"});
});
function doQuery(currentPage){
	var keyWord = $.trim($('#keyWordSearch').val())?$.trim($('#keyWordSearch').val()):null;
	var dataObj = { 
			"currentPage":currentPage?currentPage:1,
			"pageSize":8,
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
		url:path+'/userMgr/getUserList',
		type:"post",
		dataType:"json",
		data:dataObj,
		success:function(r){
			$(".container table tbody tr").remove();
			if(r.content.length!=0){
				$('.pagination').show();
				for(var i=0;i<r.content.length;i++){
				$('<tr>'+
				  '  <td>'+
					'	<a href="'+path+'/userMgr/goUserDetail/'+r.content[i].userId+'" title="点击查看详细">'+r.content[i].name+'</a>'+
					'</td>'+
					'<td>'+r.content[i].idCard+'</td>'+
					'<td class="hidden-480">'+(r.content[i].sex=='1'?'男':(r.content[i].sex=='2'?'女':'保密'))+'</td>'+
					'<td class="hidden-480">'+r.content[i].level+'级</td>'+
					'<td class="hidden-480">'+r.content[i].career+'</td>'+
					'<td>'+
					'	<div class="visible-md visible-lg visible-sm visible-xs action-buttons">'+
					'		<a class="btn btn-minier btn-primary" href="'+path+'/userMgr/goUserDetail/'+r.content[i].userId+'" title="查看用户详细信息">查看</a>'+
					'		<a class="btn btn-minier btn-danger" href="javascript:doDelete('+r.content[i].userId+')" title="删除该员工">删除</a>'+
					'	</div>'+
					'</td>'+
				  '</tr>').appendTo('.container table tbody');
				}
			}else{
				$('.pagination').hide();
				$('<tr>'+
						'<td colspan="7">暂无数据</td>'+
					  '</tr>').appendTo(".container table tbody");
			}
				
			$('.pagination').jqPaginator('option', {
				totalPages: r.totalPages,
				currentPage:currentPage?currentPage:1
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
function doDelete(userId){
	if(confirm("您确认要删除该员工？")){
	$.ajax({
		url:path+'/userMgr/doDeleteUser/'+userId,
		type:"post",
		dataType:"json",
		success:function(r){
			if(r.result)
				doQuery();
			else{
				$.gritter.add({
					title: '删除失败',
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