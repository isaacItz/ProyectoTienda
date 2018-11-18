package vista;

import static modelo.Utileria.continuar;
import static modelo.Utileria.esNumero;
import static modelo.Utileria.escribir;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.print.attribute.TextSyntax;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import modelo.Conexion;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegistroProducto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField idProducto;
	private JTextField textNombre;
	private JTextField textMarca;
	private JTextField textColor;
	private JTextField textMaterial;
	private JTextField textPrecio;
	private JTextField textCantidad;
	private int contadorProductos;
	private Conexion conexion;
	private JCheckBox chckbxFechaAcual_1;
	private JDateChooser dateChooser;

	public RegistroProducto(Conexion conexion) {
		this.contadorProductos = conexion.getIDProductos();
		this.conexion = conexion;
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Isaac\\Desktop\\fondo.jpg"));
		setResizable(false);
		setSize(600, 800);
		setTitle("Registro de Productos CASA RAIZ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 1, 2, 3, 91, 4, 5, 8, 10, 11, 12, 17 };
		gridBagLayout.rowHeights = new int[] { 0, 1, 2, 0, 3, 34, 0, 6, 7, 0, 8, 9, 0, 10, 11, 0, 12, 13, 0, 14, 15, 0,
				16, 17, 0, 18, 19, 0, 149, 21, 21 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0 };
		gridBagLayout.rowWeights = new double[] { Double.MIN_VALUE, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		getContentPane().setLayout(gridBagLayout);

		JLabel lblIdProdcto = new JLabel("ID Prodcto");
		GridBagConstraints gbc_lblIdProdcto = new GridBagConstraints();
		gbc_lblIdProdcto.anchor = GridBagConstraints.WEST;
		gbc_lblIdProdcto.insets = new Insets(0, 0, 5, 5);
		gbc_lblIdProdcto.gridx = 3;
		gbc_lblIdProdcto.gridy = 3;
		getContentPane().add(lblIdProdcto, gbc_lblIdProdcto);

		idProducto = new JTextField();
		idProducto.setEnabled(false);
		idProducto.setText(String.valueOf(contadorProductos));
		GridBagConstraints gbc_idProducto = new GridBagConstraints();
		gbc_idProducto.insets = new Insets(0, 0, 5, 5);
		gbc_idProducto.fill = GridBagConstraints.HORIZONTAL;
		gbc_idProducto.gridx = 3;
		gbc_idProducto.gridy = 5;
		getContentPane().add(idProducto, gbc_idProducto);
		idProducto.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre");
		GridBagConstraints gbc_lblNombre = new GridBagConstraints();
		gbc_lblNombre.anchor = GridBagConstraints.WEST;
		gbc_lblNombre.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombre.gridx = 3;
		gbc_lblNombre.gridy = 6;
		getContentPane().add(lblNombre, gbc_lblNombre);

		textNombre = new JTextField();
		textNombre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textNombre.setBackground(Color.WHITE);
			}
		});

		GridBagConstraints gbc_textNombre = new GridBagConstraints();
		gbc_textNombre.insets = new Insets(0, 0, 5, 5);
		gbc_textNombre.fill = GridBagConstraints.HORIZONTAL;
		gbc_textNombre.gridx = 3;
		gbc_textNombre.gridy = 7;
		getContentPane().add(textNombre, gbc_textNombre);
		textNombre.setColumns(10);

		JLabel lblNewLabel = new JLabel("Marca");
		lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 9;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);

		textMarca = new JTextField();
		textMarca.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textMarca.setBackground(Color.WHITE);
			}
		});
		GridBagConstraints gbc_textMarca = new GridBagConstraints();
		gbc_textMarca.insets = new Insets(0, 0, 5, 5);
		gbc_textMarca.fill = GridBagConstraints.HORIZONTAL;
		gbc_textMarca.gridx = 3;
		gbc_textMarca.gridy = 10;
		getContentPane().add(textMarca, gbc_textMarca);
		textMarca.setColumns(10);

		JLabel lblColor = new JLabel("Color");
		GridBagConstraints gbc_lblColor = new GridBagConstraints();
		gbc_lblColor.anchor = GridBagConstraints.WEST;
		gbc_lblColor.insets = new Insets(0, 0, 5, 5);
		gbc_lblColor.gridx = 3;
		gbc_lblColor.gridy = 12;
		getContentPane().add(lblColor, gbc_lblColor);

		textColor = new JTextField();
		textColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textColor.setBackground(Color.WHITE);
			}
		});
		GridBagConstraints gbc_textColor = new GridBagConstraints();
		gbc_textColor.insets = new Insets(0, 0, 5, 5);
		gbc_textColor.fill = GridBagConstraints.HORIZONTAL;
		gbc_textColor.gridx = 3;
		gbc_textColor.gridy = 13;
		getContentPane().add(textColor, gbc_textColor);
		textColor.setColumns(10);

		JLabel lblMaterial = new JLabel("Material");
		GridBagConstraints gbc_lblMaterial = new GridBagConstraints();
		gbc_lblMaterial.anchor = GridBagConstraints.WEST;
		gbc_lblMaterial.insets = new Insets(0, 0, 5, 5);
		gbc_lblMaterial.gridx = 3;
		gbc_lblMaterial.gridy = 15;
		getContentPane().add(lblMaterial, gbc_lblMaterial);

		textMaterial = new JTextField();
		textMaterial.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textMaterial.setBackground(Color.WHITE);
			}
		});
		GridBagConstraints gbc_textMaterial = new GridBagConstraints();
		gbc_textMaterial.insets = new Insets(0, 0, 5, 5);
		gbc_textMaterial.fill = GridBagConstraints.HORIZONTAL;
		gbc_textMaterial.gridx = 3;
		gbc_textMaterial.gridy = 16;
		getContentPane().add(textMaterial, gbc_textMaterial);
		textMaterial.setColumns(10);

		JLabel lblFechaDeIngreso = new JLabel("Fecha de Ingreso");
		GridBagConstraints gbc_lblFechaDeIngreso = new GridBagConstraints();
		gbc_lblFechaDeIngreso.anchor = GridBagConstraints.WEST;
		gbc_lblFechaDeIngreso.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaDeIngreso.gridx = 3;
		gbc_lblFechaDeIngreso.gridy = 18;
		getContentPane().add(lblFechaDeIngreso, gbc_lblFechaDeIngreso);

		dateChooser = new JDateChooser();
		dateChooser.getCalendarButton().setToolTipText("seleccionar fecha");
		GridBagConstraints gbc_dateChooser = new GridBagConstraints();
		gbc_dateChooser.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooser.fill = GridBagConstraints.BOTH;
		gbc_dateChooser.gridx = 3;
		gbc_dateChooser.gridy = 19;
		getContentPane().add(dateChooser, gbc_dateChooser);

		GridBagConstraints gbc_chckbxFechaAcual = new GridBagConstraints();
		gbc_chckbxFechaAcual.gridheight = 2;
		gbc_chckbxFechaAcual.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxFechaAcual.gridx = 5;
		gbc_chckbxFechaAcual.gridy = 19;

		chckbxFechaAcual_1 = new JCheckBox("Fecha Acual");
		chckbxFechaAcual_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateChooser.setEnabled(!chckbxFechaAcual_1.isSelected());
			}
		});

		getContentPane().add(chckbxFechaAcual_1, gbc_chckbxFechaAcual);
		JLabel lblPrecio = new JLabel("Precio");
		GridBagConstraints gbc_lblPrecio = new GridBagConstraints();
		gbc_lblPrecio.anchor = GridBagConstraints.WEST;
		gbc_lblPrecio.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrecio.gridx = 3;
		gbc_lblPrecio.gridy = 21;
		getContentPane().add(lblPrecio, gbc_lblPrecio);

		textPrecio = new JTextField();
		textPrecio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textPrecio.setBackground(Color.WHITE);
			}
		});
		GridBagConstraints gbc_textPrecio = new GridBagConstraints();
		gbc_textPrecio.insets = new Insets(0, 0, 5, 5);
		gbc_textPrecio.fill = GridBagConstraints.HORIZONTAL;
		gbc_textPrecio.gridx = 3;
		gbc_textPrecio.gridy = 22;
		getContentPane().add(textPrecio, gbc_textPrecio);
		textPrecio.setColumns(10);

		JLabel lblCantidad = new JLabel("Cantidad");
		GridBagConstraints gbc_lblCantidad = new GridBagConstraints();
		gbc_lblCantidad.anchor = GridBagConstraints.WEST;
		gbc_lblCantidad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCantidad.gridx = 3;
		gbc_lblCantidad.gridy = 24;
		getContentPane().add(lblCantidad, gbc_lblCantidad);

		textCantidad = new JTextField();
		textCantidad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textCantidad.setBackground(Color.WHITE);
			}
		});
		GridBagConstraints gbc_textCantidad = new GridBagConstraints();
		gbc_textCantidad.insets = new Insets(0, 0, 5, 5);
		gbc_textCantidad.fill = GridBagConstraints.HORIZONTAL;
		gbc_textCantidad.gridx = 3;
		gbc_textCantidad.gridy = 25;
		getContentPane().add(textCantidad, gbc_textCantidad);
		textCantidad.setColumns(10);

		JLabel lblCaracteristicasEspecificas = new JLabel("Caracteristicas Especificas");
		GridBagConstraints gbc_lblCaracteristicasEspecificas = new GridBagConstraints();
		gbc_lblCaracteristicasEspecificas.anchor = GridBagConstraints.WEST;
		gbc_lblCaracteristicasEspecificas.insets = new Insets(0, 0, 5, 5);
		gbc_lblCaracteristicasEspecificas.gridx = 3;
		gbc_lblCaracteristicasEspecificas.gridy = 27;
		getContentPane().add(lblCaracteristicasEspecificas, gbc_lblCaracteristicasEspecificas);

		TextArea textAreaCaracteristicas = new TextArea();
		textAreaCaracteristicas.setRows(10);
		GridBagConstraints gbc_textAreaCaracteristicas = new GridBagConstraints();
		gbc_textAreaCaracteristicas.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaCaracteristicas.gridx = 3;
		gbc_textAreaCaracteristicas.gridy = 28;
		getContentPane().add(textAreaCaracteristicas, gbc_textAreaCaracteristicas);

		JButton btnRegisroProducto = new JButton("Registrar");
		btnRegisroProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (validarFormularioRegistroProducto()) {
					do {
						for (int i = 0; i < Integer.parseInt(textCantidad.getText()); i++) {
							registroProducto(textAreaCaracteristicas);
							idProducto.setText(String.valueOf(contadorProductos));
						}
						escribir("Producto registrado con exito");
						limpiarCampos(textAreaCaracteristicas);
					} while (continuar("¿Desea Agregar Mas Productos?"));
					dispose();

				}
			}
		});
		GridBagConstraints gbc_btnRegisroProducto = new GridBagConstraints();
		gbc_btnRegisroProducto.insets = new Insets(0, 0, 5, 5);
		gbc_btnRegisroProducto.gridx = 7;
		gbc_btnRegisroProducto.gridy = 29;
		getContentPane().add(btnRegisroProducto, gbc_btnRegisroProducto);
	}

	private boolean registroProducto(TextArea t) {
		Date curDate = new Date();

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		String fechaa = chckbxFechaAcual_1.isSelected() ? format.format(curDate) : format.format(dateChooser.getDate());
		escribir(fechaa);

		String consulta = "INSERT INTO `productos`(`id_producto`, `marca`, `color`, `material`, `detalles especificos`, `fecha Ingreso`, `estado`, `precio`, `nombre`,`fecha salida` ) "
				+ "VALUES (" + contadorProductos++ + ",'" + textMarca.getText() + "','" + textColor.getText() + "','"
				+ textMaterial.getText() + "','" + t.getText() + "','" + fechaa + "' ,'nuevo',"
				+ Integer.parseInt(textPrecio.getText()) + ",'" + textNombre.getText() + "',0000-00-00)";

		try {
			conexion.realizarConsulta(consulta);
			return true;
		} catch (SQLException e) {
			System.err.println("Hubo errores: \n" + e.getMessage());
			return false;
		}

	}

	public void limpiarCampos(TextArea t) {
		textMarca.setText("");
		textColor.setText("");
		textMaterial.setText("");
		t.setText("");
		textPrecio.setText("");
		textNombre.setText("");
		textCantidad.setText("");
		// fechaIngreso.setText(obtenerTimesStamp());

	}

	private boolean validarFormularioRegistroProducto() {
		boolean ok = true;

		if (textNombre.getText().isEmpty()) {
			escribir("Escriba un nombre valido");
			textNombre.setBackground(new Color(255, 87, 51));
			return false;
		}
		if (textMarca.getText().isEmpty()) {
			escribir("escribe una marca valida");
			textMarca.setBackground(Color.RED);
			return false;
		}
		if (textColor.getText().isEmpty()) {
			escribir("escriba un color valido");
			textColor.setBackground(Color.RED);
			return false;
		}
		if (textMaterial.getText().isEmpty()) {
			escribir("escribe un material valido");
			textMaterial.setBackground(Color.RED);
			return false;
		}
		if (!chckbxFechaAcual_1.isSelected() && dateChooser.getDate() == null) {
			escribir("selecciona una fecha");
			return false;
		}
		if (textPrecio.getText().isEmpty() || !esNumero(textPrecio.getText())) {
			escribir("ingresa un precio valido");
			textPrecio.setBackground(Color.RED);
			return false;
		}
		if (textCantidad.getText().isEmpty() || !esNumero(textCantidad.getText())) {
			escribir("ingrese una cantidad valida");
			textCantidad.setBackground(Color.RED);
			return false;
		}

		return ok;
	}

}
