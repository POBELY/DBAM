<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include file="header.jsp" %>
</head>
<body>

<form action="Controller" method="get">
  <input type="hidden" name="op" value="inscription">
  Pseudo : <input type="text" name="pseudo"> <br>
  Mot de Passe : <input type="text" name="mdp"> <br>
  Confirmation de mot de passe : <input type="text" name="mdp_confirm"> <br>
  Adresse mail : <input type="text" name="email">
  <button> Go </button>
  <a href="mdp_oublie.jsp"> Vous avez oublié votre mot de passe ?</a>
</form>
 
</body>
</html>