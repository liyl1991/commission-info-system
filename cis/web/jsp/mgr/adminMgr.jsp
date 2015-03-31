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
							<li class="active">员工管理</li>
						</ul><!-- .breadcrumb -->
					</div>
					<div class="container">
						<div class="page-content">
							<div class="row">
								<div class="col-xs-12">
									<div class="table-header">
										<a href="<%=path %>/admin/goInputUser" class="btn btn-xs btn-info">
											<i class="icon-edit"></i>
											<span class="no-text-shadow">新增</span>
										</a>
									</div>
									<div class="table-responsive">
										<table id="sample-table-2" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th>姓名</th>
													<th class="hidden-480">性别</th>
													<th class="hidden-480">等级</th>
													<th>上月收入</th>
													<th>上月业绩</th>
													<th class="hidden-480">职业</th>
													<th></th>
												</tr>
											</thead>
	
											<tbody>
												<!-- <tr>
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
												</tr> -->
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
		<script src="<%=path %>/jsp/mgr/adminMgr.js" type="text/javascript"></script>
		
</body>
</html>

