<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>账号激活</title>
</head>
<body>
	<font size="6" color="blue">激活失败,请重新激活!!</font>
	<br>
	<a href="http://192.168.1.86:8080/Mail/waioMa?account='"+${account}+"'&email='"+${email}+"'">点击发送激活码</a>
</body>
</html>