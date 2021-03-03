package servlet;

import java.sql.*;
import java.util.ArrayList;

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

			String sql = "SELECT count(*) FROM USUARIOS WHERE usuario='" + usuario + "' " + "and password='" + password
					+ "'";
			s.execute(sql);
			ResultSet rs = s.getResultSet();
			rs.next();
			if (rs.getInt(1) > 0)
				check = true;

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return check;

	}

	public ArrayList<Libro> consultaLibros(String filtro) {
		ArrayList<Libro> lista = new ArrayList<Libro>();
		try {
			Statement s = conexion.createStatement();
			String sql = "SELECT * FROM LIBROS WHERE TITULO LIKE '%" + filtro + "%'";
			s.execute(sql);
			ResultSet rs = s.getResultSet();
			while (rs.next()) {
				Libro libro = new Libro(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
						rs.getString(6), rs.getInt(7));
				lista.add(libro);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return lista;
	}

	public void insertarLibro(Libro libro) {
		String query ="insert into libros (id, titulo, autor, editorial, fecha, categoria, novedad)" + "values (?,?,?,?,?,?,?)";
		try {
						
			PreparedStatement preparedStmt = conexion.prepareStatement(query);
			preparedStmt.setInt(1,libro.getId());
			preparedStmt.setString(2,libro.getTitulo());
			preparedStmt.setString(3,libro.getAutor());
			preparedStmt.setString(4,libro.getEditorial());
			Date sqlDate = new Date(libro.getFecha().getTime());
			preparedStmt.setDate(5, sqlDate);
			preparedStmt.setString(6, libro.getCategoria());
			preparedStmt.setInt(7,libro.getNovedad());
			preparedStmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}

	public void eliminarLibro(String id) {
		String query = " DELETE FROM libros WHERE id=" + Integer.parseInt(id) ;

		try {
			PreparedStatement preparedStmt = conexion.prepareStatement(query);
			preparedStmt.executeUpdate();
		} catch (SQLException e){
			System.out.println(e.getMessage());
		}
	}
		
}
