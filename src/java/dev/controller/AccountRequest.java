/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package dev.controller;

import dev.entity.Client;
import dev.exceptions.NoEntityFoundException;
import dev.services.ClientService;
import dev.services.impl.ClientServiceImpl;
import dev.utils.Status;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patrick
 */
@WebServlet(name = "AccountRequest", urlPatterns = {"/AccountRequest"})
public class AccountRequest extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            RequestDispatcher rd = request.getRequestDispatcher("/views/AccountRequest.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            PrintWriter out = response.getWriter();
            out.println("<h3>Erro não identificado. Cheque o log </h3> \n" + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ClientService service = new ClientServiceImpl();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        try{
            String cpf = request.getParameter("cpf");
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String cep = request.getParameter("cep");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            Integer houseNumber = Integer.valueOf(request.getParameter("houseNumber"));
            Date birthDate = dateFormat.parse(request.getParameter("birthDate"));
            
            System.out.println(cpf + name + phone + cep + email + password + houseNumber + birthDate);
            
            if (cpf.isEmpty() || name.isEmpty() || phone.isEmpty() || cep.isEmpty() || email.isEmpty()
                    || password.isEmpty() || houseNumber == null || birthDate == null) {
                
                RequestDispatcher rd = request.getRequestDispatcher("/views/AccountRequest.jsp");
                request.setAttribute("errorMessage", "Não é possível deixar campos em branco.");
                rd.forward(request, response);
            }
            
            try{
                service.insert(cpf, name, phone, cep, email, password, houseNumber, birthDate);
                Client createdCLient = service.getClientByLogin(cpf, password);
                if (createdCLient.getCpf() != null) {
                    Logger.getLogger(AccountRequest.class.getName()).log(Level.INFO, "Cliente inserido com sucesso!");
                }
            } catch(Exception ex) {
                throw new RuntimeException("Cliente não pôde ser inserido. Erro" + ex.getClass() + "Mensagem: " + ex.getMessage());
            }
            RequestDispatcher rd = request.getRequestDispatcher("index.html");
            rd.forward(request, response);
            
        } catch(NoEntityFoundException ex) {
            RequestDispatcher rd = request.getRequestDispatcher("/views/AccountRequest.jsp");
            request.setAttribute("errorMessage", "Dados inválidos");
            rd.forward(request, response);
        } catch(NumberFormatException ex) {
            RequestDispatcher rd = request.getRequestDispatcher("/views/AccountRequest.jsp");
            request.setAttribute("errorMessage", "Dados envolvendo números estão incorretos. mensagem: " + ex.getMessage());
            rd.forward(request, response);
        } catch(Exception ex) {
            PrintWriter out = response.getWriter();
            out.println("<h3>Erro não identificado. Cheque o log</h3> Erro: " + ex.getClass() + " Mensagem: " + ex.getMessage());
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
