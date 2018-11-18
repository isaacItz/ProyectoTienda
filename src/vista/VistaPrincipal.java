package vista;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import modelo.Conexion;

public class VistaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	public VistaPrincipal() {

		Conexion conexion = new Conexion();
		if (!conexion.generarConexion())
			JOptionPane.showMessageDialog(null, "Error al Establecer Conexion", "Fauil Connect", 0);
		// /// PanelInicioAdmin panel = new PanelInicioAdmin(conexion);
		// panel.setBackground(new Color(255, 255, 224));
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// getContentPane().add(panel, BorderLayout.CENTER);
		this.setTitle("Casa Raiz");
		this.setSize(600, 400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

}
