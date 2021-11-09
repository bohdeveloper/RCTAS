<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/vistas/inc/head.jsp"%>

<article class="container-fluid pb-5">
	<h3 class="display-5 text-info p-3 border-top mb-2">¿Qué tipo de
		receta es?</h3>
	<form action="categorias/validate" method="post" class="row g-3 p-2"
		novalidate>
		<div class="col-sm-12">

			<c:forEach items="${categorias}" var="c">
				<div class="form-check form-check-inline">
					<input class="form-check-input" type="checkbox" name="categoria"
						id="flexCheckDefault" value="${c.id}"> <label
						class="form-check-label" for="inlineRadio1">${c.nombre}</label>
				</div>
			</c:forEach>

		</div>

		<div class="d-grid gap-2 d-md-flex justify-content-md-start">
			<button type="submit"
				class="btn btn-info btn-lg mt-4 text-light w-25">
				<p class="h4">Terminar receta</p>
			</button>
		</div>
	</form>
</article>

<%@ include file="/WEB-INF/vistas/inc/footer.jsp"%>


