/*
 * Programacion interactiva
 * Autores: Johan Andres Ruiz Bermudez - 201942434
 * 			Victor Alfonso Alomia Angulo - 201943758
 * Fecha: 24/08/2021
 * Miniproyecto 3 - Juego de palabras
 */
package juegopalabras;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Vector;

// TODO: Auto-generated Javadoc
/**
 * The Class RegistrosUsuarios. Se encarga de cargar y guardar los usuarios que
 * hayan jugado.
 */
public class RegistrosUsuarios {
	private FileInputStream fileInput;
	private ObjectInputStream input;
	private FileOutputStream fileOutput;
	private ObjectOutputStream output;

	private Vector<Usuario> usuarios = new Vector<>();
	private int aux = 0;

	/**
	 * Save user. Guarda el jugador actual. Esto lo hace junto a los jugadores que
	 * estan en el archivo serializado "users"
	 *
	 * @param user1 el usuario que se desea guardar.
	 */
	public void saveUser(Usuario user1) {
		usuarios.clear();

		usuarios.add(user1);

		if (!isEmpty())
			readUsers(user1);

		writeUsers();
	}

	/**
	 * Checks if is empty. Checa si no hay usuarios guardados en el archivo "users"
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		try {
			fileInput = new FileInputStream("src/usuarios/users");
			input = new ObjectInputStream(fileInput);
		} catch (IOException e) {
			return true;
		} finally {
			try {
				fileInput.close();
			} catch (Exception e) {
			}
		}
		return false;
	}

	/**
	 * Write users. Guarda los usuarios en el archivo "users"
	 */
	public void writeUsers() {
		try {
			
			fileOutput = new FileOutputStream("src/usuarios/users", false);
			output = new ObjectOutputStream(fileOutput);
			output.writeObject(new ArrayList<Usuario>(usuarios));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
				fileOutput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Read users. Carga los usuarios del archivo "users", teniendo en cuenta el
	 * usuario que pueda estar jugando en ese momento para reemplazar la informacion
	 * por una mas reciente.
	 *
	 * @param user1 el usario que se quiera añadir
	 */
	public void readUsers(Usuario user1) {

		try {
			
			fileInput = new FileInputStream("src/usuarios/users");
			input = new ObjectInputStream(fileInput);

			@SuppressWarnings("unchecked")
			Vector<Usuario> userInput = new Vector<Usuario>((ArrayList<Usuario>) input.readObject());

			if (contiene(userInput, user1)) {
				userInput.removeElementAt(aux);
			}
			
			usuarios.addAll(userInput);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
				fileInput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Contiene. Comprueba si el usuario exite en el archivo serializado.
	 *
	 * @param v the vector de usuarios
	 * @param u the usuario
	 * @return true, if successful
	 */
	public boolean contiene(Vector<Usuario> v, Usuario u) {
		for (int i = 0; i < v.size(); i++) {
			if (v.get(i).getNombre().equalsIgnoreCase(u.getNombre())) {
				aux = i;
				return true;
			}
		}
		return false;
	}

	/**
	 * Read users. Carga los usuarios del archivo "users".
	 */
	public void readUsers() {
		try {
			fileInput = new FileInputStream("src/usuarios/users");
			input = new ObjectInputStream(fileInput);

			@SuppressWarnings("unchecked")
			Vector<Usuario> userInput = new Vector<Usuario>((ArrayList<Usuario>) input.readObject());

			usuarios.addAll(userInput);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
				fileInput.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// --------

	public Vector<Usuario> getUsuarios() {
		return usuarios;
	}

}
