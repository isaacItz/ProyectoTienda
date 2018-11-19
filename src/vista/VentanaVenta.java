package vista;

import static modelo.Utileria.esNumero;
import static modelo.Utileria.escribir;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.toedter.calendar.JDateChooser;

import modelo.Conexion;

public class VentanaVenta extends JDialog {

	private static final long serialVersionUID = 1L;
	private Conexion conexion;
	private JTextField claveProducto_1;
	private JTextField claveUser;
	private JTextField idVenta;
	private JTextField vendedor;
	private JPanel panel_3;
	private JTextField nombreProducto;
	private JPanel panel_5;
	private JTextField descripcionProducto;
	private JPanel panel_6;
	private JTextField precioP;
	private JPanel panel_7;
	private JTextField existencias;
	private JPanel panel_8;
	private JTextField cantidadP;
	private JPanel panel_9;
	private JTextField precioFinal;
	private JPanel panel_11;
	private JTextField monto;
	private JPanel panel_12;
	private JButton btnVenta;
	private int claveProducto;
	private int idCliente;
	private String nombreUsuario;
	private int precio;
	private JDateChooser dateChooser;
	private JCheckBox chckbxNewCheckBox;
	private JComboBox<String> comboBox;
	private int idVentaNum;

	public VentanaVenta(Conexion conexion, int claveProducto, int idCliente, String nombreUsuario, boolean clienteRnd) {

		this.idVentaNum = conexion.getIDVenta();
		this.claveProducto = claveProducto;
		this.idCliente = idCliente;
		this.nombreUsuario = nombreUsuario;
		this.conexion = conexion;
		setModal(true);
		setTitle("Registro Clientes CASA RAIZ");
		setSize(600, 500);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Clave Producto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(186, 37, 98, 43);
		getContentPane().add(panel);
		panel.setLayout(null);

		claveProducto_1 = new JTextField();
		claveProducto_1.setEditable(false);
		claveProducto_1.setBounds(6, 16, 86, 20);
		panel.add(claveProducto_1);
		claveProducto_1.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Clave Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(333, 37, 98, 43);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		claveUser = new JTextField();
		claveUser.setEditable(false);
		claveUser.setBounds(6, 16, 86, 20);
		panel_1.add(claveUser);
		claveUser.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "ID Venta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(48, 37, 98, 43);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		idVenta = new JTextField();
		idVenta.setEditable(false);
		idVenta.setBounds(6, 16, 86, 20);
		panel_2.add(idVenta);
		idVenta.setColumns(10);

		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Vendedor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(470, 37, 98, 43);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);

		vendedor = new JTextField();
		vendedor.setEditable(false);
		vendedor.setBounds(6, 16, 86, 20);
		panel_3.add(vendedor);
		vendedor.setColumns(10);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Fecha de Venta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(333, 208, 235, 43);
		getContentPane().add(panel_4);
		panel_4.setLayout(null);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(6, 16, 126, 20);
		panel_4.add(dateChooser);

		chckbxNewCheckBox = new JCheckBox("Fecha Actual");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dateChooser.setEnabled(!dateChooser.isEnabled());
				dateChooser.setDate(null);
			}
		});
		chckbxNewCheckBox.setBounds(138, 16, 91, 23);
		panel_4.add(chckbxNewCheckBox);

		panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Producto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(48, 120, 236, 43);
		getContentPane().add(panel_5);
		panel_5.setLayout(null);

		nombreProducto = new JTextField();
		nombreProducto.setEditable(false);
		nombreProducto.setBounds(6, 16, 220, 20);
		panel_5.add(nombreProducto);
		nombreProducto.setColumns(10);

		panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "Descripcion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(333, 120, 235, 43);
		getContentPane().add(panel_6);
		panel_6.setLayout(null);

		descripcionProducto = new JTextField();
		descripcionProducto.setEditable(false);
		descripcionProducto.setBounds(6, 16, 219, 20);
		panel_6.add(descripcionProducto);
		descripcionProducto.setColumns(10);

		panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "Precio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.setBounds(48, 208, 98, 43);
		getContentPane().add(panel_7);
		panel_7.setLayout(null);

		precioP = new JTextField();
		precioP.setEditable(false);
		precioP.setBounds(6, 16, 86, 20);
		panel_7.add(precioP);
		precioP.setColumns(10);

		panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(null, "Existencias", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBounds(186, 208, 98, 43);
		getContentPane().add(panel_8);
		panel_8.setLayout(null);

		existencias = new JTextField();
		existencias.setEditable(false);
		existencias.setBounds(6, 16, 86, 20);
		panel_8.add(existencias);
		existencias.setColumns(10);

		panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "Cantidad", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_9.setBounds(48, 291, 98, 43);
		getContentPane().add(panel_9);
		panel_9.setLayout(null);

		cantidadP = new JTextField();
		cantidadP.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cantidadP.setBackground(Color.white);
			}
		});
		cantidadP.setBounds(6, 16, 86, 20);
		panel_9.add(cantidadP);
		cantidadP.setColumns(10);
		cantidadP.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				int prec = 0;
				try {
					prec = Integer.parseInt(cantidadP.getText()) * precio;
					precioFinal.setText(String.valueOf(prec));
				} catch (NumberFormatException ex) {
					precioFinal.setText(String.valueOf(prec));
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				int prec = 0;
				try {
					prec = Integer.parseInt(cantidadP.getText()) * precio;
					precioFinal.setText(String.valueOf(prec));
				} catch (NumberFormatException ex) {
					precioFinal.setText(String.valueOf(prec));
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				int prec = 0;
				try {
					prec = Integer.parseInt(cantidadP.getText()) * precio;
					precioFinal.setText(String.valueOf(prec));
				} catch (NumberFormatException ex) {
					precioFinal.setText(String.valueOf(prec));
				}
			}
		});

		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new TitledBorder(null, "Tipo de Pago", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_10.setBounds(186, 293, 98, 43);
		getContentPane().add(panel_10);
		panel_10.setLayout(null);

		comboBox = new JComboBox<>();
		comboBox.setBounds(6, 16, 82, 20);
		comboBox.addItem("Contado");
		comboBox.addItem("Plazos");
		panel_10.add(comboBox);

		panel_11 = new JPanel();
		panel_11.setBorder(new TitledBorder(null, "Precio Total", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_11.setBounds(333, 291, 98, 43);
		getContentPane().add(panel_11);
		panel_11.setLayout(null);

		precioFinal = new JTextField();
		precioFinal.setEditable(false);
		precioFinal.setBounds(6, 16, 86, 20);
		panel_11.add(precioFinal);
		precioFinal.setColumns(10);

		panel_12 = new JPanel();
		panel_12.setBorder(new TitledBorder(null, "Monto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_12.setBounds(48, 370, 98, 43);
		getContentPane().add(panel_12);
		panel_12.setLayout(null);

		monto = new JTextField();
		monto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				monto.setBackground(Color.white);
			}
		});
		monto.setBounds(6, 16, 86, 20);
		panel_12.add(monto);
		monto.setColumns(10);

		btnVenta = new JButton("Venta");
		btnVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (validar()) {

					Date fecha = getFechaChoser() != null ? getFechaChoser() : getFechaActual();
					SimpleDateFormat s = new SimpleDateFormat("yyyy/MM/dd");
					String consulta = "INSERT INTO `ventas`"
							+ "(`id_venta`, `id_producto`, `fecha venta`, `estado`,  `total`, `pagado`, `id_cliente`, `balance`,`vendedor`) "
							+ "VALUES (?,?,?,?,?,?,?,?,?)";

					try {
						PreparedStatement ps = conexion.getPreparedStatement(consulta);
						ps.setInt(1, idVentaNum);
						ps.setInt(2, claveProducto);
						ps.setString(3, s.format(fecha));
						ps.setString(4, comboBox.getSelectedIndex() == 0 ? "Pagado" : "Parcial");
						ps.setString(5, precioFinal.getText());
						ps.setString(6, monto.getText());
						ps.setInt(7, idCliente);
						ps.setInt(8, Integer.parseInt(precioFinal.getText()) - Integer.parseInt(monto.getText()));
						ps.setString(9, nombreUsuario);
						ps.executeUpdate();
						escribir("Producto vendido");
						int n = Integer.parseInt(existencias.getText()) - Integer.parseInt(cantidadP.getText());
						consulta = "UPDATE `productos` SET `existencias` = '" + n
								+ "' WHERE `productos`.`id_producto` = " + claveProducto + "";

						ps = conexion.getPreparedStatement(consulta);
						ps.executeUpdate();
					} catch (SQLException e) {
						System.err.println("Error al vender: " + e.getMessage());
					}

				}
				dispose();
			}
		});
		btnVenta.setBounds(333, 384, 176, 43);
		getContentPane().add(btnVenta);

		if (clienteRnd) {
			comboBox.setEnabled(false);
		}

		asignaciones();
	}

	public void asignaciones() {
		String consulta = "Select nombre,precio,descripcion,existencias from productos WHERE id_producto = "
				+ claveProducto + "";
		try {
			ResultSet rs = (ResultSet) conexion.Consulta(consulta);
			rs.next();
			nombreProducto.setText(rs.getString(1));
			precioP.setText(rs.getString(2));
			descripcionProducto.setText(rs.getString(3));
			existencias.setText(rs.getString(4));
			this.precio = rs.getInt(2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		idVenta.setText(String.valueOf(idVentaNum));
		claveProducto_1.setText(String.valueOf(claveProducto));
		claveUser.setText(String.valueOf(idCliente));
		vendedor.setText(nombreUsuario);

	}

	private Date getFechaChoser() {
		return dateChooser.getDate();
	}

	private Date getFechaActual() {
		return new Date();
	}

	public boolean validar() {

		if (cantidadP.getText().isEmpty()) {
			cantidadP.setBackground(Color.RED);
			escribir("No dejes el Campo Vacio");
			return false;
		}
		if (!esNumero(cantidadP.getText())) {
			cantidadP.setBackground(Color.RED);
			escribir("ingresa una cantidad valida");
			return false;
		}
		int canti = Integer.parseInt(cantidadP.getText());
		int existe = Integer.parseInt(existencias.getText());
		if (canti > existe) {
			cantidadP.setBackground(Color.RED);
			escribir("Solo puedes Vender Maximo " + existe);
			return false;
		}
		if (!chckbxNewCheckBox.isSelected() && getFechaChoser() == null) {
			escribir("Selecciona un campo de fecha");
			return false;
		}
		if (getFechaChoser() != null && getFechaChoser().after(getFechaActual())) {
			escribir("No Puedes vender un Producto en el Futuro");
			return false;
		}
		if (monto.getText().isEmpty()) {
			monto.setBackground(Color.red);
			escribir("Rellena el campo");
			return false;
		}
		if (!esNumero(monto.getText())) {
			monto.setBackground(Color.red);
			escribir("Escribe solo numeros");
			return false;
		}
		int mont = Integer.parseInt(monto.getText());
		int tot = Integer.parseInt(precioFinal.getText());
		if (comboBox.getSelectedItem().equals("Contado") && mont < tot) {
			monto.setBackground(Color.red);
			escribir("Necesita El Total de la Cuenta");
			return false;
		}

		return true;
	}
}
