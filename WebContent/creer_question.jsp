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
			<p>Question</p>
			<input type="text" name="text_question">
			<p>Choix 1</p>
			<input type="text" name="text_choix_1">
			<p>Choix 2</p>
			<input type="text" name="text_choix_2">
			
			<script>
			    $(document).ready(function(){
					$("#btn_ajouter_choix").click(function(){
						$.post("Controller", {action: "ajouter"}, function(data, status){
							$("#pos_prochaine_question").before(creer_question_insert_choix());
						});
					});
				}); 
			</script>
			<div id="pos_prochaine_question"></div>
			<button id="btn_ajouter_choix" type="button" class="btn btn-success">Ajouter un choix</button>
			
			<input type="hidden" name="source" value="creer_question">
			<input type="hidden" name="destination" value="milieu_creer_scenario">
			<input class="btn btn-success" type="submit" value="Valider ma question !">
		</form>
	</div>
</div>

<%@include file="foot.jsp" %>
</body>
</html>