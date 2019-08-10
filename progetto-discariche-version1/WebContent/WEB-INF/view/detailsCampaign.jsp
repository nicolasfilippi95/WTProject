<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Dettagli della campagna:</title>
<script type="text/javascript" src="js/chosePresistentOrNewLocality.js"></script>
<style>
.tab {
	display: none;
}
</style>
</head>
<body>
	<a href="logout">logout</a><br>
	<c:if test="${sessionScope.User.role == true }">
	
			Dettagli:<br> Nome:
			<c:out value="${Campaign.name}" />
		<br> Customer:
			<c:out value="${Campaign.customer}" />
		<br> Status:
			<c:out value="${Campaign.status}" />
		<br> Località associate alla campagna:<br> 
		<c:if test="${empty requestScope.ListLocality}">
		Non sono ancora state inserite Localita'.<br>
		</c:if>
		<c:if test="${not empty requestScope.ListLocality}">

			<c:forEach var="element" items="${requestScope.ListLocality}">
				<br>
				<tr>
					<td>nome:<c:out value="${element.name}" />   </td>

					<td>town:<c:out value="${element.town}" />   </td>
					
					<td>region:<c:out value="${element.region}" />   </td>
					<br>
				</tr>
				<c:forEach var="el" items="${requestScope.ListImage}">

					<c:if test="${element.id == el.localityId}">
						
							<td><img src="${el.picture}" width="50" height="50" />  </td>

						

					</c:if>
				</c:forEach>
			</c:forEach>
		</c:if>
<c:if test="${Campaign.status == 'CREATED'  }">
		<form method="post" enctype="multipart/form-data"
			action="createLocalityAndImage" id="wiz">
			<input type="hidden" name="campaignid"
				value=<c:out value="${Campaign.id}"/> />

			<h1>Inserimento Nuove Località e nuove immagini:</h1>
			<div class="tab">
				<p>Seleziona località:</p>

				<select name="localityWithSelect" id="sel">
					<c:forEach var="item" items="${requestScope.ListLocality}">

						<option value="${item.id}" onclick="myFunction()">${item.name}</option>
					</c:forEach>

					<option value="new" onclick="myFunction()">new locality</option>
				</select>

				<div id="addLocality" hidden>

					Inserisci latitudine:<input type="text" name="latitude" /><br>
					Inserisci longitudine:<input type="text" name="longitude" /><br>
					Inserisci nome:<input type="text" name="name" /><br>
					Inserisci comune:<input type="text" name="town" /><br>
					Inserisci regione:<input type="text" name="region" /><br>

				</div>

			</div>


			<div class="tab">

				<p>carica immagine:</p>
				<input type="file" name="aFile"
					accept="image/x-png,image/gif,image/jpeg,image/jpg" />
			</div>

			<div class="tab">
				<p>inserisci i dati dell' immagine</p>
				inserisci provenienza: <input type="text" name="origin" />
				inserisci data recupero:<input type="text" name="shooting_date" />
				inserisci risoluzione:<input type="text" name="resolution" />
			</div>

			<button type="button" id="prevBtn" onclick="nextPrev(-1)">Previous</button>
			<button type="button" id="nextBtn" onclick="nextPrev(1)">Next</button>
			<button type="reset" id="reset" onclick="resetFunction()">Reset</button>


		</form>

		<script type="text/javascript" src="js/wizard.js"></script>
	</c:if>
	</c:if>
	<form  action="startCampaign" method="POST">
							<input type="hidden" name="campaignid"
								value="<c:out value = "${Campaign.id}"/>" /> <input
								type="submit" value="Avvia campagna" />
						</form>
							<form  action="closeCampaign" method="POST">
							<input type="hidden" name="campaignid"
								value="<c:out value = "${Campaign.id}"/>" /> <input
								type="submit" value="Chiudi campagna" />
						</form>
	<a href="modifyProfile">Modify Profile</a>
	<a name="campaignid" href='showStatistics?campaignid=<c:out value ="${Campaign.id}" />'>visualize statistics</a>
	<a href="showHome">Back to home </a>
</body>
</html>