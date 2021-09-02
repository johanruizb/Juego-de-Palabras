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
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;

// TODO: Auto-generated Javadoc
/**
 * The Class VistaGUIControl. Clase que se encarga de crear, añadir y reemplazar
 * los paneles. Seria algo como un control de paneles de una unica ventana.
 */
@SuppressWarnings("unused")
public class VistaGUIControl extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4479818571994188957L;

	private VistaGUIControl referencia;
	private InicioVista inicio;
	private InformacionVista infoVista;
	private UsuarioVista vistaP;
	private RegistroVista newUser;
	private JuegoVista juego;

	private JLabel titulo;
	private EscuchaMouse escuchaTitulo;

	/**
	 * Instantiates a new vista GUI control.
	 */
	public VistaGUIControl() {
		initWindow();

		this.setUndecorated(true);
		this.setBackground(new Color(156, 39, 176, 125));
		this.setVisible(true);
		this.setSize(400, 330);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Inits the Window. Inicia la ventana y añade el panel de inicio.
	 */
	private void initWindow() {
		getContentPane().setLayout(new BorderLayout());

		// Titulo

		escuchaTitulo = new EscuchaMouse();

		titulo = new JLabel();

		titulo.setText("Juego de palabras");
		titulo.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		titulo.setBackground(new Color(156, 39, 176, 125));
		titulo.setForeground(Color.WHITE);
		titulo.setHorizontalAlignment(JLabel.CENTER);
		titulo.setOpaque(true);

		titulo.setBorder(BorderFactory.createEmptyBorder(3, 5, 5, 5));

		titulo.addMouseListener(escuchaTitulo);
		titulo.addMouseMotionListener(escuchaTitulo);

		getContentPane().add(titulo, BorderLayout.NORTH);

		//

		referencia = this;
		initLogo();
	}

	/**
	 * Inits the logo. Inicia el panel de inicio e internamente se añade a la
	 * ventana.
	 */
	public void initLogo() {

		titulo.setVisible(false);

		inicio = new InicioVista(referencia);

		repaint();
		revalidate();
	}

	/**
	 * Inits the menu. Internamente se reemplaza el panel actual por el panel de
	 * perfiles/usuarios guardados.
	 * 
	 */
	public void initMenu() {
		titulo.setVisible(true);

		vistaP = new UsuarioVista(referencia);

		revalidate();
		repaint();
	}

	/**
	 * Inits the add user. Internamente se reemplaza el panel actual por el panel de
	 * registro de un usuario/perfil nuevo.
	 * 
	 */
	public void initAddUser() {
		titulo.setVisible(true);

		newUser = new RegistroVista(referencia);

		revalidate();
		repaint();
	}

	/**
	 * Inits the game. Internamente se reemplaza el panel actual por el panel de
	 * juego creando un usuario nuevo.
	 * 
	 * @param name el nombre de usuario.
	 */
	public void initGame(String name) {
		titulo.setVisible(true);

		juego = new JuegoVista(referencia, name);

		revalidate();
		repaint();
	}

	/**
	 * Inits the game. Internamente se reemplaza el panel actual por el panel de
	 * juego con un usuario existente.
	 *
	 * @param u el usuario.
	 */
	public void initGame(Usuario u) {
		titulo.setVisible(true);

		juego = new JuegoVista(referencia, u);

		revalidate();
		repaint();
	}

	/**
	 * Inits the info. Internamente se reemplaza el panel actual por el panel de
	 * informacion del juego.
	 */
	public void initInfo() {
		titulo.setVisible(true);

		infoVista = new InformacionVista(referencia);

		revalidate();
		repaint();
	}

	/**
	 * The Class EscuchaMouse. Clase que se encarga de mover la ventana.
	 */
	private class EscuchaMouse extends MouseAdapter {

		private int x, y;

		/**
		 * Mouse pressed.
		 *
		 * @param eventMouse the event mouse
		 */
		@Override
		public void mousePressed(MouseEvent eventMouse) {
			// TODO Auto-generated method stub
			x = eventMouse.getX();
			y = eventMouse.getY();

			titulo.setCursor(new Cursor(Cursor.MOVE_CURSOR));
		}

		/**
		 * Mouse released.
		 *
		 * @param eventMouse the event mouse
		 */
		@Override
		public void mouseReleased(MouseEvent eventMouse) {
			// TODO Auto-generated method stub
			titulo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}

		/**
		 * Mouse dragged.
		 *
		 * @param eventMotionMouse the event motion mouse
		 */
		@Override
		public void mouseDragged(MouseEvent eventMotionMouse) {
			setLocation(referencia.getLocation().x + eventMotionMouse.getX() - x,
					referencia.getLocation().y + eventMotionMouse.getY() - y);
		}
	}
}
