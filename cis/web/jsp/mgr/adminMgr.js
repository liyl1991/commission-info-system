$(function(){
	initPagination();
});
function doQuery(currentPage){
	var dataObj = { 
			"currentPage":currentPage?currentPage:1,
	"pageSize":8}; 
	$.ajax({
		url:path+'/admin/getUserList',
		type:"post",
		dataType:"json",
		data:dataObj,
		success:function(r){
			$(".container table tbody tr").remove();
			if(r.downlineUsers.content.length!=0){
				$('.pagination').show();
				for(var i=0;i<r.downlineUsers.content.length;i++){
				$('<tr>'+
				  '  <td>'+
					'	<a href="'+path+'/admin/goUserDetail/'+r.downlineUsers.content[i].userId+'" title="点击查看详细">'+r.downlineUsers.content[i].name+'</a>'+
					'</td>'+
					'<td>'+(r.downlineUsers.content[i].sex=='1'?'男':(r.downlineUsers.content[i].sex=='2'?'女':'保密'))+'</td>'+
					'<td class="hidden-480">'+r.downlineUsers.content[i].level+'级</td>'+
					'<td>'+(r.downlineUsers.content[i].preMonthIncome?r.downlineUsers.content[i].preMonthIncome:'暂无数据')+'</td>'+
					'<td>'+(r.downlineUsers.content[i].preMonthPerformance?r.downlineUsers.content[i].preMonthPerformance:'暂无数据')+'</td>'+
					'<td class="hidden-480">'+
					'	<span>'+r.downlineUsers.content[i].career+'</span>'+
					'</td>'+
					'<td>'+
					'	<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
					'		<a class="btn btn-minier btn-primary" href="'+path+'/admin/goUserDetail/'+r.downlineUsers.content[i].userId+'" title="查看用户详细信息">查看</a>'+
					'		<a class="btn btn-minier btn-danger" href="javascript:doDelete('+r.downlineUsers.content[i].userId+')" title="删除该员工">删除</a>'+
				/* 	'		<a class="green" href="#" title="编辑">'+
					'			<i class="icon-pencil bigger-130"></i>'+
					'		</a>'+
					'		<a class="red" href="#" title="删除">'+
					'			<i class="icon-trash bigger-130"></i>'+
					'		</a>'+
					'		<a class="red" href="#" title="降级">'+
					'			<i class="icon-arrow-down bigger-130"></i>'+
					'		</a>'+ */
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
				totalPages: r.downlineUsers.totalPages
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
		url:path+'/admin/doDeleteUser/'+userId,
		type:"post",
		dataType:"json",
		success:function(r){
			if(r.result)
				doQuery();
			else{
				//alert(r.msg);
				$.gritter.add({
					// (string | mandatory) the heading of the notification
					title: '删除失败',
					// (string | mandatory) the text inside the notification
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