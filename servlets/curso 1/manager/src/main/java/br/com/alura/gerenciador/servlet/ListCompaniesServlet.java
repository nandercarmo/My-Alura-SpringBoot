package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.model.Company;
import br.com.alura.gerenciador.service.Database;

@WebServlet("/listCompanies")
public class ListCompaniesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Database database = new Database();
		List<Company> companiesList = database.getCompanies();
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<ul>");
		
		companiesList.forEach(company -> {
			
			out.println("<li>" + company.toString() + "</li>");
		});
		
		out.println("</ul>");
		out.println("</body>");
		out.println("</html>");		
		
	}
}
