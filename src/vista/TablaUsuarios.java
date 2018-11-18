package vista;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TablaUsuarios extends JTable {

	private static final long serialVersionUID = 1L;
	DefaultTableModel modelo;
	JTable tabla;
	int contador;
	boolean doEditable;
	int fila, columna;
	boolean presionado = false;

	public TablaUsuarios() {
		fila = -1;
		columna = -1;
		modelo = new DefaultTableModel() {

			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				if (row == fila && column == columna)
					return true;
				return false;
			}
		};
		tabla = new JTable(modelo);
		contador = 0;
		add(tabla);
		setVisible(true);
		tabla.setVisible(true);
		tabla.setBorder(BorderFactory.createRaisedBevelBorder());
	}

	// public void agregarColumnass(ResultSet rs) {
	// try {
	// for (; rs.next(); contador++) {
	// modelo.addColumn(rs.getString(1));
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }

	public void agregarDatos(ResultSet rs) {

		try {
			ResultSetMetaData metaDatos = rs.getMetaData();
			int numColumnas = metaDatos.getColumnCount();
			Object[] columnas = new Object[numColumnas];

			for (int i = 1; i <= columnas.length; i++)
				columnas[i - 1] = metaDatos.getColumnLabel(i);
			modelo.setColumnIdentifiers(columnas);

			while (rs.next()) {
				List<String> fila = new ArrayList<>();
				for (int i = 1; i <= numColumnas; i++)
					fila.add(rs.getString(i));
				Object[] fial = fila.toArray();

				modelo.addRow(fial);

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void hacerEditable(int fila, int columna) {
		// tabla.setEditingColumn(1);
		this.fila = fila;// Integer.parseInt(JOptionPane.showInputDialog("fila"));
		this.columna = columna;// Integer.parseInt(JOptionPane.showInputDialog("Columna"));
		modelo.isCellEditable(fila, columna);

	}

	public void agregarEdit(ResultSet rs2) {
		if (!presionado) {

			Object[] edit = new Object[modelo.getRowCount()];
			System.out.println(modelo.getRowCount());

			try {
				System.out.println(rs2.findColumn("contraseña"));

				for (int i = 0; rs2.next(); i++) {
					edit[i] = rs2.getString(rs2.findColumn("contraseña"));
					System.out.println(rs2.findColumn("contraseña"));
					System.out.println(edit[i]);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			modelo.addColumn("Contraseña", edit);
		}
		presionado = true;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return column > 1 ? false : true;
	}

	public JScrollPane devolverTablaScroll() {
		return new JScrollPane(tabla);
	}
	//
	// public void agregarDatos(ResultSet rs) {
	//
	// try {
	// while (rs.next()) {
	// List<String> fila = new ArrayList<>();
	// for (int i = 1; i <= contador; i++)
	// fila.add(rs.getString(i));
	// Object[] fial = fila.toArray();
	// modelo.addRow(fial);
	//
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// }

}
