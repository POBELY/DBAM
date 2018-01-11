<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Don't Be A Mouton - Scenario</title>
<%@include file="head.jsp" %>
<%@include file="header.jsp" %>
</head>
<body>

<div class="container">
	<div class="jumbotron bg-secondary center">
		<h2>Créez votre checkpoint !</h2>
		<form action="Controller" method="post">
			<p>Entrez le texte en cas de réussite</p>
  			<textarea name="text_reussite" rows="10" cols="30"></textarea>
			<p>Entrez le texte en cas de défaite</p>
  			<textarea name="text_defaite" rows="10" cols="30"></textarea>
			<p>Entrez le nombre de victoires requises<p>
			<input type="nb_victoires" step="1" value="1" min="1" max="99"> 
			<p>Entrez le nombre de défaites maximum</p>
			<input type="nb_defaites" step="1" value="1" min="1" max="99"> 
			<input type="hidden" name="source" value="creer_checkpoint">
			<input type="hidden" name="destination" value="question">
			<input class="btn btn-success" type="submit" value="Valider mon checkpoint">
		</form>
	</div>
</div>

<%@include file="foot.jsp" %>
</body>
</html>