<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
	<c:if test="${sessionScope.User.role == true}">
		<p>Campagne:</p>
		<table>
		<c:forEach var="element" items="${requestScope.Campaign}">
			
			
			<tr> 
			<td><c:out value="${element.name}"/></td>
			<td><c:out value="${element.customer}"/></td>
			<td><c:out value="${element.status}"/></td>
			</tr>
			
			</c:forEach>
			

</table>

	</c:if>
<a href="profile">modifica dettagli del profilo</a>
</body>
</html>