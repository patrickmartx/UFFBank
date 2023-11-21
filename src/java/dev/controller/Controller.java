package dev.controller;

import dev.exceptions.NonExistingURLException;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
                RequestDispatcher rd = request.getRequestDispatcher("/views/Home.jsp");
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
                /*
                TODO: Validar conta
                */
                String username = (String) request.getParameter("username");
                String password = (String) request.getParameter("password");
                System.out.println(username);
                System.out.println(password);

                response.sendRedirect("/UFFBank/Home");
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
