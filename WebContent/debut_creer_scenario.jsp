<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Don't Be A Mouton - Scenario</title>
<%@include file="head.jsp" %>
<%@include file="header.jsp" %>
</head>
<body>

<div class="container">
	<div class="jumbotron bg-secondary center">
		<h2>Créez votre propre Scénario !!!</h2>
		<form action="Controller" method="post">
			<input type="hidden" name="source" value="debut_creer_scenario">
			<input type="hidden" name="destination" value="explication_scenario">
		    <input class="btn btn-info" type="submit" value="Vous avez besoin d'aide ? On va vous aider ! =)">
		</form>
		<form action="Controller" method="post">
			<p>Nom du scénario</p>
			<input type="text" name="nom_scenario">
			<p>Description</p>
  			<textarea name="description" rows="10" cols="30"></textarea>
		    <input type="hidden" name="source" value="debut_creer_scenario">
			<input type="hidden" name="destination" value="milieu_creer_scenario">
			<br>
		    <input class="btn btn-success" type="submit" value="Suivant !">
		</form>
	</div>
</div>


<%@include file="foot.jsp" %>
</body>
</html>