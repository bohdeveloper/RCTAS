package com.ipartek.formacion.mf0223_3.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.mf0223_3.dal.Dao;
import com.ipartek.formacion.mf0223_3.dal.DaoMysqlCategoria;
import com.ipartek.formacion.mf0223_3.dal.DaoMysqlOrigen;
import com.ipartek.formacion.mf0223_3.entidades.Categoria;
import com.ipartek.formacion.mf0223_3.entidades.Origen;

/**
 * Una clase para poder redireccionar a "mi_receta.jsp" y llamar a la capa DAL
 * para mostrar todas las categorias y todos los origenes
 * 
 * @version 1.0, 02/11/2021
 * @author Borja Olazabal Hernandez
 */

@WebServlet("/mi_receta")
public class NuevaRecetaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Dao<Origen> daoO = new DaoMysqlOrigen();
		Dao<Categoria> daoC = new DaoMysqlCategoria();

		request.setAttribute("origenes", daoO.obtenerTodos());
		request.setAttribute("categorias", daoC.obtenerTodos());
		request.getRequestDispatcher("/WEB-INF/vistas/mi_receta.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}