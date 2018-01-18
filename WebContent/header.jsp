<%@ page language="java" import="java.util.*, dbam.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Header</title>
<%@include file="head.jsp" %>
</head>
<body>

<nav class="navbar bg-primary navbar-dark">
  <ul class="nav navbar-nav">
    <li class="nav-item active">
		<form action="Controller" method="post">
			<input type="hidden" name="source" value="header">
			<input type="hidden" name="destination" value="accueil">
			<input class="btn btn-success" type="submit" value="DBAM">
		</form>
    </li>
  </ul>
  <ul class="nav navbar-nav navbar-right">
    <% String user = (String) session.getAttribute("pseudoS"); 
 		if (user == "anonymous") { %>
 	<div class="row">
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
    </div>
	   	<% }else{%>
	<div class="row">
    <li class="nav-item">
    	<p class="navbar-brand"><%=user %></p>
    </li> 
    <li class="nav-item">
		<form action="Controller" method="post">
			<input type="hidden" name="deconnexion" value="oui">
			<input type="hidden" name="source" value="header">
			<input type="hidden" name="destination" value="accueil">
			<input class="btn btn-primary" type="submit" value="Se déconnecter">
		</form>
    </li>
    </div>
    	<%} %>
  </ul>
</nav>



<%@include file="foot.jsp" %>
</body>
</html>