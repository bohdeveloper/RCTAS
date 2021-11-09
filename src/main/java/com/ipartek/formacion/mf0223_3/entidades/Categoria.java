package com.ipartek.formacion.mf0223_3.entidades;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

/**
 * 
 * Una clase para representar las categorias de un plato de comida. Utiliza
 * la librería lombok.
 * 
 * @version 1.0, 02/11/2021
 * @author Borja Olazabal Hernandez
 *
 */

public class Categoria {
	private Long id;
	private String nombre;

	private List<Plato> platos;
}
