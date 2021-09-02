/*
 * Programacion interactiva
 * Autores: Johan Andres Ruiz Bermudez - 201942434
 * 			Victor Alfonso Alomia Angulo - 201943758
 * Fecha: 24/08/2021
 * Miniproyecto 3 - Juego de palabras
 */
package juegopalabras;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

// TODO: Auto-generated Javadoc
/**
 * The Class InicioVista. Clase que encarga de crear y añadir los elementos al
 * panel de "inicio" del programa. Él mismo es ese panel inicial.
 */
public class InicioVista extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6113760264015699991L;

	private JLabel logo, inicio, salir, info;

	private VistaGUIControl control;
	private InicioVista dis;

	/**
	 * Instantiates a new inicio vista.
	 *
	 * @param c the c
	 */
	public InicioVista(VistaGUIControl c) {
		control = c;
		initGUI();
	}

	/**
	 * Inits the GUI. Crea los elementos de la GUI y los añade al panel de "inicio".
	 */
	private void initGUI() {

		dis = this;
		dis.setOpaque(false);

		// LOGO
		logo = new JLabel(new ImageIcon("src/imagenes/logo.png"));

		// BORDE VACIO
		Border emptyBorderLR = BorderFactory.createEmptyBorder(0, 20, 0, 20);

		// INICIO
		inicio = new JLabel(new ImageIcon("src/imagenes/play.png"));
		inicio.setBorder(emptyBorderLR);
		inicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.remove(dis);
				control.initMenu();
			}
		});

		// INFORMACION
		info = new JLabel(new ImageIcon("src/imagenes/info.png"));
		info.setBorder(emptyBorderLR);
		info.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.remove(dis);
				control.initInfo();
			}
		});

		// SALIR
		salir = new JLabel(new ImageIcon("src/imagenes/salir.png"));
		salir.setBorder(emptyBorderLR);
		salir.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});

		// AÑADIR COMPONENTES
		dis.add(logo);
		dis.add(inicio);
		dis.add(info);
		dis.add(salir);

		control.add(dis, BorderLayout.CENTER);
	}

}
