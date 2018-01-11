<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        <th>Status</th>
        <th>Jouer</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td>Scenario 1</td>
        <td>Kami123</td>
        <td>C'est le tout premier scénario du jeu !!! =)</td>
        <td>10</td>
        <td>2</td>
        <td>Public</td>
        <td>
	        <form action="Controller" method="post" class="center">
				<input type="hidden" name="source" value="scenarios">
				<input type="hidden" name="destination" value="checkpoint">
				<input class="btn btn-success" type="submit" value="Jouer !">
			</form>
        </td>
      </tr>
    </tbody>
  </table>
</div>

<%@include file="foot.jsp" %>
</body>
</html>