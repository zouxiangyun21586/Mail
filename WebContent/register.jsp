<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/jquery-1.9.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<title>Insert title here</title>
</head>
<script type="text/javascript">
</script>
<body>
	<form action="<%=request.getContextPath() %>/mailLogin" method="post">
		账　号: <input type="text" name="account" id="account" /><br/>
		用户名：<input type="text" name="name" id="name" /><br>
		密　码：<input type="password" name="password1" id="password1"/><br>
		确认密码：<input type="password" name="password2" id="password2"/><br>
		邮　箱: <input type="text" name="email" id="email"/><br>
		${requestScope.error}
		<input type="submit"/> &nbsp;&nbsp;&nbsp;&nbsp; <input type="reset"/>
	</form>
	<a href="javascript:window.location.href='<%=request.getContextPath() %>/login.jsp'">返回</a>
</body>
</html>