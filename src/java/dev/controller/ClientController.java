/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package dev.controller;

import dev.exceptions.NonExistingURLException;
import dev.model.complements.BankAccountRepository;
import dev.model.complements.ClientRepository;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dev.services.BankAccountService;
import dev.services.ClientService;
import dev.services.impl.BankAccountServiceImpl;
import dev.services.impl.ClientServiceImpl;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author Patrick
 */
@WebServlet(name = "ClientController", urlPatterns = {"/Deposit", "/Transfer", "/Investment",
    "/Deposit/Send", "/Transfer/Send", "/Investment/Send"})
public class ClientController extends HttpServlet {
    

    private ClientService clientService = new ClientServiceImpl(new ClientRepository());
    private BankAccountService bankAccountService = new BankAccountServiceImpl(new BankAccountRepository());
            
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        switch (action) {
            case "/Deposit" -> {
                
                /*
                * bankAccountService.save(10.00, 1234, "1213-23");
                *
                * Calendar dataNascimento = Calendar.getInstance();
                * dataNascimento.set(1999, Calendar.JUNE, 3); // 3 de junho de 1999
                * clientService.save("111222333-44", "Patrick", "21933334444", "24753-800", "pkxavier@gmail.com", "123", 12, dataNascimento, 1L);
                
                */
                RequestDispatcher rd = request.getRequestDispatcher("/views/Deposit.jsp");
                rd.forward(request, response);
            }
            case "/Investment" -> {
                RequestDispatcher rd = request.getRequestDispatcher("/views/Investment.jsp");
                rd.forward(request, response);
            }
            case "/Transfer" -> {
                RequestDispatcher rd = request.getRequestDispatcher("/views/Transfer.jsp");
                rd.forward(request, response);
            }
            default ->
                throw new NonExistingURLException("Essa URL não é aceita: " + action);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        switch (action) {
            case "/Deposit/Send" -> {
                String value = (String) request.getParameter("value");
                System.out.println(value);
                /*
                Tratar depósito
                 */
                response.sendRedirect("/UFFBank/Deposit");
            }
            case "/Investment/Send" -> {
                String value = (String) request.getParameter("value");
                System.out.println(value);
                /*
                Tratar depósito
                 */
                response.sendRedirect("/UFFBank/Investment");
            }
            case "/Transfer/Send" -> {
                String account = (String) request.getParameter("account");
                String pix = (String) request.getParameter("pix");
                String value = (String) request.getParameter("value");

                System.out.println(account);
                System.out.println(pix);
                System.out.println(value);

                /*
                Tratar depósito
                 */
                response.sendRedirect("/UFFBank/Transfer");
            }
            default ->
                throw new NonExistingURLException("Essa URL não é aceita: " + action);
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
