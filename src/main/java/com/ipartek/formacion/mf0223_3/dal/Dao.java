package com.ipartek.formacion.mf0223_3.dal;

import java.util.List;

public interface Dao<T> {
	default Iterable<T> obtenerTodos() {
		throw new DalException("No implementado");
	}

	default T obtenerPorId(long id) {
		throw new DalException("No implementado");
	}

	default List<T> obtenerCategoriasPlato(long id) {
		throw new DalException("No implementado");
	}
	
	default T insertar(T objeto) {
		throw new DalException("No implementado");
	}

	default void insertarCategPlato(long categoriaID, long platoID) {
		throw new DalException("No implementado");
	}

	default T modificar(T objeto) {
		throw new DalException("No implementado");
	}

	default void borrar(long id) {
		throw new DalException("No implementado");
	}


}
