package vista;

import static modelo.Utileria.escribir;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.toedter.calendar.JDateChooser;

import modelo.Conexion;

public class VentanaBusquedaInventario extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private Conexion conexion;
	private JComboBox<String> comboBox;
	private JScrollPane scrollPane;
	private Object[][] datos;
	private Object[] columnasTabla = null;
	private JDateChooser dateChooser;

	public VentanaBusquedaInventario(JFrame j, Conexion conexion) {

		super(j, true);

		this.conexion = conexion;
		setTitle("CASA RAIZ");
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setSize(700, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(61, 166, 558, 248);
		contentPane.add(scrollPane);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Dato a Buscar", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(322, 60, 205, 87);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Criterio de Busqueda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(82, 61, 205, 53);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		textField = new JTextField();
		textField.setBounds(10, 22, 189, 20);
		panel.add(textField);
		textField.setToolTipText("dato a buscar");
		textField.setColumns(10);

		textField.setEnabled(false);

		dateChooser = new JDateChooser();
		dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				if (comboBox != null && comboBox.getSelectedIndex() != 0)
					llenarTabla();
			}
		});
		dateChooser.setBounds(10, 53, 189, 20);
		panel.add(dateChooser);
		dateChooser.setEnabled(false);
		comboBox = new JComboBox<String>();
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (table != null)
					table.removeAll();
				if (comboBox.getSelectedIndex() > 0) {
					if (comboBox.getSelectedIndex() == 6 || comboBox.getSelectedIndex() == 7) {
						dateChooser.setEnabled(true);
						textField.setEnabled(false);
					} else {
						textField.setEnabled(true);
						dateChooser.setEnabled(false);
					}

				} else
					textField.setEnabled(false);
			}
		});
		comboBox.setBounds(10, 22, 185, 20);
		panel_1.add(comboBox);

		comboBox.addItem("Selecciona un criterio");
		try {
			columnasTabla = conexion.getCamposTabla("productos");
			for (Object string : columnasTabla)
				comboBox.addItem(string.toString());
		} catch (SQLException e) {
			escribir("no se encontro la tabla");
			e.printStackTrace();
		}

		comboBox.setToolTipText("criterio de busqueda");

		textField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				llenarTabla();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				llenarTabla();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}
		});

		setVisible(true);
	}

	private void llenarTabla() {
		ResultSet rs;
		try {

			String operador = " LIKE '" + textField.getText().concat("%");
			if (dateChooser.getDate() != null && dateChooser.isEnabled())
				operador = " = '" + new SimpleDateFormat("yyyy/MM/dd").format(dateChooser.getDate()).toString();

			rs = (ResultSet) conexion.Consulta("Select * from productos where `"
					+ comboBox.getSelectedItem().toString().concat("` ") + operador + "'");
			datos = conexion.getDatosTabla(rs);
			table = new JTable(datos, columnasTabla);
			table.setToolTipText("tabla");
			// table.setUpdateSelectionOnSort(false);
			scrollPane.setViewportView(table);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}
}
