<%@ page language="java" pageEncoding="UTF-8" %>
<% String path = request.getContextPath();%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
	<head>
		<title>提成明细-${year}月${month }日</title>
		<jsp:include page="/common/inc.jsp"></jsp:include>
		<link rel="stylesheet" href="<%=path %>/assets/css/jquery.gritter.css" />
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
				<jsp:include page="/jsp/sidebar.jsp"></jsp:include>
				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="#">首页</a>
							</li>
							<li><a href="<%=path%>/userIncome/goIncomeInfo">收入与业绩</a></li>
							<li class="active">提成明细</li>
						</ul><!-- .breadcrumb -->
					</div>
					<div class="container">
						<div class="page-header">
							<h1>
								${loginedUser.name }的提成明细
								<small>
									<i class="icon-double-angle-right"></i>
									${year}年${month }月
								</small>
							</h1>
						</div><!-- /.page-header -->
						<div class="page-content">
							<div class="row">
								<div class="col-xs-12">
									<div class="table-responsive">
										<table id="userIncomeTable" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th>姓名</th>
													<th class="hidden-480">等级</th>
													<th>提成金额</th>
													<th>业绩</th>
												</tr>
											</thead>
											<tbody>
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
	<script src="<%=path %>/assets/js/jquery.gritter.min.js"></script>
	<script src="<%=path %>/assets/js/chosen.jquery.min.js"></script>
	<script type="text/javascript">
	var year = ${year};
	var month = ${month};
	$(function(){
		initPagination();
	});
	
	function doQuery(currentPage){
		var dataObj = { 
				"year":year,
				"month":month,
				}; 
		$.ajax({
			url:path+'/userIncome/getUserIncomeFrom',
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
						'	<a href="'+path+'/userMgr/goUserDetail/'+r.content[i].fromDownline+'" title="点击查看详细">'+r.content[i].downlineName+'</a>'+
						'</td>'+
						'<td class="hidden-480">'+r.content[i].downlineLevel+'级</td>'+
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
	</script>
		
</body>
</html>

