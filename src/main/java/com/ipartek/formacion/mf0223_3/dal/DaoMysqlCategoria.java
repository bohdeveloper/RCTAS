package com.ipartek.formacion.mf0223_3.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.mf0223_3.entidades.Categoria;

/**
 * Una clase para representar la conexión con base de datos y sus operaciónes
 * tipicas de mantenimiento. La entidad conectada = Categoria.
 * 
 * @version 1.0, 02/11/2021
 * @author Borja Olazabal Hernandez
 */

public class DaoMysqlCategoria implements Dao<Categoria> {

	private static final String URL_BD = "jdbc:mysql://localhost:3306/mf0223_3";
	private static final String USUARIO_BD = "root";
	private static final String PASSWORD_BD = "";
	private static final String SQL_SELECT = "SELECT * FROM categorias";
	private static final String SQL_SELECT_ID = "SELECT * FROM categorias WHERE id = ?";
	private static final String SQL_SELECT_CATEG_PLATO = "SELECT * FROM categorias c JOIN categorias_has_platos cathpla ON cathpla.categorias_id = c.id JOIN platos p ON cathpla.platos_id = p.id WHERE p.id = ?";
	private static final String SQL_INSERT = "INSERT INTO categorias (nombre) VALUES(?)";
	private static final String SQL_INSERT_CATEG_PLATO = "INSERT INTO categorias_has_platos (categorias_id, platos_id) VALUES (?,?)";
	private static final String SQL_UPDATE = "UPDATE categorias SET nombre=?";
	private static final String SQL_DELETE = "DELETE FROM categorias WHERE id = ?";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new DalException("No se ha podido cargar el driver", e);
		}
	}

	private static Connection obtenerConexion() {
		try {
			return DriverManager.getConnection(URL_BD, USUARIO_BD, PASSWORD_BD);
		} catch (SQLException e) {
			throw new DalException("No se ha encontrado la conexion a la base de datos", e);
		}
	}

	/**
	 * Método para obtener todos los registros de la tabla categorias.
	 * 
	 * @return La lista de todas las categorias.
	 */

	@Override
	public Iterable<Categoria> obtenerTodos() {
		try (Connection con = obtenerConexion()) {
			PreparedStatement ps = con.prepareStatement(SQL_SELECT);
			ResultSet rs = ps.executeQuery();

			ArrayList<Categoria> categorias = new ArrayList<>();

			while (rs.next()) {
				categorias.add(new Categoria(rs.getLong("id"), rs.getString("nombre"), null));
			}

			return categorias;
		} catch (Exception e) {
			throw new DalException("Error al obtener todos los registros", e);
		}
	}

	/**
	 * Método para obtener una categoría mediante su id.
	 * 
	 * @param id El id de una categoría para la búsqueda de sus datos.
	 * @return La categoría buscada por su id.
	 */

	@Override
	public Categoria obtenerPorId(long id) {

		try (Connection con = obtenerConexion()) {
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);
			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();

			Categoria categoria = null;

			while (rs.next()) {
				categoria = new Categoria(rs.getLong("id"), rs.getString("nombre"), null);
			}

			return categoria;
		} catch (SQLException e) {
			throw new DalException("No se ha encontrado la categoria con id: " + id, e);
		}

	}

	/**
	 * Método para obtener las categorias de un plato mediante su id.
	 * 
	 * @param id El id de un plato para la búsqueda de sus categorias.
	 * @return La lista de categorias buscada por el id de un plato.
	 */

	@Override
	public List<Categoria> obtenerCategoriasPlato(long id) {

		try (Connection con = obtenerConexion()) {
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_CATEG_PLATO);
			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();

			List<Categoria> categorias = new ArrayList<>();

			while (rs.next()) {
				Categoria categoria = new Categoria(rs.getLong("id"), rs.getString("nombre"), null);
				categorias.add(categoria);
			}

			return categorias;
		} catch (SQLException e) {
			throw new DalException("No se ha encontrado la categoria con id: " + id, e);
		}

	}

	/**
	 * Método para insertar una categoría.
	 * 
	 * @param categoria El objeto categoría para su inserción.
	 * @return La categoria insertada.
	 */

	@Override
	public Categoria insertar(Categoria categoria) {

		try (Connection con = obtenerConexion()) {
			PreparedStatement ps = con.prepareStatement(SQL_INSERT);
			ps.setString(1, categoria.getNombre());

			if (ps.executeUpdate() != 1) {
				throw new DalException("Se ha modificar un número de registros diferente de uno");
			} else {
				return categoria;
			}
		} catch (SQLException e) {
			throw new DalException("No se ha podido añadir la categoria: " + categoria, e);
		}

	}

	/**
	 * Método para insertar las categorías a un plato.
	 * 
	 * @param categoriaID El id de la categoría para unirla al plato.
	 * @param platoID     El id del plato para unirlo a la categoría.
	 */

	@Override
	public void insertarCategPlato(long categoriaID, long platoID) {

		try (Connection con = obtenerConexion()) {
			PreparedStatement ps = con.prepareStatement(SQL_INSERT_CATEG_PLATO);
			ps.setLong(1, categoriaID);
			ps.setLong(2, platoID);

			ps.executeUpdate();

		} catch (SQLException e) {
			throw new DalException("No se ha podido añadir las categorias del plato con id" + platoID);
		}

	}

	/**
	 * Método para modificar una categoría.
	 * 
	 * @param categoria El objeto plato para su modificación.
	 * @return La categoria modificada.
	 */

	@Override
	public Categoria modificar(Categoria categoria) {
		try (Connection con = obtenerConexion(); PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {
			ps.setString(1, categoria.getNombre());
			ps.setLong(2, categoria.getId());

			if (ps.executeUpdate() != 1) {
				throw new DalException("Se ha modificar un número de registros diferente de uno");
			} else {
				return categoria;
			}
		} catch (Exception e) {
			throw new DalException("No se ha podido modificar el registro cuyo id es " + categoria.getId(), e);
		}
	}

	/**
	 * Método para borrar una categoría.
	 * 
	 * @param id El id de una categoría para la eliminación de sus datos.
	 */

	@Override
	public void borrar(long id) {
		try (Connection con = obtenerConexion(); PreparedStatement ps = con.prepareStatement(SQL_DELETE)) {
			ps.setLong(1, id);

			if (ps.executeUpdate() != 1) {
				throw new DalException("Se ha borrado un número de registros diferente de uno");
			}
		} catch (Exception e) {
			throw new DalException("No se ha podido borrar el registro cuyo id es " + id, e);
		}
	}

}
