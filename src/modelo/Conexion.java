package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

public class Conexion {

	private Connection conexion;
	private Statement consulta;

	public boolean generarConexion() {
		try {
			conexion = DriverManager.getConnection("jdbc:mysql://127.4.0.1/casaraiz", "root", "");
			consulta = conexion.createStatement();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getCampo(String tabla, String campo, String criterio, String dato) {
		String consulta = "Select " + campo + " From " + tabla + " where " + criterio + "= '" + dato + "'";
		ResultSet rs;
		try {
			rs = (ResultSet) Consulta(consulta);

			if (rs.next())
				return rs.getString(1);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Object[] getCamposTabla(String tablaName) throws SQLException {

		String consulta = "DESCRIBE " + tablaName;
		ArrayList<String> ar = new ArrayList<String>();
		ResultSet rs = (ResultSet) Consulta(consulta);
		while (rs.next()) {
			ar.add((String) rs.getString(1));
		}

		return ar.toArray();
	}

	public int getIDProductos() {
		String consulta = "SELECT max(id_producto) from productos";

		ResultSet rs;
		try {
			rs = (ResultSet) Consulta(consulta);
			rs.next();
			return rs.getInt(1) + 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int generarClienteRnd() {

		int id = getIDClienteRnd();
		String consulta = "insert into cliente (nombre) values('clienteNo:" + id + "')";

		try {
			getPreparedStatement(consulta).executeUpdate();
			return id;
		} catch (SQLException e) {

			System.err.println("error al registrar Cliente Random: " + e.getMessage());
			return -1;
		}

	}

	private int getIDClienteRnd() {
		String consulta = "SELECT max(id_cliente) from cliente";

		ResultSet rs;
		try {
			rs = (ResultSet) Consulta(consulta);
			rs.next();
			return rs.getInt(1) + 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Object[][] getDatosTabla(ResultSet rs) throws SQLException {

		ResultSetMetaData metaDatos = rs.getMetaData();
		int numColumnas = metaDatos.getColumnCount();

		Object[] fila = new Object[numColumnas];

		ArrayList<Object[]> arreglo = new ArrayList<>();

		while (rs.next()) {
			for (int i = 0; i < fila.length; i++) {
				fila[i] = rs.getString(i + 1);
			}
			arreglo.add(fila);
			fila = new Object[numColumnas];
		}

		Object[][] are = new Object[arreglo.size()][numColumnas];

		for (int i = 0; i < arreglo.size(); i++) {
			for (int j = 0; j < numColumnas; j++) {
				are[i][j] = arreglo.get(i)[j];
			}
		}

		return are;
	}

	public int getIDVenta() {
		String consulta = "SELECT max(id_venta) from ventas";

		ResultSet rs;
		try {
			rs = (ResultSet) Consulta(consulta);
			rs.next();
			return rs.getInt(1) + 1;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public Resultset Consulta(String c) throws SQLException {
		return (Resultset) consulta.executeQuery(c);
	}

	public void cerrarConexion() throws SQLException {
		conexion.close();
	}

	public boolean agregarProductoExistente(int cant, String clave) {
		int cantidad = 0;
		String consulta = "Select existencias from productos where id_producto = '" + clave + "'";
		ResultSet rs;
		try {
			rs = (ResultSet) Consulta(consulta);
			rs.next();
			cantidad = rs.getInt(1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String consulta1 = "UPDATE `productos` SET `existencias` = '" + (cant + cantidad)
				+ "' WHERE `productos`.`id_producto` = '" + clave + "'";

		try {
			PreparedStatement ps = getPreparedStatement(consulta1);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		}

	}

	public boolean hayExistencia(int dato) {
		String consulta = "Select existencias From productos where id_producto = '" + dato + "' and existencias > 0";
		ResultSet rs;
		try {
			rs = (ResultSet) Consulta(consulta);

			if (rs.next())
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean existe(String tabla, String colum, String dato) {
		String consulta = "Select * From " + tabla + " where " + colum + "= '" + dato + "'";
		ResultSet rs;
		try {
			rs = (ResultSet) Consulta(consulta);

			if (rs.next())
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean existeUsuario(String user) {
		String consulta = "Select * From usuarios where usuario = '" + user + "'";

		ResultSet rs;
		try {
			rs = (ResultSet) Consulta(consulta);

			if (rs.next())
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean altaUser(String user, String nom, String contra, int edad, String tipo) {
		String insertTableSQL = "INSERT INTO `usuarios`(`Usuario`, `Nombre Completo`, `contraseï¿½a`, `edad`, `tipo`) VALUES "
				+ "(?,?,?,?,?)";

		try {

			PreparedStatement preparedStatement = conexion.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, nom);
			preparedStatement.setString(3, contra);
			preparedStatement.setInt(4, edad);
			preparedStatement.setString(5, tipo);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean bajauser(String user) {
		String insertTableSQL = "DELETE FROM `usuarios` WHERE `usuarios`.`Usuario` = (?)";

		try {

			PreparedStatement preparedStatement = conexion.prepareStatement(insertTableSQL);
			preparedStatement.setString(1, user);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	public void realizarConsulta(String consulta) throws SQLException {

		PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
		preparedStatement.executeUpdate();

	}

	public PreparedStatement getPreparedStatement(String consulta) throws SQLException {
		PreparedStatement preparedStatement = conexion.prepareStatement(consulta);
		return preparedStatement;
	}

	public String consultarUsuario(String user) {
		String consulta = "Select * From usuarios where usuario = '" + user + "'";
		String salida = "Datos sobre el usuario: " + user.concat("\n");
		ResultSet rs;
		try {
			rs = (ResultSet) Consulta(consulta);
			ResultSetMetaData metaDatos = rs.getMetaData();
			int tamaño = metaDatos.getColumnCount();
			rs.next();
			for (int i = 2; i <= tamaño; i++) {
				salida += metaDatos.getColumnLabel(i) + ":  ";
				salida += rs.getString(i) + "  ";
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return salida;
	}

}
