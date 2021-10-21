package br.com.alura.gerenciador.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.model.Company;
import br.com.alura.gerenciador.service.Database;

@WebServlet("/new-company")
public class NewCompanyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("Cadastrando nova empresa");
		
		Company company = new Company();
		company.setName(req.getParameter("name"));
		
		Database database = new Database();
		database.addCompany(company);
		
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("Empresa " + company.getName() + " cadastrada com sucesso!");
		out.println("</body>");
		out.println("</html>");
	}
}
