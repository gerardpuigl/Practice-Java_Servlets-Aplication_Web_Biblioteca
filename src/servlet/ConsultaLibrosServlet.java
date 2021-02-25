package servlet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ConsultaLibrosServlet")
public class ConsultaLibrosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String titulo = request.getParameter("titulo");
		String path = getServletContext().getRealPath("/");
		File f = new File(path + "libros.txt");
		BufferedReader entrada = new BufferedReader(new FileReader(f));
		StringBuffer sb = new StringBuffer();
		while (entrada.ready()) {
			String linea = entrada.readLine();
			boolean presencia = linea.contains(titulo.toUpperCase());
			if (presencia)
				sb.append("<h3>" + linea + "</h3>");
		}
		response.sendRedirect("consulta.jsp?lista=" + sb.toString());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

}