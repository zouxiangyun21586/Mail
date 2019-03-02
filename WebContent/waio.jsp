<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>激活账号</title>
</head>
<body>
	<center>
		<font size="6" color="red">该账号未激活,请激活后使用!</font>
		<br><br>
		<form action="<%=request.getContextPath() %>/waioMa" method="get">
			账号名：<input type="text" name="account" id="account"/>
			<br>
			邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：<input type="text" name="email" id="email"/>
			<br>
			<input type="submit">&nbsp;&nbsp;&nbsp;<input type="reset">
		</form>
	</center>
</body>
</html>