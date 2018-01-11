<%@ page language="java" import="java.util.*, dbam.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Header</title>
<%@include file="head.jsp" %>
</head>
<body>

DBAM
<form action="Controller" method="post">
	<input type="hidden" name="source" value="header">
	<input type="hidden" name="destination" value="inscription">
	<input type="submit" value="S'inscrire">
</form>

<form action="Controller" method="post">
	<input type="hidden" name="source" value="header">
	<input type="hidden" name="destination" value="connexion">
	<input type="submit" value="Se connecter">
</form>

Vous etes sur la session de <%out.print(request.getAttribute("ATT_SESSION_USER")); %>

<%@include file="foot.jsp" %>
</body>
</html>