package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.util.EscapeTokenizer;
import com.mysql.cj.util.Util;

import modelo.Conexion;
import modelo.Utileria;

public class VistaPrincipalAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable tabla;

	public VistaPrincipalAdmin(Conexion conexion) {
		setForeground(Color.LIGHT_GRAY);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Isaac\\Desktop\\fondo.jpg"));
		this.setTitle("Casa Raiz Panel ADMINISTRADOR");
		this.setSize(600, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnGestionDeUsuarios = new JMenu("Gestion de Usuarios");
		menuBar.add(mnGestionDeUsuarios);

		JMenuItem mntmAltaUsuarios = new JMenuItem("Alta Usuarios");
		mntmAltaUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre, usuario, contraseña;
				do {
					usuario = JOptionPane.showInputDialog(null, "Ingresa su Nombre De Usuario (Debe ser unico)",
							"Nuevo Usuario", JOptionPane.QUESTION_MESSAGE);
					if (usuario != null) {
						if (Utileria.valido(usuario)) {
							if (!conexion.existeUsuario(usuario)) {
								nombre = JOptionPane.showInputDialog(null,
										"Ingresa el Nombre de la Persona a Registrar", "Nuevo Usuario",
										JOptionPane.QUESTION_MESSAGE);
								JPasswordField passwordField = new JPasswordField();
								passwordField.setEchoChar('☺');
								passwordField.setColumns(20);

								JOptionPane.showConfirmDialog(null, passwordField, "Contraseña",
										JOptionPane.OK_CANCEL_OPTION);
								contraseña = String.copyValueOf(passwordField.getPassword());
								int edad = Utileria.leerInt("Ingresa la edad de la persona a Registar");
								String[] tipos = { "Empleado", "Administrador" };
								String tipo = (String) JOptionPane.showInputDialog(null, "tipo de Usuario?", "Tipo",
										JOptionPane.QUESTION_MESSAGE, null, tipos, tipos[0]);
								System.out.println(tipo);

								conexion.altaUser(usuario, nombre, contraseña, edad, tipo);

							} else
								Utileria.escribir("El usuario Ya Existe");

						} else
							Utileria.escribir("invalido no deje el campo vacio ");
					} else
						break;
				} while (Utileria.continuar("Desea Registrar Otro Usuario"));

			}
		});
		mnGestionDeUsuarios.add(mntmAltaUsuarios);

		JMenuItem mntmBajaUsuarios = new JMenuItem("Baja Usuarios");
		mnGestionDeUsuarios.add(mntmBajaUsuarios);
		mntmBajaUsuarios.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String usuario;
				do {
					usuario = JOptionPane.showInputDialog(null, "Ingresa su Nombre De Usuario (Debe ser unico)",
							"Eliminar Usuario", JOptionPane.QUESTION_MESSAGE);
					if (usuario != null) {
						if (Utileria.valido(usuario)) {
							if (conexion.existeUsuario(usuario)) {
								conexion.bajauser(usuario);
								Utileria.escribir("Eliminado con exito");
							} else
								Utileria.escribir("El usuario No Existe");

						} else
							Utileria.escribir("invalido no deje el campo vacio ");
					} else
						break;
				} while (Utileria.continuar("Desea Eliminar Otro Usuario"));

			}
		});

		JSeparator separator = new JSeparator();
		mnGestionDeUsuarios.add(separator);

		JMenuItem mntmConsultar = new JMenuItem("Consultar");
		mntmConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String usuario;
				do {
					usuario = JOptionPane.showInputDialog(null, "Ingresa su Nombre De Usuario que Desea Consultar",
							"Consultar Usuario", JOptionPane.QUESTION_MESSAGE);
					if (usuario != null) {
						if (Utileria.valido(usuario)) {
							if (conexion.existeUsuario(usuario)) {
								Utileria.escribir(conexion.consultarUsuario(usuario));

							} else
								Utileria.escribir("El usuario No Existe");

						} else
							Utileria.escribir("invalido no deje el campo vacio ");
					} else
						break;
				} while (Utileria.continuar("Desea Consultar Otro Usuario"));
			}
		});
		mnGestionDeUsuarios.add(mntmConsultar);

		JMenuItem mntmModificar = new JMenuItem("Modificar");
		mntmModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String usuario;
				do {
					usuario = JOptionPane.showInputDialog(null, "Ingresa su Nombre De Usuario que Desea Modificar",
							"Modificar Usuario", JOptionPane.QUESTION_MESSAGE);
					if (usuario != null) {
						if (Utileria.valido(usuario)) {
							if (conexion.existeUsuario(usuario)) {
								Utileria.escribir(conexion.consultarUsuario(usuario));

							} else
								Utileria.escribir("El usuario No Existe");

						} else
							Utileria.escribir("invalido no deje el campo vacio ");
					} else
						break;
				} while (Utileria.continuar("Desea Consultar Otro Usuario"));

			}
		});
		mnGestionDeUsuarios.add(mntmModificar);

		JMenuItem mntmListar = new JMenuItem("Listar");
		mntmListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TablaUsuarios tabla = new TablaUsuarios();
				ResultSet rs;
				try {

					rs = (ResultSet) conexion.Consulta("select *From Usuarios");
					tabla.agregarDatos(rs);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				// getContentPane().add(tabla.devolverTablaScroll(), BorderLayout.CENTER);
				// pack();
				// setExtendedState(JFrame.MAXIMIZED_BOTH);

				JOptionPane.showMessageDialog(null, tabla.devolverTablaScroll(), getTitle(), 1);

			}
		});

		mnGestionDeUsuarios.add(mntmListar);

		JMenu mnInventario = new JMenu("Inventarios");
		menuBar.add(mnInventario);

		JMenuItem mntmIngresarArticulos = new JMenuItem("Ingresar Articulos");
		mntmIngresarArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int claveP = Utileria.leerInt("Digite la Clave del Producto");
				if (conexion.existe("productos", "id_producto", String.valueOf(claveP))) {
					int opcion = JOptionPane.showConfirmDialog(null,
							"El Producto Ya existe\nDesea Agregar mas al Inventario?", "Titulo",
							JOptionPane.YES_NO_OPTION);
					switch (opcion) {
					case 0:
						int cant = Utileria.leerInt("Ingresa la Cantidad a registrar ");
						conexion.agregarProductoExistente(cant, String.valueOf(claveP));
						Utileria.escribir("Exito!!");
						break;
					case 1:
						Utileria.escribir("cancelado");
						break;

					}
				} else
					new RegistroProducto(re(), conexion, claveP).setVisible(true);

			}
		});
		mnInventario.add(mntmIngresarArticulos);

		JMenuItem mntmEliminarArticulos = new JMenuItem("Eliminar Articulos");
		mnInventario.add(mntmEliminarArticulos);

		JMenuItem mntmListarArticulos = new JMenuItem("Listar Articulos");
		mntmListarArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// try {
				//
				// Object[] cabezera = conexion.getCamposTabla("productos");
				// Object[][] datos = conexion.getDatosTabla((ResultSet)
				// conexion.Consulta("Select * from productos"));
				// tabla = new JTable(datos, cabezera);
				// // JPanel panel = new JPanel()
				// // tabla.setBorder(new TitledBorder(null, "Tabla de Articulos",
				// // TitledBorder.LEADING, TitledBorder.TOP,
				// // null, null));
				// getContentPane().setLayout(new BorderLayout());
				// getContentPane().add(new JScrollPane(tabla), BorderLayout.CENTER);
				// pack();
				// setExtendedState(JFrame.MAXIMIZED_BOTH);
				//
				// } catch (SQLException e) {
				// e.printStackTrace();
				// }

				try {

					Object[] cabezera = conexion.getCamposTabla("productos");
					DefaultTableModel modelo = new DefaultTableModel();
					Object[][] datos = conexion.getDatosTabla((ResultSet) conexion.Consulta("Select * from productos"));
					tabla = new JTable();
					modelo.setColumnIdentifiers(cabezera);
					for (int i = 0; i < datos.length; i++) {
						modelo.addRow(datos[i]);
					}
					tabla.setModel(modelo);
					// JPanel panel = new JPanel()
					// tabla.setBorder(new TitledBorder(null, "Tabla de Articulos",
					// TitledBorder.LEADING, TitledBorder.TOP,
					// null, null));
					getContentPane().setLayout(new BorderLayout());
					getContentPane().add(new JScrollPane(tabla), BorderLayout.CENTER);
					System.out.println("constructron");
					modelo.fireTableDataChanged();

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		});
		mnInventario.add(mntmListarArticulos);

		JMenuItem mntmBuscar = new JMenuItem("Buscar");
		mntmBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VentanaBusquedaInventario(re(), conexion);

			}
		});
		mnInventario.add(mntmBuscar);

		JMenu mnVenta = new JMenu("Venta");
		menuBar.add(mnVenta);

		JMenuItem mntmRealizarVenta = new JMenuItem("Realizar Venta");
		mntmRealizarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int claveP = Utileria.leerInt("Ingrese la Clave del Producto a Vender");
				if (conexion.existe("productos", "id_producto", String.valueOf(claveP))) {

					Object[] optionsUser = new Object[] { "Ya Existe el Cliente", "Crear Nuevo Cliente",
							"No Deseo Registar informacion" };
					Object opcionU = JOptionPane.showInputDialog(null, "Desea Registrar la Informacion del Cliente",
							"Elegir", JOptionPane.QUESTION_MESSAGE, null, optionsUser, optionsUser[0]);

					switch (opcionU.toString()) {
					case "Ya Existe el Cliente":
						int clave = Utileria.leerInt("Digita la Clave del Cliente");
						String clavee = conexion.getCampo("cliente", "id_cliente", "id_cliente", String.valueOf(clave));
						if (clavee != null) {
							new VentanaVenta(conexion, clavee, claveP).setVisible(true);
						} else {
							int op = JOptionPane.showConfirmDialog(null, "El Cliente No Existe.\n ¿Desea Buscarlo?");
							if (op == 0)
								Utileria.escribir("vusqueda");
						}

						break;
					case "Crear Nuevo Cliente":
						new RegistroCliente(conexion).setVisible(true);
						break;

					case "No Deseo Registar informacion":

						break;
					}

				} else {
					int op = JOptionPane.showConfirmDialog(null, "El Producto No Existe. \n¿Desea Buscarlo?",
							"No entontrado", JOptionPane.YES_NO_OPTION);

					if (op == 0) {
						new VentanaBusquedaInventario(re(), conexion);
					}
				}

			}
		});
		mnVenta.add(mntmRealizarVenta);

		JMenu mnGestionDeclientes = new JMenu("Gestion deClientes");
		menuBar.add(mnGestionDeclientes);

		JMenuItem mntmRegistrarCliente = new JMenuItem("Registrar Cliente");
		mntmRegistrarCliente.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				new RegistroCliente(conexion).setVisible(true);

			}
		});
		mnGestionDeclientes.add(mntmRegistrarCliente);

		JMenuItem mntmBuscarCliente = new JMenuItem("Buscar Cliente");
		mnGestionDeclientes.add(mntmBuscarCliente);

		JMenuItem mntmBorrarCliente = new JMenuItem("Borrar Cliente");
		mnGestionDeclientes.add(mntmBorrarCliente);

		JMenuItem mntmModificarCliente = new JMenuItem("Modificar Cliente");
		mnGestionDeclientes.add(mntmModificarCliente);

		JLabel lblNewLabel = new JLabel("Tabla de Productos");
		lblNewLabel.setFont(new Font("Vivaldi", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);

		getContentPane().add(lblNewLabel, BorderLayout.NORTH);

		// tabla = new JTable();
		// getContentPane().add(tabla);

		this.setVisible(true);
	}

	public JFrame re() {
		return this;
	}

}
