<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
			<!-- 最新情况统计 -->
			<jsp:include page="latestHeader.jsp"></jsp:include>
			

			<h1 class="page-title">工作台</h1>
		</div>

		<ul class="breadcrumb">
			<li><a
				href="${pageContext.request.contextPath}/menu!homepage.action">首页</a>
				<span class="divider">/</span>
			</li>
			<li class="active">工作台</li>
		</ul>

		<div class="container-fluid">
			<div class="row-fluid">

				<div class="dialog">
					<div class="block">
						<p class="block-heading">用户注册</p>
						<div class="block-body">
							<form method="post" 
		action="${pageContext.request.contextPath }/userinfo!signup.action">
								<label>姓名</label> <input type="text" class="x-large" class="span12" name="info.realname" />
									<p>填写真实姓名</p>
								<label>账户</label> <input type="text" class="x-large" class="span12" name="info.loginname" />
									<p>填写登录名，字符数字组合，不要使用中文!</p>
								<label>密码</label> <input type="password" class="x-large" class="span12" name="info.loginpwd" />
									<p>填写登录密码</p>
								<div style="color:red;font-size:1.5ex;">${signupMsg }</div>
							<input name="submit" value="增加" class="btn" id="addyear" type="submit" />
								<div class="clearfix"></div>
							</form>
							
						</div>
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
			$('.demo-cancel-click').click(function() {
				return false;
			});
		});
	</script>

</body>
</html>
