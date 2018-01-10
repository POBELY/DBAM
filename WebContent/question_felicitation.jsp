<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Question Felicitation</title>
<%@include file="header.jsp" %>
</head>
<body>

Victoire !
<br>

<form action="Controller" method="post">
	<input type="hidden" name="destination" value="inscription">
	<input type="submit" value="S'inscrire">
</form>
<br>

<form action="Controller" method="post">
	<input type="hidden" name="destination" value="connexion">
	<input type="submit" value="Se connecter">
</form>
<br>

<form action="Controller" method="post">
	<input type="hidden" name="destination" value="accueil">
	<input type="submit" value="Acceuil">
</form>
<br>

</body>
</html>