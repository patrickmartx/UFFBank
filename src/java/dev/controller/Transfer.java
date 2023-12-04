/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package dev.controller;

import dev.entity.BankAccount;
import dev.entity.Client;
import dev.exceptions.NoEntityFoundException;
import dev.services.BankAccountService;
import dev.services.ClientService;
import dev.services.TransactionHistoryService;
import dev.services.impl.BankAccountServiceImpl;
import dev.services.impl.ClientServiceImpl;
import dev.services.impl.TransactionHistoryServiceImpl;
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
@WebServlet(name = "Transfer", urlPatterns = {"/Transfer"})
public class Transfer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            RequestDispatcher rd = request.getRequestDispatcher("/views/Transfer.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            PrintWriter out = response.getWriter();
            out.println("<h3>Erro não identificado. Cheque o log</h3>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            ClientService clientService = new ClientServiceImpl();
            BankAccountService bankAccountService = new BankAccountServiceImpl();
            TransactionHistoryService transactionHistoryService = new TransactionHistoryServiceImpl();

            Integer bankNumber = Integer.valueOf(request.getParameter("otherBankNumber"));
            String accountNumber = request.getParameter("otherAccountNumber");
            Double value = Double.valueOf(request.getParameter("value"));

            HttpSession existingSession = request.getSession(false);
            if (existingSession != null && existingSession.getAttribute("client") != null) {
                try{
                    Client client = (Client) existingSession.getAttribute("client");
                    BankAccount bankAccountSender = bankAccountService.getById(client.getBankAccountId());
                    BankAccount bankAccountReceiver = bankAccountService.getAccountByBankNumberAndAccountNumber(bankNumber, accountNumber);

                    System.out.println("ID 1: " + bankAccountSender.getId());
                    System.out.println("ID 2: " + bankAccountReceiver.getId());

                    clientService.transferBetweenTwoAccounts(bankAccountSender.getId(), bankAccountReceiver.getId(), value);

                    RequestDispatcher rd = request.getRequestDispatcher("/views/Transfer.jsp");
                    request.setAttribute("sucessMessege", "Valor R$"+value+" transferido!" );
                    rd.forward(request, response);
                } catch (NoEntityFoundException ex) {
                    RequestDispatcher rd = request.getRequestDispatcher("/views/Transfer.jsp");
                    request.setAttribute("errorMessege", "A conta para qual você está tentando transferir não existe." );
                    rd.forward(request, response);
                }
            } else 
                throw new RuntimeException("Cliente não está na sessao");
        } catch (Exception ex) {
            RequestDispatcher rd = request.getRequestDispatcher("/views/Transfer.jsp");
                request.setAttribute("errorMessage", ex.getMessage() );
                rd.forward(request, response);
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
