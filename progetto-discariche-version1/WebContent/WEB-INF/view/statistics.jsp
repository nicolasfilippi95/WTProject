<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>statistics of campaign</title>
</head>
<body>
<a href="logout">logout</a><br>
<c:if test="${sessionScope.User.role == true }">
<c:if test="${Campaign.status != CREATED }">
<br>
Il numero di località della campagna corrente è: <c:out value="${requestScope.numberLocality}"></c:out>
<br>
Il numero di immagini della campagna corrente è: <c:out value="${requestScope.numberImage}"></c:out>
<br>
Il numero medio di immagini per località: <c:out value="${requestScope.averageNumberImage}"></c:out>
<br>
Il numero medio di note per immagine : <c:out value="${requestScope.averageNumberNote}"></c:out>
<br>
Il numero di conflitti e' : <c:out value="${requestScope.numberConflict}"> </c:out>
<br>
Le immagini con conflitti sono : 
<br>
<c:forEach var ="e" items="${requestScope.listImageConflict}">
<a href= "showDetailsImage?imageid=<c:out value="${e.id}"/>" ><img src="${e.picture}"  width="50" height="50" /></a>

</c:forEach>
<a href="modifyProfile">Modify Profile</a>
</c:if>
</c:if>

</body>
</html>