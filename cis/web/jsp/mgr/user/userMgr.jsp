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
									<div class="row well no-margin" style="padding-top: 8px;">
										<form id="searchForm" class="form-horizontal" role="form">
											<div class="form-group col-md-4 no-margin">
									          <label for="keyWordSearch" class="col-md-3 col-sm-2 control-label no-padding-left no-padding-right">搜索：</label>
									          <div class="col-md-9 col-sm-8">
									            <input maxlength="32" type="text" name="nameOrIdCardLike" class="form-control input-sm" id="keyWordSearch" placeholder="姓名或者身份证"/>
									          </div>
									        </div>
									        <div class="form-group col-md-5 no-margin">
									          <label for="level-chosen" class="col-md-3 control-label no-padding-left no-padding-right">等级：</label> 
									          <div class="col-md-9 no-padding">
									            <select id="level-chosen" class="form-control col-sm-2 tag-input-style" multiple data-placeholder="选择等级...">
									            	<option value="B">B级</option>
									            	<option value="C">C级</option>
									            	<option value="D">D级</option>
									            	<option value="E">E级</option>
									            	<option value="X">X级</option>
									            </select>
									          </div>
									        </div>
										</form>
										<div class="col-md-2 no-margin">
											<button type="button" class="btn btn-xs btn-info" onclick="doQuery()">搜索</button>
											<a href="<%=path %>/userMgr/goInputUser" class="btn btn-xs btn-purple">新增</a>
										</div>
									</div>
									<div class="table-responsive">
										<table id="sample-table-2" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th>姓名</th>
													<th>身份证</th>
													<th class="hidden-480">性别</th>
													<th class="hidden-480">等级</th>
													<th class="hidden-480">职业</th>
													<th>操作</th>
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
		<script src="<%=path %>/jsp/mgr/user/userMgr.js" type="text/javascript"></script>
		
</body>
</html>

