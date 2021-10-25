package br.com.alura.gerenciador.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.alura.gerenciador.model.Company;
import br.com.alura.gerenciador.service.DatabaseService;

public class ListCompaniesAction {
	
	public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println("Listando empresas");

		DatabaseService database = new DatabaseService();
		List<Company> companiesList = database.getCompanies();

		request.setAttribute("companies", companiesList);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/listCompanies.jsp");
		requestDispatcher.forward(request, response);
	}
}
