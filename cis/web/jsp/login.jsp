<%@ page language="java" pageEncoding="UTF-8" %>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html>
	<head>
		<title>经纪人系统</title>
		<jsp:include page="/common/inc.jsp"></jsp:include>
		<link rel="stylesheet" href="<%=path %>/assets/css/jquery.gritter.css" />
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
									<span class="blue">经纪人系统</span>
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
															<input type="text" maxlength="20" name="idCard" class="form-control" placeholder="身份证号" />
															<i class="icon-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" maxlength="50" name="password" class="form-control" placeholder="密码" />
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

											<form class="form-horizontal" role="form" id="registerForm">
 													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="form-field-idcard">身份证号 </label>
				
														<div class="col-sm-9">
															<input type="text" name="idCard"  maxlength="20" id="form-field-idcard" class="col-sm-12" />
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="form-field-name"> 姓名 </label>
				
														<div class="col-sm-9">
															<input type="text" name="name" id="form-field-name" class="col-sm-12" />
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="form-field-pwd"> 密码 </label>
				
														<div class="col-sm-9">
															<input type="password" name="pwd" id="form-field-pwd" class="col-sm-12" />
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="form-field-affirmPwd"> 确认密码</label>
				
														<div class="col-sm-9">
															<input type="password" name="affirmPwd" id="form-field-affirmPwd" class="col-sm-12" />
														</div>
													</div>		
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="form-field-sex"> 性别 </label>
				
														<div class="col-sm-9">
														  <div class="col-sm-3" style="padding-left: 0px;">
															<label>
																<input type="radio" class="ace" name="sex" value="1" checked="checked"/>
																<span class="lbl"> 男</span>
															</label>
														  </div>
														  <div class="col-sm-4">
															<label>
																<input type="radio" class="ace" name="sex" value="2"/>
																<span class="lbl"> 女</span>
															</label>
														  </div>
														  <div class="col-sm-5">
															<label>
																<input type="radio" class="ace" name="sex" value="3"/>
																<span class="lbl"> 保密</span>
															</label>
														  </div>
														</div>
													</div>
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="form-field-birthday"> 出生年月</label>
				
														<div class="col-sm-9">
															<input type="text" data-date-format="yyyy年mm月dd日" id="form-field-birthday"  placeholder="年/月/日" class="col-sm-12 birthdate-picker" />
															<input type="hidden" name="birthday" />													
														</div>
													</div>	
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="form-field-career"> 职业</label>
				
														<div class="col-sm-9">
															<input type="text" name="career" id="form-field-career" class="col-sm-12" />
														</div>
													</div>	
													<div class="form-group">
														<label class="col-sm-3 control-label no-padding-right" for="form-field-address"> 住址</label>
				
														<div class="col-sm-9">
															<input type="text" name="address" id="form-field-address" class="col-sm-12" />
															
														</div>
													</div>	
													<div class="space-4"></div>
													<div class="clearfix">
														<button type="reset" class="width-30 pull-left btn btn-sm">
															<i class="icon-refresh"></i>
															重置
														</button>

														<button type="button" class="width-65 pull-right btn btn-sm btn-success registerBtn">
															注册
															<i class="icon-arrow-right icon-on-right"></i>
														</button>
													</div>
												<!-- </fieldset> -->
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
		<script src="<%=path %>/assets/js/jquery.form.js" type="text/javascript"></script>
		<script src="<%=path %>/assets/js/jquery.gritter.min.js"></script>
		<script type="text/javascript">
			var errorMsg = '${errorMsg}';
			$(function(){
				//登陆事件绑定
				$(".loginBtn").click(function(){
					var idCard = $("#loginForm input[name='idCard']").val();
					var pwd = $("#loginForm input[name='password']").val();
					if(idCard&&pwd)
						$("#loginForm").submit();
					else
						showError("请输入您的登陆信息");
				});
				//注册事件绑定
				$(".registerBtn").click(function(){
					var validateResult = registerValidate();
					if(validateResult){
						$.gritter.add({
							title: '注册失败',
							text: validateResult,
							time:'5000',
							class_name: 'gritter-error gritter-light'
						});				
						return;
					}
					$("#registerForm").ajaxSubmit({
						url:path+"/login/doRegister",
			    		type:'post',
				    	"dataType":"json",
				    	success: function (r) {
				    		if(r.result){
				    			$.gritter.add({
									title: '注册成功',
									text: '请等待审核',
									time:'5000',
									class_name: 'gritter-success gritter-light'
								});
				    		}else{
				    			$.gritter.add({
									title: '注册失败',
									text: r.msg,
									time:'5000',
									class_name: 'gritter-error gritter-light'
								});
				    		}
				    	},
						error:function(){
							$.gritter.add({
								title: '操作失败',
								text: '',
								time:'5000',
								class_name: 'gritter-error gritter-light'
							});
						}
					});
				});
				
				//关闭警告框事件
				$(".login-box").on('click','.alert .close',function(){
					$(this).parent(".alert").remove();
				});
				if(errorMsg){
					showError(errorMsg);
				}
				
				$('.birthdate-picker').datepicker({autoclose:true}).on('changeDate', function(ev){
					var dt = new Date(ev.date.valueOf());
					$('input[name="birthday"]').val(dt);
				});
			});
			function show_box(id) {
				 jQuery('.widget-box.visible').removeClass('visible');
				 jQuery('#'+id).addClass('visible');
			}
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
			function registerValidate(){
				//if($.trim($('.signup-box input[name="idCard"]').val()).length==0)
				if(!$('.signup-box input[name="idCard"]').val())
					return '请输入您的身份证号码!';
				if(!$('.signup-box input[name="name"]').val())
					return '请输入您姓名!';
				if(!$('.signup-box input[name="pwd"]').val())
					return '请输入密码!';
				if(!$('.signup-box input[name="affirmPwd"]').val())
					return '请输入确认密码!';
				if($('.signup-box input[name="affirmPwd"]').val()!=$('.signup-box input[name="pwd"]').val())
					return '两次输入密码不一致';
				if((!$('.signup-box input[name="birthday"]').val())||
						(!$('#form-field-birthday').val()))
					return '请选择您的出生日期!';
				return null;
			}
		</script>

</body>
</html>
