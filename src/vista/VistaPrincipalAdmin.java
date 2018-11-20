package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import modelo.Conexion;
import modelo.Utileria;

public class VistaPrincipalAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private Conexion conexion;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;

	public VistaPrincipalAdmin(Conexion conexion, String nombreUser) {

		this.conexion = conexion;
		setForeground(Color.LIGHT_GRAY);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Isaac\\Desktop\\fondo.jpg"));
		this.setTitle("Casa Raiz Panel ADMINISTRADOR");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 700);
		// this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setSize(getSize());

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
				llenarTabla("Usuarios");
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

				llenarTabla("productos");
			}
		});
		mnInventario.add(mntmListarArticulos);

		JMenuItem mntmBuscar = new JMenuItem("Buscar");
		mntmBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VentanaBusquedaGeneral(conexion, "productos");

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
					if (conexion.hayExistencia(claveP)) {
						Object[] optionsUser = new Object[] { "Ya Existe el Cliente", "Crear Nuevo Cliente",
								"No Deseo Registar informacion" };
						Object opcionU = JOptionPane.showInputDialog(null, "Desea Registrar la Informacion del Cliente",
								"Elegir", JOptionPane.QUESTION_MESSAGE, null, optionsUser, optionsUser[0]);

						switch (opcionU.toString()) {
						case "Ya Existe el Cliente":
							int clave = Utileria.leerInt("Digita la Clave del Cliente");
							String clavee = conexion.getCampo("cliente", "id_cliente", "id_cliente",
									String.valueOf(clave));
							if (clavee != null) {
								new VentanaVenta(conexion, claveP, clave, nombreUser, false).setVisible(true);
							} else {
								int op = JOptionPane.showConfirmDialog(null,
										"El Cliente No Existe.\n ¿Desea Buscarlo?");
								if (op == 0)
									Utileria.escribir("Busqueda");
							}

							break;
						case "Crear Nuevo Cliente":
							new RegistroCliente(conexion).setVisible(true);
							break;

						case "No Deseo Registar informacion":
							new VentanaVenta(conexion, claveP, conexion.generarClienteRnd(), nombreUser, true)
									.setVisible(true);
							break;
						}
					} else
						Utileria.escribir("No hay Existencias de ese Producto");

				} else {
					int op = JOptionPane.showConfirmDialog(null, "El Producto No Existe. \n¿Desea Buscarlo?",
							"No entontrado", JOptionPane.YES_NO_OPTION);

					if (op == 0) {
						new VentanaBusquedaInventario(conexion);
					}
				}

			}
		});
		mnVenta.add(mntmRealizarVenta);

		JMenuItem mntmListarVentas = new JMenuItem("Listar Ventas");
		mntmListarVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				llenarTabla("ventas");
			}
		});

		JMenuItem mntmBuscarVenta = new JMenuItem("Buscar Venta");
		mntmBuscarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VentanaBusquedaGeneral(conexion, "ventas");
			}
		});
		mnVenta.add(mntmBuscarVenta);
		mnVenta.add(mntmListarVentas);

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
		mntmBuscarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new VentanaBusquedaGeneral(conexion, "cliente");
			}
		});
		mnGestionDeclientes.add(mntmBuscarCliente);

		JMenuItem mntmBorrarCliente = new JMenuItem("Borrar Cliente");
		mnGestionDeclientes.add(mntmBorrarCliente);

		JMenuItem mntmModificarCliente = new JMenuItem("Modificar Cliente");
		mnGestionDeclientes.add(mntmModificarCliente);

		JMenuItem mntmListarClientes = new JMenuItem("Listar Clientes");
		mntmListarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				llenarTabla("cliente");
			}
		});
		mnGestionDeclientes.add(mntmListarClientes);
		getContentPane().setLayout(null);

		lblNewLabel = new JLabel("Casa Raiz");
		lblNewLabel.setBounds(0, 0, 984, 25);
		lblNewLabel.setFont(new Font("Vivaldi", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);

		getContentPane().add(lblNewLabel);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 30, 974, 609);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		// tabla = new JTable();
		// getContentPane().add(tabla);

		this.setVisible(true);
	}

	public void llenarTabla(String tabla) {
		try {
			lblNewLabel.setText("Tabla de " + tabla);

			Object[] cabezera = conexion.getCamposTabla(tabla);
			Object[][] datos = conexion.getDatosTabla((ResultSet) conexion.Consulta("Select * from " + tabla));
			table = new JTable(datos, cabezera);
			scrollPane.setViewportView(table);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public JFrame re() {
		return this;
	}
}
