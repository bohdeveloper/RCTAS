package com.ipartek.formacion.mf0223_3.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ipartek.formacion.mf0223_3.entidades.Origen;

/**
 * Una clase para representar la conexi�n con base de datos y sus operaci�nes
 * tipicas de mantenimiento. La entidad conectada = Origen.
 * 
 * @version 1.0, 02/11/2021
 * @author Borja Olazabal Hernandez
 */

public class DaoMysqlOrigen implements Dao<Origen> {

	private static final String URL_BD = "jdbc:mysql://localhost:3306/mf0223_3";
	private static final String USUARIO_BD = "root";
	private static final String PASSWORD_BD = "";
	private static final String SQL_SELECT = "SELECT * FROM origenes";
	private static final String SQL_SELECT_ID = "SELECT * FROM origenes WHERE id = ?";
	private static final String SQL_INSERT = "INSERT INTO origenes (nombre) VALUES(?)";
	private static final String SQL_UPDATE = "UPDATE origenes SET nombre=?";
	private static final String SQL_DELETE = "DELETE FROM origenes WHERE id = ?";

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
	 * M�todo para obtener todos los registros de la tabla origenes.
	 * 
	 * @return La lista de todos los origenes.
	 */

	@Override
	public Iterable<Origen> obtenerTodos() {
		try (Connection con = obtenerConexion()) {
			PreparedStatement ps = con.prepareStatement(SQL_SELECT);
			ResultSet rs = ps.executeQuery();

			ArrayList<Origen> origenes = new ArrayList<>();

			while (rs.next()) {
				origenes.add(new Origen(rs.getLong("id"), rs.getString("nombre")));
			}

			return origenes;
		} catch (Exception e) {
			throw new DalException("Error al obtener todos los registros", e);
		}
	}

	/**
	 * M�todo para obtener un origen mediante su id.
	 * 
	 * @param id El id de un origen para la b�squeda de sus datos.
	 * @return El origen buscado por su id.
	 */

	@Override
	public Origen obtenerPorId(long id) {

		try {
			Connection con = obtenerConexion();
			PreparedStatement ps = con.prepareStatement(SQL_SELECT_ID);
			ps.setLong(1, id);

			ResultSet rs = ps.executeQuery();

			Origen origen = null;

			while (rs.next()) {
				origen = new Origen(rs.getLong("id"), rs.getString("nombre"));
			}

			return origen;
		} catch (SQLException e) {
			throw new DalException("No se ha encontrado el origen con id: " + id, e);
		}

	}

	/**
	 * M�todo para insertar un origen.
	 * 
	 * @param origen El objeto origen para su inserci�n.
	 * @return El origen insertado.
	 */

	@Override
	public Origen insertar(Origen origen) {
		try (Connection con = obtenerConexion(); PreparedStatement ps = con.prepareStatement(SQL_INSERT)) {
			ps.setString(1, origen.getNombre());

			if (ps.executeUpdate() != 1) {
				throw new DalException("Se ha insertado un n�mero de registros erroneo");
			} else {
				ResultSet rs = ps.getGeneratedKeys();
				rs.next();
				origen.setId(rs.getLong(1));
				return origen;
			}

		} catch (SQLException e) {
			throw new DalException("No se ha podido a�adir el origen: " + origen, e);
		}
	}

	/**
	 * M�todo para modificar un origen.
	 * 
	 * @param origen El objeto origen para su modificaci�n.
	 * @return El origen modificado.
	 */

	@Override
	public Origen modificar(Origen origen) {
		try (Connection con = obtenerConexion(); PreparedStatement ps = con.prepareStatement(SQL_UPDATE)) {
			ps.setString(1, origen.getNombre());
			ps.setLong(2, origen.getId());

			if (ps.executeUpdate() != 1) {
				throw new DalException("Se ha modificar un n�mero de registros diferente de uno");
			} else {
				return origen;
			}
		} catch (Exception e) {
			throw new DalException("No se ha podido modificar el registro cuyo id es " + origen.getId(), e);
		}
	}

	/**
	 * M�todo para borrar un origen.
	 * 
	 * @param id El id de un origen para la eliminaci�n de sus datos.
	 */

	@Override
	public void borrar(long id) {
		try (Connection con = obtenerConexion(); PreparedStatement ps = con.prepareStatement(SQL_DELETE)) {
			ps.setLong(1, id);

			if (ps.executeUpdate() != 1) {
				throw new DalException("Se ha borrado un n�mero de registros diferente de uno");
			}
		} catch (Exception e) {
			throw new DalException("No se ha podido borrar el registro cuyo id es " + id, e);
		}
	}

}
