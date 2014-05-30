<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>My JSP 'fileup.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>
	<!-- enctype 默认是 application/x-www-form-urlencoded -->
	<form action="FileUpload2.action" enctype="multipart/form-data"
		method="post">

		用户名：<input type="text" name="usename"> <br /> 上传文件：<input
			type="file" name="file1"><br /> <input type="submit"
			value="提交" />
	</form>

	<hr />
	<h1>html 5 新属性，多图上传</h1>
	<!-- html 5 新属性，按住 control 选择多图 -->
	<form action="${pageContext.request.contextPath}/FileUpload2.action"
		method="POST">
		Select images: <input type="file" name="img" multiple> <br />
		<input type="submit">
	</form>



</body>
</html>







