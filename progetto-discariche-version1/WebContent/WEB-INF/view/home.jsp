
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
		<p>Campagne:</p>
		<br>
		<table>

			<c:forEach var="element" items="${requestScope.ListCampaign}">


				<tr>
					<td><a name="campaignid"
						href='showDetailsCampaign?campaignid=<c:out value ="${element.id}" />'>${element.name}</a></td>
					<td><c:out value="${element.customer}" /></td>
					<td><c:out value="${element.status}" /></td>
				</tr>

			</c:forEach>

		</table>


		<br>
		<p>Crea nuova campagna:</p>
		<br>
		<form method="post" action="createCampaign">
			<label for="name">Inserisci il nome della campagna:</label><br>
			<input type="text" placeholder="enter name" name="name" /><br>
			<label for="customer">Inserisci il cliente della campagna:</label><br>
			<input type="text" placeholder="enter customer" name="customer" /><br>
			<input type="submit" value="create" /><br>

		</form>

	</c:if>

	<c:if test="${sessionScope.User.role == false }">
		<br>
		<p>Campagne avviate e per cui non hai ancora optato:</p>
		<br>
		<br>
		<form method ="post" action="joinToCampaign">
			<table>
				<c:forEach var="element" items="${requestScope.CampaignAvailable}">
					<tr>
						<td><input type="radio" name="campaignid"
							value="<c:out value = '${element.id}'/>" />&nbsp;&nbsp;${element.name}</td>
						<td><c:out value="${element.customer}" /></td>
						<td><c:out value="${element.status}" /></td>
					</tr>

				</c:forEach>
			</table>
			<button>Iscriviti</button>
		</form>
		<p>Campagne avviate e per cui hai optato:</p>
		<br>
		<table>
			<c:forEach var="element" items="${requestScope.CampaignChoosen}">
				<tr>
					<td ><a href="showMap?campaignid=<c:out value = "${element.id}"/>">${element.name}</a></td>
					<td><c:out value="${element.customer}" /></td>
					<td><c:out value="${element.status}" /></td>
				</tr>

			</c:forEach>
		</table>

	</c:if>
	<br>
	<a href="modifyProfile">modifica dettagli del profilo</a>
</body>
</html>