/*
 * Programacion interactiva
 * Autores: Johan Andres Ruiz Bermudez - 201942434
 * 			Victor Alfonso Alomia Angulo - 201943758
 * Fecha: 24/08/2021
 * Miniproyecto 3 - Juego de palabras
 */
package juegopalabras;

import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class Usuario. La clase que contiene la informacion del usuario
 */

public class Usuario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1262263051274307629L;
	private int nivel = 1, errores = 0, aciertos = 0;
	private String nombre = "";

	public int getNivel() {
		return nivel;
	}

	public int getErrores() {
		return errores;
	}

	public int getAciertos() {
		return aciertos;
	}

	public String getNombre() {
		return nombre;
	}

	/**
	 * Up nivel. Aumenta el nivel del jugador.
	 */
	public void upNivel() {
		nivel++;
	}

	/**
	 * Up errores. Aumenta los errores del jugador.
	 */
	public void upErrores() {
		errores++;
	}

	/**
	 * Up aciertos. Aumenta los aciertos del jugador.
	 */
	public void upAciertos() {
		aciertos++;
	}

	/**
	 * Reset errores. Restablece los errores del jugador.
	 */
	public void resetErrores() {
		errores = 0;
	}

	/**
	 * Reset aciertos. Restablece los aciertos del jugador.
	 */
	public void resetAciertos() {
		aciertos = 0;

	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
