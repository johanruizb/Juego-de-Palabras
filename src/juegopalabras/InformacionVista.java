/*
 * Programacion interactiva
 * Autores: Johan Andres Ruiz Bermudez - 201942434
 * 			Victor Alfonso Alomia Angulo - 201943758
 * Fecha: 25/08/2021
 * Miniproyecto 3 - Juego de palabras
 */
package juegopalabras;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

// TODO: Auto-generated Javadoc
/**
 * The Class InformacionVista. Se encarga de crear y añadir los elementos al
 * panel de "informacion"
 */
public class InformacionVista extends JPanel {

	private static final long serialVersionUID = 7240628100611859604L;

	private JLabel etiq, atras;
	private String texto1;
	private InformacionVista dis;
	private VistaGUIControl control;

	/**
	 * Instantiates a new informacion vista.
	 *
	 * @param c the c
	 */
	public InformacionVista(VistaGUIControl c) {
		control = c;
		iniComponent();
	}

	/**
	 * Ini component. Crea y añade los componentes al panel de "informacion"
	 */
	public void iniComponent() {

		dis = this;
		dis.setLayout(new BorderLayout());
		dis.setOpaque(false);

		// FUENTE
		Font font = new Font(Font.DIALOG, Font.BOLD + Font.ROMAN_BASELINE, 13);

		// BORDE
		Border line = BorderFactory.createLineBorder(new Color(251, 192, 45), 3, true);
		Border titledLine = BorderFactory.createTitledBorder(line, "¿COMO JUGAR?", TitledBorder.CENTER,
				TitledBorder.DEFAULT_JUSTIFICATION, font, Color.WHITE);

		// INFORMACION
		JPanel informacion = new JPanel();
		informacion.setPreferredSize(new Dimension(380, 280));
		informacion.setOpaque(false);

		texto1 = "<html>El jugador debe ingresar el mayor número posible "
				+ "de palabras que recuerde de las series que se presentan.<br><br>"
				+ "Se muestran 2 series por nivel. Una vez se termine de mostrar "
				+ "una de las series el jugador podrá usar el teclado para ingresar las "
				+ "palabras que recuerde y usar la tecla ENTER para confirmar la palabra "
				+ "escrita o usar la tecla SUPRIMIR o RETROCESO para borrar la ultima letra. "
				+ "El objetivo es completar la serie ingresando las palabras correctas y subir "
				+ "de nivel acertando cierto numero de palabras, si no es posible, el jugador "
				+ "puede finalizar la serie cuando desee. Se perdera el nivel si el jugador falla "
				+ "una cantidad de veces o si no sube de nivel antes de terminar ambas series.</html>";

		etiq = new JLabel(texto1);
		etiq.setFont(font);
		etiq.setForeground(Color.WHITE);
		etiq.setPreferredSize(new Dimension(350, 270));

		informacion.add(etiq);

		// BOTON ATRAS
		atras = new JLabel(new ImageIcon("src/imagenes/atras.png"));
		atras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent event) {
				control.remove(dis);

				control.initLogo();
			}
		});

		// SCROLL
		JScrollPane srcoll = new JScrollPane(informacion);
		srcoll.getViewport().setOpaque(false);
		srcoll.setOpaque(false);
		srcoll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		srcoll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		srcoll.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
		srcoll.getVerticalScrollBar().setOpaque(false);
		srcoll.setBorder(null);

		// AÑADIR COMPONENTES
		dis.setBorder(titledLine);

		dis.add(srcoll, BorderLayout.CENTER);
		dis.add(atras, BorderLayout.SOUTH);

		control.add(dis, BorderLayout.CENTER);
	}

}
