<%@ page language="java" import="dbam.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Victoire à ce CheckPoint</title>
</head>
<body>

<%Session cetteSession = (Session) request.getAttribute("session");%>
<%=cetteSession.getCheckpointCourant().getTexteVictoire()%>
<form action="Controller" method="post">
<input type="hidden"  name="source" value="checkpoint_fin" >
<input type="hidden" name="destination" value="checkpoint">
<input type="hidden" name="sessionID" value="<%=cetteSession.getId()%>">

</form>
</body>
</html>