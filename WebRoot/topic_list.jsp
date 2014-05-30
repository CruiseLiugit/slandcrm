<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>妮欧甲艺App后台管理系统 | 汉甲</title>
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="keywords" content="武汉华苑艺龙公司,刘立立,窗帘,ipad,iphone,ios,华苑艺龙" />
<meta name="description"
	content="本系统是由刘立立，为武汉华苑艺龙公司开发的 《武汉华苑艺龙公司 App 》后台管理系统，联系方式 836131325@qq.com." />

<title>武汉华苑艺龙公司App后台管理系统</title>

<link rel="stylesheet" type="text/css"
	href="lib/bootstrap/css/bootstrap.css">

<link rel="stylesheet" type="text/css" href="stylesheets/theme.css">
<link rel="stylesheet" href="lib/font-awesome/css/font-awesome.css">

<script src="lib/jquery-1.7.2.min.js" type="text/javascript"></script>

<!-- Demo page code -->
<style type="text/css">
#line-chart {
	height: 300px;
	width: 800px;
	margin: 0px auto;
	margin-top: 1em;
}

.brand {
	font-family: georgia, serif;
}

.brand .first {
	color: #ccc;
	font-style: italic;
}

.brand .second {
	color: #fff;
	font-weight: bold;
}
</style>

<!-- Le HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

<!-- Le fav and touch icons -->
<link rel="shortcut icon" href="../assets/ico/favicon.ico">
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="../assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="../assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="../assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="../assets/ico/apple-touch-icon-57-precomposed.png">
</head>

<!--[if lt IE 7 ]> <body class="ie ie6"> <![endif]-->
<!--[if IE 7 ]> <body class="ie ie7 "> <![endif]-->
<!--[if IE 8 ]> <body class="ie ie8 "> <![endif]-->
<!--[if IE 9 ]> <body class="ie ie9 "> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!-->
<body class="">
	<!--<![endif]-->

	<jsp:include page="header.jsp"></jsp:include>

	<jsp:include page="menu.jsp"></jsp:include>



	<div class="content">

		<div class="header">
			<h1 class="page-title">主题管理</h1>
		</div>

		<ul class="breadcrumb">
			<li><a
				href="${pageContext.request.contextPath}/menu!homepage.action">首页</a>
				<span class="divider">/</span>
			</li>
			<li><a
				href="${pageContext.request.contextPath}/menu!periodicalpage.action">期刊</a>
				<span class="divider">/</span>
			</li>
			<li class="active">增加期刊</li>
		</ul>


		<div class="container-fluid">
			<div class="row-fluid">
			
				<div class="btn-toolbar">
						<button class="btn btn-primary disabled" onClick="window.history.back();">
							<i class="icon-plus"></i>后退
						</button>
					<div class="btn-group"></div>
				</div>
				
			
				<div class="block">
					<p class="block-heading">
						<s:property value="#request.month" />
						期刊主题
					</p>
					<!-- 显示模式切换 
					<div class="row-fluid">
						&nbsp;&nbsp; <a
							href="#"
							onclick="return post('periodical!seeimages.action', {cm1:'<s:property
												value="#request.month" />'});"
							title="缩略图模式显示&#10;查看每个主题的缩略图，不能编辑"><i class="icon-th-large"></i>
						</a>&nbsp;&nbsp; <a
							href="#"
							onclick="return post('periodical!seelist.action', {cm1:'<s:property
												value="#request.month" />'});"
							title="列表模式显示&#10;列表状态下，可以编辑每个主题"><i class="icon-list"></i> </a>
						<div class="btn-group"></div>
					</div>
					-->
					
					<table class="table">
						<thead>
							<tr>
								<th>总刊号</th>
								<th>月刊号</th>
								<th>标题</th>
								<th style="width: 26px;"></th>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="#request.periodical_list" var="peris">
								<tr>
									<td><s:property value="#peris.column2" /></td>
									<td><s:property value="#peris.column1" /></td>
									<td><a href="periodical!seeimages.action?cm1=<s:property value="#peris.id" />"><s:property
												value="#peris.coverpageName" /> </a></td>
									<td><a href="#"
										onclick="return post('periodical!readytoAddTopic.action', {cm1:'<s:property
												value="#peris.id" />',pmonth:'<s:property value="#request.month" />',pernum:'<s:property value="#peris.column1" />'});"><i
											class="icon-plus"></i> </a> 
											<!--  删除按钮
									<a href="#myModal" role="button"
										data-toggle="modal"><i class="icon-remove"></i> </a>-->
										</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>




				<div class="modal small hide fade" id="myModal" tabindex="-1"
					role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h3 id="myModalLabel">删除提示</h3>
					</div>
					<div class="modal-body">
						<p class="error-text">
							<i class="icon-warning-sign modal-icon"></i>您确定要删除该期期刊以及附带的所有杂志内容?
						</p>
					</div>
					<div class="modal-footer">
						<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
						<a href="#"
							onclick="return post('periodical!readytoDeletTopic.action', {cm1:'<s:property
												value="#peris.id" />'});"><button
								class="btn btn-danger" data-dismiss="modal">删除</button>
						</a>
						<script type="text/javaScript">
											function post(URL, PARAMS) {
												var temp = document
														.createElement("form");
												temp.action = URL;
												temp.method = "post";
												temp.style.display = "none";
												for ( var x in PARAMS) {
													var opt = document
															.createElement("input");
													opt.name = x;
													opt.value = PARAMS[x];
													// alert(opt.name)        
													temp.appendChild(opt);
												}
												document.body.appendChild(temp);
												temp.submit();
												return temp;
											}
										</script>
					</div>
				</div>


				<jsp:include page="footer.jsp"></jsp:include>

			</div>
		</div>
	</div>



	<script src="lib/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript">
        $("[rel=tooltip]").tooltip();
        $(function() {
            $('.demo-cancel-click').click(function(){return false;});
        });
    </script>

</body>
</html>

