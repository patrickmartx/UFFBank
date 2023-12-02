/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package dev.controller;

import dev.entity.Client;
import dev.services.ClientService;
import dev.services.impl.ClientServiceImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dev.exceptions.NoEntityFoundException;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Patrick
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {
    ClientService service = new ClientServiceImpl();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Verificar se já existe uma sessão
            HttpSession existingSession = request.getSession(false);
            if (existingSession != null && existingSession.getAttribute("client") != null) {
                // Se já estiver autenticado, redirecione para a página Home
                response.sendRedirect(request.getContextPath() + "/views/Home.jsp");
            } else {
                // Caso contrário, encaminhe para a página de login
                RequestDispatcher rd = request.getRequestDispatcher("/views/Login.jsp");
                rd.forward(request, response);
            }
        } catch (Exception ex) {
            PrintWriter out = response.getWriter();
            out.println("<h3>Erro não identificado. Cheque o log </h3> \n" + ex.getMessage());
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            String cpf = request.getParameter("cpf");
            String password = request.getParameter("password");
            if (cpf.isEmpty() || password.isEmpty()) {
                RequestDispatcher rd = request.getRequestDispatcher("/views/Login.jsp");
                request.setAttribute("errorMessage", "Cpf ou senha inválidos");
                rd.forward(request, response);
            }
            
            Client client = new Client();
            
            try{
                client = service.getClientByLogin(cpf, password);
            } catch(Exception ex) {
                throw new NoEntityFoundException();
            }
            
            if (client.getId() != 0) {
                HttpSession session = request.getSession();
                session.setAttribute("client", client);
            
                RequestDispatcher rd = request.getRequestDispatcher("/views/Home.jsp");
                rd.forward(request, response);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/views/Login.jsp");
                request.setAttribute("errorMessage", "Cpf ou senha inválidos");
                rd.forward(request, response);
                throw  new NoEntityFoundException();
            }
        } catch(NoEntityFoundException ex) {
            RequestDispatcher rd = request.getRequestDispatcher("/views/Login.jsp");
            request.setAttribute("errorMessage", "Cpf ou senha inválidos");
            rd.forward(request, response);
        } catch(Exception ex) {
            PrintWriter out = response.getWriter();
            out.println("<h3>Erro não identificado. Cheque o log</h3>");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
