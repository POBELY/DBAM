<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Question Felicitation</title>
<%@include file="head.jsp" %>
<%@include file="header.jsp" %>
</head>
<body>

<div class="container">
	<div class="jumbotron bg-secondary center">
		<h2>Victoire !</h2>
		
		<h4>Vous n'êtes pas inscrit ?? Inscrivez-vous ! =)</h4>

		<form action="Controller" method="post">
			<input type="hidden" name="source" value="question_felicitation">
			<input type="hidden" name="destination" value="inscription">
			<input class="btn btn-primary" type="submit" value="S'inscrire">
		</form>
		
		<form action="Controller" method="post">
			<input type="hidden" name="source" value="question_felicitation">
			<input type="hidden" name="destination" value="connexion">
			<input class="btn btn-primary" type="submit" value="Se connecter">
		</form>
		
		<form action="Controller" method="post">
			<input type="hidden" name="source" value="question_felicitation">
			<input type="hidden" name="destination" value="accueil">
			<input class="btn btn-success" type="submit" value="Acceuil">
		</form>
	</div>
</div>

<%@include file="foot.jsp" %>
</body>
</html>