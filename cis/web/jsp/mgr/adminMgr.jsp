<%@ page language="java" pageEncoding="UTF-8" %>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
	<head>
		<title>控制台</title>
		<jsp:include page="/common/inc.jsp"></jsp:include>
		
	</head>

	<body>
		<jsp:include page="/jsp/navbar.jsp"></jsp:include>
		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>
				<jsp:include page="/jsp/mgr/sidebar.jsp"></jsp:include>
				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="http://www.haohao.cn">首页</a>
							</li>
							<li class="active">员工管理</li>
						</ul><!-- .breadcrumb -->
					</div>
					<div class="container">
						<div class="page-content">
							<div class="row">
								<div class="col-xs-12">
									<h3 class="header smaller lighter blue">员工管理</h3>
	
									<div class="table-responsive">
										<table id="sample-table-2" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th>姓名</th>
													<th>性别</th>
													<th class="hidden-480">等级</th>
													<th>收入月份</th>
													<th>上次收入</th>
													<th>上次业绩</th>
													<th class="hidden-480">职业</th>
													<th></th>
												</tr>
											</thead>
	
											<tbody>
												<tr>
													<td>
														<a href="#">李四</a>
													</td>
													<td>男</td>
													<td class="hidden-480">D</td>
													<td>15555</td>
	
													<td class="hidden-480">
														<span class="label label-sm label-warning">律师</span>
													</td>
	
													<td>
														<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">
															<a class="blue" href="#" title="查看">
																<i class="icon-zoom-in bigger-130"></i>
															</a>
	
															<a class="green" href="#" title="编辑">
																<i class="icon-pencil bigger-130"></i>
															</a>
	
															<a class="red" href="#" title="删除">
																<i class="icon-trash bigger-130"></i>
															</a>
															<a class="red" href="#" title="降级">
																<i class="icon-arrow-down bigger-130"></i>
															</a>
														</div>
													</td>
												</tr>
											</tbody>
										</table>
										<div>
											<ul class="pagination">
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div><!-- /.modal-dialog -->
					</div><!-- PAGE CONTENT ENDS -->
				</div>
		   </div>
		</div>
		<!-- basic scripts -->

		<jsp:include page="/common/inc_js.jsp"></jsp:include>
		<script src="<%=path %>/assets/js/jqPaginator.js" type="text/javascript"></script>
		<script type="text/javascript">
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
								'	<a href="#">'+r.downlineUsers.content[i].name+'</a>'+
								'</td>'+
								'<td>'+(r.downlineUsers.content[i].sex=='1'?'男':(r.downlineUsers.content[i].sex=='2'?'女':'保密'))+'</td>'+
								'<td class="hidden-480">'+r.downlineUsers.content[i].level+'级</td>'+
								'<td>'+formatDate(r.downlineUsers.content[i].incomeDate)+'</td>'+
								'<td>'+(r.downlineUsers.content[i].income?r.downlineUsers.content[i].income:'暂无数据')+'</td>'+
								'<td>'+(r.downlineUsers.content[i].performance?r.downlineUsers.content[i].performance:'暂无数据')+'</td>'+
								'<td class="hidden-480">'+
								'	<span class="label label-sm label-warning">'+r.downlineUsers.content[i].career+'</span>'+
								'</td>'+
								'<td>'+
								'	<div class="visible-md visible-lg hidden-sm hidden-xs action-buttons">'+
								'		<a class="blue" href="'+path+'/admin/goIncomeInfo/'+r.downlineUsers.content[i].userId+'" title="查看收入与业绩">'+
								'			<i class="icon-list-alt bigger-130"></i>'+
								'		</a>'+
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
						$('<tr class="'+statusClass+'">'+
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
		</script>
		
</body>
</html>

