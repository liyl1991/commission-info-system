<%@ page language="java" pageEncoding="UTF-8" %>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
	<head>
		<title>用户审核</title>
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
							<li class="active">用户审核</li>
						</ul><!-- .breadcrumb -->
					</div>
					<div class="container">
						<div class="page-content">
							<div class="row">
								<div class="col-xs-12">
									<h3 class="header smaller lighter blue">用户审核</h3>
									<div class="table-header">
										您可以审核用户的注册申请
									</div>

									<div class="table-responsive">
										<table id="vauditUserTabel" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th>身份证</th>
													<th>姓名</th>
													<th>性别</th>
													<th class="hidden-480">出生年月</th>
													<th class="hidden-480">职业</th>
													<th class="hidden-480">住址</th>
													<th>状态</th>
													<th>操作</th>
												</tr>
											</thead>

											<tbody>
												<!-- <tr>
													<td>
														<a href="#">李四</a>
													</td>
													<td>男</td>
													<td class="hidden-480">福建福清</td>
													<td>15555</td>

													<td class="hidden-480">
														<span class="label label-sm label-warning">律师</span>
													</td>

													<td>
														<button class="btn btn-xs btn-success">
														   <i class="icon-ok bigger-110"></i>
														   通过
														</button>
														<button class="btn btn-xs btn-danger">
														   <i class="icon-remove bigger-110"></i>
														 驳回
														</button>
													</td>
												</tr>
												<tr>
													<td>
														<a href="#">张三</a>
													</td>
													<td>男</td>
													<td class="hidden-480">福建福清</td>
													<td>15555</td>

													<td class="hidden-480">
														<span class="label label-sm label-warning">律师</span>
													</td>

													<td>
														<span class="green">已通过</span>
													</td>
												</tr>
												<tr>
													<td>
														<a href="#">王五</a>
													</td>
													<td>男</td>
													<td class="hidden-480">福建福清</td>
													<td>15555</td>

													<td class="hidden-480">
														<span class="label label-sm label-warning">律师</span>
													</td>

													<td>
													    <span class="red">已驳回</span>
														
													</td> 
												</tr>-->
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
		<script src="<%=path %>/jsp/mgr/audit/auditUser.js"></script>
</body>
</html>

