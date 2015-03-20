<%@ page contentType="text/html; charset=UTF-8"%>
<% String path = request.getContextPath();%>
<meta charset="utf-8">
<meta name="keywords" content="福清经纪人系统,经纪人系统,好好财经网,福清好好财经网" />
<meta name="description" content="福清经纪人系统,经纪人系统,好好财经网,福清好好财经网" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- basic styles -->
<link href="<%=path %>/assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=path %>/assets/css/font-awesome.min.css" />

<!--[if IE 7]>
  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
<![endif]-->

<!-- page specific plugin styles -->

<!-- ace styles -->

<link rel="stylesheet" href="<%=path %>/assets/css/ace.min.css" />
<link rel="stylesheet" href="<%=path %>/assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="<%=path %>/assets/css/ace-skins.min.css" />
<link rel="stylesheet" href="<%=path %>/css/style.css" />
<!--[if lte IE 8]>
  <link rel="stylesheet" href="<%=path %>/assets/css/ace-ie.min.css" />
<![endif]-->

<!-- inline styles related to this page -->

<!-- ace settings handler -->

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>
<script src="<%=path %>/assets/js/html5shiv.js"></script>
<script src="<%=path %>/assets/js/respond.min.js"></script>
<![endif]-->
<script type="text/javascript">
var path = '<%=path %>';
if (!Array.prototype.indexOf)
{
  Array.prototype.indexOf = function(elt /*, from*/)
  {
    var len = this.length >>> 0;
    var from = Number(arguments[1]) || 0;
    from = (from < 0)
         ? Math.ceil(from)
         : Math.floor(from);
    if (from < 0)
      from += len;
    for (; from < len; from++)
    {
      if (from in this &&
          this[from] === elt)
        return from;
    }
    return -1;
  };
}
function formatDate(str){
	if(str){
		var dt = new Date(str);
	  	var yy = dt.getFullYear();
	  	var mm= dt.getMonth()+1<10?'0'+(dt.getMonth()+1):(dt.getMonth()+1);
	  	/*var dd = dt.getDate()<10?'0'+dt.getDate():dt.getDate();
	  	 var hh = dt.getHours()<10?'0'+dt.getHours():dt.getHours();
	  	var mi = dt.getMinutes()<10?'0'+dt.getMinutes():dt.getMinutes(); */
	  	return yy+"年"+mm+"月";
	}else{
		return '';
	}
}
</script>