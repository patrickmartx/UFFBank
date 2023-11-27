package dev.controller;

import dev.exceptions.NonExistingURLException;
import dev.model.complements.AdminRepository;
import dev.model.complements.BankAccountRepository;
import dev.model.complements.ClientRepository;
import dev.services.AdminService;
import dev.services.BankAccountService;
import dev.services.ClientService;
import dev.services.impl.AdminServiceImpl;
import dev.services.impl.BankAccountServiceImpl;
import dev.services.impl.ClientServiceImpl;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dev.entity.Admin;
import dev.entity.Client;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Patrick
 */
@WebServlet(name = "Controller", urlPatterns = {"/Controller", "/Login", "/Login/Auth",
                                                "/Home"})
public class Controller extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/Login" -> {
                RequestDispatcher rd = request.getRequestDispatcher("/views/Login.jsp");
                rd.forward(request, response);
            }
            case "/Home" -> {
                RequestDispatcher rd = request.getRequestDispatcher("/index.html");
                rd.forward(request, response);
            }
            default ->
                throw new NonExistingURLException("Essa URL não é aceita: " + action);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        switch (action) {
            case "/Login/Auth" -> {
                String cpf = (String) request.getParameter("cpf");
                String password = (String) request.getParameter("password");
            }
            case "" -> {
            }
            default ->
                throw new NonExistingURLException("Essa URL não é aceita: " + action);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
