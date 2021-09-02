/*
 * Programacion interactiva
 * Autores: Johan Andres Ruiz Bermudez - 201942434
 * 			Victor Alfonso Alomia Angulo - 201943758
 * Fecha: 24/08/2021
 * Miniproyecto 3 - Juego de palabras
 */
package juegopalabras;

import java.awt.EventQueue;

// TODO: Auto-generated Javadoc
/**
 * The Class JuegoPrincipal. La clase que contiene el metedo main.
 */
public class Principal {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				@SuppressWarnings("unused")
				VistaGUIControl inicio = new VistaGUIControl();

			}
		});

	}

}
