<%@ page language="java" pageEncoding="UTF-8" %>
<% String path = request.getContextPath();%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<!DOCTYPE html>
<html>
	<head>
		<title>密码修改</title>
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
							<li class="active">密码管理</li>
						</ul><!-- .breadcrumb -->
					</div>
					<div class="container">
						<div class="page-content">
							<div class="page-header">
								<h1>
									密码管理
									<small>
										<i class="icon-double-angle-right"></i>
										请输入您的当前密码并设置新密码
									</small>
								</h1>
							</div><!-- /.page-header -->
							<div class="row">
							<div class="col-xs-12">
								<!-- PAGE CONTENT BEGINS -->
									<div class="row alterContent"></div>
									<form class="form-horizontal" role="form">
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-2"> 旧密码 </label>
										<div class="col-sm-9">
											<input type="password" name="oldPwd" maxlength="50" id="form-field-2" placeholder="请输入" class="col-xs-10 col-sm-5" />
											<span class="help-inline col-xs-12 col-sm-7">
												<!-- <span class="middle">请输入</span> -->
											</span>
										</div>
									</div>

									<div class="space-4"></div>
									
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-2"> 新密码 </label>

										<div class="col-sm-9">
											<input type="password" name="newPwd" maxlength="50" id="form-field-2" placeholder="请输入" class="col-xs-10 col-sm-5" />
											<span class="help-inline col-xs-12 col-sm-7">
												<!-- <span class="middle">Inline help text</span> -->
											</span>
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-right" for="form-field-2"> 确认密码 </label>

										<div class="col-sm-9">
											<input type="password" name="newPwd2" maxlength="50" id="form-field-2" placeholder="请输入" class="col-xs-10 col-sm-5" />
											<span class="help-inline col-xs-12 col-sm-7">
												<!-- <span class="middle">Inline help text</span> -->
											</span>
										</div>
									</div>

									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<button class="btn btn-info submitBtn" type="button">
												<i class="icon-ok bigger-110"></i>
												确认
											</button>
										</div>
									</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
		   </div>
		</div>
		<!-- basic scripts -->

		<jsp:include page="/common/inc_js.jsp"></jsp:include>
		<script type="text/javascript">
		$(function(){
			$(".submitBtn").click(function(){///user/doUpdatePwd
				var obj = {};
				obj.oldPwd =$("input[name='oldPwd']").val();
				obj.newPwd =$("input[name='newPwd']").val();
				obj.newPwd2 =$("input[name='newPwd2']").val();
				if(!(obj.oldPwd&&obj.newPwd&&obj.newPwd2)){
					showInfo("输入的信息不完整",false);
					return;
				}
				if(obj.newPwd!=obj.newPwd2){
					showInfo("两次输入的密码不一致",false);
					return;	
				}
				$.ajax({
					url:path+'/user/doUpdatePwd',
					type:"post",
					dataType:"json",
					data:obj,
					success:function(r){
						showInfo(r.msg,r.result);
					},
					error:function(){
						
					}
				});
			});
		});
		function showInfo(msg,flag){
			var alertCnt = $(".alterContent .alert").length;
			if(alertCnt!=0) $(".alterContent .alert").remove();
			var status = flag?'success':'danger';
			var icon = flag?'ok':'remove';
			var iconColor = flag?'green':'red';
			$(".alterContent").append(
				'<div class="alert alert-block alert-'+status+'">'+
				'	<button type="button" class="close" data-dismiss="alert">'+
				'		<i class="icon-remove"></i>'+
				'	</button>'+
				'	<i class="icon-'+icon+' '+iconColor+'"></i>'+msg+
				'</div>');
		}
		</script>

</body>
</html>

