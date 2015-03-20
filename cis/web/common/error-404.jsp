<%@ page contentType="text/html; charset=UTF-8"%>
<% String path = request.getContextPath();%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="utf-8" />
		<title>500错误</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<jsp:include page="inc.jsp"></jsp:include>
	</head>

	<body>
		<div class="page-content">
			<div class="row">
				<div class="col-xs-12">
					<!-- PAGE CONTENT BEGINS -->

					<div class="error-container">
						<div class="well">
							<h1 class="grey lighter smaller">
								<span class="blue bigger-125">
									<i class="icon-random"></i>
									404
								</span>
								找不到您请求的路径
							</h1>

							<hr />
							<h3 class="lighter smaller">
								请确认您的输入的地址！
							</h3>

							<hr />
							<div class="space"></div>

							<div class="center">
								<a href="javascript:history.go(-1);" class="btn btn-grey">
									<i class="icon-arrow-left"></i>
									返回
								</a>
							</div>
						</div>
					</div>

					<!-- PAGE CONTENT ENDS -->
				</div><!-- /.col -->
			</div><!-- /.row -->
		</div><!-- /.page-content -->
</body>
</html>

