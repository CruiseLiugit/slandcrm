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

<title>发表评论</title>

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
	<h1>发布评论</h1>
	<br>
	<form class="form-inline" method="get"
		action="${pageContext.request.contextPath }/comments!addComment.action">
		
		<label class="control-label" for="input01">periodicalID</label><br /> 
		<input type="text" class="x-large" id="yearname" name="periodicalid" /><br />
			
			
		<label class="control-label" for="input01">topicID</label><br /> 
		<input type="text" class="x-large" id="yearname" name="topicid" /><br />
			
		<label class="control-label" for="input01">comment</label><br /> 
		<input type="text" class="x-large" id="yearname" name="comment" /><br />
			
		<div style="color:red;font-size:1.5ex;">${addYearError }</div>
		<input name="submit" value="增加" class="btn" id="addyear" type="submit" />
	</form>


</body>
</html>
