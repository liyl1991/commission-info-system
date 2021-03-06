<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
	<head>
		<title>个人收入信息</title>
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
							<li class="active">收入与业绩</li>
						</ul><!-- .breadcrumb -->
					</div>
					<div class="space-24"></div>
					<div class="container">
						<div class="row clearfix income-content">
							<div class="col-md-2 column">
							    <div class="infobox infobox-green infobox-small infobox-dark pre-income">
									<div class="infobox-data">
										<div class="infobox-content">上月收入</div>
										<div class="infobox-content money">暂无数据</div>
									</div>
								</div>
							    <div class="infobox  infobox-green infobox-small infobox-dark pre-performance">
									<div class="infobox-data">
										<div class="infobox-content">上月业绩</div>
										<div class="infobox-content money">暂无数据</div>
									</div>
								</div>
								<div class="infobox infobox-blue infobox-small infobox-dark sum-income">
									<div class="infobox-data">
										<div class="infobox-content">总收入</div>
										<div class="infobox-content money">暂无数据</div>
									</div>
								</div>
								<div class="infobox infobox-blue infobox-small infobox-dark sum-performance">
									<div class="infobox-data">
										<div class="infobox-content">总业绩</div>
										<div class="infobox-content money">暂无数据</div>
									</div>
								</div>
							</div>
							<div class="col-md-10 column">
								<table class="table table-hover table-bordered" id="userIncomeTable">
									<thead>
										<tr>
											<th>月份</th>
											<c:if test="${loginedUser.level eq 'X' }">
												<th>创收</th>
											</c:if>
											<c:if test="${loginedUser.level eq 'B' }">
												<th>收入</th>
												<th>业绩</th>
											</c:if>
											<c:if test="${loginedUser.level eq 'C' || loginedUser.level eq 'D' ||loginedUser.level eq 'E'}">
												<th>收入</th>
												<th>业绩</th>
												<th>达标指数</th>
											</c:if>
										</tr>
									</thead>
									<tbody></tbody>
								</table>
								<div>
									<ul class="pagination">
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
		   </div>
		</div>
		<!-- basic scripts -->

		<jsp:include page="/common/inc_js.jsp"></jsp:include>
		<script src="<%=path %>/assets/js/jqPaginator.js" type="text/javascript"></script>
		<script type="text/javascript">
		$(function(){
			initPagination();
			$('#userIncomeTable').on('click','.goIncomeFromBtn',function(){
				var userId = $(this).attr('userId');
				var $tr = $(this).closest('tr');
				var dateStr = $tr.find('td:eq(0)').text();
				dateStr = dateStr.replace('年',',');
				dateStr = dateStr.replace('月','');
				var year = dateStr.split(',')[0];
				var month = dateStr.split(',')[1];
				location.href = path+'/userIncome/goUserIncomeFrom/'+year+'/'+month+'/'+userId; 
			});
		});
		function doQuery(currentPage){
			var dataObj = { 
					"currentPage":currentPage?currentPage:1,
					"pageSize":8}; 
			$.ajax({
				url:path+"/userIncome/getIncomeInfo",
				type:"post",
				dataType:"json",
				data:dataObj,
				success:function(r){
					$(".container table tbody tr").remove();
					if(r.incomeList.content.length!=0){
						$('.pagination').show();
						var list = r.incomeList.content;
						for(var i=0;i<list.length;i++){
							var isEnough = list[i].isEnough==1?'ok':'remove';
							var statusClass = '';
							if(list[i].level !='X' && list[i].level != 'B'){
								statusClass = list[i].performance >= list[i].reachPerformance?'success':'danger';
							}
							var tr =  '<tr class="'+statusClass+'">'+
										'<td>'+formatDate(list[i].incomeDate)+'</td>'+
										(list[i].level != 'X'?('<td>'+(list[i].income?('<a class="goIncomeFromBtn" href="#" userId="'+list[i].userId+'" title="点击查看提成明细">' + list[i].income +'</a>'):'暂无数据')+'</td>'):'')+
										'<td>'+(list[i].performance?list[i].performance:'暂无数据')+'</td>';
							if(list[i].level == 'C' || list[i].level == 'D' || list[i].level == 'E')
								tr += '<td>'+(list[i].reachPerformance?list[i].reachPerformance:'暂无数据')+'</td>';
							tr += '</tr>';
							$(tr).appendTo(".container table tbody");
						}
						$('.pagination').jqPaginator('option', {
							totalPages: r.incomeList.totalPages
						});
					}
					if($(".container table tbody tr").length == 0){
						$('.pagination').hide();
						$('<tr>'+
								'<td colspan="4">暂无数据</td>'+
							  '</tr>').appendTo(".container table tbody");
					}
					//$(".pre-income,.pre-performance").remove infobox-green
					if(r.preIncome){
						$(".pre-income .money").text(r.preIncome.income?("￥"+r.preIncome.income):'暂无数据');
						$(".pre-performance .money").text(r.preIncome.performance?("￥"+r.preIncome.performance):'暂无数据');
						if(r.preIncome.isEnough==1) $(".pre-performance").addClass("infobox-green");
						else $(".pre-performance").addClass("infobox-red");
					}
					$(".sum-income .money").text(r.incomeSum.income?("￥"+r.incomeSum.income):'暂无数据');
					$(".sum-performance .money").text(r.incomeSum.performance?("￥"+r.incomeSum.performance):'暂无数据');
				},
				error:function(){}
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

