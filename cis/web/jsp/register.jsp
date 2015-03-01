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
		<link rel="stylesheet" href="<%=path %>/css/unicorn.main.css" />
    <body>
        <div class="row-fluid">
			<div class="span12">
				<div class="widget-box">
					<div class="widget-title">
						<span class="icon">
							<i class="icon-align-justify"></i>									
						</span>
						<h5>注册</h5>
						<span class="label label-important">48 notices</span>
					</div>
					<div class="widget-content nopadding">
						<form class="form-horizontal" method="post" action="#" name="basic_validate" id="basic_validate" novalidate="novalidate">
                            <div class="control-group">
                                <label class="control-label">Required</label>
                                <div class="controls">
                                    <input type="text" name="required" id="required" />
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">Email</label>
                                <div class="controls">
                                    <input type="text" name="email" id="email" />
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">Date</label>
                                <div class="controls">
                                    <input type="password" name="date" id="date" />
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">URL</label>
                                <div class="controls">
                                    <input type="password" name="url" id="url" />
                                </div>
                            </div>
                            <div class="form-actions">
                                <input type="submit" value="Validate" class="btn btn-primary" />
                            </div>
                        </form>
					</div>
				</div>			
			</div>
		</div>
        <script src="<%=path %>/js/excanvas.min.js"></script>
        <script src="<%=path %>/js/jquery.min.js"></script>
        <script src="<%=path %>/js/jquery.ui.custom.js"></script>
        <script src="<%=path %>/js/bootstrap.min.js"></script>
        <script src="<%=path %>/js/jquery.flot.min.js"></script>
        <script src="<%=path %>/js/jquery.flot.resize.min.js"></script>
        <script src="<%=path %>/js/jquery.peity.min.js"></script>
        <script src="<%=path %>/js/unicorn.js"></script>
    </body>
</html>
