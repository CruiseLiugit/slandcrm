<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<title>fileUpload Demo</title>

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
	<center>
		<form
			action="${pageContext.request.contextPath}/fileupload!upload.action"
			method="POST" enctype="multipart/form-data">

			File1 : <input type="file" name="myFile" /><br /> File2 : <input
				type="file" name="myFile" /><br /> File3 : <input type="file"
				name="myFile" /><br /> <input type="submit" value="提交" />
		</form>

		<hr />
		<h1>html 5 新属性，多图上传</h1>
		<!-- html 5 新属性，按住 control 选择多图 -->
		<form action="${pageContext.request.contextPath}/fileupload!uploadmore.action"
			method="POST">
			Select images: <input type="file" name="img" multiple> <br />
			<input type="submit">
		</form>


	</center>

</body>
</html>
