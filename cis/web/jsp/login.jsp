<%@ page language="java" pageEncoding="UTF-8" %>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
	<head>
		<title>经纪人系统</title>
		<jsp:include page="/common/inc.jsp"></jsp:include>
	</head>

	<body class="login-layout">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<i class="icon-leaf green"></i>
									<span class="red">好好财经网</span>
									<span class="white">经纪人系统</span>
								</h1>
								<!-- <h4 class="blue">&copy; Company Name</h4> -->
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="icon-coffee green"></i>
												请输入您的信息！
											</h4>

											<div class="space-6 errorPre"></div>
											<form id="loginForm" action="<%=path%>/login/doLogin" method="post">
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" name="idCard" class="form-control" placeholder="身份证号" />
															<i class="icon-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" name="password" class="form-control" placeholder="密码" />
															<i class="icon-lock"></i>
														</span>
													</label>

													<div class="space"></div>

													<div class="clearfix">
														<button type="button" class="width-35 pull-right btn btn-sm btn-primary loginBtn">
															<i class="icon-key"></i>
															登陆
														</button>
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>
										</div><!-- /widget-main -->

										<div class="toolbar clearfix">
											<!-- <div>
												<a href="#" onclick="show_box('forgot-box'); return false;" class="forgot-password-link">
													<i class="icon-arrow-left"></i>
													忘记密码
												</a>
											</div> -->

											<div>
												<a href="#" onclick="show_box('signup-box'); return false;" class="user-signup-link">
													<i class="icon-arrow-left"></i>
													我要注册
													<!-- <i class="icon-arrow-right"></i> -->
												</a>
											</div>
										</div>
									</div><!-- /widget-body -->
								</div><!-- /login-box -->

								<div id="forgot-box" class="forgot-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header red lighter bigger">
												<i class="icon-key"></i>
												找回密码
											</h4>

											<div class="space-6"></div>
											<p>
												请输入您的邮箱地址：
											</p>

											<form>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email" class="form-control" placeholder="Email" />
															<i class="icon-envelope"></i>
														</span>
													</label>

													<div class="clearfix">
														<button type="button" class="width-35 pull-right btn btn-sm btn-danger">
															<i class="icon-lightbulb"></i>
															发送!
														</button>
													</div>
												</fieldset>
											</form>
										</div><!-- /widget-main -->

										<div class="toolbar center">
											<a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">
												返回登陆
												<i class="icon-arrow-right"></i>
											</a>
										</div>
									</div><!-- /widget-body -->
								</div><!-- /forgot-box -->

								<div id="signup-box" class="signup-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header green lighter bigger">
												<i class="icon-group blue"></i>
												用户注册
											</h4>

											<div class="space-6"></div>
											<p> 请输入您的信息: </p>

											<form>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="姓名" />
															<i class="icon-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="密码" />
															<i class="icon-lock"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="确认密码" />
															<i class="icon-retweet"></i>
														</span>
													</label>
													
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input class="form-control date-picker" id="id-date-picker-1" placeholder="出生年月" type="text" data-date-format="yyyy-mm-dd" />
															<i class="icon-calendar bigger-110"></i>
														</span>
													</label>
													
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="住址" />
															<i class="icon-home"></i>
														</span>
													</label>
													
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="职业" />
															<i class="icon-coffee"></i>
														</span>
													</label>
													<!-- <label class="block">
														<input type="checkbox" class="ace" />
														<span class="lbl">
															I accept the
															<a href="#">User Agreement</a>
														</span>
													</label> -->

													<div class="space-24"></div>

													<div class="clearfix">
														<button type="reset" class="width-30 pull-left btn btn-sm">
															<i class="icon-refresh"></i>
															重置
														</button>

														<button type="button" class="width-65 pull-right btn btn-sm btn-success">
															注册
															<i class="icon-arrow-right icon-on-right"></i>
														</button>
													</div>
												</fieldset>
											</form>
										</div>

										<div class="toolbar center">
											<a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">
												<i class="icon-arrow-left"></i>
												返回登陆
											</a>
										</div>
									</div><!-- /widget-body -->
								</div><!-- /signup-box -->
							</div><!-- /position-relative -->
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div>
		</div><!-- /.main-container -->

		<!-- basic scripts -->


		<script type="text/javascript">
			window.jQuery || document.write("<script src='"+path+"/assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='"+path+"/assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
		</script>
		<![endif]-->
		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='"+path+"/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>

		<!-- inline scripts related to this page -->
		<script src="<%=path %>/assets/js/date-time/bootstrap-datepicker.js"></script>
		<script type="text/javascript">
			var errorMsg = '${errorMsg}';
			function show_box(id) {
				 jQuery('.widget-box.visible').removeClass('visible');
				 jQuery('#'+id).addClass('visible');
			}
			$(function(){
				/* $('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
					$(this).prev().focus();
				}); */
				//登陆事件绑定
				$(".loginBtn").click(function(){
					var idCard = $("#loginForm input[name='idCard']").val();
					var pwd = $("#loginForm input[name='password']").val();
					if(idCard&&pwd)
						$("#loginForm").submit();
					else
						showError("请输入您的登陆信息");
				});
				//关闭警告框事件
				$(".login-box").on('click','.alert .close',function(){
					$(this).parent(".alert").remove();
				});
				if(errorMsg){
					showError(errorMsg);
				}
			});
			function showError(msg){
				var alertCnt = $(".login-box .alert").length;
				if(alertCnt!=0) $(".login-box .alert").remove();
				$(".errorPre").after(
					'<div class="alert alert-block alert-danger">'+
					'	<button type="button" class="close" data-dismiss="alert">'+
					'		<i class="icon-remove"></i>'+
					'	</button>'+
					'	<i class="icon-ok red"></i>'+msg+
					'</div>');
			}
		</script>

</body>
</html>
