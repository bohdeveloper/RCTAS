<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/vistas/inc/head.jsp"%>

<input type="hidden" id="ckEditor">

<article class="container-fluid pb-5">
	<h3 class="display-5 text-info p-3 border-top mb-2">Crea una
		receta</h3>
	<form action="plato/validate" method="post" class="row g-3 p-2"
		novalidate>
		<div class="col-md-4">
			<label for="nombre" class="form-label">Nombre del plato *</label> <input
				type="text"
				class="form-control ${plato.errores.nombre != null ? 'is-invalid' : '' }"
				id="nombre" maxlength="50" name="nombre" value="${plato.nombre}"
				required>
			<div class="invalid-feedback">${plato.errores.nombre}</div>
		</div>
		<div class="col-md-5">
			<label for="url_imagen" class="form-label">Imagen (URL) *</label> <input
				type="text"
				class="form-control ${plato.errores.url_imagen != null ? 'is-invalid' : '' }"
				id="url_imagen" name="url_imagen" value="${plato.url_imagen}"
				required>
			<div class="invalid-feedback">${plato.errores.url_imagen}</div>
		</div>
		<div class="col-md-3">
			<label for="origen" class="form-label">Origen</label> <select
				class="form-select" name="origen" id="origen">
				<c:forEach items="${origenes}" var="o">
					<option value="${o.id}">${o.nombre}</option>
				</c:forEach>
			</select>
		</div>
		<div class="col-md-10">
			<label for="descripcion" class="form-label">Descripción *</label> <input
				type="text"
				class="form-control ${plato.errores.descripcion != null ? 'is-invalid' : '' }"
				id="descripcion" name="descripcion" value="${plato.descripcion}" maxlength="171"
				required>
			<div class="invalid-feedback">${plato.errores.descripcion}</div>
		</div>
		<div class="col-md-2">
			<label for="calorias" class="form-label">Kcal / 100gr *</label> <input
				type="number"
				class="form-control ${plato.errores.calorias != null ? 'is-invalid' : '' }"
				id="calorias" name="calorias" value="${plato.calorias}" required>
			<div class="invalid-feedback">${plato.errores.calorias}</div>
		</div>
		<div class="col-md-6">
			<label for="calorias" class="form-label">Ingredientes *</label>
			<div class="form-floating">
				<textarea
					class="form-control ${plato.errores.ingredientes != null ? 'is-invalid' : '' }"
					placeholder="Escribe tus ingredientes" id="editor1"
					name="ingredientes" required>${plato.ingredientes}</textarea>
				<div class="invalid-feedback">${plato.errores.ingredientes}</div>
			</div>
		</div>
		<div class="col-md-6">
			<label for="calorias" class="form-label">Receta *</label>
			<div class="form-floating">
				<textarea
					class="form-control ${plato.errores.receta != null ? 'is-invalid' : '' }"
					placeholder="Escribe tu receta" id="editor2" name="receta" required>${plato.receta}</textarea>
				<div class="invalid-feedback">${plato.errores.receta}</div>
			</div>
		</div>
		<div class="d-grid gap-2 d-md-flex justify-content-md-start">
			<button type="submit"
				class="btn btn-light btn-lg mt-5 text-info w-100">
				<p class="h4">Crear receta</p>
			</button>
		</div>
	</form>
</article>

<%@ include file="/WEB-INF/vistas/inc/footer.jsp"%>


