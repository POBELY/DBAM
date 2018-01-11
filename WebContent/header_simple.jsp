<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Header simple</title>
<%@include file="head.jsp" %>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-primary navbar-dark">
  <ul class="navbar-nav">
    <li class="nav-item active">
		<form action="Controller" method="post">
			<input type="hidden" name="destination" value="accueil">
			<input class="btn btn-success" type="submit" value="DBAM">
		</form>
    </li>
  </ul>
</nav>

<%@include file="foot.jsp" %>
</body>
</html>