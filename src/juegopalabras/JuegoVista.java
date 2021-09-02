/*
 * Programacion interactiva
 * Autores: Johan Andres Ruiz Bermudez - 201942434
 * 			Victor Alfonso Alomia Angulo - 201943758
 * Fecha: 24/08/2021
 * Miniproyecto 3 - Juego de palabras
 */
package juegopalabras;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

// TODO: Auto-generated Javadoc
/**
 * The Class JuegoVista. Se encarga de crear y añadir los elementos del juego.
 */
public class JuegoVista extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2125796357015697280L;

	private JLabel nombre, nivel, accion, palabra, lienzo, terminar, salir;
	private JPanel norte, centro, sur;
	private JuegoControl controlJuego = new JuegoControl();;

	private VistaGUIControl control;
	private JuegoVista dis;
	private Vector<String> palabrasSerie = new Vector<>();

	private EscuchaT escuchaTeclado;
	private Escucha escuchaTimer;
	private EscuchaM escuchaMouse;

	private Timer inicioNivel, inicioSerie, comprobarJuego;

	private String s = new String();
	private boolean escribir = false;

	/**
	 * Instantiates a new juego vista.
	 *
	 * @param c the c
	 */
	public JuegoVista(VistaGUIControl c, String s) {
		controlJuego.crearUsuario(s);
		controlJuego.save();
		control = c;

		initGUI();

	}

	public JuegoVista(VistaGUIControl c, Usuario u) {
		controlJuego.cargarUsuario(u);
		control = c;

		initGUI();
	}

	/**
	 * Crear usuario. Recibe el nombre del usuario y lo pasa a control para crear el
	 * perfil e inciar el juego.
	 *
	 * @param name el nombre del jugador
	 */
	public void crearUsuario(String name) {

	}

	/**
	 * Cargar usuario. Recibe el usuario y lo pasa a control para inciar el juego.
	 *
	 * @param u el usuario
	 */
	public void cargarUsuario(Usuario u) {

	}

	/**
	 * Inits the GUI.
	 */
	private void initGUI() {

		dis = this;

		// ESCUCHAS
		escuchaTeclado = new EscuchaT();
		escuchaTimer = new Escucha();
		escuchaMouse = new EscuchaM();

		// TIMER

		inicioNivel = new Timer(1000, escuchaTimer);
		inicioSerie = new Timer(2000, escuchaTimer);
		comprobarJuego = new Timer(2000, escuchaTimer);

		// ------

		dis.setLayout(new BorderLayout());
		dis.setOpaque(false);

		// --- NORTE
		norte = new JPanel();
		norte.setOpaque(false);

		// BORDES VACIOS
		Border emptyBorderLR20 = BorderFactory.createEmptyBorder(0, 20, 0, 20);
		Border emptyBorderLR = BorderFactory.createEmptyBorder(0, 100, 0, 100);

		// NOMBRE
		nombre = new JLabel("Nombre: " + controlJuego.getNombre());
		nombre.setForeground(Color.WHITE);
		nombre.setBorder(emptyBorderLR20);

		// NIVEL
		nivel = new JLabel("Nivel: " + controlJuego.getNivel());
		nivel.setForeground(Color.WHITE);
		nivel.setBorder(emptyBorderLR20);

		norte.add(nombre);
		norte.add(nivel);

		dis.add(norte, BorderLayout.NORTH);

		// --- CENTRO

		centro = new JPanel();
		centro.setOpaque(false);

		// PALABRAS
		palabrasSerie = controlJuego.initPalabras();

		palabra = new JLabel("Iniciando nivel.");
		palabra.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
		palabra.setForeground(Color.WHITE);
		palabra.setBorder(emptyBorderLR);

		// ICONO DE ACCION
		accion = new JLabel(new ImageIcon("src/imagenes/teclado.png"));
		accion.setVisible(false);
		accion.setForeground(Color.WHITE);
		accion.setBorder(emptyBorderLR);

		// LIENZO DE PALABRAS
		lienzo = new JLabel(" ");
		lienzo.setFont(new Font(Font.DIALOG, Font.BOLD, 30));
		lienzo.setOpaque(false);
		lienzo.setBackground(Color.WHITE);
		lienzo.setBorder(emptyBorderLR);

		// AÑADIR AL CENTRO
		centro.add(palabra);
		centro.add(lienzo);
		centro.add(accion);

		dis.add(centro, BorderLayout.CENTER);

		// --------

		// --- SUR

		sur = new JPanel();
		sur.setOpaque(false);

		// TERMINAR
		terminar = new JLabel("Terminar serie", new ImageIcon("src/imagenes/terminar2.png"), 0);
		terminar.setVisible(false);
		terminar.setBackground(new Color(255, 218, 44));
		terminar.setOpaque(true);
		terminar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		terminar.addMouseListener(escuchaMouse);

		// SALIR
		salir = new JLabel("Salir", new ImageIcon("src/imagenes/salir2.png"), 0);
		salir.setVisible(false);
		salir.setBackground(new Color(255, 218, 44));
		salir.setOpaque(true);
		salir.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		salir.addMouseListener(escuchaMouse);

		// AÑADIR AL SUR
		sur.add(terminar);
		sur.add(salir);

		dis.add(sur, BorderLayout.SOUTH);

		//
		control.add(dis, BorderLayout.CENTER);
		// ---------------

		control.setVisible(false);
		control.setVisible(true);
		control.addKeyListener(escuchaTeclado);
		control.setFocusable(true);

		inicioNivel.start();

	}

	/**
	 * The Class Escucha. Clase encargada del escucha para los timers.
	 */
	private class Escucha implements ActionListener {

		private int aux = 0;

		/**
		 * Action performed. Realiza varias acciones como iniciar, finalizar y reiniciar
		 * el juego o la serie. Ademas de cosas como comprobar las condiciones del juego
		 * (gameOver, lvUp, etc);
		 *
		 * @param e the event
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			// INICIO DE NIVEL (inicioNivel)
			if (e.getSource() == inicioNivel) {
				inicioNivel.stop();

				if (inicioNivel.getDelay() == 1000) {
					inicioNivel.setDelay(3000);
				}

				palabra.setText("Iniciando nivel..");

				inicioSerie.start();

				// INICIO DE SERIES (inicioSerie)
			} else if (e.getSource() == inicioSerie) {

				if (palabra.getIcon() != null)
					palabra.setIcon(null);

				if (aux < (controlJuego.getNivel() * 2 + 2)) {
					palabra.setText(palabrasSerie.elementAt(aux));
					aux++;

					repaint();
					revalidate();
				} else {

					terminar.setVisible(true);
					terminar.setEnabled(true);
					salir.setVisible(true);

					palabra.setText("Escribe las palabras..");
					accion.setVisible(true);

					lienzo.setOpaque(true);

					repaint();
					revalidate();

					inicioSerie.stop();

					escribir = true;

					aux = 0;
				}

				// COMPROBACION DE PALABRAS Y ESTADO DEL JUEGO (comprobarJuego)
			} else if (e.getSource() == comprobarJuego) {
				comprobarJuego.stop();

				lienzo.setText(" ");
				lienzo.setForeground(Color.BLACK);

				if (controlJuego.isCompletado()) {
					lienzo.setVisible(false);
					terminar.setVisible(false);

					palabra.setText("¡Haz completado el juego!");
					accion.setIcon(new ImageIcon("src/imagenes/trofeo.png"));

				}

				else if (controlJuego.isUpLv()) {
					palabra.setText("¡Haz subido de nivel!");
					palabra.setIcon(new ImageIcon("src/imagenes/up.png"));

					controlJuego.setUpLv();

					palabrasSerie = controlJuego.initPalabras();

					lienzo.setOpaque(false);
					accion.setVisible(false);

					nivel.setText("Nivel: " + controlJuego.getNivel());

					inicioNivel.start();
				} else if (controlJuego.isGameOver()) {

					escribir = false;

					palabra.setText("¡Haz perdido!");

					controlJuego.setGameOver();
					palabrasSerie = controlJuego.initPalabras();

					lienzo.setOpaque(false);
					accion.setVisible(false);

					inicioNivel.start();

				} else if (controlJuego.isFinalSerie()) {
					palabra.setText("¡No hay mas series!");
					controlJuego.setFinalSerie();
					controlJuego.gameOver();

					palabrasSerie = controlJuego.initPalabras();

					lienzo.setOpaque(false);
					accion.setVisible(false);

					comprobarJuego.start();

				} else if (controlJuego.isEmptyPS()) {
					palabra.setText("¡Nueva serie!");
					controlJuego.setEmpyPS();

					palabrasSerie = controlJuego.initPalabras();

					lienzo.setOpaque(false);
					accion.setVisible(false);

					inicioSerie.start();

				} else {
					escribir = true;
				}

				repaint();
				revalidate();
			}
		}

	}

	/**
	 * The Class EscuchaT. Escucha encargado del teclado para ingresar las palabras
	 * o borrarlas.
	 */
	private class EscuchaT extends KeyAdapter {

		/**
		 * Key typed. Pasa las palabras que se ingresan a una cadena de texto y luego a
		 * una etiqueta (JLabel).
		 *
		 * @param e the event
		 */
		@Override
		public void keyTyped(KeyEvent e) {
			String abc = "qwertyuiopasdfghjklñzxcvbnm".toUpperCase();
			boolean abcContiene = abc.contains(String.valueOf(e.getKeyChar()).toUpperCase());

			if (s.length() < 15 && abcContiene && escribir) {

				if (s.equals(" "))
					s = "";

				s += String.valueOf(e.getKeyChar()).toUpperCase();

				lienzo.setText(s);
				repaint();
				revalidate();
			}

		}

		/**
		 * Key pressed. Se encarga de recibir los caracteres de INTRO, DELETE y SUPR.
		 * para realizar las respectivas acciones sobre la etiqueta
		 *
		 * @param e the event
		 */
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == 10 && !lienzo.getText().equals(" ") && escribir) {

				escribir = false;
				comprobarJuego.start();

				if (controlJuego.isCorrecto(lienzo.getText())) {
					lienzo.setForeground(Color.GREEN);
				} else {
					lienzo.setForeground(Color.RED);
				}

				s = " ";

				repaint();
				revalidate();

			} else if (e.getKeyCode() == 8 || e.getKeyCode() == 127) {

				if (s.length() > 1 && !s.equals(" ")) {
					s = s.substring(0, s.length() - 1);
				} else {
					s = " ";
				}

				lienzo.setText(s);

				repaint();
				revalidate();
			}

		}

	}

	/**
	 * The Class EscuchaM. Clase que se encarga del escucha del Mouse.
	 * Principalmente clicks sobre alguna etiqueta.
	 */
	private class EscuchaM extends MouseAdapter {

		/**
		 * Mouse clicked. Se encarga de ejecutar las acciones de SALIR y TERMINAR SERIE.
		 *
		 * @param e the e
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getSource() == terminar && terminar.isEnabled()) {
				terminar.setEnabled(false);
				escribir = false;

				escuchaTimer.aux = 0;

				s = " ";
				lienzo.setText(s);
				lienzo.setOpaque(false);

				accion.setVisible(false);

				palabra.setText("¡Serie terminada!");

				repaint();
				revalidate();

				controlJuego.setSerie();
				controlJuego.resetSerie();

				palabrasSerie = controlJuego.initPalabras();

				comprobarJuego.start();

			} else if (e.getSource() == salir && salir.isEnabled()) {
				salir.setEnabled(false);

				control.remove(dis);
				control.initMenu();
			}
		}

	}

}
