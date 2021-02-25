package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/GestorBibliotecaServlet")
public class GestorBibliotecaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private boolean Yainiciado=false; 
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		if (usuario.equals("Gerard")&&password.equals("1234")) {
			boolean iniciado = Yainiciado;
			if (!Yainiciado == true) {
				Yainiciado = true;
				response.sendRedirect("bienvenida.jsp?usuario=" + usuario + "&iniciado=" + iniciado + "&method=GET");
			}
		} else {
			response.sendRedirect("error.jsp?usuario=" + usuario);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String usuario = request.getParameter("usuario");
		String password = request.getParameter("password");
		if (usuario.equals("Gerard")&&password.equals("1234")) {
			boolean iniciado = Yainiciado;
			if (!Yainiciado == true) {
				Yainiciado = true;
				response.sendRedirect("bienvenida.jsp?usuario=" + usuario + "&iniciado=" + iniciado + "&method=POST");
			}
		} else {
			response.sendRedirect("error.jsp?usuario=" + usuario);
		}
	}

}
