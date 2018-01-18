<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Accueil</title>
<%@include file="head.jsp" %>
<%@include file="header.jsp" %>
</head>
<body>
<% user = (String) session.getAttribute("pseudoS"); 
if (user == null) {
	session.setAttribute("pseudoS","anonymous");
	user = (String) session.getAttribute("pseudoS");%>
   <% session.setAttribute("idS", "-1");} %>
<div class="container">
	<div class="jumbotron bg-secondary center">
		<form action="Controller" method="post" class="center">
			<input type="hidden" name="source" value="accueil">
			<input type="hidden" name="destination" value="checkpoint">
			<input class="btn btn-success" type="submit" value="Jouer !">
		</form>
		<br>
		
		<form action="Controller" method="post" class="center">
			<input type="hidden" name="source" value="accueil">
			<input type="hidden" name="destination" value="scenarios">
			<input class="btn btn-success" type="submit" value="Autres Scénarios">
			<br>
		</form>
		<br>
  		 <% user = (String) session.getAttribute("pseudoS"); 
 			if (!user.equals("anonymous")) { 
 		 %>
		<form action="Controller" method="post" class="center">
			<input type="hidden" name="source" value="accueil">
			<input type="hidden" name="destination" value="mes_scenarios">
			<input class="btn btn-dark" type="submit" value="Mes Scénarios">
			<br>
		</form>
		<br>
		<form action="Controller" method="post" class="center">
			<input type="hidden" name="source" value="accueil">
			<input type="hidden" name="destination" value="debut_creer_scenario">
			<input class="btn btn-dark" type="submit" value="Creer Scenario">
			<br>
		</form>
		<br>
		<% }else{
		%>
		<p>Pas encore inscrit ?</p>
		<form action="Controller" method="post" class="center">
			<input type="hidden" name="source" value="accueil">
			<input type="hidden" name="destination" value="inscription">
			<input class="btn btn-primary" type="submit" value="S'inscire">
		</form>
		<br>
		<p>S'inscrire vous permettra de créer de nouveau scénarios !</p>
		<%} %>
	</div>
		
</div>

<%@include file="foot.jsp" %>
</body>
</html>