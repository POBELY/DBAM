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
<%Scenario s = (Scenario) request.getAttribute("scenario");
int id_scenario = s.getId();%>


 <div class="container">
 	<div class="jumbotron bg-secondary">
 		<h2 class="center">Création de Scénario</h2>
 		 		
		 <script>
		    $(document).ready(function(){
		    	  <%int i = 0;
		    	  for(Checkpoint c : s.getCheckpoints()) {
					 %>$("#pos_prochain_groupe").before(milieu_creer_scenario_insert_groupe(<%=id_scenario%>));<%
		    		 i ++;
		    		 for(Question q : c.getQuestions()) {
		 				%>$("#pos_prochaine_question_dans_groupe_" + <%=i%>).before(milieu_creer_scenario_insert_question(<%=i%>);<%
		    			 %>milieu_creer_scenario_insert_question(<%=i%>);<%
		    		 }
		    	  }%>
			}); 
		</script>
 		 		
		<script>
		    $(document).ready(function(){
				$("#btn_ajouter_groupe").click(function(){
					$.post("Controller", {action: "ajouter", type: "groupe", id_scenario: <%=id_scenario%>}, function(data, status){
						$("#pos_prochain_groupe").before(milieu_creer_scenario_insert_groupe(<%=id_scenario%>));
					});
				});
			}); 
		</script>

		<div id="pos_prochain_groupe"></div>
		<button id="btn_ajouter_groupe" type="button" class="btn btn-success">Ajouter un groupe !</button>
		
		<div>
			<form action="Controller" method="post">
				<p>Texte de victoire</p>
				<textarea name="text_victoire" rows="5" cols="30"></textarea>
				<div class="row">
					<br>
					<p>Visibilité </p>
					   	<select name="visibilite">
					      	<option value="public">Public</option>
					      	<option value="privee">Privée</option>
					    </select>
					<br>
					<input type="hidden" name="id_scenario" value="<%=id_scenario%>">
					<input type="hidden" name="source" value="milieu_creer_scenario">
					<input type="hidden" name="destination" value="mes_scenarios">
					<input class="btn btn-primary" type="submit" value="Enregistrer">
				</div>
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