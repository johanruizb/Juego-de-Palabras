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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

// TODO: Auto-generated Javadoc
/**
 * The Class RegistroVista. Se encarga de crear y añadir los elementos al panel
 * de creacion de un nuevo perfil/usuario.
 */
public class RegistroVista extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8205636831334829530L;

	private JLabel aceptar, registro, atras;
	private JTextField nombre;
	private VistaGUIControl control;

	private RegistroVista dis;

	/**
	 * Instantiates a new registro vista.
	 *
	 * @param c the c
	 */
	public RegistroVista(VistaGUIControl c) {
		control = c;
		initGUI();
	}

	/**
	 * Inits the GUI. Se crean y añaden los elementos al panel.
	 */
	private void initGUI() {

		// BORDE VACIO
		Border emptyUp = BorderFactory.createEmptyBorder(50, 0, 0, 0);

		dis = this;
		dis.setOpaque(false);
		dis.setBorder(emptyUp);

		registro = new JLabel("Nombre: ");
		registro.setForeground(Color.WHITE);

		// NOMBRE
		nombre = new JTextField(18);
		nombre.setText("");

		// BOTON PARA REGISTRO
		aceptar = new JLabel(new ImageIcon("src/imagenes/guardar2.png"));
		aceptar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (nombre.getText().length() != 0 && !nombre.getText().contains(" ")) {
					((JLabel) e.getSource()).setEnabled(false);
					control.remove(dis);
					control.initGame(nombre.getText());
				} else {
					if (nombre.getText().length() == 0) {
						JOptionPane.showMessageDialog(null, "Por favor ingrese un nombre.", "Error",
								JOptionPane.INFORMATION_MESSAGE);
					} else if (nombre.getText().contains(" ")) {
						JOptionPane.showMessageDialog(null, "Por favor no use ESPACIOS en el nombre", "Error",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});

		// BOTON ATRAS
		atras = new JLabel(new ImageIcon("src/imagenes/atras.png"));
		atras.setBorder(BorderFactory.createEmptyBorder(50, 100, 0, 100));

		atras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				control.remove(dis);
				control.initMenu();
			}
		});

		// AÑADIR LOS COMPONENTES
		dis.add(registro);
		dis.add(nombre);
		dis.add(aceptar);
		dis.add(atras);

		control.add(dis, BorderLayout.CENTER);
	}

}
