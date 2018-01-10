<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Accueil</title>
<%@include file="head.jsp" %>
<%@include file="header.jsp" %>
</head>
<body>

<form action="Controller" method="post">
	<input type="hidden" name="destination" value="checkpoint">
	<input type="submit" value="Jouer !">
	<br>
</form>

<div class="alert alert-success alert-dismissable fade show">
  <button type="button" class="close" data-dismiss="alert">&times;</button>
  <strong>Success!</strong> J'ai laissé cette alerte pour montrer qu'on peut faire des trucs cools en Bootstrat =)
</div>

<form action="Controller" method="post">
	<input type="hidden" name="destination" value="scenario">
	<input type="submit" value="Autres Scénarios">
	<br>
</form>

<form action="Controller" method="post">
	<input type="hidden" name="destination" value="mes_scenarios">
	<input type="submit" value="Mes Scénarios">
	<br>
</form>

<form action="Controller" method="post">
	Pas encore inscrit ?
	<br>
	<input type="hidden" name="destination" value="inscription">
	<input type="submit" value="S'inscire">
	<br>
	S'inscrire vous permettra de créer de nouveau scénarios !
</form>

<%@include file="foot.jsp" %>
</body>
</html>