package com.ipartek.formacion.mf0223_3.entidades;

import java.util.List;
import java.util.TreeMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

/**
 * 
 * Una clase para representar las categorias de un plato de comida. Utiliza la
 * librer�a lombok.
 * 
 * @version 1.0, 02/11/2021
 * @author Borja Olazabal Hernandez
 *
 */

public class Plato {
	private Long id;
	private String nombre;
	private String descripcion;
	private String ingredientes;
	private String receta;
	private String url_imagen;
	private int calorias;
	private Origen origen;
	private List<Categoria> categorias;

	private TreeMap<String, String> errores = new TreeMap<>();

	public Plato(Long id, String nombre, String descripcion, String ingredientes, String receta, String url_imagen,
			String calorias, Origen origen, List<Categoria> categorias) {
		super();
		setId(id);
		setNombre(nombre);
		setDescripcion(descripcion);
		setIngredientes(ingredientes);
		setReceta(receta);
		setUrl_imagen(url_imagen);
		setCalorias(calorias);
		setOrigen(origen);
		setCategorias(categorias);
	}

	/**
	 * Comprueba la validaci�n correcta al insertar nuevas recetas.
	 * 
	 * @param nombre El parametro nombre de la receta.
	 */
	public void setNombre(String nombre) {
		if (nombre == null || nombre.trim().length() == 0) {
			errores.put("nombre", "A�ade un nombre a la receta");
			return;
		}
		this.nombre = nombre;
	}

	/**
	 * Comprueba la validaci�n correcta al insertar nuevas recetas.
	 * 
	 * @param descripcion El parametro descripci�n de la receta.
	 */
	public void setDescripcion(String descripcion) {
		if (descripcion == null || descripcion.trim().length() == 0) {
			errores.put("descripcion", "La receta necesita una descripci�n");
			return;
		}
		if (descripcion.length() > 170) {
			errores.put("descripcion", "La descripci�n es muy larga");
			return;
		}
		this.descripcion = descripcion;
	}

	/**
	 * Comprueba la validaci�n correcta al insertar nuevas recetas.
	 * 
	 * @param ingredientes El parametro ingredientes de la receta.
	 */
	public void setIngredientes(String ingredientes) {
		if (ingredientes == null || ingredientes.trim().length() == 0) {
			errores.put("ingredientes", "La receta necesita ingredientes");
			return;
		}
		this.ingredientes = ingredientes;
	}

	/**
	 * Comprueba la validaci�n correcta al insertar nuevas recetas.
	 * 
	 * @param calorias El parametro calorias de la receta.
	 */
	public void setCalorias(String calorias) {
		if ("".equals(calorias)) {
			errores.put("calorias", "A�ade n�meros, no letras");
			return;
		}
		if (calorias.trim().length() == 0) {
			errores.put("calorias", "A�ade calorias a la receta");
			return;
		}
		int iCalorias = Integer.parseInt(calorias);
		if (iCalorias <= 0) {
			errores.put("calorias", "A�ade calorias positivas");
			return;
		}
		this.calorias = iCalorias;
	}

	/**
	 * Comprueba la validaci�n correcta al insertar nuevas recetas.
	 * 
	 * @param receta El parametro receta de la receta.
	 */
	public void setReceta(String receta) {
		if (receta == null || receta.trim().length() == 0) {
			errores.put("receta", "Necesita una receta de preparaci�n");
			return;
		}
		this.receta = receta;
	}

	/**
	 * Comprueba la validaci�n correcta al insertar nuevas recetas.
	 * 
	 * @param url_imagen El parametro url_imagen de la receta.
	 */
	public void setUrl_imagen(String url_imagen) {
		if (url_imagen == null || url_imagen.trim().length() == 0) {
			errores.put("url_imagen", "A�ade una imagen a la receta");
			return;
		}
		this.url_imagen = url_imagen;
	}

}
