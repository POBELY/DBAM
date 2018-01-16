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
			
			
</body>
</html>