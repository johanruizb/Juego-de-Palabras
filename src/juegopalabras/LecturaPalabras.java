/*
 * Programacion interactiva
 * Autores: Johan Andres Ruiz Bermudez - 201942434
 * 			Victor Alfonso Alomia Angulo - 201943758
 * Fecha: 24/08/2021
 * Miniproyecto 3 - Juego de palabras
 */
package juegopalabras;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class LecturaPalabras. La clase encargada de leer las palabras del
 * archivo .txt
 */
public class LecturaPalabras {
	private FileReader file;
	private BufferedReader archivo;

	/**
	 * Leer palabras. Carga/Lee las palabras desde el archivo .txt para retornarlas
	 * en un Vector de cadenas de texto.
	 *
	 * @return the vector de palabras
	 */
	public Vector<String> leerPalabras() {

		Vector<String> palabras = new Vector<String>();

		try {

			file = new FileReader("src/palabras.txt");
			archivo = new BufferedReader(file);

			String temp = archivo.readLine();

			// System.out.println(temp);

			while (temp != null) {
				palabras.add(temp);
				temp = archivo.readLine();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return palabras;
	}

}
