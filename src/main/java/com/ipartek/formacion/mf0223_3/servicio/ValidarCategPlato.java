package com.ipartek.formacion.mf0223_3.servicio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.formacion.mf0223_3.dal.Dao;
import com.ipartek.formacion.mf0223_3.dal.DaoMysqlCategoria;
import com.ipartek.formacion.mf0223_3.dal.DaoMysqlPlato;
import com.ipartek.formacion.mf0223_3.entidades.Categoria;
import com.ipartek.formacion.mf0223_3.entidades.Plato;

/**
 * Una Servlet para validar las categorias de un plato pasados por el metodo
 * POST, al completar su función se crea la receta.
 * 
 * @version 1.0, 02/11/2021
 * @author Borja Olazabal Hernandez
 */

@WebServlet("/categorias/validate")
public class ValidarCategPlato extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("mensaje", "Ahora, elige las categorias donde entraría esta receta");
		request.setAttribute("tipo", "info");
		Dao<Categoria> daoC = new DaoMysqlCategoria();
		request.setAttribute("categorias", daoC.obtenerTodos());
		request.getRequestDispatcher("/WEB-INF/vistas/categorias_receta.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		/*
		 * Llamada a Dao tipo Plato. Llamada a Dao tipo Categoria.
		 */

		Dao<Plato> daoP = new DaoMysqlPlato();
		Dao<Categoria> daoC = new DaoMysqlCategoria();

		/*
		 * Creamos el acceso al entorno de sesión para recoger el plato.
		 */

		HttpSession session = request.getSession();

		Plato plato = (Plato) session.getAttribute("plato");

		List<Categoria> categorias = new ArrayList<>();

		/*
		 * Recogemos los valores de las categorias recibidas desde el formulario (POST)
		 * en un array, buscamos las categorias mediante sus valores que son sus ids y
		 * añadimos esas categorias a una lista. Añadimos la referencia de esa unión
		 * entre el plato y las caracteristicas.
		 */

		String[] fCategorias = request.getParameterValues("categoria");
		if (fCategorias != null) {
			for (String fCategoria : fCategorias) {
				Long lCategoria = Long.parseLong(fCategoria);
				Categoria categoria = daoC.obtenerPorId(lCategoria);
				categorias.add(categoria);
				daoC.insertarCategPlato(categoria.getId(), plato.getId());
			}
		}

		// Añadimos la lista al plato.
		plato.setCategorias(categorias);

		List<Plato> platos = (List<Plato>) daoP.obtenerTodos();
		request.setAttribute("platos", platos);
		request.setAttribute("mensaje", "Se ha añadido tu receta correctamente");
		request.setAttribute("tipo", "info");

		// Eliminamos las variables de sesión bloqueo y plato.
		session.removeAttribute("plato");
		session.removeAttribute("bloqueo");

		// Redirección a "recetas.jsp".
		response.sendRedirect(request.getContextPath() + "/recetas");
	}

}
