<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Récupération du mot de passe</title>
<%@ include file="header.jsp" %>
</head>
<body>
<form action="Controller" method="post">
	<input type="hidden" name="source" value="mdp_oublie">
	Email : <input type="text" name="email"></input>
	<input type="submit" value="envoyer"> 
	<input type="hidden" name="destination" value ="connexion">
  <a href="connexion.html"> Revenir à la page de connexion</a>

</form>

</body>
</html>