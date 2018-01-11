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

<div class="container">
	<div class="jumbotron bg-secondary">
		<h2 class="center">Création de Scénario</h2>
		<script>
		    $(document).ready(function(){
				$("#btn_delete1").click(function(){
					$.post("Controller", {action: "delete"}, function(data, status){
						$("#groupe1").hide();
					});
				});
			}); 
		</script>
		<div id="groupe1">
			<div class="row">
				<h4>Groupe 1 </h4>
				<form action="Controller" method="post" class="center">
					<input type="hidden" name="source" value="milieu_creer_scenario">
					<input type="hidden" name="destination" value="creer_checkpoint">
					<input class="btn btn-success" type="submit" value="Modifier">
				</form>
				<button id="btn_delete1" type="button" class="btn btn-warning">Supprimer</button>
			</div>
			
			<script>
			    $(document).ready(function(){
					$("#btn_delete_question1").click(function(){
						$.post("Controller", {action: "delete"}, function(data, status){
							$("#question1").hide();
						});
					});
				}); 
			</script>
			<div class="row" id="question1">
				<h5>Question 1 </h5>
				<form action="Controller" method="post" class="center">
					<input type="hidden" name="source" value="milieu_creer_scenario">
					<input type="hidden" name="destination" value="creer_question">
					<input class="btn btn-success" type="submit" value="Modifier">
				</form>
				<button id="btn_delete_question1" type="button" class="btn btn-warning">Supprimer</button>
			</div>
			
			<script>
			    $(document).ready(function(){
					$("#btn_ajouter_question1").click(function(){
						$.post("Controller", {action: "ajouter"}, function(data, status){
							$("#pos_prochaine_question1").prepend('<p>On pourrait rajouter une question ici si on modifie le script =)</p>');
						});
					});
				}); 
			</script>
			<div id="pos_prochaine_question1"></div>
			<button id="btn_ajouter_question1" type="button" class="btn btn-success">Ajouter une question !</button>
		</div>
		
		<script>
		    $(document).ready(function(){
				$("#btn_ajouter_groupe1").click(function(){
					$.post("Controller", {action: "ajouter"}, function(data, status){
						$("#pos_prochain_groupe1").prepend('<p>On pourrait rajouter un GROUPE ici si on modifie le script =)</p>');
					});
				});
			}); 
		</script>
		<div id="pos_prochain_groupe1"></div>
		<button id="btn_ajouter_groupe1" type="button" class="btn btn-success">Ajouter un groupe !</button>
		
		<div class="row">
			<form action="Controller" method="post">
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