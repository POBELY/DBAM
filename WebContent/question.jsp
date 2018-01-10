<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Question</title>
<%@ include file="header.jsp" %>
</head>
<body>
<form action="Controller" method="post">
Ceci est une question. <br>
<input type="submit" value="choix 1">
<input type="hidden" name="choix" value="1">
<input type="submit" value="choix 2">
<input type="hidden" name="choix" value="2">


</form>
</body>
</html>