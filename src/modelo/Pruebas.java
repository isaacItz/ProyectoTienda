package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class Pruebas {

	public static void main(String[] args) {
		Conexion cn = new Conexion();
		cn.generarConexion();

		String consulta = "INSERT INTO `usuarios`(`Usuario`, `Nombre Completo`, `contraseña`, `edad`, `tipo`) VALUES "
				+ "('juanito','fsdadlfhlkdas','jfdksa',123,'administrador')";
		try {
			PreparedStatement p = cn.getPreparedStatement(consulta);

			p.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("huo");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
