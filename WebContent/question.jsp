<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<p>Ceci est une question.</p>
		<br>
		
		<div class="row">
			<form action="Controller" method="post" class="col-md-6">
				<input type="hidden" name="source" value="question">
				<input type="hidden" name="destination" value="question">
				<input class="btn btn-success" type="submit" value="choix 1">
			</form>
			
			<form action="Controller" method="post" class="col-md-6">
				<input type="hidden" name="source" value="question">
				<input type="hidden" name="destination" value="checkpoint">
				<input class="btn btn-danger" type="submit" value="choix 2">
			</form>
		</div>
	</div>
</div>

<%@include file="foot.jsp" %>
</body>
</html>