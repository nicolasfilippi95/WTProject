<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dettagli della campagna:</title>
</head>
<body>
	<a href="logout">logout</a>
	<c:if test="${sessionScope.User.role == true }">
		<div>
			Dettagli:<br> Nome:
			<c:out value="${Campaign.name}" />
			<br> Customer:
			<c:out value="${Campaign.customer}" />
			<br> Status:
			<c:out value="${Campaign.status}" />
			<br>


		</div>
		
		Località associate alla campagna:<br>
		<c:if test="${empty requestScope.ListLocality}">
		Non sono ancora state inserite Localita'.<br>

		</c:if>
		<c:if test="${not empty requestScope.ListLocality}">

			<c:forEach var="element" items="${requestScope.ListLocality}">
				<tr>
					<td>nome:<c:out value="${element.name}" /><br>
					<td>latitude:<c:out value="${element.latitude }" /></td>
					<br>
					<td>longitude:<c:out value="${element.longitude}" /></td>
					<br>
					<td>town:<c:out value="${element.town}" /></td>
					<br>
					<td>region:<c:out value="${element.region}" /></td>
					<br>
				</tr>
				<c:forEach var="el" items="${requestScope.ListImage}">

					<c:if test="${element.id == el.localityId}">
						<tr>
							<c:out value="${el.resolution}" />
							<c:out value="${el.picture}" />
							<td><img src="<c:out value="${el.picture}"/>" width="150"
								height="200" /></td>

						</tr>

					</c:if>
				</c:forEach>
			</c:forEach>
		</c:if>
	</c:if>
	<a href="modifyProfile">Modify Profile</a>
	<a href="showHome">Back to home </a>
</body>
</html>