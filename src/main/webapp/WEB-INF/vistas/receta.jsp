<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/vistas/inc/head.jsp"%>

<h3 class="display-5 text-info p-3 border-top">
	<a href="javascript: history.go(-1)"><em
		class="fas fa-caret-left pe-4 cpointer"></em></a>${plato.nombre}</h3>
<div class="row">
	<div class="col">
		<div class="card h-100 p-5">
			<div class="cdefault pb-5">
				<h4 class="d-inline"><span class="badge bg-info p-1 m-1 cdefault">${plato.origen.nombre}</span></h4>
				<c:forEach items="${plato.categorias}" var="c">
					<h4 class="d-inline"><span class="badge bg-secondary p-1 m-1 cdefault">${c.nombre}</span></h4>
				</c:forEach>
			</div>
			<div class="row">
				<div class="col-lg-4">
					<h2 class="p-3 cdefault">Ingredientes</h2>
					<div class="card-footer cdefault">${plato.ingredientes}</div>
				</div>
				<div class="col-lg-8">
					<h2 class="text-center p-3 cdefault">Receta</h2>
					<div class="card-footer cdefault">${plato.receta}</div>
				</div>
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/vistas/inc/footer.jsp"%>


