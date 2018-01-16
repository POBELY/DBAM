<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
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
					$("#btn_ajouter_question1").click(function(){
						$.post("Controller", {action: "ajouter"}, function(data, status){
							$("#pos_prochaine_question1").prepend(<%@include file="nouvelle_question.jsp" %>);
						});
					});
				}); 
			</script>
			<div id="pos_prochaine_question1"></div>
			<button id="btn_ajouter_question1" type="button" class="btn btn-success">Ajouter une question !</button>
		</div>
</body>
</html>