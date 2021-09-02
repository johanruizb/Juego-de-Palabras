/*
 * Programacion interactiva
 * Autores: Johan Andres Ruiz Bermudez - 201942434
 * 			Victor Alfonso Alomia Angulo - 201943758
 * Fecha: 24/08/2021
 * Miniproyecto 3 - Juego de palabras
 */
package juegopalabras;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.Border;

// TODO: Auto-generated Javadoc
/**
 * The Class UsuarioVista. Se encarga de crear y añadir los elementos en el
 * panel en que se muestran los usuarios/perfiles disponibles guardados en el
 * archivo "users"
 */
public class UsuarioVista extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1171619590898461889L;

	private VistaGUIControl control;

	private JPanel panelUsuarios, panelBotones;
	private JLabel atras, salir, creajugador;
	private ImageIcon icono = new ImageIcon("src/imagenes/u1.png");

	private UsuarioVista dis;

	private RegistrosUsuarios registros;

	/**
	 * Instantiates a new usuario vista.
	 *
	 * @param c the c
	 */
	public UsuarioVista(VistaGUIControl c) {
		control = c;

		initGUI();
	}

	/**
	 * Inits the GUI. Crea y añade los elementos al panel.
	 */
	private void initGUI() {

		dis = this;

		// BORDE VACIO
		Border border = BorderFactory.createEmptyBorder(5, 10, 5, 10);

		// -
		dis.setLayout(new BorderLayout());
		dis.setOpaque(false);
		// -

		// PANELES
		panelUsuarios = new JPanel();
		panelUsuarios.setOpaque(false);

		panelBotones = new JPanel();
		panelBotones.setLayout(new BorderLayout());
		panelBotones.setOpaque(false);

		// BOTON DE SALIR
		salir = new JLabel(new ImageIcon("src/imagenes/salirS.png"));
		salir.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		salir.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}

		});

		// BOTON ATRAS
		atras = new JLabel(new ImageIcon("src/imagenes/atras.png"));
		atras.setBorder(border);
		atras.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				control.remove(dis);

				control.initLogo();
			}

		});

		panelBotones.add(atras, BorderLayout.WEST);

		// CREA UN NUEVO JUGADOR
		creajugador = new JLabel(new ImageIcon("src/imagenes/crear.png"));
		creajugador.setBorder(border);
		creajugador.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				control.remove(dis);

				control.initAddUser();
			}
		});
		panelBotones.add(creajugador, BorderLayout.EAST);

		// - USUARIOS
		registros = new RegistrosUsuarios();

		if (!registros.isEmpty()) {
			registros.readUsers();
		}

		Vector<Usuario> perfiles = registros.getUsuarios();
		Vector<JLabel> vistaPerfiles = new Vector<>(perfiles.size());

		// AÑADIR USUARIOS AL PANEL
		for (int i = 0; i < perfiles.size(); i++) {

			String nombre = perfiles.elementAt(i).getNombre();
			int nivel = perfiles.elementAt(i).getNivel();
			String nombre2 = nombre + " (Nivel " + nivel + ")";

			JLabel perfil = new JLabel(nombre2, icono, 0);

			perfil.setBorder(border);
			perfil.setForeground(Color.WHITE);
			perfil.addMouseListener(new MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
					((JLabel) e.getSource()).setEnabled(false);

					control.remove(dis);
					control.initGame(perfiles.elementAt(vistaPerfiles.indexOf(e.getSource())));
				}
			});

			panelUsuarios.add(perfil);
			vistaPerfiles.add(perfil);
		}

		if (perfiles.size() == 0) {
			JLabel noPlayers = new JLabel("No hay usuarios disponibles", new ImageIcon("src/imagenes/x.png"), 0);
			noPlayers.setForeground(Color.WHITE);
			panelUsuarios.add(noPlayers);
		}

		panelUsuarios.setPreferredSize(new Dimension(390, 65 * (panelUsuarios.getComponentCount() / 2)));

		// SCROLL PANEL DE USUARIOS
		JScrollPane scr = new JScrollPane(panelUsuarios);
		scr.getViewport().setOpaque(false);
		scr.setOpaque(false);
		scr.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scr.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scr.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
		scr.getVerticalScrollBar().setOpaque(false);
		scr.setBorder(null);

		// AÑADIR COMPONENTES
		dis.add(panelBotones, BorderLayout.NORTH);
		dis.add(scr, BorderLayout.CENTER);
		dis.add(salir, BorderLayout.SOUTH);

		control.add(dis, BorderLayout.CENTER);

	}

}
