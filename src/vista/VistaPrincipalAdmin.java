package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import modelo.Conexion;
import modelo.Utileria;

public class VistaPrincipalAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	JDialog busqueda = null;

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
				new RegistroProducto(conexion).setVisible(true);
			}
		});
		mnInventario.add(mntmIngresarArticulos);

		JMenuItem mntmEliminarArticulos = new JMenuItem("Eliminar Articulos");
		mnInventario.add(mntmEliminarArticulos);

		JMenuItem mntmListarArticulos = new JMenuItem("Listar Articulos");
		mntmListarArticulos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					Object[] cabezera = conexion.getCamposTabla("productos");
					Object[][] datos = conexion.getDatosTabla((ResultSet) conexion.Consulta("Select * from productos"));
					JTable tabla = new JTable(datos, cabezera);
					tabla.setBorder(new TitledBorder(null, "Tabla de Articulos", TitledBorder.LEADING, TitledBorder.TOP,
							null, null));
					getContentPane().setLayout(new BorderLayout());
					getContentPane().add(new JScrollPane(tabla), BorderLayout.CENTER);

				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		});
		mnInventario.add(mntmListarArticulos);

		JMenuItem mntmBuscar = new JMenuItem("Buscar");
		mntmBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				busqueda = new VentanaBusquedaInventario(re(), conexion);

			}
		});
		mnInventario.add(mntmBuscar);

		JMenu mnVenta = new JMenu("Venta");
		menuBar.add(mnVenta);
		this.setVisible(true);
	}

	public JFrame re() {
		return this;
	}

}
