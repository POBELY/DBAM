<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Don't Be A Mouton - Accueil</title>
<%@include file="header.jsp" %>
</head>
<body>

<form action="Controller" method="post">
	<input type="hidden" name="destination" value="checkpoint">
	<input type="submit" value="Jouer !">
	<br>
</form>

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

</body>
</html>