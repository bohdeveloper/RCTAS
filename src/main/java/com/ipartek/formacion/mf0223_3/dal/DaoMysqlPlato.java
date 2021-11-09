package com.ipartek.formacion.mf0223_3.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ipartek.formacion.mf0223_3.entidades.Categoria;
import com.ipartek.formacion.mf0223_3.entidades.Origen;
import com.ipartek.formacion.mf0223_3.entidades.Plato;

/**
 * Una clase para representar la conexión con base de datos y sus operaciónes
 * tipicas de mantenimiento. La entidad conectada = Plato.
 * 
 * @version 1.0, 02/11/2021
 * @author Borja Olazabal Hernandez
 */

public class DaoMysqlPlato implements Dao<Plato> {

	private static final String URL_BD = "jdbc:mysql://localhost:3306/mf0223_3";
	private static final String USUARIO_BD = "root";
	private static final String PASSWORD_BD = "";
	private static final String SQL_SELECT = "SELECT * FROM platos p JOIN origenes o ON o.id = p.origenes_id";
	private static final String SQL_SELECT_ID = "SELECT * FROM platos p JOIN origenes o ON o.id = p.origenes_id JOIN categorias_has_platos cathpla ON p.id = cathpla.platos_id JOIN categorias c ON c.id = cathpla.categorias_id WHERE p.id = ?";
	private static final String SQL_INSERT = "INSERT INTO platos (nombre, descripcion, ingredientes, receta, url_imagen, calorias, origenes_id) VALUES(?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE = "UPDATE platos SET nombre=?,descripcion=?,ingredientes=?,receta=?,url_imagen=?,calorias=?,origenes_id=? WHERE id=?";
	private static final String SQL_DELETE = "DELETE FROM platos WHERE id = ?";

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
	 * Método para obtener todos los registros de la tabla platos.
	 * 
	 * @return La lista de todos los platos.
	 */

	@Override
	public Iterable<Plato> obtenerTodos() {
		try (Connection con = obtenerConexion()) {
			PreparedStatement ps = con.prepareStatement(SQL_SELECT);
			ResultSet rs = ps.executeQuery();

			ArrayList<Plato> platos = new ArrayList<>();

			Plato plato;

			while (rs.next()) {
				plato = new Plato(rs.getLong("p.id"), rs.getString("p.nombre"), rs.getString("p.descripcion"),
						rs.getString("p.ingredientes"), rs.getString("p.receta"), rs.getString("p.url_imagen"),
						rs.getInt("p.calorias"), null, null, null);
				plato.setOrigen(new Origen(rs.getLong("o.id"), rs.getString("o.nombre")));
				platos.add(plato);
			}
			return platos;
		} catch (Exception e) {
			throw new DalException("Error al obtener todos los registros", e);
		}
	}

	/**
	 * Método para obtener un plato mediante su id.
	 * 
	 * @param id El id de un plato para la búsqueda de sus datos.
	 * @return El plato buscado por su id.
	 */

	@Override
	public Plato obtenerPorId(long id) {

		try {
			Connection con = obtenerConexion();
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);
			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();

			Plato plato = null;

			List<Categoria> categorias = new ArrayList<>();

			while (rs.next()) {
				plato = new Plato(rs.getLong("id"), rs.getString("nombre"), rs.getString("descripcion"),
						rs.getString("ingredientes"), rs.getString("receta"), rs.getString("url_imagen"),
						rs.getInt("calorias"), null, null, null);
				plato.setOrigen(new Origen(rs.getLong("o.id"), rs.getString("o.nombre")));
				categorias.add(new Categoria(rs.getLong("c.id"), rs.getString("c.nombre"), null));
				plato.setCategorias(categorias);
			}

			return plato;
		} catch (SQLException e) {
			throw new DalException("No se ha encontrado el plato con id: " + id, e);
		}

	}

	/**
	 * Método para insertar un plato.
	 * 
	 * @param plato El objeto plato para su inserción.
	 * @return El plato insertado.
	 */

	@Override
	public Plato insertar(Plato plato) {
		try (Connection con = obtenerConexion();
				PreparedStatement ps = con.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
			ps.setString(1, plato.getNombre());
			ps.setString(2, plato.getDescripcion());
			ps.setString(3, plato.getIngredientes());
			ps.setString(4, plato.getReceta());
			ps.setString(5, plato.getUrl_imagen());
			ps.setInt(6, plato.getCalorias());
			ps.setLong(7, plato.getOrigen().getId());

			if (ps.executeUpdate() != 1) {
				throw new DalException("Se ha insertado un número de registros erroneo");
			} else {
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				plato.setId(rs.getLong(1));
				return plato;
			}

		} catch (SQLException e) {
			throw new DalException("No se ha podido añadir el plato: " + plato, e);
		}
	}

	/**
	 * Método para modificar un plato.
	 * 
	 * @param plato El objeto plato para su modificación.
	 * @return El plato modificado.
	 */

	@Override
	public Plato modificar(Plato plato) {
		try (Connection con = obtenerConexion(); PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {
			ps.setString(1, plato.getNombre());
			ps.setString(2, plato.getDescripcion());
			ps.setString(3, plato.getIngredientes());
			ps.setString(4, plato.getReceta());
			ps.setString(5, plato.getUrl_imagen());
			ps.setInt(6, plato.getCalorias());
			ps.setLong(7, plato.getOrigen().getId());
			ps.setLong(8, plato.getId());

			if (ps.executeUpdate() != 1) {
				throw new DalException("Se ha modificar un número de registros diferente de uno");
			} else {
				return plato;
			}
		} catch (Exception e) {
			throw new DalException("No se ha podido modificar el registro cuyo id es " + plato.getId(), e);
		}
	}

	/**
	 * Método para borrar un plato.
	 * 
	 * @param id El id de un plato para la eliminación de sus datos.
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
