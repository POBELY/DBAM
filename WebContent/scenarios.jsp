<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,dbam.Scenario,dbam.Checkpoint"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Don't Be A Mouton - Scenario</title>
<%@include file="head.jsp" %>
<%@include file="header.jsp" %>
</head>
<body>

<div class="container center">
  <h2>Scénarios</h2>            
  <table class="table table-dark table-hover">
    <thead>
      <tr>
        <th>Nom</th>
        <th>Auteur</th>
        <th>Description</th>
        <th>Nb questions</th>
        <th>Nb Checkpoints</th>
        <th>Jouer</th>
        <% user = (String) session.getAttribute("pseudoS"); 
 			if (user != null) { 
 		 %> <th>Fait</th><%} %>
      </tr>
    </thead>
    <tbody>
    
    
<%
ArrayList<Scenario> scenariosPublic = (ArrayList<Scenario>) request.getAttribute("scenariosPublic");
ArrayList<Scenario> scenariosSessions = (ArrayList<Scenario>) request.getAttribute("scenariosSessions");
//ArrayList<Scenario> scenariosTermines = (ArrayList<Scenario>) request.getAttribute("scenariosTermines");
for (Scenario scenario : scenariosPublic ) {%>
	<tr>
		<td><%=scenario.getNom()%></td>
		<td><%=scenario.getAuteur().getPseudo()%></td>	
		<td><%=scenario.getDescription()%></td>
		
		<%
		List<Checkpoint> checkpoints = (List<Checkpoint>) scenario.getCheckpoints();
		int nbQuestion = 0;
		for (Checkpoint checkpoint : checkpoints) {
			nbQuestion+=checkpoint.getQuestions().size();
		}
		%>
		<td><%=nbQuestion %></td>
		<td><%=checkpoints.size() %></td>
		
		<td>
			<form action="Controller" method="post" class="center">
				<input type="hidden" name="scenarioID" value=<%=scenario.getId()%>>
				<input type="hidden" name="source" value="scenarios">
				<input type="hidden" name="destination" value="checkpoint">
				<input class="btn btn-success" type="submit" value="Jouer !">
			</form>
        </td>
        <%if (user != null) {%> 
        	<td>
        		<% String fait = "Non";
				for (Scenario scenarioSession : scenariosSessions) {
        			if (scenarioSession.getId() == scenario.getId()) {
        				fait = "En cours";
        			}
				}
        		%>
        		
     
        		<%=fait%>
        	</td>
        <%}%>

    </tr>
<%}%>
      
    </tbody>
  </table>
</div>

<%@include file="foot.jsp" %>
</body>
</html>