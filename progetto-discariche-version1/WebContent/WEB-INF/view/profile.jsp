<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
</head>

<body>

	<form method="post" enctype="multipart/form-data" action="modifyProfile">
		Informazioni di:
		<c:out value="${sessionScope.User.name}" />
		<br> Email:
		<c:out value="${sessionScope.User.email}" />
		<br> Password:
		<c:out value="${sessionScope.User.password}" />
		<c:if test= "${sessionScope.User.role==false}">
		<br> Livello Esperienza: 
		<c:out value="${sessionScope.User.experience}" />
		<br>
		<img src="${sessionScope.User.photo}" width="45" height="45" />
		</c:if>
		<br>Name: <input type="text" name="name" /> <br> <br>
		<br> Email: <input type="text" name="email" /><br>
		Password: <input type="password" name="password" /><br> Confirm:
		<input type="password" name="passwordconfirm" /><br>
		 <c:if test= "${sessionScope.User.role==false}"> 
		
		livello : <select name="experience">
			<option>basso</option>
			<option>medio</option>
			<option>alto</option>
		</select> 
		Immagine Profilo: <input type="file" name="newphoto" />
		</c:if>  
		<input type="submit" value="Modifica" /><br> <br>

	</form>

	<p>se vuoi cambiare il nome, la mail ,la password o entrambi
		inserisci il nuovo nome e/o nova mail e/o nuova password</p>
	<p>aleno un valore deve essere modificato altrimenti si viene
		reindirizzati alla pagina di
</body>
</html>