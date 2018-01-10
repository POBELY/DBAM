<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Checkpoint</title>
<%@include file="head.jsp" %>
<%@include file="header_simple.jsp" %>
</head>
<body>

Vous avez atteint un Chekpoint ! <br>


<form action="Controller" method="post">
<input type="hidden" name="op" value="checkpoint">

	<input type="hidden" name="destination" value="question">
	<input type="submit" value="C'est parti !">
</form>

<%@include file="foot.jsp" %>
</body>
</html>