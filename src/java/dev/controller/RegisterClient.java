/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package dev.controller;

import dev.entity.BankAccount;
import dev.entity.Client;
import dev.entity.InvestmentWallet;
import dev.exceptions.NoEntityFoundException;
import dev.services.AdminService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import dev.services.BankAccountService;
import dev.services.ClientService;
import dev.services.InvestmentWalletService;
import dev.services.impl.AdminServiceImpl;
import dev.services.impl.BankAccountServiceImpl;
import dev.services.impl.ClientServiceImpl;
import dev.services.impl.InvestmentWalletServiceImpl;
import dev.utils.Status;

/**
 *
 * @author Patrick
 */
@WebServlet(name = "RegisterClient", urlPatterns = {"/RegisterClient"})
public class RegisterClient extends HttpServlet {
    private String parameter;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            this.parameter = request.getParameter("userId");
            
            HttpSession session = request.getSession();
            RequestDispatcher rd = request.getRequestDispatcher("/views/RegisterClient.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            PrintWriter out = response.getWriter();
            out.println("<h3>Erro não identificado. Cheque o log</h3>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdminService service = new AdminServiceImpl();
        BankAccountService bankService = new BankAccountServiceImpl();
        InvestmentWalletService investmentWalletService = new InvestmentWalletServiceImpl();
        try{
            Integer bankNumber =Integer.valueOf(request.getParameter("bankNumber"));
            String accountNumber = request.getParameter("accountNumber");
            
            investmentWalletService.insert();
            try {
            InvestmentWallet investmentWallet = investmentWalletService.getWalletWithLastId();
            
            bankService.insert(bankNumber, accountNumber, investmentWallet.getId());
            } catch (Exception ex) {
                throw new RuntimeException("Ocorreu um erro ao criar a conta ou carteira de investimento."
                        + "\nClasse: " + ex.getClass() + " Mensagem: " + ex.getMessage());
            }
            
            if (bankNumber == null || accountNumber.isEmpty()) {
                RequestDispatcher rd = request.getRequestDispatcher("/views/RegisterClient.jsp");
                request.setAttribute("errorMessage", "numero do banco ou numero da conta inválidos");
                rd.forward(request, response);
            }
            
            BankAccount bankAccount = new BankAccount();
            try{
                bankAccount = bankService.getAccountByBankNumberAndAccountNumber(bankNumber, accountNumber);
            } catch(Exception ex) {
                throw new NoEntityFoundException();
            }
            
            if (bankAccount.getId() != 0 || bankAccount.getId() != null) {
                Long clientId = Long.valueOf(this.parameter);
                
                service.activateClient(clientId, bankNumber, accountNumber);
                
                HttpSession session = request.getSession();
                session.setAttribute("bankAccount", bankAccount);
            
                RequestDispatcher rd = request.getRequestDispatcher("/views/AdminHome.jsp");
                request.setAttribute("sucessMessage", "Conta validado com sucesso!");
                rd.forward(request, response);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("/views/RegisterClient.jsp");
                request.setAttribute("errorMessage", "conta no banco ou numero de conta inválido.");
                rd.forward(request, response);
                throw  new NoEntityFoundException();
            }
        } catch(NoEntityFoundException ex) {
            RequestDispatcher rd = request.getRequestDispatcher("/views/Login.jsp");
            request.setAttribute("errorMessage", "Cpf ou senha inválidos");
            rd.forward(request, response);
        } catch(Exception ex) {
            PrintWriter out = response.getWriter();
            out.println(ex.getMessage());
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
