<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Page</title>

</head>
<body>
<h1>welcome to this web application</h1>
<p>Effettua la registrazione :<p>
<form method ="post" action = "/registration">
    <label for="name">Inserisci username:</label><br>
    <input type="text" placeholder ="enter name" name ="name" REQUIRED><br>
    <label for ="email">Inserisci email valida:</label><br>
    <input type= "text" placeholder="enter email" name = "email" REQUIRED><br>
    <label for ="password">Inserisci passord :</label><br>
    <input type="password" placeholder="enter password" name="password" REQUIRED><br>
    <label for = "role">registrazione manager o lavoratore? :</label><br>
    <select name ="role">
        <option value="0">Manager </option>
        <option value="1" id ="clickable">Lavoratore</option>
    </select>


    <br>
  
    <label for="experience">Inserisci il tuo livello di esperienza: </label><br>
    <select name="experience">
        <option>basso</option>
        <option>medio</option>
        <option>alto</option>
    </select>
    <label for="photo">aggiungi immagine</label><br>
    <input type ="file" name="photo"><br>
    
    <input type ="submit" value ="registration" />
    
</form>

<p>Sei già registrato? </p>
<c:url value="login.jsp" var="loginUrl"/>
<form method="post" action="${loginUrl}">
    <button type="submit">login</button>
</form>


</body>
</html>