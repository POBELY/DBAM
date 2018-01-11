<%@ page language="java" import="java.util.*, dbam.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Header</title>
<%@include file="head.jsp" %>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-primary navbar-dark">
  <ul class="navbar-nav">
    <li class="nav-item active">
		<form action="Controller" method="post">
			<input type="hidden" name="source" value="header">
			<input type="hidden" name="destination" value="accueil">
			<input class="btn btn-success" type="submit" value="DBAM">
		</form>
    </li>
    <li class="nav-item">
		<form action="Controller" method="post">
			<input type="hidden" name="source" value="header">
			<input type="hidden" name="destination" value="connexion">
			<input class="btn btn-primary" type="submit" value="Se connecter">
		</form>
    </li>
    <li class="nav-item">
		<form action="Controller" method="post">
			<input type="hidden" name="source" value="header">
			<input type="hidden" name="destination" value="inscription">
			<input class="btn btn-primary" type="submit" value="S'inscrire">
		</form>
    </li>
  </ul>
</nav>

Vous etes sur la session de <%out.print(request.getAttribute("ATT_SESSION_USER")); %>

<%@include file="foot.jsp" %>
</body>
</html>