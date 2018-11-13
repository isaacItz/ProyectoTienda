package modelo;

import java.sql.*;

import javax.swing.JOptionPane;

public class Pruebas {

	public static void main(String[] args) {

		try {
			Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost/casaraiz", "root", "");

			Statement s = conexion.createStatement();

			String user = "juan";
			String contra = "holamundo";
			ResultSet rs = s.executeQuery("SELECT * FROM usuarios WHERE Usuario ='" + user + "' AND contraseña = '"
					+ contra + "' AND tipo = 'administrador'");

			if (rs.next()) {
				JOptionPane.showMessageDialog(null,
						rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + "fila: " + rs.getRow(),
						"pop", 1);
			} else
				JOptionPane.showMessageDialog(null, "el usuario no existe", "Eror", 0);

			conexion.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
