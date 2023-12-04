/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package dev.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Patrick
 */
@WebServlet(name = "ListActiveAccounts", urlPatterns = {"/ListActiveAccounts"})
public class ListActiveAccounts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Verificar se já existe uma sessão
            HttpSession existingSession = request.getSession(false);
            if (existingSession != null && existingSession.getAttribute("admin") != null) {
                // Se já estiver autenticado, redirecione para a página Home
                RequestDispatcher rd = request.getRequestDispatcher("/views/AdminUsers.jsp");
                rd.forward(request, response);
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
