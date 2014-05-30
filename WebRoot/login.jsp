<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 当前页面不使用 session ，关闭session -->
<%@page session="false"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
	window.optimizely_is_free = true;
</script>
<script src="//cdn.optimizely.com/js/10955010.js"></script>

<meta charset="UTF-8" />
<title>登录</title>
<meta name="keywords" content="武汉华苑艺龙公司,刘立立,窗帘,ipad,iphone,ios,华苑艺龙" />
<meta name="description"
	content="本系统是由刘立立，为武汉华苑艺龙公司开发的 《武汉华苑艺龙公司 App 》后台管理系统，联系方式 836131325@qq.com." />

<title>武汉华苑艺龙公司App后台管理系统</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<!-- Bootstrap -->
<link
	href="${pageContext.request.contextPath}/dist/css/bootstrap.min.css"
	rel="stylesheet" />
<style>
/* http://css-tricks.com/perfect-full-page-background-image/ */
html {
	background: url(images/136246642448.jpg) no-repeat center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}

body {
	padding-top: 20px;
	font-size: 16px;
	font-family: "Open Sans", serif;
	background: transparent;
}

h1 {
	font-family: "Abel", Arial, sans-serif;
	font-weight: 400;
	font-size: 40px;
}

/* Override B3 .panel adding a subtly transparent background */
.panel {
	background-color: rgba(255, 255, 255, 0.9);
}

.margin-base-vertical {
	margin: 40px 0;
}

/* footer */
.legal{
	color:#888888;
}
</style>

<!-- include stylesheets here... -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/build/css/messenger.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/build/css/messenger-spinner.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/build/css/messenger-theme-air.css">
<!-- include source files here... -->
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/build/js/messenger.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						//选择好弹出框的样式
						$._messengerDefaults = {
							extraClasses : 'messenger-fixed messenger-theme-air messenger-on-top'
						};

						//$.globalMessenger().post("请输入用户名和密码!");
						$.globalMessenger().post({
							message : '请输入用户名和密码!',
							type : 'error',
							hideAfter : 6,
							showCloseButton : true
						});

						$("#login").click(function() {
							var name = $("#login_name").val();
							var pwd = $("#login_password").val();
							if (name == "") {
								$.globalMessenger().post({
									message : "用户名不能为空!",
									hideAfter : 4,
									type : 'error',
								});
								return false;
							}
							if (pwd == "") {
								$.globalMessenger().post({
									message : "密码不能为空!",
									hideAfter : 4,
									type : 'error',
								});
								return false;
							}
							return true;
						});
					});
</script>

</head>


<body>
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3 panel panel-default">

				<h1 class="margin-base-vertical">华苑艺龙 App 后台管理系统</h1>

				<p>华苑艺龙布艺品牌，定制 App 管理后台。</p>
				<p style="color:red;font-size:3ex;">${loginerror }</p>

				<form class="margin-base-vertical" id="frmLogin" class="login_form"
					method="post"
					action="${pageContext.request.contextPath }/userinfo!login.action">
					<input type="hidden" name="login[pending_tsm_group_guid]"
						id="login_pending_tsm_group_guid" /> <input type="hidden"
						name="login[redirect_url]"
						value="https://www.hellofax.com/home/team" id="login_redirect_url" />
					<input type="hidden" name="login[error_redirect_url]"
						id="login_error_redirect_url" />

					<p class="input-group">
						<span class="input-group-addon"><span
							class="glyphicon glyphicon-user"></span>
						</span> <input type="text" class="form-control input-lg" id="login_name" name="info.loginname"
							placeholder="账户名" />
					</p>
					<p class="input-group">
						<span class="input-group-addon"><span
							class="glyphicon glyphicon-lock"></span>
						</span> <input type="password" class="form-control input-lg"  id="login_password" name="info.loginpwd"
							placeholder="密码" />
					</p>
					<p class="help-block text-center">
						<small>请输入管理员分配的账户.</small>
					</p>
					<p class="text-center">
						<button type="submit" id="login" class="btn btn-success btn-lg">登 录</button>
					</p>
					</span>
				</form>

				<div class="margin-base-vertical">
					<small class="text-muted"> <a href="#">开发者邮箱</a>.
						836131325@qq.com</small>
				</div>

			</div>
			<!-- //main content -->
		</div>
		<!-- //row -->
	</div>
	<!-- //container -->


<hr />




	<!-- footer begin -->
	<div id="footer">
		<div class="footer_container">

			<div class="inner border-box">
				<div id="footer_links" class="clearfix">
					<!-- 友情链接 -->
				</div>
				<br />
				<p class="legal">
					&copy; <strong>武汉华苑艺龙有限公司, Inc.</strong>, 2014. All rights
					reserved.
				</p>
			</div>

		</div>
		<!-- footer end -->
</body>
</html>
