<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,dbam.Scenario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Scenario</title>
<%@include file="head.jsp" %>
<%@include file="header.jsp" %>
</head>
<body>

<div class="container center">
  <h2>Mes Scénarios</h2>            
  <table class="table table-dark table-hover">
    <thead>
      <tr>
        <th>Nom</th>
        <th>Description</th>
        <th>Status</th>
        <th>Modifier</th>
        <th>Jouer</th>
      </tr>
    </thead>
    <tbody>
    
	<%
	ArrayList<Scenario> mesScenarios = (ArrayList<Scenario>) request.getAttribute("mesScenarios");
	for (Scenario scenario : mesScenarios ) {%>
	<tr>
		<td><%=scenario.getNom()%></td>
		<td><%=scenario.getDescription()%></td>
		<td><%=scenario.getStatut()%></td>
		<td>
			<form action="Controller" method="post" class="center">
				<input type="hidden" name="scenarioID" value=<%=scenario.getId()%>>
				<input type="hidden" name="source" value="mes_scenarios">
				<input type="hidden" name="destination" value="checkpoint">
				<input class="btn btn-success" type="submit" value="Jouer !">
			</form>
        </td>
        <td>
        	<form action="Controller" method="post" class="center">
     		   	<input type="hidden" name="scenarioID" value=<%=scenario.getId()%>>
				<input type="hidden" name="source" value="mes_scenarios">
				<input type="hidden" name="destination" value="milieu_creer_scenario">
				<input class="btn btn-success" type="submit" value="Modifier">
			</form>
        </td>
    </tr>
<%}%>

    </tbody>
  </table>
  <br>
  <form action="Controller" method="post" class="center">
		<input type="hidden" name="source" value="mes_scenarios">
		<input type="hidden" name="destination" value="debut_creer_scenario">
		<input class="btn btn-success" type="submit" value="Créer un nouveau Scénario ! =)">
	</form>
</div>

<%@include file="foot.jsp" %>
</body>
</html>