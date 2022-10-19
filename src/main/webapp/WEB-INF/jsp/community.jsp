<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script src="https://kit.fontawesome.com/64d58efce2.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="/resources/css/community.css" />
<link rel="icon" type="image/x-icon" href="/resources/img/Logo.svg" />
<title>Community</title>

<script type="text/javascript">
	function filterC() {
		document.getElementById('filtroC').action = document
				.getElementById('categoriaT').value;

		document.getElementById('filtroC').submit();
	}
	function filterI() {
		document.getElementById('filtroI').action = document
				.getElementById('intolleranzaT').value;

		document.getElementById('filtroI').submit();
	}
</script>

</head>
<body>
	<div class="container">
		<div class="forms-container">
			<div class="profile">
				<form class="users-form">
					<h2 class="title">Nuove Ricette</h2>
					<div class="container-2">
						<c:if test="${ruolo=='ADMIN'}">
							<div class="dropdown-container">
								<form class="dropdown"></form>
								<form class="dropdown-intolerance" action="" method="post"
									name="filtroC" id="filtroC">
									<select id="categoriaT" onchange="filterC()">
										<option value="filter" selected hidden>Seleziona
											filtro</option>
										<option value="/categoria/getAllVegan">Ricette vegane</option>
										<option value="/categoria/getAllVegetarian">Ricette
											vegetariane</option>
										<option value="/categoria/getAllError">Ricette errate</option>
									</select>
								</form>
								<form class="dropdown-intolerance" action="" method="post"
									name="filtroI" id="filtroI">
									<select id="intolleranzaT" onchange="filterI()">
										<option value="filter" selected hidden>Seleziona
											intolleranza</option>
										<option value="/intolleranza/getAllLactoseIntolerance">Lattosio</option>
										<option value="/intolleranza/getAllNichelIntolerance">Nichel</option>
										<option value="/intolleranza/getAllGlutenIntolerance">Glutine</option>
									</select>
								</form>
							</div>
						</c:if>
						<br>
						<div id="output-ricette">
							<c:forEach items="${tutte}" var="globale">
								<h4>
									titolo: ${globale.titolo} &nbsp;
									<c:if test="${ruolo=='ADMIN'}">
										<input type="submit"
											formaction="/ricetta/updateRecipe?id=${globale.id}"
											formmethod="post" class="little-btn" value="modifica" />
                                        &nbsp; <input type="submit"
											formaction="/ricetta/deleteRecipe?id=${globale.id}"
											formmethod="post" class="little-btn" value="elimina" />
									</c:if>
								</h4>
								<p>ingredienti: ${globale.ingredienti}</p>
								<p>preparazione: ${globale.preparazione}</p>
								<p>cottura: ${globale.cottura}</p>
							</c:forEach>
						</div>
					</div>
					<div class="search-container">
						<div class="search">
							<form name="search" class="search-form" method="post"
								action="/ricetta/search">
								<input type="search" class="input-search" required
									name="keyword"> <i class="fa fa-search"></i>
							</form>
						</div>
						<div class="link">
							<a href="/logout">Log out</a>
						</div>
					</div>
			</div>

			</form>
		</div>
	</div>

	<form class="panels-container">
		<div class="panel right-panel">
			<div class="content">
				<h3>Vuoi condividere una tua ricetta?</h3>
				<p>
					Clicca sul bottone per aggiungere, modificare o <br> eliminare
					le tue ricette!
				</p>
				<input type="submit" formaction="/user/account" formmethod="post"
					value="Vai al tuo account  " class="btn transparent"
					id="account-btn" />
			</div>
			<img src="/resources/img/utente.svg" class="image" alt="" />
		</div>
	</form>
	</div>

</body>
</html>