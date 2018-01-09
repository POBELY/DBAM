<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@ include="header.jsp" %>

</head>
<body>
<form action="DBAM" method="get">
  Pseudo : <input type="text" name="pseudo"> <br>
  Mot de Passe : <input type="text" name="mdp"> <br>
  Confirmation de mot de passe : <input type="text" name="mdp_confirm"> <br>
  Adresse mail : <input type="text" name="email">
  <button> Go </button>
  <a href="mdp_oublie.html"> Vous avez oublié votre mot de passe ?</a>
</form> 
</body>
</html>