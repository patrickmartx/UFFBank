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
@WebServlet(name = "ClientController", urlPatterns = {"/Client/*"})
public class ClientController extends HttpServlet {
    
    private BankAccountRepository bankAccountRepository = new BankAccountRepository();
    private BankAccountService bankService = new BankAccountServiceImpl(bankAccountRepository);
    
    private ClientRepository clientRepository = new ClientRepository();
    private ClientService clientService = new ClientServiceImpl(clientRepository);
            
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        switch (action) {
            case "/Client/Deposit" -> {
                RequestDispatcher rd = request.getRequestDispatcher("/views/Deposit.jsp");
                rd.forward(request, response);
            }
            case "/Client/Investment" -> {
                RequestDispatcher rd = request.getRequestDispatcher("/views/Investment.jsp");
                rd.forward(request, response);
            }
            case "/Client/Transfer" -> {
                RequestDispatcher rd = request.getRequestDispatcher("/views/Transfer.jsp");
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
            case "/Client/Deposit/Send" -> {
                String value = (String) request.getParameter("value");
                System.out.println(value);
                /*
                TODO: Tratar depósito
                 */
                response.sendRedirect("/UFFBank/Deposit");
            }
            case "/Client/Investment/Send" -> {
                String value = (String) request.getParameter("value");
                System.out.println(value);
                /*
                TODO: Tratar depósito
                 */
                response.sendRedirect("/UFFBank/Investment");
            }
            case "/Client/Transfer/Send" -> {
                String account = (String) request.getParameter("account");
                String value = (String) request.getParameter("value");

                /*
                TODO: Tratar depósito
                 */
                response.sendRedirect("/UFFBank/Transfer");
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
