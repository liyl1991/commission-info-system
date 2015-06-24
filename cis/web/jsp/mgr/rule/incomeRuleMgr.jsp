<%@ page language="java" pageEncoding="UTF-8" %>
<% String path = request.getContextPath();%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html>
<html>
	<head>
		<title>提成规则设置</title>
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
							<li class="active">提成规则</li>
						</ul><!-- .breadcrumb -->
					</div>
					<!-- 内容始 -->
					<div class="tabbable">
						<ul class="nav nav-tabs padding-12 tab-color-blue background-blue" id="myTab4">
							<li class="active">
								<a data-toggle="tab" href="#ruleTab">提成比例</a>
							</li>
							<li>
								<a data-toggle="tab" href="#reachTab">达标指数</a>
							</li>
							<li>
								<a data-toggle="tab" href="#baseRuleTab">基础提成比例</a>
							</li>
							<li>
								<a data-toggle="tab" href="#specialRuleTab">个人特殊设置</a>
							</li>
						</ul>

					<div class="tab-content">
						<div id="ruleTab" class="tab-pane in active">
							<div class="page-header">
								<h1>
									提成比例管理
									<small>
										<i class="icon-double-angle-right"></i>
										您可以修改各主线中各级的提成比例(%)
									</small>
								</h1>
							</div><!-- /.page-header -->
							<form id="updateRuleForm" class="form-horizontal" role="form" method="post">
								<div class="form-group" style="height: 31px">
									<label class="col-sm-3 control-label no-padding-right" for="ruleId">主线：</label>
									<div class="col-sm-9">
										<select name="incomeRuleId" id="ruleId" class="col-sm-5">
											<c:forEach items="${incomeRules}" var="rule">
												<option value="${rule.ruleId}" <c:if test="${rule.ruleId ==100 }">selected="selected"</c:if> >${rule.detailContent}</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="form-group" style="height: 31px">
									<label class="col-sm-3 control-label no-padding-right">基础提成</label>
									<div class="col-sm-9">
										<input type="text" readonly name="baseRule" value="${baseRuleSetting.proportion}" class="col-xs-10 col-sm-5" />
									</div>
								</div>
								<div class="form-group use-time">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-sex"> 何时生效 </label>
									<div class="col-sm-9">
									  <div class="col-sm-2">
										<label>
											<input type="radio" class="ace" name="usingFlag" value="1" checked="checked"/>
											<span class="lbl"> 本月</span>
										</label>
									  </div>
									  <div class="col-sm-2">
										<label>
											<input type="radio" class="ace" name="usingFlag" value="2"/>
											<span class="lbl"> 下月</span>
										</label>
									  </div>
									</div>
								</div>
								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-9">
										<button class="btn btn-primary submitUpdateBtn" type="button">
											<i class="icon-ok bigger-110"></i>
											修改
										</button>
									</div>
								</div>
							</form>
						</div>
						<div id="reachTab" class="tab-pane">
							<div class="page-header">
								<h1>
									达标指数管理
									<small>
										<i class="icon-double-angle-right"></i>
										您可以修改各级业绩达标指数(单位：元)
									</small>
								</h1>
							</div>
							<form id="updateReachForm" class="form-horizontal" role="form" method="post">
								<c:forEach items="${reachSettings}" var="reach">
									<div class="form-group" style="height: 31px">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-lvc">${reach.settingLevel}级</label>

										<div class="col-sm-9">
											<input type="text" name="reach${reach.settingLevel}" value="${reach.reachPerformance}" id="form-field-lvc" class="col-xs-10 col-sm-5" />
										</div>
									</div>
								</c:forEach>
								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-9">
										<button class="btn btn-primary submitUpdateBtn" type="button">
											<i class="icon-ok bigger-110"></i>
											修改
										</button>
									</div>
								</div>
							</form>
						</div>
						<div id="baseRuleTab" class="tab-pane">
							<div class="page-header">
								<h1>
									基础提成比例管理
									<small>
										<i class="icon-double-angle-right"></i>
										您可以修改基础的提成比例(%)
									</small>
								</h1>
							</div>
							<form id="updateBaseRuleForm" class="form-horizontal" role="form" method="post">
								<div class="form-group" style="height: 31px">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-baserule">基础提成</label>
									<div class="col-sm-9">
										<input type="text" name="newProportion" value="${baseRuleSetting.proportion}" id="form-field-baserule" class="col-xs-10 col-sm-5" />
									</div>
								</div>
								<div class="clearfix form-actions">
									<div class="col-md-offset-3 col-md-9">
										<button class="btn btn-primary submitUpdateBtn" type="button">
											<i class="icon-ok bigger-110"></i>
											修改
										</button>
									</div>
								</div>
							</form>
						</div>
						<div id="specialRuleTab" class="tab-pane">
							<div class="col-xs-12 col-md-3 side-user-list">
								<div>
									<div class="input-group">
										<input type="text" id="userNameSearch" class="form-control search-query" placeholder="用户名或身份证号..." />
										<span class="input-group-btn">
											<button type="button" class="btn btn-info btn-sm" onclick="reLoadUsers()">
												搜索
												<i class="icon-search icon-on-right bigger-110"></i>
											</button>
										</span>
									</div>
								</div>
								<div class="space-8"></div>
								<div class="user-select-list">
									<ul class="list-unstyle">
									</ul>
								</div>
							</div>
							<div class="col-xs-12 col-md-9 user-list-content">
								<div id="settingAccordion" class="accordion-style1 panel-group">
									<div class="panel panel-default">
										<div class="panel-heading">
											<h4 class="panel-title">
												<a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
													<i class="icon-angle-down bigger-110" data-icon-hide="icon-angle-down" data-icon-show="icon-angle-right"></i>
													&nbsp;BX
												</a>
											</h4>
										</div>

										<div class="panel-collapse collapse in" id="collapseOne">
											<div class="panel-body">
												<div class="row">
													<blockquote class="col-sm-3 panel-primary">总提成：<span>30</span>%</blockquote>
													<blockquote class="col-sm-3 panel-primary">B提成：<span>5</span>%</blockquote>
													<blockquote class="col-sm-3 panel-primary">D提成：<span>5</span>%</blockquote>
													<blockquote class="col-sm-3 panel-primary">E提成：<span>15</span>%</blockquote>
												</div>
												<div class="form-group">
													<label class="col-sm-1">常规：</label>
													<input type="text" class="col-sm-3" disabled="disabled"/>
													<label class="col-sm-1">特殊：</label>
													<input type="text" class="col-sm-3"/>
													<span class="col-sm-2">
														<button class="btn btn-sm btn-success">设置</button>
													</span>
												</div>
											</div>
										</div>
									</div>

								</div>
							</div>							
						</div>
					</div>
					</div>
					<!-- 内容结束 -->
				</div>
		   </div>
		</div>
		<!-- basic scripts -->

		<jsp:include page="/common/inc_js.jsp"></jsp:include>
		<script src="<%=path %>/assets/js/jqPaginator.js" type="text/javascript"></script>
		<script src="<%=path %>/assets/js/jquery.gritter.min.js"></script>
		<script src="<%=path %>/assets/js/chosen.jquery.min.js"></script>
		<script src="<%=path %>/assets/js/date-time/bootstrap-datepicker.js"></script>
		<script src="<%=path %>/assets/js/jquery.form.js" type="text/javascript"></script>
		<script src="<%=path %>/jsp/mgr/rule/incomeRuleMgr.js" type="text/javascript"></script>
		<script type="text/javascript">
		var baseProportion = ${baseRuleSetting.proportion};
		</script>
</body>
</html>

