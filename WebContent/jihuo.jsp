<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>此链接已失效,请重新获取链接激活</h4>
	<br/>
	
	${email}
	<a href="http://192.168.1.86:8080/Mail/waioMa?account=\"+${account}+\"&email=\"+${email}+\"">点击发送激活码</a>
	<%-- <a href="http://192.168.1.86:8080/Mail/waioMa?account="+${account}+/"&email="+${email}+"/">点击发送激活码</a> --%>
</body>
</html>