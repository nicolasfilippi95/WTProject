<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>
<script  type="text/javascript" src="showAndHideFieldRegistration" >
window.onload= function() {
	var worker  = document.getElementById("manager_select");
	var manager = document.getElementById("worker_select");
	manager.onclick = function(){
		document.getElementById("work_extra").hidden = false;
	}
	worker.onclick = function(){ 
	document.getElementById("work_extra").hidden= true ;
	}
}

</script>

</head>
<body >
<h1>welcome to this web application</h1>
<p>Effettua la registrazione :<p>
<form method ="post" action = "registration.jsp">
    <label for="name">Inserisci username:</label><br>
    <input type="text" placeholder ="enter name" name ="name" REQUIRED><br>
    <label for ="email">Inserisci email valida:</label><br>
    <input type= "text" placeholder="enter email" name = "email" REQUIRED><br>
    <label for ="password">Inserisci passord :</label><br>
    <input type="password" placeholder="enter password" name="password" REQUIRED><br>
    <label for ="confirmpassword">rinserisci Password per conferma</label>
    <input type="password" placeholder = "confirm password" name = "confirmpassword"><br>
    <input type="radio" name="role" value= "manager" id= "manager_select"  />Registra come manager <br>
    <input type="radio" name="role" value="worker" id ="worker_select"   />Registra come lavoratore <br>

<div id= "work_extra" hidden>
    <label for="experience">Inserisci il tuo livello di esperienza: </label><br>
    <select name="experience">
        <option>basso</option>
        <option>medio</option>
        <option>alto</option>
    </select>
    <label for="photo">aggiungi immagine</label><br>
    <input type ="file" name="photo"><br>
</div>    
    <input type ="submit" value ="registration" />

</form>

<p>Sei già registrato? </p>

<form method="post" action="login.jsp">
    <button type="submit">login</button>
</form>


</body>
</html>