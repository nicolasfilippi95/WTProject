<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Dettagli della località</title>
</head>
<body>
	<a href="logout">logout</a>
	<br>
	<c:if test="${sessionScope.User.role == true }">
	Dettagli:<br>
		<tr>

			<td>Nome Località: <c:out value="${Locality.name}" /></td>
			<br>
			<td>comune: <c:out value="${Locality.town}" /></td>
			<br>
			<td>regione: <c:out value="${Locality.region}" /></td>
			<br>
			<td>latitudine: <c:out value="${Locality.latitude}" /></td>
			<br>
			<td>longitudine: <c:out value="${Locality.longitude}" /></td>
			<br>

		</tr>
		<c:if test="${empty requestScope.ListImage}">
		Non sono ancora state inserite immagini.<br>
		</c:if>
		<c:if test="${not empty requestScope.ListImage}">
			<c:forEach var="element" items="${requestScope.ListImage}">
				<tr>
					<td>Risoluzione :<c:out value="${element.resolution}" /></td>
					<br>
					<td>Provenienza:<c:out value="${element.origin}" /></td>
					<br>
					<td>Data di recupero:<c:out value="${element.shooting_date}" /></td>
					<br>
					<td><img src="${element.picture}" width="50" height="50" /></td>
					<br>
					<c:forEach var="el" items="${requestScope.ListNote}">
				</tr>
				<c:if test="${element.id == el.imageId}">
					<tr>
						<td>Nota scritta dall' utente con id : <c:out
								value="${el.userId}" /></td>
						<br>
						<td>In data: <c:out value="${el.date}" /></td>
						<br>
						<td>Validità: <c:out value="${el.validity}" /></td>
						<br>
						<td>Fiducia: <c:out value="${el.reliability}" /></td>
						<br>
						<td>commenti: <c:out value="${el.comment}" /></td>
						<br>
					</tr>
				</c:if>
				<br>
			</c:forEach>




			</c:forEach>



		</c:if>

	</c:if>

	<c:if test="${sessionScope.User.role == false }">
		<script type="text/javascript" src="js/showFormNote.js"></script>
		<form method="post" action="createNote">
			<input type="text" name="localityid" value="${Locality.id}" hidden />
			<input type="text" name="campaignid" value="${Locality.campaignId}"
				hidden />
			<div id="im">
				<c:forEach var="e" items="${requestScope.ListImage}">

					<input type="radio" name="imagetonote" value="${e.id}">
					<img src="${e.picture}" width="50" height="50" />
					</input>
				</c:forEach>
			</div>
			
			<div id="newnote" hidden>
				Validità <select name="validity">
					<option value="true">vero</option>
					<option value="false">falso</option>
				</select>
				 <br> Fiducia:
				  <select name="reliability">
					<option value="high">alta</option>
					<option value="medium">media</option>
					<option value="low">bassa</option>
				</select><br> Commenti: <input type="text" name="comment" /> <br>
				<input type="submit" value="crea nota" />
			</div>

		</form>

	</c:if>

	<a href="modifyProfile">Modify Profile</a>
</body>
</html>