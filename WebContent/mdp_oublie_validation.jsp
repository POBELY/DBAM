<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Validation de la récupération du mot de passe </title>
<%@include file="head.jsp" %>
<%@ include file="header.jsp" %>
</head>
<body>

<div class="container">
	<div class="jumbotron bg-secondary center">
		<h2>Votre mot de passe vient de vous être envoyé !</h2>
		<form action="Controller" method="post">
			<input type="hidden" name="source" value="mdp_oublie_validation">
			<input type="hidden" name="destination" value="accueil">
		    <input class="btn btn-info" type="submit" value="Retourner à l'accueil">
		</form>
	</div>
</div>

<%@include file="foot.jsp" %>
</body>
</html>