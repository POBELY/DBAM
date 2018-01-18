<%@ page language="java" import="dbam.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Victoire à ce CheckPoint</title>
<%@include file="head.jsp" %>
<%@include file="header.jsp" %>
</head>
<body>

<div class="container">
	<div class="jumbotron bg-secondary center">
		<%Session sessionJeu = (Session) request.getAttribute("Session");%>
		<p><%=sessionJeu.getCheckpointCourant().getTexteVictoire() %></p>
		<br>		
		<form action="Controller" method="post">
			<input type="hidden"  name="source" value="checkpoint_fin" >
			<input type="hidden" name="destination" value="checkpoint">
			<input type="hidden" name="sessionID" value=<%=sessionJeu.getId()%>>
			<input class="btn btn-success" type="submit" value="et c'est partie pour un nouveau checkpoint">
		</form>
	</div>
</div> 

</body>
</html>