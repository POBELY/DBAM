<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Question</title>
<%@include file="head.jsp" %>
<%@include file="header.jsp" %>
</head>
<body>

Ceci est une question.
<br>

<form action="Controller" method="post">
	<input type="hidden" name="destination" value="question">
	<input type="submit" value="choix 1">
</form>

<form action="Controller" method="post">
	<input type="hidden" name="destination" value="checkpoint">
	<input type="submit" value="choix 2">
</form>

<%@include file="foot.jsp" %>
</body>
</html>