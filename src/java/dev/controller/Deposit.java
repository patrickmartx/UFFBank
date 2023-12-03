/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package dev.controller;

import dev.entity.*;
import dev.services.*;
import dev.services.impl.*;
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
@WebServlet(name = "Deposit", urlPatterns = {"/Deposit"})
public class Deposit extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            RequestDispatcher rd = request.getRequestDispatcher("/views/Deposit.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            PrintWriter out = response.getWriter();
            out.println("<h3>Erro não identificado. Cheque o log</h3>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClientService clientService = new ClientServiceImpl();
        BankAccountService bankAccountService = new BankAccountServiceImpl();
        TransactionHistoryService transactionHistoryService = new TransactionHistoryServiceImpl();
        
        Double value = Double.valueOf(request.getParameter("value"));
        
        HttpSession existingSession = request.getSession(false);
        if (existingSession != null && existingSession.getAttribute("client") != null) {
            Client client = (Client) existingSession.getAttribute("client");
            BankAccount bankAccount = bankAccountService.getById(client.getBankAccountId());
            
            clientService.depositInBankAccount(client.getBankAccountId(), value);
            
            RequestDispatcher rd = request.getRequestDispatcher("/views/Deposit.jsp");
            request.setAttribute("sucessMessege", "Valor R$"+value+" depositado!" );
            rd.forward(request, response);
        } else 
            throw new RuntimeException("Cliente não está na sessao");
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
