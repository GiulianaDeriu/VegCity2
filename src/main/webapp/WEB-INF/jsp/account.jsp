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
<link rel="stylesheet" href="/resources/css/account.css">
<link rel="icon" type="image/x-icon" href="/resources/img/Logo.svg" />
<title>Account</title>
</head>
<body>
	<div class="container">
		<div class="forms-container">
			<div class="profile">
				<form class="account-form">
					<h2 class="title">Le tue ricette</h2>
					<div class="container-1">
						<div id="output-account">
							<c:forEach items="${tue}" var="globale">
								<h4>
									titolo: ${globale.titolo} &nbsp; <input type="submit"
										formaction="/ricetta/updateRecipe?id=${globale.id}"
										formmethod="post" class="little-btn" value="modifica" />
									&nbsp; <input type="submit"
										formaction="/ricetta/deleteRecipe?id=${globale.id}"
										formmethod="post" class="little-btn" value="elimina" />
								</h4>
								<p>ingredienti: ${globale.ingredienti}</p>
								<p>preparazione: ${globale.preparazione}</p>
								<p>cottura: ${globale.cottura}</p>
							</c:forEach>
						</div>
					</div>
					<input type="submit" formaction="/ricetta/newRecipe"
						formmethod="post" class="btn" value="Aggiungi" name="aggiungi" />
				</form>
			</div>
		</div>
		
		<form class="panels-container">
			<div class="panel left-panel">
				<div class="content">
					<h3>Cerchi qualcosa di nuovo?</h3>
					<p>Clicca sul bottone per scoprire le ricette degli altri
						utenti!</p>						
						
						<input type="submit" formaction="/user/community"
						formmethod="post" class="btn transparent" value="Nuove ricette" id="users-btn"/>
						
				</div>
				<img src="/resources/img/community.svg" class="image" alt="" />
			</div>
		</form>
	</div>
</body>
</html>