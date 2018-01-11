<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Récupération du mot de passe</title>
<%@include file="head.jsp" %>
<%@ include file="header.jsp" %>
</head>
<body>

<div class="container">
	<div class="jumbotron bg-secondary center">
		<h2>Récupérer votre mot de passe</h2>
		<small>C'est pas bien d'oublier son mot de passe :p</small>
		<br>
		<p>Veuillez renseigner l'e-mail correspondant à votre compte afin que nous puissions vous envoyer votre mot de passe.<p>
		<form action="Controller" method="post">
			<p>Pseudo</p>
			<input type="text" name="pseudo">
			<p>Email</p>
			<input type="email" name="pseudo">
		    <input type="hidden" name="source" value="mdp_oublie">
			<input type="hidden" name="destination" value="mdp_oublie_validation">
		    <input class="btn btn-success" type="submit" value="On envoie la sauce ?">
		</form>
	</div>
</div>

<%@include file="foot.jsp" %>
</body>
</html>