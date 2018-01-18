<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Scenario</title>
<%@include file="head.jsp" %>
<%@include file="header.jsp" %>
</head>
<body>
<%int idScenario = (Integer) request.getAttribute("idScenario"); %>
 <div class="container">
 	<div class="jumbotron bg-secondary">
 		<h2 class="center">Création de Scénario</h2>
 		
		<script>
		    $(document).ready(function(){
				$("#btn_ajouter_groupe").click(function(){
					$.post("Controller", {action: "ajouter"}, function(data, status){
						$("#pos_prochain_groupe").before(milieu_creer_scenario_insert_groupe(<%=idScenario%>));
					});
				});
			}); 
		</script>

		<div id="pos_prochain_groupe"></div>
		<button id="btn_ajouter_groupe" type="button" class="btn btn-success">Ajouter un groupe !</button>
		
		<div class="row">
			<form action="Controller" method="post" class="row">
				<p>Visibilité</p>
				   	<select name="visibilite">
				      	<option value="public">Public</option>
				      	<option value="privee">Privée</option>
				    </select>
				<br>
				<input type="hidden" name="source" value="milieu_creer_scenario">
				<input type="hidden" name="destination" value="scenarios">
				<input class="btn btn-primary" type="submit" value="Enregistrer">
			</form>
			<form action="Controller" method="post">
				<input type="hidden" name="source" value="milieu_creer_scenario">
				<input type="hidden" name="destination" value="debut_creer_scenario">
				<input class="btn btn-primary" type="submit" value="Précédent">
			</form>			
		</div>
	</div>
</div>


<%@include file="foot.jsp" %>
</body>
</html>