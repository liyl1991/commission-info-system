<%@ page language="java" pageEncoding="UTF-8" %>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>提成信息系统-登陆</title>
		<meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="<%=path %>/css/bootstrap.min.css" />
		<link rel="stylesheet" href="<%=path %>/css/bootstrap-responsive.min.css" />
        <link rel="stylesheet" href="<%=path %>/css/unicorn.login.css" />
    <body>
        <div id="logo">
            <img src=" img/logo.png" alt="" />
        </div>
        <div id="loginbox">            
            <form id="loginform" class="form-vertical" action="<%=path %>/jsp/index.jsp">
				<p>请入您的用户名和密码.</p>
                <div class="control-group">
                    <div class="controls">
                        <div class="input-prepend">
                            <span class="add-on"><i class="icon-user"></i></span><input type="text" placeholder="用户名" />
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="input-prepend">
                            <span class="add-on"><i class="icon-lock"></i></span><input type="password" placeholder="密码" />
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <span class="pull-left"><a href="<%=path %>/jsp/index.jsp" class="flip-link" id="to-recover">忘记密码?</a></span>
                    <span class="pull-right" style="margin-left: 14px;"><a href="<%=path %>/jsp/register.jsp" class="btn btn-info"/>注册</a></span>
                    <span class="pull-right"><input type="submit" class="btn btn-info" value="登陆" /></span>
                </div>
            </form>
            <!-- <form id="recoverform" action="#" class="form-vertical">
				<p>Enter your e-mail address below and we will send you instructions how to recover a password.</p>
				<div class="control-group">
                    <div class="controls">
                        <div class="input-prepend">
                            <span class="add-on"><i class="icon-envelope"></i></span><input type="text" placeholder="E-mail address" />
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <span class="pull-left"><a href="#" class="flip-link" id="to-login">&lt; Back to login</a></span>
                    <span class="pull-right"><input type="submit" class="btn btn-inverse" value="Recover" /></span>
                </div>
            </form> -->
        </div>
        
        <script src="<%=path %>/js/jquery.min.js"></script>  
        <script src="<%=path %>/js/unicorn.login.js"></script> 
    </body>
</html>
