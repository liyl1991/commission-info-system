<%@ page language="java" pageEncoding="UTF-8" %>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
	<head>
		<title>员工管理</title>
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
							<li class="active">公告管理</li>
						</ul><!-- .breadcrumb -->
						<div class="nav-search minimized">
							<span class="input-icon">
								<input type="text" autocomplete="off" class="input-small nav-search-input" id="searchField" placeholder="公告搜索" />
								<i class="icon-search nav-search-icon"></i>
							</span>
						</div>
					</div>
					<div class="container">
						<div class="space-4"></div>
						<div class="page-header">
								<h1>
									公告列表
									<small>
										<i class="icon-double-angle-right"></i>
										<a href="<%=path%>/noticeMgr/goLaunchNotice">发布新公告</a>
									</small>
								</h1>
							</div><!-- /.page-header -->
						<div class="space-8"></div>
						<div class="message-list-container">
							<div id="message-list" class="message-list">
							</div>
						</div>
						<div>
							<ul class="pagination">
							</ul>
						</div>
					</div>
				</div>
		   </div>
		</div>
		<!-- basic scripts -->
		
		<jsp:include page="/common/inc_js.jsp"></jsp:include>
		<script src="<%=path %>/assets/js/jqPaginator.js" type="text/javascript"></script>
		<script src="<%=path %>/assets/js/jquery.gritter.min.js"></script>
		<script type="text/javascript">
		$(function(){
			initPagination();
			
			$('#searchField').keydown(function(e){
				if(e.keyCode==13){
					doQuery(dataObj.currentPage);
				}
			});
		});
		var dataObj;
		function doQuery(currentPage){
			dataObj = { 
					"currentPage":currentPage?currentPage:1,
					"pageSize":8,
					"titleMatch":$(".nav-search-input").val()}; 
			$.ajax({
				url:path+'/noticeMgr/getNoticeList',
				type:"post",
				dataType:"json",
				data:dataObj,
				success:function(r){
					$(".message-list").empty();
					if(r.notices.content.length!=0){
						for(var i=0;i<r.notices.content.length;i++){
							var str = 
							'<div class="message-item">'+
							'	<i class="icon-star orange2"></i>'+
							'	<span title="点击预览" class="sender">'+
							'		<a href='+path+'/noticeMgr/goNoticeView/'+r.notices.content[i].noticeId+'>'+r.notices.content[i].title+'</a>'+
							//(r.notices.content[i].topFlag==1?'<i class="icon-arrow-up"></i>':'')	+
							'	</span>'+
							'	<span class="date-time">'+formatDateTime(r.notices.content[i].createDate)+'</span>'+
							'	<div class="btns-groups">';
							if(r.notices.content[i].topFlag==1)
								str += '<span class="btn btn-purple btn-minier" onclick="setNoticeTop('+r.notices.content[i].noticeId+')">取消置顶</span>';
							else
								str += '<span class="btn btn-purple btn-minier" onclick="setNoticeTop('+r.notices.content[i].noticeId+')">置顶</span>'
							str += 
							'		<span class="btn btn-success btn-minier" onclick="goUpdate('+r.notices.content[i].noticeId+')"">修改</span>'+
							'		<span class="btn btn-danger btn-minier" onclick="doDelete('+r.notices.content[i].noticeId+')">删除</span>'+
							'	</div>'+
							'</div>';
							$(".message-list").append(str);
						}
						$('.pagination').show();
					}else{
						$('.pagination').hide();
					}
						
					$('.pagination').jqPaginator('option', {
						totalPages: r.notices.totalPages
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
		function goUpdate(noticeId){
			location.href = path + "/noticeMgr/goUpdateNotice/"+noticeId;
		}
		function setNoticeTop(noticeId){
			$.ajax({
				url:path+"/noticeMgr/doSetTopNotice/"+noticeId,
				type:"get",
				dataType:"json",
				success:function(r){
					if(r.result){
						doQuery(dataObj.currentPage);
					}else{
						$.gritter.add({
							title: '删除失败',
							text: r.msg,
							class_name: 'gritter-error gritter-light'
							});
					}
				}
			});
		}
		function doDelete(noticeId){
			if(confirm("您确认要删除该公告信息？")){
				$.ajax({
					url:path+'/noticeMgr/doDelete/'+noticeId,
					type:"post",
					dataType:"json",
					success:function(r){
						if(r.result)
							doQuery(dataObj.currentPage);
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
		function formatDateTime(str){
			if(str){
				var dt = new Date(str);
			  	var yy = dt.getFullYear();
			  	var mm= dt.getMonth()+1<10?'0'+(dt.getMonth()+1):(dt.getMonth()+1);
			  	var dd = dt.getDate()<10?'0'+dt.getDate():dt.getDate();
			  	var hh = dt.getHours()<10?'0'+dt.getHours():dt.getHours();
			  	var mi = dt.getMinutes()<10?'0'+dt.getMinutes():dt.getMinutes();
			  	return yy+"年"+mm+"月"+dd+"日";
			}else{
				return '';
			}
		}
		</script>
</body>
</html>

