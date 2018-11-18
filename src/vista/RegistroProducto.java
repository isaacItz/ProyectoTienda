package vista;

import static modelo.Utileria.continuar;
import static modelo.Utileria.esNumero;
import static modelo.Utileria.escribir;

import java.awt.Color;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.toedter.calendar.JDateChooser;

import modelo.Conexion;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class RegistroProducto extends JDialog {

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

	public RegistroProducto(JFrame fa, Conexion conexion) {
		super(fa, true);
		this.contadorProductos = conexion.getIDProductos();
		this.conexion = conexion;
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Isaac\\Desktop\\fondo.jpg"));
		setResizable(false);
		setSize(580, 500);
		setTitle("Registro de Productos CASA RAIZ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Clave Prodcto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(45, 50, 191, 43);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		idProducto = new JTextField();
		idProducto.setBounds(6, 16, 179, 20);
		panel_1.add(idProducto);
		idProducto.setEnabled(false);
		idProducto.setText(String.valueOf(contadorProductos));
		idProducto.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Nombre", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(298, 50, 191, 43);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		textNombre = new JTextField();
		textNombre.setBounds(6, 16, 179, 20);
		panel_2.add(textNombre);
		textNombre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textNombre.setBackground(Color.WHITE);
			}
		});
		textNombre.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Marca", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(45, 128, 191, 43);
		getContentPane().add(panel);
		panel.setLayout(null);

		textMarca = new JTextField();
		textMarca.setBounds(6, 16, 179, 20);
		panel.add(textMarca);
		textMarca.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textMarca.setBackground(Color.WHITE);
			}
		});
		textMarca.setColumns(10);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Color", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(298, 128, 191, 43);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);

		textColor = new JTextField();
		textColor.setBounds(6, 16, 179, 20);
		panel_3.add(textColor);
		textColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textColor.setBackground(Color.WHITE);
			}
		});
		textColor.setColumns(10);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Material", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(45, 208, 191, 43);
		getContentPane().add(panel_4);
		panel_4.setLayout(null);

		textMaterial = new JTextField();
		textMaterial.setBounds(6, 16, 179, 20);
		panel_4.add(textMaterial);
		textMaterial.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textMaterial.setBackground(Color.WHITE);
			}
		});
		textMaterial.setColumns(10);

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(
				new TitledBorder(null, "Fecha de Ingreso", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(45, 283, 284, 43);
		getContentPane().add(panel_6);
		panel_6.setLayout(null);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(6, 16, 178, 20);
		panel_6.add(dateChooser);
		dateChooser.getCalendarButton().setToolTipText("seleccionar fecha");

		chckbxFechaAcual_1 = new JCheckBox("Fecha Acual");
		chckbxFechaAcual_1.setBounds(190, 16, 83, 23);
		panel_6.add(chckbxFechaAcual_1);
		chckbxFechaAcual_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateChooser.setEnabled(!chckbxFechaAcual_1.isSelected());
			}
		});

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Precio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(298, 208, 191, 43);
		getContentPane().add(panel_5);
		panel_5.setLayout(null);

		textPrecio = new JTextField();
		textPrecio.setBounds(6, 16, 179, 20);
		panel_5.add(textPrecio);
		textPrecio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textPrecio.setBackground(Color.WHITE);
			}
		});
		textPrecio.setColumns(10);

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "Cantidad", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.setBounds(354, 283, 135, 43);
		getContentPane().add(panel_7);
		panel_7.setLayout(null);

		textCantidad = new JTextField();
		textCantidad.setBounds(6, 16, 121, 20);
		panel_7.add(textCantidad);
		textCantidad.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textCantidad.setBackground(Color.WHITE);
			}
		});
		textCantidad.setColumns(10);

		JLabel lblCaracteristicasEspecificas = new JLabel("Caracteristicas Especificas");
		lblCaracteristicasEspecificas.setBounds(51, 361, 126, 14);
		getContentPane().add(lblCaracteristicasEspecificas);

		TextArea textAreaCaracteristicas = new TextArea();
		textAreaCaracteristicas.setBounds(51, 382, 278, 80);
		textAreaCaracteristicas.setRows(10);
		getContentPane().add(textAreaCaracteristicas);

		JButton btnRegisroProducto = new JButton("Registrar");
		btnRegisroProducto.setBounds(422, 400, 90, 23);
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
		getContentPane().add(btnRegisroProducto);
	}

	private boolean registroProducto(TextArea t) {
		Date curDate = new Date();

		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		String fechaa = chckbxFechaAcual_1.isSelected() ? format.format(curDate) : format.format(dateChooser.getDate());

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
