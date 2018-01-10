<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Validation de la récupération du mot de passe </title>
<%@ include file="header.jsp" %>

</head>
<body>
<form action="Controller" method="post">
<input type="hidden" name="op" value="md_oublie_val">

Votre mot de passe vient de vous être envoyé.<br>
<input type="submit" value="Retourner à l'acceuil"/>
<input type="hidden" name="destination" value="acceuil">
 

 
</form>
</body>
</html>