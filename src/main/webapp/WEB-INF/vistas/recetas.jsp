<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/vistas/inc/head.jsp"%>

<c:choose>
	<c:when test="${platos != null}">
		<h3 class="display-5 text-info p-3 border-top">Todas las recetas</h3>
		<div class="row row-cols-1 row-cols-md-3 g-4 p-2 pb-5">
			<c:forEach items="${platos}" var="p">
				<div class="col">
					<div class="card h-100 cpointer cardAnimat"
						onclick="verReceta(${p.id});">
						<img src="${p.url_imagen}" class="card-img-top" alt="${p.nombre}">
						<div class="card-header">
							<h2 class="card-title text-center p-1 cpointer">${p.nombre}</h2>
							<h3 class="card-subtitle text-center p-1 cpointer">${p.calorias}
								Kcal / 100 gr</h3>
						</div>
						<div class="card-body">
							<h4 class="text-center">
								<span class="badge bg-info p-1 cpointer">${p.origen.nombre}</span>

								<c:forEach items="${p.categorias}" var="c">
									<span class="badge bg-secondary p-1 cpointer">${c.nombre}</span>
								</c:forEach>
							</h4>
						</div>
						<div class="card-footer cpointer">
							<h5>${p.descripcion}</h5>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</c:when>
	<c:otherwise>
		<div class="no_recetas">
			<h3 class="display-5 text-center text-info mt-5 p-5">Vaya, parece que nos hemos quedado sin recetas.</h3>
			<p class="text-center h4">
				<a href="mi_receta">¿Te apetece ser el primero?</a>
			</p>
		</div>
	</c:otherwise>
</c:choose>

<%@ include file="/WEB-INF/vistas/inc/footer.jsp"%>


