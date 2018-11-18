package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import modelo.Conexion;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import java.awt.Toolkit;

public class PanelInicioAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton btnInicioDeSesion;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPanel panel;
	private JPanel panel_1;

	public PanelInicioAdmin() {

		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Isaac\\Desktop\\fondo.jpg"));
		Conexion conexion = new Conexion();
		if (!conexion.generarConexion())
			JOptionPane.showMessageDialog(null, "Error al Establecer Conexion", "Fauil Connect", 0);

		new VistaPrincipalAdmin(conexion);
		this.dispose();
		this.setVisible(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Casa Raiz");
		getContentPane().setLayout(null);

		btnInicioDeSesion = new JButton("Inicio de Sesion");

		btnInicioDeSesion.setBounds(195, 235, 153, 33);
		getContentPane().add(btnInicioDeSesion);

		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(181, 88, 185, 52);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		textField = new JTextField();
		textField.setBounds(10, 21, 166, 20);
		panel_1.add(textField);
		textField.setToolTipText("Usuario");
		textField.setColumns(10);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Contraseña", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(181, 156, 185, 52);
		getContentPane().add(panel);
		panel.setLayout(null);

		passwordField = new JPasswordField();
		passwordField.setBounds(10, 21, 165, 20);
		panel.add(passwordField);
		passwordField.setToolTipText("Contrase\u00F1a");

		btnInicioDeSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					ResultSet rs = (ResultSet) conexion.Consulta(
							"SELECT * FROM usuarios WHERE Usuario ='" + textField.getText() + "' AND contraseña = '"
									+ String.valueOf(passwordField.getPassword()) + "' AND tipo = 'administrador' ");

					if (rs.next()) {
						if (rs.getString(5).toLowerCase().equals("administrador"))
							new VistaPrincipalAdmin(conexion);
						else
							new VistaPrincipalEmpleado(textField.getText());
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "el usuario o contraseña no existe", "Eror", 0);
						textField.setText("");
						passwordField.setText("");
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});

		this.setSize(600, 400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

	public static void main(String[] args) {
		new PanelInicioAdmin();
	}
}
