<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Connexion</title>
<%@include file="head.jsp" %>
<%@include file="header.jsp" %>
</head>
<body>

<div class="container">
	<div class="jumbotron bg-secondary center">
		<h2>Se Connecter</h2>
		<form action="Controller" method="post">
			<input type="hidden" name="source" value="connexion">
			<input type="hidden" name="destination" value="accueil">
			<p>Pseudo :</p>
			<input type="text" name="pseudo">
			<br>
			<p>Mot de Passe :</p>
			<input type="password" name="mdp">
			<br>
			<input class="btn btn-success" type="submit" value="Go !">
		</form>
		<form action="Controller" method="post">
			<input type="hidden" name="source" value="connexion">
			<input type="hidden" name="destination" value="mdp_oublie">
			<input class="btn btn-dark" type="submit" value="Vous avez oublié votre mot de passe ?">
		</form>
		<form action="Controller" method="post">
			<input type="hidden" name="source" value="connexion">
			<input type="hidden" name="destination" value="inscription">
			<input class="btn btn-info" type="submit" value="Vous n'avez pas encore de compte ?">
		</form>
	</div>
</div>

<%@include file="foot.jsp" %>
</body>
</html>