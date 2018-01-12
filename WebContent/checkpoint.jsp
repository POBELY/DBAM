<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="dbam.Session"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Checkpoint</title>
<%@include file="head.jsp" %>
<%@include file="header_simple.jsp" %>
</head>
<body>

<div class="container">
	<div class="jumbotron bg-secondary center">
		<%Session sessionJeu = (Session) request.getAttribute("Session");%>
		<p><%=sessionJeu.getCheckpointCourant().getTexteVictoire() %></p>
		<br>		
		<form action="Controller" method="post">
			<input type="hidden" name="sessionID" value=<%=sessionJeu.getId()%>>
			<input type="hidden" name="source" value="checkpoint">
			<input type="hidden" name="destination" value="question">
			<input class="btn btn-success" type="submit" value="C'est parti !">
		</form>
	</div>
</div>

<%@include file="foot.jsp" %>
</body>
</html>