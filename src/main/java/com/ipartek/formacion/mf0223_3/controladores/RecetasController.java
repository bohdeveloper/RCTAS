package com.ipartek.formacion.mf0223_3.controladores;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ipartek.formacion.mf0223_3.dal.Dao;
import com.ipartek.formacion.mf0223_3.dal.DaoMysqlCategoria;
import com.ipartek.formacion.mf0223_3.dal.DaoMysqlPlato;
import com.ipartek.formacion.mf0223_3.entidades.Categoria;
import com.ipartek.formacion.mf0223_3.entidades.Plato;

/**
 * Una clase para poder redireccionar a "mi_receta.jsp" y llamar a la capa DAL
 * para obtener todas las categorias y un plato buscado por su id para añadir sus categorias.   
 * 
 * @version 1.0, 02/11/2021
 * @author Borja Olazabal Hernandez
 */

@WebServlet("/recetas")
public class RecetasController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Dao<Plato> daoP = new DaoMysqlPlato();
		Dao<Categoria> daoC = new DaoMysqlCategoria();

		List<Plato> platos = (List<Plato>) daoP.obtenerTodos();

		for (Plato plato : platos) {
			List<Categoria> categorias = daoC.obtenerCategoriasPlato(plato.getId());
			plato.setCategorias(categorias);
		}

		if (platos.size() == 0) {
			platos = null;
			request.setAttribute("platos", platos);
		} else {
			request.setAttribute("platos", platos);
			request.setAttribute("mensaje", "Si quieres ver las recetas de cada plato, haz click en ellos.");
			request.setAttribute("tipo", "info");
		}

		request.getRequestDispatcher("/WEB-INF/vistas/recetas.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}