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
@WebServlet(name = "Withdrawal", urlPatterns = {"/Withdrawal"})
public class Withdrawal extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Direciona o cliente para a página de saque
            HttpSession session = request.getSession();
            RequestDispatcher rd = request.getRequestDispatcher("/views/Withdrawal.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            // Caso algo dê errado com o redirecionamento, o cliente é instrído a passar a mensagem para um desenvolvedor
            PrintWriter out = response.getWriter();
            out.println("Erro não identificado. Avise ao Desenvolvedor sobre: " + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // inicializa os services
        ClientService clientService = new ClientServiceImpl();
        BankAccountService bankAccountService = new BankAccountServiceImpl();
        TransactionHistoryService transactionHistoryService = new TransactionHistoryServiceImpl();
        
        try{
            // Obtem valor do parâmetro "value" vindo do formulário
            Double value = Double.valueOf(request.getParameter("value"));
            // Verifica se existe sessão(conta de cliente logada)
            HttpSession existingSession = request.getSession(false);
            if (existingSession != null && existingSession.getAttribute("client") != null) {
                // Caso exista, construir um cliente com os dados da sessão.
                Client client = (Client) existingSession.getAttribute("client");
                // Constrói uma conta bancária baseada no id da conta do banco do usuário
                BankAccount bankAccount = bankAccountService.getById(client.getBankAccountId());
                try {
                    // Tenta fazer o saque do valor (Há um tratamento de exceção em que chamo um ArithmeticException
                    // Caso o valor do saque seja maior do que o valor que há na conta).
                    clientService.withdrawalInBankAccount(client.getBankAccountId(), value);
                } catch(ArithmeticException ex) {
                    // No caso de acontecer o que foi mencionado, o cliente irá retornar para a página com uma mensagem dizendo
                    // Que o saldo na conta dele é menor do que o valor que ele quer sacar.
                    RequestDispatcher rd = request.getRequestDispatcher("/views/Withdrawal.jsp");
                    request.setAttribute("errorMessege", ex.getMessage() );
                    rd.forward(request, response);
                }
                // Casi esteja tudo certo, vai retornar para a página com uma mensagem de sucesso.
                RequestDispatcher rd = request.getRequestDispatcher("/views/Withdrawal.jsp");
                request.setAttribute("sucessMessege", "Valor R$"+value+" sacado!" );
                rd.forward(request, response);
            } else 
                // caso o cliente não esteja logado, lança uma exceção
                throw new RuntimeException("Cliente não está na sessao");
        } catch(Exception ex) {
            // Caso caia em alguma exceção, redirecionar o usuário para a página de saque com a mensagem de erro.
            RequestDispatcher rd = request.getRequestDispatcher("/views/Withdrawal.jsp");
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
