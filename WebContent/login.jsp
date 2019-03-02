<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
</head>
<body>
	<font size="5" color="red">${requestScope.error}</font>
	<form action="<%=request.getContextPath() %>/login" method="get">
		账号: <input type="text" name="account" id="account"><br>
		密码: <input type="password" name="pass" id="pass"><br>
		<input type="submit" value="登录"> &nbsp;&nbsp;&nbsp; <input type="reset">
	</form>
	<a href="register.jsp">注册用户</a>
</body>
</html>