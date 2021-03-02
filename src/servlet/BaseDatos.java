package servlet;

import java.sql.*;

public class BaseDatos {

	private Connection conexion;

	public BaseDatos() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String conex = "jdbc:mysql://127.0.0.1:3306/m12_biblioteca_online";
			this.conexion = DriverManager.getConnection(conex, "root", "");
			System.out.println("Base de datos mysql conectada con éxito");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean compruebaUsuario(String usuario, String password) {

		boolean check = false;

		try {
			Statement s = conexion.createStatement();

			String sql = "SELECT count(*) FROM USUARIOS WHERE usuario='" + usuario + "' "
					+ "and password='" + password + "'";
			s.execute(sql);
			ResultSet rs=s.getResultSet();
			rs.next();
			if (rs.getInt(1)>0)
				check=true;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return check;

	}

}
