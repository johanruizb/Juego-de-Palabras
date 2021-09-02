/*
 * Programacion interactiva
 * Autores: Johan Andres Ruiz Bermudez - 201942434
 * 			Victor Alfonso Alomia Angulo - 201943758
 * Fecha: 24/08/2021
 * Miniproyecto 3 - Juego de palabras
 */

package juegopalabras;

import java.util.Arrays;
import java.util.Random;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class JuegoControl. Una clase que se encarga de la logica del juego.
 */
public class JuegoControl {
	private boolean upLv = false, gameOver = false, emptyPS = false, finalSerie = false, completado = false;
	private int serie = 0;

	private Usuario usuario1;

	private LecturaPalabras lectorArchivo = new LecturaPalabras();
	private RegistrosUsuarios save = new RegistrosUsuarios();

	private Vector<String> palabras = new Vector<String>();
	private Vector<String> copyPalabras = new Vector<String>();
	private Vector<String> palabrasSerie = new Vector<String>();

	private Vector<Integer> erroresMax = new Vector<Integer>(Arrays.asList(3, 4, 6, 7, 9));
	private Vector<Integer> subirNivel = new Vector<Integer>(Arrays.asList(7, 9, 12, 15, 18));

	/**
	 * Instantiates a new juego control.
	 */
	JuegoControl() {
		usuario1 = new Usuario();
		palabras = lectorArchivo.leerPalabras();
		copyPalabras.addAll(palabras);
	}

	/**
	 * Crear usuario. Metodo que crea un nuevo usuario para empezar a jugar.
	 *
	 * @param name el nombre del usuario
	 */
	public void crearUsuario(String name) {
		usuario1.setNombre(name);
	}

	/**
	 * Cargar usuario. Carga el usuario para empezar el juego. Lo carga para hacer
	 * las operaciones logicas.
	 *
	 * @param u el usuario ya existente
	 */
	public void cargarUsuario(Usuario u) {
		usuario1 = u;
	}

	/**
	 * Inits the palabras. Inicia las palabras de manera aleatoria para una unica
	 * serie.
	 *
	 * @return las palabras de la serie
	 */
	public Vector<String> initPalabras() {
		aleatorio();

		return palabrasSerie;
	}

	/**
	 * Aleatorio. Se encarga de recibir las lecturas del archivo .txt y extraer
	 * palabras aleatoriamente para la serie de palabras
	 */
	private void aleatorio() {
		int size = palabrasSerie.size();

		Random random = new Random();
		int number = random.nextInt(palabras.size());

		for (int i = 0; i < (usuario1.getNivel() * 2 + 2) - size; i++) {

			palabrasSerie.add(palabras.elementAt(number).toUpperCase());
			palabras.removeElementAt(number);

			if (palabras.size() > 0) {
				number = random.nextInt(palabras.size());
			} else {
				break;
			}
		}

	}

	/**
	 * Checks if is correcto. Chequa si la palabra ingresada por el usuario esta en
	 * la serie de palabras
	 *
	 * @param w la palabra ingresada
	 * @return true, if is correcto
	 */
	public boolean isCorrecto(String w) {

		if (palabrasSerie.contains(w)) {

			usuario1.upAciertos();
			palabrasSerie.removeElementAt(palabrasSerie.indexOf(w));

			if (subirNivel.elementAt(usuario1.getNivel() - 1).equals(usuario1.getAciertos())) {
				levelUp();

				serie = 0;
			}

			if (palabrasSerie.size() == 0) {
				emptyPS = true;
			}

			return true;

		} else {
			usuario1.upErrores();

			if (erroresMax.elementAt(usuario1.getNivel() - 1).equals(usuario1.getErrores())) {
				gameOver();

				serie = 0;
			}
		}

		return false;
	}

	/**
	 * Game over. Hace saber que el juego termino por fallar las palabras.
	 */
	public void gameOver() {
		gameOver = true;
		usuario1.resetErrores();
		usuario1.resetAciertos();

		palabrasSerie.clear();

		palabras.clear();
		palabras.addAll(copyPalabras);
	}

	/**
	 * Level up. Hace saber que el jugador subio de nivel y hace los respectivos
	 * cambios ademas de guardar la "partida" en ese punto.
	 */
	private void levelUp() {

		if (usuario1.getNivel() == 5) {
			completado = true;
		} else {
			upLv = true;
			usuario1.resetAciertos();
			if (usuario1.getNivel() < 5) {
				usuario1.upNivel();
				save.saveUser(usuario1);
			}
		}
	}

	/**
	 * Sets the up lv. Define que el usuario ya subio de nivel.
	 */
	public void setUpLv() {
		upLv = false;
	}

	/**
	 * Sets the game over. Define el gameOver en falso para poder continuar el
	 * juego.
	 */
	public void setGameOver() {
		gameOver = false;
	}

	/**
	 * Sets the empy PS. Establece que las palabras de la serie se terminaron y se
	 * necesita empezar una serie nueva.
	 */
	public void setEmpyPS() {
		emptyPS = false;
	}

	/**
	 * Reset serie. Define que la nueva serie debe iniciarse por que el jugador ha
	 * decido finalizar la actual serie.
	 */
	public void resetSerie() {
		emptyPS = true;
	}

	/**
	 * Sets the serie. Controla el numero de series por nivel. Maximo 2 series por
	 * nivel. Se activa un indicador que da gameOver por haber pasado las series y
	 * no haber subido de nivel;
	 */
	public void setSerie() {
		serie++;

		if (serie == 2) {
			finalSerie = true;
			serie = 0;
		}
	}

	/**
	 * Sets the final serie. Desactiva el indicador de gameOver por el numero de
	 * series.
	 */
	public void setFinalSerie() {
		finalSerie = false;
		usuario1.resetErrores();
		usuario1.resetAciertos();
	}

	/**
	 * Save. Guarda el usuario actual. Esto lo hace un archivo serializado.
	 */
	public void save() {
		save.saveUser(usuario1);
	}

	// ----------------

	public int getNivel() {
		return usuario1.getNivel();
	}

	public int getErrores() {
		return usuario1.getErrores();
	}

	public boolean isUpLv() {
		return upLv;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public String getNombre() {
		return usuario1.getNombre();
	}

	public boolean isEmptyPS() {
		return emptyPS;
	}

	public boolean isFinalSerie() {
		return finalSerie;
	}

	public boolean isCompletado() {
		return completado;
	}

}
