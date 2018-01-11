<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Inscription</title>
<%@include file="head.jsp" %>
<%@ include file="header.jsp" %>
</head>
<body>

<div class="container">
	<div class="jumbotron bg-secondary center">
		<h2>Inscription</h2>
		<form action="Controller" method="post">
		  <input type="hidden" name="source" value="inscription">
		  <input type="hidden" name="destination" value="accueil">
		  <p>Pseudo :</p>
		  <input type="text" name="pseudo">
		  <br>
		  <p>Mot de Passe :</p>
		  <input type="password" name="mdp" pattern=".{4,}">
		  <br>
		  <p>Confirmation de mot de passe :</p>
		  <input type="password" name="mdp_confirm">
		  <br>
		  <p>Adresse mail :</p>
		  <input type="email" name="mail">
		  <br>
		  <input class="btn btn-success" type="submit" value="Je m'inscris !">
		</form>
	</div>
</div>

 
<%@include file="foot.jsp" %>
</body>
</html>