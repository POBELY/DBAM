<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Don't Be A Mouton - Accueil</title>
</head>
<body>

<form action="Controller" method="post">

<input type="hidden" name="destination" value="Jouer">
<input type="submit" value="Jouer !">

<input type="hidden" name="destination" value="scenario">
<input type="submit" value="Autres Scénarios">


<input type="hidden" name="destination" value="mes_scenarios">
<input type="submit" value="Mes Scénarios">

Pas encore inscrit ?
<input type="hidden" name="destination" value="inscription">
<input type="submit" value="S'inscire">
S'inscrire vous permettra de créer de nouveau scénarios !

</form>

</body>
</html>