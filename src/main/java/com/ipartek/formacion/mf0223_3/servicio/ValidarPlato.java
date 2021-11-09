package com.ipartek.formacion.mf0223_3.servicio;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.mf0223_3.dal.Dao;
import com.ipartek.formacion.mf0223_3.dal.DaoMysqlCategoria;
import com.ipartek.formacion.mf0223_3.dal.DaoMysqlOrigen;
import com.ipartek.formacion.mf0223_3.dal.DaoMysqlPlato;
import com.ipartek.formacion.mf0223_3.entidades.Categoria;
import com.ipartek.formacion.mf0223_3.entidades.Origen;
import com.ipartek.formacion.mf0223_3.entidades.Plato;

/**
 * Una Servlet para validar los datos de un plato pasados por el metodo POST,
 * contiene comprobación de errores en la capa de entidades.
 * 
 * @version 1.0, 02/11/2021
 * @author Borja Olazabal Hernandez
 */

@WebServlet("/plato/validate")
public class ValidarPlato extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		/*
		 * Llamada a Dao tipo Plato. Llamada a Dao tipo Origen. Llamada a Dao tipo
		 * Categoria.
		 */

		Dao<Plato> daoP = new DaoMysqlPlato();
		Dao<Origen> daoO = new DaoMysqlOrigen();
		Dao<Categoria> daoC = new DaoMysqlCategoria();

		/*
		 * Recogida de datos enviados por formulario (POST).
		 */

		String fNombre = request.getParameter("nombre");
		String fImagen = request.getParameter("url_imagen");
		String fDescripcion = request.getParameter("descripcion");
		String fIngredientes = request.getParameter("ingredientes");
		String fCalorias = request.getParameter("calorias");
		String fReceta = request.getParameter("receta");
		String fOrigen = request.getParameter("origen");
		Long lOrigen = Long.parseLong(fOrigen);
		Origen origen = daoO.obtenerPorId(lOrigen);

		/*
		 * Creación de objeto tipo Plato relleno con los datos del formulario.
		 */

		Plato plato = new Plato(null, fNombre, fDescripcion, fIngredientes, fReceta, fImagen, fCalorias, origen, null);

		/*
		 * Si hay errores en la validación del formulario...
		 */
		if (plato.getErrores().size() != 0) {
			request.setAttribute("origenes", daoO.obtenerTodos());
			request.setAttribute("categorias", daoC.obtenerTodos());

			request.setAttribute("plato", plato);
			request.setAttribute("mensaje", "Tienes fallos en el formulario");
			request.setAttribute("tipo", "danger");

			// Redirección al formulario para su corrección.
			request.getRequestDispatcher("/WEB-INF/vistas/mi_receta.jsp").forward(request, response);

			/*
			 * Si no hay errores en la validación del formulario...
			 */
		} else {
			// Se inserta el plato creado en la BD.
			daoP.insertar(plato);
			// Guardamos el plato en una variable de sesión.
			request.getSession().setAttribute("plato", plato);

			// Booleano creado con el fin de evitar la evasión del formulario
			// caracteristicas, tiene la función de bloquear la barra de navegación mediante
			// CSS.
			boolean bloqueo = true;
			// Guardamos el booleano bloqueo en una variable de sesión.
			request.getSession().setAttribute("bloqueo", bloqueo);

			// Redirección al formulario de categorias para elegir las categorias del plato.
			response.sendRedirect(request.getContextPath() + "/categorias/validate");
		}

	}

}