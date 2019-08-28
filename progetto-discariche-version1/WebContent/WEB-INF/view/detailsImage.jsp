<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Details image with conflict</title>
</head>
<body>
Le annotazioni relative all' immagine con conflitto selezionato:
<c:forEach var="element" items="${requestScope.ListNote}">
nota con id: <c:out value="${element.id}"/><br>
Effettuato in data: <c:out value="${element.date}"/><br>
Validità: <c:out value="${element.validity}"/><br>
Fiducia: <c:out value="${element.reliability}"/><br>
Commenti: <c:out value="${element.comment}"/><br>
id dell' utente che fatto il commento:<c:out value="${element.userId}"/><br>
</c:forEach>
</body>
</html>