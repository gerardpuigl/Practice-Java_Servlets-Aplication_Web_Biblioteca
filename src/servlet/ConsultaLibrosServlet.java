package servlet;


import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ConsultaLibrosServlet")
public class ConsultaLibrosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {
		
		BaseDatos db = new BaseDatos();
		
		String boton = request.getParameter("submit");
		String filtro="";
		if (boton.equals("Consulta Libros")) {
			
			filtro = request.getParameter("titulo");


			
		}else if (boton.equals("Insertar Libro")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String titulo = request.getParameter("titulo");
			String autor = request.getParameter("autor");
			String editorial = request.getParameter("editorial");
			DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date fecha = formatter.parse(request.getParameter("fecha"));
			Date sqlfecha= new Date(fecha.getTime());
			String categoria = request.getParameter("categoria");
			int novedad = Integer.parseInt(request.getParameter("novedad"));
			Libro libro = new Libro(id, titulo, autor, editorial, sqlfecha, categoria, novedad);
			db.insertarLibro(libro);
		}
		
		ArrayList<Libro> libros = db.consultaLibros(filtro);
		request.setAttribute("lista", libros);
		getServletContext().getRequestDispatcher("/consulta.jsp").forward(request, response);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {

			try {
				processRequest(request, response);
			} catch (ServletException | IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException  {

			try {
				processRequest(request, response);
			} catch (ServletException | IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

}
