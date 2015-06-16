<%@ page language="java" pageEncoding="UTF-8" %>
<% String path = request.getContextPath();%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
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
							<li class="active">员工收入</li>
						</ul><!-- .breadcrumb -->
					</div>
					<div class="container">
						<div class="page-content">
							<div class="row">
								<div class="col-xs-12">
									<div class="row well no-margin" style="padding-top: 8px;">
										<form id="searchForm" class="form-horizontal" role="form">
											<div class="form-group col-md-3 no-margin">
									          <label for="keyWordSearch" class="col-md-3 col-sm-2 control-label no-padding-left no-padding-right">搜索：</label>
									          <div class="col-md-9 col-sm-8">
									            <input type="text" name="nameOrIdCardLike" class="form-control input-sm" id="keyWordSearch" placeholder="姓名或者身份证"/>
									          </div>
									        </div>
									        <div class="form-group col-md-3 no-margin">
									          <label for="level-chosen" class="col-md-3 col-sm-2 control-label no-padding-left no-padding-right">等级：</label> 
									          <div class="col-md-9 col-sm-8">
									            <select id="level-chosen" class="form-control col-sm-2 tag-input-style" multiple data-placeholder="选择等级...">
									            	<option value="B">B级</option>
									            	<option value="C">C级</option>
									            	<option value="D">D级</option>
									            	<option value="E">E级</option>
									            	<option value="X">X级</option>
									            </select>
									          </div>
									        </div>
									        <div class="form-group col-md-2 no-margin">
									          <label for="yearSelect" class="col-md-3 control-label no-padding-left no-padding-right">年：</label>
									          <div class="col-md-9">
									            <select id="yearSelect" class="form-control col-sm-2 year"></select>
									          </div>
									        </div>
									        <div class="form-group col-md-2 no-margin">
									          <label for="monthSelect" class="col-md-3 control-label no-padding-left no-padding-right">月：</label>
									          <div class="col-md-9">
									           <select id="monthSelect" class="form-control col-sm-2 month">
													<option value="1">1</option>
													<option value="2">2</option>
													<option value="3">3</option>
													<option value="4">4</option>
													<option value="5">5</option>
													<option value="6">6</option>
													<option value="7">7</option>
													<option value="8">8</option>
													<option value="9">9</option>
													<option value="10">10</option>
													<option value="11">11</option>
													<option value="12">12</option>
												</select>
									          </div>
									        </div>
										</form>
										<div class="col-md-2 no-margin">
											<button type="button" class="btn btn-xs btn-info" onclick="doQuery()">搜索</button>
											<button type="button" class="btn btn-xs btn-purple" id="inputIncomeBtn">录入收入信息</button>
										</div>
									</div>
									<div class="table-responsive">
										<table id="userIncomeTable" class="table table-striped table-bordered table-hover">
											<thead>
												<tr>
													<th>姓名</th>
													<th class="hidden-480">身份证</th>
													<th class="hidden-480">性别</th>
													<th class="hidden-480">等级</th>
													<th>收入</th>
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
		<!-- modal start -->
		<div id="inputIncomeDialog" class="modal" tabindex="-1">
			<div class="modal-dialog margin-top-55" style="width: 800px;">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h3 class="blue bigger">录入收入信息</h3>
					</div>

					<div class="modal-body overflow-visible">
						<form id="inputUserIncomeForm" class="form-horizontal" role="form">
							<div class="form-group">
								<label class="col-sm-4 control-label no-padding-right" for="form-field-upline"> 选择员工(X级) </label>
								<div class="col-sm-8">
									<div class="col-sm-6 no-padding">
									<select name="uplineUser" class="chosen-select col-sm-12" id="form-field-upline" data-placeholder="">
										<c:forEach items="${forSelectUsers }" var="user">
											<option value="${user.userId }">${user.name }(${user.idCard })</option>
										</c:forEach>
									</select>
									</div>
								</div>
							</div>
							<div class="upline-users">
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label no-padding-right" for="form-field-income"> 创收 </label>
								<div class="col-sm-8">
									<input type="text" value="" id="form-field-income" class="col-xs-10 col-sm-6" placeholder="请输入创收金额"/>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label no-padding-right" for="form-field-income"> 日期 </label>
								<div class="col-sm-8">
									<label class="col-sm-1" style="padding-top: 4px;">年</label>
									<select class="col-sm-2 year"></select>
									<label class="col-sm-1" style="padding-top: 4px;">月</label>
									<select class="col-sm-2 month">
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4">4</option>
										<option value="5">5</option>
										<option value="6">6</option>
										<option value="7">7</option>
										<option value="8">8</option>
										<option value="9">9</option>
										<option value="10">10</option>
										<option value="11">11</option>
										<option value="12">12</option>
									</select>
								</div>
							</div>
						</form>
					</div>

					<div class="modal-footer">
						<button class="btn btn-sm" data-dismiss="modal">
							<i class="icon-remove"></i>
							取消
						</button>

						<button class="btn btn-sm btn-primary submitIncomeBtn">
							<i class="icon-ok"></i>
							保存
						</button>
					</div>
				</div>
			</div>
		</div>
		<!-- modal end -->
		<jsp:include page="/common/inc_js.jsp"></jsp:include>
		<script src="<%=path %>/assets/js/jqPaginator.js" type="text/javascript"></script>
		<script src="<%=path %>/assets/js/jquery.gritter.min.js"></script>
		<script src="<%=path %>/assets/js/chosen.jquery.min.js"></script>
		<script src="<%=path %>/jsp/mgr/income/incomeMgr.js" type="text/javascript"></script>
		
</body>
</html>

