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

<form action="Controller" method="post">
	<input type="hidden" name="source" value="connexion">
	<input type="hidden" name="destination" value="accueil">

	Se Connecter 
	  Pseudo : <input type="text" name="pseudo"> <br>
	  Mot de Passe : <input type="text" name="mdp"> <br>
	  <button> Go </button>
	  <a href="mdp_oublie.jsp"> Vous avez oublié votre mot de passe ?</a>
</form>

<%@include file="foot.jsp" %>
</body>
</html>