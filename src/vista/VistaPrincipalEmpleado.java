package vista;

import javax.swing.JFrame;

public class VistaPrincipalEmpleado extends JFrame {

	private static final long serialVersionUID = 1L;

	public VistaPrincipalEmpleado(String nombre) {
		this.setTitle("Casa Raiz Panel EMPLEADO");
		this.setSize(600, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

}
