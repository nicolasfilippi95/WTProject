<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
</head>

<body>

	<form method="POST" action="modifyProfile">
		Informazioni di:
		<c:out value="${sessionScope.User.name}" />
		<br> Email:
		<c:out value="${sessionScope.User.email}" />
		<br> Password:
		<c:out value="${sessionScope.User.password}" />
		<br>Name: <input type="text" name="name" /> <br> <br>
		<br> 
		Email: <input type="text" name="email" /><br>
		Password: <input type="password" name="password" /><br> 
		Confirm:  <input type="password" name="passwordconfirm" /><br>
		<input type="submit" value="Modifica" /><br> <br>
	</form>
    
    <p>se vuoi cambiare  il nome, la mail ,la password o entrambi inserisci il nuovo nome e/o nova mail e/o nuova password</p> 
    <p>aleno un valore deve essere modificato altrimenti si viene reindirizzati alla pagina di 
    


</body>
</html>