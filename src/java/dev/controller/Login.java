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
import dev.utils.Status;
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
            out.println("Erro não identificado. Avise ao Desenvolvedor sobre: " + ex.getMessage());
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            // Tenta construir os dados através dos parâmetros obtidos pelo formulario
            String cpf = request.getParameter("cpf");
            String password = request.getParameter("password");
            
            if (cpf.isEmpty() || password.isEmpty()) {
                // Caso algum dos parâmetros fique vazio, redireciona para Login com uma mensagem de erro.
                RequestDispatcher rd = request.getRequestDispatcher("/views/Login.jsp");
                request.setAttribute("errorMessage", "Cpf ou senha inválidos");
                rd.forward(request, response);
            }
            
            // Caso esteja tudo certo, pode inicializar o cliente
            Client client = new Client();
            
            try{
                // e tentar construir o mesmo através do método getClientByLogin
                // 
                client = service.getClientByLogin(cpf, password);
            } catch(Exception ex) {
                // Caso não exista, chamar a exceção para ser tratada.
                throw new NoEntityFoundException();
            }
            
            if (client.getId() != 0 && client.getStatus().getValue().equals(Status.ACTIVE.getValue())) {
                // se o id do cliente é diferente de 0 E o seu status é ativo, a sessão recebe o cliente e ele pode acessar sua página.
                HttpSession session = request.getSession();
                session.setAttribute("client", client);
            
                RequestDispatcher rd = request.getRequestDispatcher("/views/Home.jsp");
                rd.forward(request, response);
            } else {
                // caso seu id seja 0 OU seu status não seja ativo, reencaminhar para login com mensagem de erro.
                RequestDispatcher rd = request.getRequestDispatcher("/views/Login.jsp");
                request.setAttribute("errorMessage", "Sua conta ainda não foi validada.");
                rd.forward(request, response);
                throw  new NoEntityFoundException();
            }
        } catch(NoEntityFoundException ex) {
            // encaminhar para login com mensagem de erro caso não exista a conta no banco de dados.
            RequestDispatcher rd = request.getRequestDispatcher("/views/Login.jsp");
            request.setAttribute("errorMessage", "Cpf ou senha inválidos");
            rd.forward(request, response);
        } catch(Exception ex) {
            // caso ocorra um erro desconhecido, instruir falar com o desenvolvedor
            PrintWriter out = response.getWriter();
            out.println("Erro não identificado. Avise ao Desenvolvedor sobre: " + ex.getMessage());
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
