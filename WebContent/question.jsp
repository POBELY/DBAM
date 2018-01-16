<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,dbam.Session,dbam.Reponse"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Question</title>
<%@include file="head.jsp" %>
<%@include file="header.jsp" %>
</head>
<body>

<div class="container">
	<div class="jumbotron bg-secondary center">
		<%Session sessionJeu = (Session) request.getAttribute("Session");%>
		<p> <%=sessionJeu.getQuestionCourante().getTexteQuestion() %></p>
		<br>
		
		<div class="row">
		<%ArrayList<Reponse> reponses = new ArrayList<Reponse>();
		// On recherche la valeur du nombre de choix max parmis les choix
		int nbChoisiMax = 0;
		for (Reponse choix : sessionJeu.getQuestionCourante().getChoix()) {
			nbChoisiMax = Math.max(nbChoisiMax,choix.getNbChoisi());
			if (!reponses.contains(choix)) {
					reponses.add(choix);
			}
			
		}%>
		<%--
		// On liste les choix gagnants
		ArrayList<Reponse> correctsDoublon = new ArrayList<Reponse>();
		for (Reponse choix : sessionJeu.getQuestionCourante().getChoix()) {
			if (choix.getNbChoisi() == nbChoisiMax) {
				correctsDoublon.add(choix);
			}
		}
		
		//On enlève les doublons
		ArrayList<Reponse> corrects = new ArrayList<Reponse>();
		for (Reponse choix : correctsDoublon) {
			if (!corrects.contains(choix)) {
				corrects.add(choix);
			}
		}
		<%
		for (Reponse choix : sessionJeu.getQuestionCourante().getChoix()) {
			// Dans getChoix il y a des doublons, on les gère dans cette condition
			if (!reponses.contains(choix)) {
				// Si le choix est gagnant
				if (corrects.contains(choix)) {
					sessionJeu.setNbQuestionsReussi(sessionJeu.getNbQuestionsReussi()+1);
					if (sessionJeu.getNbQuestionsReussi() == sessionJeu.getCheckpointCourant().getNbVictRequis()) {
						sessionJeu.setCheckpointCourant(sessionJeu.getCheckpointCourant().getSuivant());
						if (sessionJeu.getCheckpointCourant() == null) { %>
							<form action="Controller" method="post" class="col-md-6">
								<input type="hidden" name="sessionID" value=<%=sessionJeu.getId()%>>
								<input type="hidden" name="source" value="question">
								<input type="hidden" name="destination" value="question_felicitation">
								<input class="btn btn-success" type="submit" value=<%=choix.getTexteChoix()%>>
							</form>		
				  		}
				  	}
					
				}
				

		<%	reponses.add(choix);
			
		--%>
		<% for (Reponse r : reponses) {%>
			
		
		<form action="Controller" method="post" class="col-md-6">
			<input type="hidden" name="sessionID" value=<%=sessionJeu.getId()%>>
			<input type="hidden" name="choixID" value=<%=r.getId() %>>
			<input type="hidden" name="source" value="question">
			<input type="hidden" name="destination" value="suite_question">
			<input class="btn btn-success" type="submit" value=<%=r.getTexteChoix()%>>
		</form>		
		<% } %>
		<%--
		<form action="Controller" method="post" class="col-md-6">
		 				<input type="hidden" name="sessionID" value="<%=sessionJeu.getId()%>">
								<input type="hidden" name="source" value="question">
								<input type="hidden" name="destination" value="question_felicitation">
								<input class="btn btn-success" type="submit" value="<%=nbChoisiMax%>">
							</form>	
							<form action="Controller" method="post" class="col-md-6">
								<input type="hidden" name="sessionID" value="<%=sessionJeu.getId()%>">
								<input type="hidden" name="source" value="question">
								<input type="hidden" name="destination" value="question_felicitation">
								<input class="btn btn-success" type="submit" value="<%=corrects.get(0).getId()%>">
							</form>	
							<form action="Controller" method="post" class="col-md-6">
								<input type="hidden" name="sessionID" value="<%=sessionJeu.getId()%>">
								<input type="hidden" name="source" value="question">
								<input type="hidden" name="destination" value="question_felicitation">
								<input class="btn btn-success" type="submit" value="<%=corrects.get(1).getId()%>">
							</form> --%>
		
		
		
			
		</div>
	</div>
</div>

<%@include file="foot.jsp" %>
</body>
</html>