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
	<a href="logout">logout</a>
	<c:if test="${sessionScope.User.role == true}">
		<p>Campagne:</p><br>
		<table>
			<c:forEach var="element" items="${requestScope.ListCampaign}">


				<tr>
					<td><c:out value="${element.name}" /></td>
					<td><c:out value="${element.customer}" /></td>
					<td><c:out value="${element.status}" /></td>
				</tr>

			</c:forEach>


		</table>


		<br><p>Crea nuova campagna:</p><br>
		<form action="POST" method="createCampaign"></form>
		<label for="name">Inserisci il nome della campagna:</label>
		<input type="text" placeholder="enter name" name="name" />
		<label for="customer">Inserisci il cliente della campagna:</label>
		<input type="text" placeholder="enter customer" name="customer">
		<input type="submit" value="create campaign">




		</form>

	</c:if>
	<c:if test="${sessionScope.User.role == false }">
		<br><p>Campagne avviate e per cui non hai ancora optato:</p><br>
		<br>
		<table>
			<c:forEach var="element" items="${requestScope.CampaignAvailable}">
				<tr>
					<td><c:out value="${element.name}" /></td>
					<td><c:out value="${element.customer}" /></td>
					<td><c:out value="${element.status}" /></td>
				</tr>

			</c:forEach>
		</table>
		<p>Campagne avviate e per cui hai optato:</p>
		<br>
		<table>
			<c:forEach var="element" items="${requestScope.CampaignChoosen}">
				<tr>
					<td><c:out value="${element.name}" /></td>
					<td><c:out value="${element.customer}" /></td>
					<td><c:out value="${element.status}" /></td>
				</tr>

			</c:forEach>
		</table>

	</c:if>
	<a href="profile">modifica dettagli del profilo</a>
</body>
</html>