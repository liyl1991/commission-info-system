<%@ page language="java" pageEncoding="UTF-8" %>
<% String path = request.getContextPath();%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html>
	<head>
		<title>员工管理</title>
		<jsp:include page="/common/inc.jsp"></jsp:include>
		<link rel="stylesheet" href="<%=path %>/assets/css/jquery.gritter.css" />
		<script src="<%=path %>/assets/js/ace-extra.min.js"></script>
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
							<li class="active"><a href="<%=path%>/noticeMgr/goNoticeMgr">公告管理</a></li>
							<li class="active">公告修改</li>
						</ul><!-- .breadcrumb -->
					</div>
					<div class="container">
						<div class="space-24"></div>
						<div class="col-xs-1"></div>
						<div class="col-xs-10">
							<div class="row form-elements">
								<label class="no-padding-left" for="form-field-title">标题：</label>
								<div class="no-padding-left">
									<input type="hidden" value="${notice.noticeId }" name="noticeId"/>
									<input type="text" value="${notice.title }" name="title" id="form-field-title"/>
								</div>
							</div>
							<div class="space-4"></div>
							<div class="row">
								<div class="wysiwyg-editor" id="editor">${notice.content }</div>
							</div>
							<div class="row">
								<div class="checkbox no-padding-left">
									<label>
										<input name="form-field-checkbox" type="checkbox" class="ace" id="isTop" <c:if test="${notice.topFlag ==1 }">checked="checked"</c:if>/>
										<span class="lbl"> 置顶</span>
									</label>
								</div>
							</div>
							<div>
								<a class="btn btn-primary" href="#" id="launchNoticeBtn">修改</a>
							</div>
						</div>
					</div>
				</div>
		   </div>
		</div>
		<!-- basic scripts -->

		<jsp:include page="/common/inc_js.jsp"></jsp:include>
		<script src="<%=path %>/assets/js/jqPaginator.js" type="text/javascript"></script>
		<script src="<%=path %>/assets/js/typeahead-bs2.min.js"></script>
		<script src="<%=path %>/assets/js/bootstrap-wysiwyg.min.js"></script>
		<script src="<%=path %>/assets/js/jquery.hotkeys.min.js"></script>
		<script src="<%=path %>/assets/js/jquery.gritter.min.js"></script>
		<script type="text/javascript">
		$(function(){
			$('#launchNoticeBtn').click(function(){
				var id = $('input[name="noticeId"]').val();
				var title = $('input[name="title"]').val();
				var content = $('#editor').html(); 
				var topFlag = ($('#isTop').prop('checked')?1:2);
				if(!title){
					$.gritter.add({
						title: '操作失败',
						text: '标题不能为空',
						time:'3700',
						class_name: 'gritter-error gritter-light'
					});
					return;
				}
				$.ajax({
					'url':path+'/noticeMgr/doUpdateNotice',
					'type':'post',
					'dataType':'json',
					'data':{'noticeId':id,'title':title,'content':content,'topFlag':topFlag},
					'success':function(r){
						if(r.result)
							$.gritter.add({
								title: '公告修改成功',
								text: '',
								class_name: 'gritter-success gritter-light'
							});
						else{
							$.gritter.add({
								title: '公告修改失败',
								text: r.msg,
								class_name: 'gritter-error gritter-light'
							});
						}
					},
					'error':function(){
						
					}
				});
			});
		});
		jQuery(function($){
			function showErrorAlert (reason, detail) {
				var msg='';
				if (reason==='unsupported-file-type') { msg = "Unsupported format " +detail; }
				else {
					console.log("error uploading file", reason, detail);
				}
				$('<div class="alert"> <button type="button" class="close" data-dismiss="alert">&times;</button>'+ 
				 '<strong>File upload error</strong> '+msg+' </div>').prependTo('#alerts');
			}
			$('#editor').ace_wysiwyg({
				toolbar:
				[
					'font',
					null,
					'fontSize',
					null,
					{name:'bold', className:'btn-info'},
					{name:'italic', className:'btn-info'},
					{name:'strikethrough', className:'btn-info'},
					{name:'underline', className:'btn-info'},
					null,
					{name:'insertunorderedlist', className:'btn-success'},
					{name:'insertorderedlist', className:'btn-success'},
					{name:'outdent', className:'btn-purple'},
					{name:'indent', className:'btn-purple'},
					null,
					{name:'justifyleft', className:'btn-primary'},
					{name:'justifycenter', className:'btn-primary'},
					{name:'justifyright', className:'btn-primary'},
					{name:'justifyfull', className:'btn-inverse'},
					null,
					{name:'createLink', className:'btn-pink'},
					{name:'unlink', className:'btn-pink'},
					null,
					{name:'insertImage', className:'btn-success'},
					null,
					'foreColor',
					null,
					{name:'undo', className:'btn-grey'},
					{name:'redo', className:'btn-grey'}
				],
				'wysiwyg': {
					fileUploadError: showErrorAlert
				}
			}).prev().addClass('wysiwyg-style2');
			
			if ( typeof jQuery.ui !== 'undefined' && /applewebkit/.test(navigator.userAgent.toLowerCase()) ) {
				
				var lastResizableImg = null;
				function destroyResizable() {
					if(lastResizableImg == null) return;
					lastResizableImg.resizable( "destroy" );
					lastResizableImg.removeData('resizable');
					lastResizableImg = null;
				}

				var enableImageResize = function() {
					$('.wysiwyg-editor')
					.on('mousedown', function(e) {
						var target = $(e.target);
						if( e.target instanceof HTMLImageElement ) {
							if( !target.data('resizable') ) {
								target.resizable({
									aspectRatio: e.target.width / e.target.height,
								});
								target.data('resizable', true);
								
								if( lastResizableImg != null ) {//disable previous resizable image
									lastResizableImg.resizable( "destroy" );
									lastResizableImg.removeData('resizable');
								}
								lastResizableImg = target;
							}
						}
					})
					.on('click', function(e) {
						if( lastResizableImg != null && !(e.target instanceof HTMLImageElement) ) {
							destroyResizable();
						}
					})
					.on('keydown', function() {
						destroyResizable();
					});
					}
					enableImageResize();
			    }
			});
		</script>
</body>
</html>

