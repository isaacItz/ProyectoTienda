package vista;

import javax.swing.JDialog;

import modelo.Conexion;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import com.toedter.calendar.JDateChooser;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class VentanaVenta extends JDialog {

	private static final long serialVersionUID = 1L;
	private Conexion conexion;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPanel panel_3;
	private JTextField textField_4;
	private JPanel panel_5;
	private JTextField textField_5;
	private JPanel panel_6;
	private JTextField textField_6;
	private JPanel panel_7;
	private JTextField textField_7;
	private JPanel panel_8;
	private JTextField textField_8;
	private JPanel panel_9;
	private JTextField textField_9;
	private JPanel panel_11;
	private JTextField textField_10;
	private JPanel panel_12;
	private JButton btnVenta;

	public VentanaVenta(Conexion conexion, String clave, int id) {

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

		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(6, 16, 86, 20);
		panel.add(textField);
		textField.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Clave Usuario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(333, 37, 98, 43);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setBounds(6, 16, 86, 20);
		panel_1.add(textField_1);
		textField_1.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "ID Venta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.setBounds(48, 37, 98, 43);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(6, 16, 86, 20);
		panel_2.add(textField_2);
		textField_2.setColumns(10);

		panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Vendedor", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(470, 37, 98, 43);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);

		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(6, 16, 86, 20);
		panel_3.add(textField_3);
		textField_3.setColumns(10);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Fecha de Venta", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setBounds(333, 208, 235, 43);
		getContentPane().add(panel_4);
		panel_4.setLayout(null);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(6, 16, 126, 20);
		panel_4.add(dateChooser);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Fecha Actual");
		chckbxNewCheckBox.setBounds(138, 16, 91, 23);
		panel_4.add(chckbxNewCheckBox);

		panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(null, "Producto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(48, 120, 236, 43);
		getContentPane().add(panel_5);
		panel_5.setLayout(null);

		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setBounds(6, 16, 220, 20);
		panel_5.add(textField_4);
		textField_4.setColumns(10);

		panel_6 = new JPanel();
		panel_6.setBorder(new TitledBorder(null, "Descripcion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_6.setBounds(333, 120, 235, 43);
		getContentPane().add(panel_6);
		panel_6.setLayout(null);

		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setBounds(6, 16, 219, 20);
		panel_6.add(textField_5);
		textField_5.setColumns(10);

		panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "Precio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_7.setBounds(48, 208, 98, 43);
		getContentPane().add(panel_7);
		panel_7.setLayout(null);

		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setBounds(6, 16, 86, 20);
		panel_7.add(textField_6);
		textField_6.setColumns(10);

		panel_8 = new JPanel();
		panel_8.setBorder(new TitledBorder(null, "Existencias", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_8.setBounds(186, 208, 98, 43);
		getContentPane().add(panel_8);
		panel_8.setLayout(null);

		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setBounds(6, 16, 86, 20);
		panel_8.add(textField_7);
		textField_7.setColumns(10);

		panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "Cantidad", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_9.setBounds(48, 291, 98, 43);
		getContentPane().add(panel_9);
		panel_9.setLayout(null);

		textField_8 = new JTextField();
		textField_8.setBounds(6, 16, 86, 20);
		panel_9.add(textField_8);
		textField_8.setColumns(10);

		JPanel panel_10 = new JPanel();
		panel_10.setBorder(new TitledBorder(null, "Tipo de Pago", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_10.setBounds(186, 293, 98, 43);
		getContentPane().add(panel_10);
		panel_10.setLayout(null);

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBounds(6, 16, 82, 20);
		comboBox.addItem("Contado");
		comboBox.addItem("Plazos");
		panel_10.add(comboBox);
		
		panel_11 = new JPanel();
		panel_11.setBorder(new TitledBorder(null, "Precio Total", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_11.setBounds(333, 291, 98, 43);
		getContentPane().add(panel_11);
		panel_11.setLayout(null);
		
		textField_9 = new JTextField();
		textField_9.setEditable(false);
		textField_9.setBounds(6, 16, 86, 20);
		panel_11.add(textField_9);
		textField_9.setColumns(10);
		
		panel_12 = new JPanel();
		panel_12.setBorder(new TitledBorder(null, "Monto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_12.setBounds(48, 370, 98, 43);
		getContentPane().add(panel_12);
		panel_12.setLayout(null);
		
		textField_10 = new JTextField();
		textField_10.setBounds(6, 16, 86, 20);
		panel_12.add(textField_10);
		textField_10.setColumns(10);
		
		btnVenta = new JButton("Venta");
		btnVenta.setBounds(333, 384, 176, 43);
		getContentPane().add(btnVenta);

	}
}
