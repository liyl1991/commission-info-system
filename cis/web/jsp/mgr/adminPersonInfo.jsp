<%@ page language="java" pageEncoding="UTF-8" %>
<% String path = request.getContextPath();%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
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
							<li><a href="<%=path%>/admin/goAdminMgr">员工管理</a></li>
							<li class="active">员工详情</li>
						</ul><!-- .breadcrumb -->
					</div>
					<!-- 内容始 -->
					<div class="tabbable">
						<ul class="nav nav-tabs padding-12 tab-color-blue background-blue" id="myTab4">
							<li class="active">
								<a data-toggle="tab" href="#home4">员工信息</a>
							</li>
							<c:if test="${user.level ne 'X' }">
							<li>
								<a data-toggle="tab" href="#profile4">员工下线</a>
							</li>
							</c:if>

							<li>
								<a data-toggle="tab" href="#dropdown14">员工收入</a>
							</li>
						</ul>

						<div class="tab-content">
							<div id="home4" class="tab-pane in active">
								<form id="updateUserForm" class="form-horizontal" role="form" method="post">
									<input type="hidden" name="userId" value="${user.userId}"/>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-idcard">身份证号 </label>

										<div class="col-sm-9">
											<input type="text" name="idCard" value="${user.idCard}" id="form-field-idcard" class="col-xs-10 col-sm-5" />
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-name"> 姓名 </label>

										<div class="col-sm-9">
											<input type="text" name="name" value="${user.name}" id="form-field-name" class="col-xs-10 col-sm-5" />
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-sex"> 性别 </label>

										<div class="col-sm-9">
										  <div class="col-sm-1">
											<label>
												<input type="radio" class="ace" name="sex" value="1" <c:if test="${user.sex eq '1'}">checked</c:if>/>
												<span class="lbl"> 男</span>
											</label>
										  </div>
										  <div class="col-sm-1">
											<label>
												<input type="radio" class="ace" name="sex" value="2" <c:if test="${user.sex eq '2'}">checked</c:if>/>
												<span class="lbl"> 女</span>
											</label>
										  </div>
										  <div class="col-sm-2">
											<label>
												<input type="radio" class="ace" name="sex" value="3" <c:if test="${user.sex eq '3'}">checked</c:if> />
												<span class="lbl"> 保密</span>
											</label>
										  </div>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-level"> 等级 </label>
										<div class="col-sm-9">
											<select class="col-sm-5" id="form-field-level" name="level">
												<option value="B" <c:if test="${user.level eq 'B'}">selected="selected"</c:if>>B</option>
												<option value="C" <c:if test="${user.level eq 'C'}">selected="selected"</c:if>>C</option>
												<option value="D" <c:if test="${user.level eq 'D'}">selected="selected"</c:if>>D</option>
												<option value="E" <c:if test="${user.level eq 'E'}">selected="selected"</c:if>>E</option>
												<option value="X" <c:if test="${user.level eq 'X'}">selected="selected"</c:if>>X</option>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-upline"> 员工上级 </label>
										<div class="col-sm-9">
											<%-- <input type="text" name="uplineName" value="${uplineUser.name }(${uplineUser.level }级)" id="form-field-1" class="col-xs-10 col-sm-5" /> --%>
											<select name="uplineUser" class="col-sm-4 chosen-select" id="form-field-upline" data-placeholder="">
												<c:forEach items="${uplineCandidate }" var="candidate">
													<option value="${candidate.userId }" <c:if test="${user.uplineUser == candidate.userId}">selected="selected"</c:if>>${candidate.name }(${candidate.level }级)</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-birth"> 出生时间 </label>

										<div class="col-sm-9">
											<input type="text" value="<fmt:formatDate value="${user.birthday }" pattern="yyyy年MM月dd日"/>" data-date-format="yyyy年mm月dd" id="form-field-birth" class="col-xs-10 col-sm-5 date-picker" />
											<input type="hidden" name="birthday" value="${user.birthday }"/>
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-addr"> 住址 </label>

										<div class="col-sm-9">
											<input type="text" name="address" value="${user.address}" id="form-field-addr" class="col-xs-10 col-sm-5" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-career"> 职业 </label>

										<div class="col-sm-9">
											<input type="text" name="career" value="${user.career}" id="form-field-career" class="col-xs-10 col-sm-5" />
										</div>
									</div>
								</form>
									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-primary submitUpdateBtn" type="button">
												<i class="icon-ok bigger-110"></i>
												修改
											</button>
										</div>
									</div>
							</div>
							<c:if test="${user.level ne 'X' }">
							<div id="profile4" class="tab-pane">
								<table class="table table-striped table-bordered table-hover downline-table">
									<thead>
										<tr>
											<th>姓名</th>
											<th class="hidden-480">性别</th>
											<th>等级</th>
											<th class="hidden-480">收入月份</th>
											<th>上次收入</th>
											<th>上次业绩</th>
											<th class="hidden-480">职业</th>
											<th></th>
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
							</c:if>
							<div id="dropdown14" class="tab-pane">
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
								<div class="table-header">
									<a href="#modal-form" class="btn btn-xs btn-info" data-toggle="modal">
										<i class="icon-edit"></i>
										<span class="no-text-shadow">新增</span>
									</a>
								</div>
								<table class="table table-hover table-bordered income-table">
									<thead>
										<tr>
											<th>月份</th>
											<th>收入</th>
											<th>业绩</th>
											<th>是否达标</th>
											<!-- <th>操作</th> -->
										</tr>
									</thead>
									<tbody>
										<!-- <tr class="success">
											<td>2014年10月</td>
											<td>1299.8</td>
											<td>129</td>
											<td><i class="icon-remove"></i></td>
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
						</div>
					</div>
					<!-- 内容结束 -->
				</div>
		   </div>
		</div>
		<!-- modal start -->
		<div id="modal-form" class="modal" tabindex="-1">
			<div class="modal-dialog margin-top-55">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h3 class="blue bigger">您正在为 [${user.name }] 添加收入信息</h3>
					</div>

					<div class="modal-body overflow-visible">
						<div class="row">
							<form id="inputUserIncomeForm" class="form-horizontal" role="form" method="post">
								<input type="hidden" name="userId" value="${user.userId }"/>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-income">收入 </label>
		
									<div class="col-sm-9">
										<input type="text" name="income" id="form-field-income" class="col-xs-10 col-sm-8" />
									</div>
								</div>
								<c:if test="${user.level ne 'X' }">
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-perform">业绩 </label>
			
										<div class="col-sm-9">
											<input type="text" name="performance" id="form-field-perform" class="col-xs-10 col-sm-8" />
										</div>
									</div>
								</c:if>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-incomedate"> 收入月份 </label>

									<div class="col-sm-9">
										<input type="text" value="" data-date-format="yyyy年mm月dd" id="form-field-incomedate" class="col-xs-10 col-sm-8 income-date-picker" />
										<input type="hidden" name="incomeDate"/>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label no-padding-right" for="form-field-idcard">是否合格 </label>
		
									<div class="col-sm-9">
										<div class="col-sm-3">
											<label>
												<input type="radio" class="ace" name="isEnough" value="1" checked="checked"/>
												<span class="lbl"> 是</span>
											</label>
										</div>
										<div class="col-sm-3">
											<label>
												<input type="radio" class="ace" name="isEnough" value="2"/>
												<span class="lbl"> 否</span>
											</label>
										</div>
									</div>
								</div>
							</form>
						</div>
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
		<!-- basic scripts -->

		<jsp:include page="/common/inc_js.jsp"></jsp:include>
		<script src="<%=path %>/assets/js/jqPaginator.js" type="text/javascript"></script>
		<script src="<%=path %>/assets/js/jquery.gritter.min.js"></script>
		<script src="<%=path %>/assets/js/chosen.jquery.min.js"></script>
		<script src="<%=path %>/assets/js/date-time/bootstrap-datepicker.js"></script>
		<script src="<%=path %>/assets/js/jquery.form.js" type="text/javascript"></script>
		<script src="<%=path %>/jsp/mgr/adminPersonInfo.js"></script>
		<script type="text/javascript">
			var userId = ${user.userId};
			var userDetail = new UserDetail(userId);
			$(".chosen-select").chosen({no_results_text: "未找到匹配项"});
			$('.date-picker').datepicker({autoclose:true}).on('changeDate', function(ev){
				var dt = new Date(ev.date.valueOf());
				$('input[name="birthday"]').val(dt);
			});
			$('.income-date-picker').datepicker({autoclose:true}).on('changeDate', function(ev){
				var dt = new Date(ev.date.valueOf());
				$('input[name="incomeDate"]').val(dt);
			});
			 //userDetail.showUserInfo();
		</script>
</body>
</html>

