<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>login page</title>
</head>
<body>
	<h1>Effettua il login:</h1>
	<form method="POST" action="login">
		<label for="email"><b>email</b></label> 
		<input type="text" placeholder="Enter email" name="email" required /> 
		<label for="password"><b>Password</b></label>
	    <input type="password" placeholder="Enter Password" name="password" required />
		<button type="submit">Login</button>
	</form>
	
	<a href="registration">Non sei ancora registrato?</a>
	
</body>
</html>