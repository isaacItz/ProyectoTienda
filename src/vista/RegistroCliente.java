package vista;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static modelo.Utileria.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import modelo.Conexion;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistroCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField nombretextField;
	private JTextField clave;
	private JTextField direccion;
	private JPanel panel_2;
	private JTextField telefono;
	private JPanel panel_3;
	private Conexion conexion;

	public RegistroCliente(Conexion conexion) {
		this.conexion = conexion;
		setModal(true);
		setTitle("Registro Clientes CASA RAIZ");
		setSize(400, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Nombre Completo del Cliente", TitledBorder.LEADING, TitledBorder.TOP,
				null, null));
		panel.setBounds(41, 139, 304, 43);
		getContentPane().add(panel);
		panel.setLayout(null);

		nombretextField = new JTextField();
		nombretextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				nombretextField.setBackground(Color.WHITE);
			}
		});
		nombretextField.setBounds(6, 16, 288, 20);
		panel.add(nombretextField);
		nombretextField.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Clave del Cliente (debe ser unica)", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel_1.setBounds(41, 68, 304, 43);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		clave = new JTextField();
		clave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				clave.setBackground(Color.WHITE);
			}
		});
		clave.setBounds(6, 16, 288, 20);
		panel_1.add(clave);
		clave.setColumns(10);

		panel_2 = new JPanel();
		panel_2.setBorder(
				new TitledBorder(null, "Direccion de Facuracion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(41, 222, 304, 43);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		direccion = new JTextField();
		direccion.setBounds(6, 16, 288, 20);
		panel_2.add(direccion);
		direccion.setColumns(10);

		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Telefono", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(41, 300, 304, 43);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);

		telefono = new JTextField();
		telefono.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				telefono.setBackground(Color.WHITE);
			}
		});
		telefono.setBounds(6, 16, 288, 20);
		panel_3.add(telefono);
		telefono.setColumns(10);

		JButton btnRegistarCliente = new JButton("Registar Cliente");
		btnRegistarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (validar()) {
					do {
						String consulta = "insert into cliente values(?,?,?,?)";
						try {
							PreparedStatement ps = conexion.getPreparedStatement(consulta);
							ps.setString(1, clave.getText());
							ps.setString(2, nombretextField.getText());
							ps.setString(3, direccion.getText());
							ps.setInt(4, Integer.parseInt(telefono.getText().replaceAll(" ", "")));
							ps.executeUpdate();
							escribir("Cliente Registrado");

						} catch (SQLException e) {
							e.printStackTrace();
						} finally {
							clave.setText("");
							nombretextField.setText("");
							direccion.setText("");
							telefono.setText("");
						}

					} while (continuar("Desea Continuar Agregando Clientes?"));
					dispose();
				}

			}
		});
		btnRegistarCliente.setBounds(127, 410, 136, 36);
		getContentPane().add(btnRegistarCliente);

	}

	public boolean validar() {

		if (clave.getText().isEmpty()) {
			clave.setBackground(Color.red);
			escribir("Ingresa una Clave Valida");
			return false;
		}
		if (conexion.getCampo("cliente", "id_cliente", "id_cliente", clave.getText()) != null) {
			clave.setBackground(Color.red);
			escribir("La Clave Ya esta Registrada");
			return false;
		}

		if (nombretextField.getText().isEmpty()) {
			nombretextField.setBackground(Color.red);
			escribir("Escriba un Nombre Valido");
			return false;
		}

		if (!esNumero(telefono.getText().replaceAll(" ", ""))) {
			telefono.setBackground(Color.red);
			escribir("El Numero Telefonico solo debe Llevar Numeros");
			return false;
		}

		return true;

	}

}
