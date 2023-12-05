/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package dev.controller;

import dev.entity.BankAccount;
import dev.entity.Client;
import dev.entity.InvestmentWallet;
import dev.services.BankAccountService;
import dev.services.ClientService;
import dev.services.TransactionHistoryService;
import dev.services.InvestmentWalletService;
import dev.services.impl.BankAccountServiceImpl;
import dev.services.impl.ClientServiceImpl;
import dev.services.impl.TransactionHistoryServiceImpl;
import dev.services.impl.InvestmentWalletServiceImpl;
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
@WebServlet(name = "Investment", urlPatterns = {"/Investment"})
public class Investment extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            RequestDispatcher rd = request.getRequestDispatcher("/views/Investment.jsp");
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
        InvestmentWalletService investmentWalletService = new InvestmentWalletServiceImpl();
        TransactionHistoryService transactionHistoryService = new TransactionHistoryServiceImpl();
        
        try{
            Double value = Double.valueOf(request.getParameter("value"));

            HttpSession existingSession = request.getSession(false);
            if (existingSession != null && existingSession.getAttribute("client") != null) {
                Client client = (Client) existingSession.getAttribute("client");
                BankAccount bankAccount = bankAccountService.getById(client.getBankAccountId());
                InvestmentWallet investmentWallet = investmentWalletService.getById(bankAccount.getInvestmentWalletId());

                clientService.investing(bankAccount.getId(), investmentWallet.getId(), value);

                RequestDispatcher rd = request.getRequestDispatcher("/views/Investment.jsp");
                request.setAttribute("sucessMessege", "Valor R$"+value+" investido!" );
                rd.forward(request, response);
            } else 
                throw new RuntimeException("Cliente não está na sessao");
        } catch(Exception ex) {
            RequestDispatcher rd = request.getRequestDispatcher("/views/Investment.jsp");
            request.setAttribute("errorMessege", ex.getMessage() );
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
