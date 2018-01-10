<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Erreur 404 !</title>
<%@include file="head.jsp" %>
</head>
<body>


<form action='Controller' method='post'>
<input type="hidden" name="op" value="erreur">

	Bonjour !
	<input type='submit' value="Retourner à l'accueil">
	<input type="hidden" name="destination" value="accueil">
</form>

<%@include file="foot.jsp" %>
</body>
</html>