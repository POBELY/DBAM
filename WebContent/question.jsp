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
		}%><br>
		Nombre de questions reussies : <%=sessionJeu.getNbQuestionsReussi() %><br>
		Nombre de questions perdues : <%=sessionJeu.getNbQuestionsPerdu() %><br>
		
		<% for (Reponse r : reponses) {%>
			
		
		<form action="Controller" method="post" class="col-md-6">
			<input type="hidden" name="sessionID" value=<%=sessionJeu.getId()%>>
			
			<input type="hidden" name="choixID" value=<%=r.getId() %>>
			<input type="hidden" name="source" value="question">
			<input type="hidden" name="destination" value="suite_question">
			<input class="btn btn-success" type="submit" value=<%=r.getTexteChoix()%>>
		</form>		
		<% } %>

		
		
		
			
		</div>
	</div>
</div>

<%@include file="foot.jsp" %>
</body>
</html>