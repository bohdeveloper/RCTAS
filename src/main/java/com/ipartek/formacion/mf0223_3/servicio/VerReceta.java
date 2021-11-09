package com.ipartek.formacion.mf0223_3.servicio;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.mf0223_3.dal.Dao;
import com.ipartek.formacion.mf0223_3.dal.DaoMysqlPlato;
import com.ipartek.formacion.mf0223_3.entidades.Plato;

/**
 * Una Servlet para poder redireccionar a "receta.jsp" pasandole un objeto de tipo
 * Plato obtenido por busqueda de id recogido desde la URL.
 * 
 * @version 1.0, 02/11/2021
 * @author Borja Olazabal Hernandez
 */

@WebServlet("/receta/*")
public class VerReceta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Dao<Plato> daoP = new DaoMysqlPlato();

		Long id = obtenerIdUrl(request);

		Plato plato = daoP.obtenerPorId(id);

		request.setAttribute("plato", plato);

		request.getRequestDispatcher("/WEB-INF/vistas/receta.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Método para obtener el último caracter de la URL.
	 * 
	 * @param request El objeto HttpServletRequest utilizada para la comunicación
	 *                entre Servlets y proporciona acceso a los datos de cabecera.
	 */

	private Long obtenerIdUrl(HttpServletRequest request) {
		String path = request.getPathInfo();

		return path == null || path.length() == 1 ? null : Long.parseLong(path.substring(1));
	}

}