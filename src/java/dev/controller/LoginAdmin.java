/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package dev.controller;

import dev.entity.Admin;
import dev.services.AdminService;
import dev.services.impl.AdminServiceImpl;
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
@WebServlet(name = "LoginAdmin", urlPatterns = {"/LoginAdmin"})
public class LoginAdmin extends HttpServlet {
    AdminService service = new AdminServiceImpl();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Verificar se já existe uma sessão
            HttpSession existingSession = request.getSession(false);
            if (existingSession != null && existingSession.getAttribute("admin") != null) {
                // Se já estiver autenticado, redirecione para a página Home
                response.sendRedirect(request.getContextPath() + "/views/AdminHome.jsp");
            } else {
                // Caso contrário, encaminhe para a página de login
                RequestDispatcher rd = request.getRequestDispatcher("/views/LoginAdmin.jsp");
                rd.forward(request, response);
            }
        } catch (Exception e) {
            PrintWriter out = response.getWriter();
            out.println("<h3>Erro não identificado. Cheque o log</h3>");
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            String cpf = request.getParameter("cpf");
            String password = request.getParameter("password");
            if (cpf.isEmpty() || password.isEmpty()) {
                RequestDispatcher rd = request.getRequestDispatcher("/views/LoginAdmin.jsp");
                request.setAttribute("errorMessage", "Cpf ou senha inválidos");
                rd.forward(request, response);
            }
            
            Admin admin = new Admin();
            
            try{
                admin = service.getAdminByLogin(cpf, password);
            } catch(Exception ex) {
                throw new NoEntityFoundException();
            }
            
            if (admin.getId() != 0 && admin.getId() != null) {
                HttpSession session = request.getSession();
                session.setAttribute("admin", admin);
            
                RequestDispatcher rd = request.getRequestDispatcher("/views/AdminHome.jsp");
                rd.forward(request, response);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/views/LoginAdmin.jsp");
                request.setAttribute("errorMessage", "Cpf ou senha inválidos");
                rd.forward(request, response);
                throw  new NoEntityFoundException();
            }
        } catch(NoEntityFoundException ex) {
            RequestDispatcher rd = request.getRequestDispatcher("/views/LoginAdmin.jsp");
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
