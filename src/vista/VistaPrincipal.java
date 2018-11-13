package vista;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import java.awt.Color;

public class VistaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	public VistaPrincipal() {

		PanelSelecionInicio panel = new PanelSelecionInicio();
		panel.setBackground(new Color(255, 255, 224));
		getContentPane().add(panel, BorderLayout.CENTER);
		this.setTitle("Casa Raiz");
		this.setSize(600, 400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new VistaPrincipal();
	}

}
