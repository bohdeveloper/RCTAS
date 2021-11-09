<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="es">

<head>
<base href="${pageContext.request.contextPath}/" />
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>RCTAS</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css"
	rel="stylesheet" />
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>

<script
	src="https://cdn.ckeditor.com/ckeditor5/31.0.0/classic/ckeditor.js"></script>

<script type="text/javascript" src="js/home.js"></script>

<link rel="stylesheet" href="css/main.css" />
</head>

<body>


	<c:choose>
		<c:when test="${bloqueo != null}">
			<nav class="navbar navbar-expand-lg navbar-light bg-white">
				<div class="container-fluid">
					<a class="navbar-brand" href="index" style="pointer-events: none;"><img
						src="img/rctas.png" alt="RCTAS" id="logo" /></a>
					<button class="navbar-toggler" type="button"
						data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
						aria-controls="navbarNavDropdown" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse justify-content-end"
						id="navbarNavDropdown">
						<ul class="navbar-nav">
							<li class="nav-item h5 ms-3"><a class="nav-link"
								aria-current="page" href="index" style="pointer-events: none;">Inicio</a></li>
							<li class="nav-item h5 ms-3"><a class="nav-link"
								aria-current="page" href="recetas" style="pointer-events: none;">Recetas</a></li>
							<li class="nav-item h5 ms-3"><a class="nav-link"
								aria-current="page" href="mi_receta"
								style="pointer-events: none;">Mi receta</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</c:when>
		<c:otherwise>
			<nav class="navbar navbar-expand-lg navbar-light bg-white">
				<div class="container-fluid">
					<a class="navbar-brand" href="index"><img src="img/rctas.png"
						alt="RCTAS" id="logo" /></a>
					<button class="navbar-toggler" type="button"
						data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
						aria-controls="navbarNavDropdown" aria-expanded="false"
						aria-label="Toggle navigation">
						<span class="navbar-toggler-icon"></span>
					</button>
					<div class="collapse navbar-collapse justify-content-end"
						id="navbarNavDropdown">
						<ul class="navbar-nav">
							<li class="nav-item h5 ms-3"><a class="nav-link"
								aria-current="page" href="index">Inicio</a></li>
							<li class="nav-item h5 ms-3"><a class="nav-link"
								aria-current="page" href="recetas">Recetas</a></li>
							<li class="nav-item h5 ms-3"><a class="nav-link"
								aria-current="page" href="mi_receta">Mi receta</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</c:otherwise>
	</c:choose>




	<c:if test="${mensaje != null}">
		<div
			class="alert alert-${tipo} alert-dismissible fade show text-center"
			role="alert">
			${mensaje}
			<button type="button" class="btn-close" data-bs-dismiss="alert"
				aria-label="Close"></button>
		</div>
	</c:if>