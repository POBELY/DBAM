<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Scenario</title>
<%@include file="head.jsp" %>
<%@include file="header.jsp" %>
</head>
<body>

<div class="container">
	<div class="jumbotron bg-secondary center">
		<h2>Explication du créateur de Scénarios</h2>
		<p>Entrez ici les instructions pour expliquer à l'utilisateur comment faire. Non mais sans dec', faites le :p</p>
		<form action="Controller" method="post">
			<input type="hidden" name="source" value="explication_scenario">
			<input type="hidden" name="destination" value="debut_creer_scenario">
		    <input class="btn btn-info" type="submit" value="J'ai tout compris ! :D">
		</form>
	</div>
</div>

<%@include file="foot.jsp" %>
</body>
</html>