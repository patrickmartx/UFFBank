<%-- 
    Document   : Home
    Created on : 7 de nov. de 2023, 20:06:11
    Author     : Patrick
--%>

<%@page import="dev.model.complements.BankAccountRepository"%>
<%@page import="java.text.DecimalFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dev.services.AdminService" %>
<%@ page import="dev.services.impl.AdminServiceImpl" %>
<%@ page import="dev.entity.Admin" %>
<%@ page import="dev.entity.Client" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="dev.utils.Status" %>

<%! SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); %>
<%! AdminService service = new AdminServiceImpl(); %>
<%
    Admin admin = new Admin();
    admin = (Admin) session.getAttribute("admin");
%>

<%! ArrayList<Client> inactiveClients = service.getInactiveClients(); %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
    </head>
    <body>
        <h1>Bem vindo, <%=admin.getName()%>!</h1>
        <h2>Contas para ativar:</h2>
        
        
        <br><!-- comment -->
        <br><!-- comment -->
        
        <% if (!inactiveClients.isEmpty()) {
                for (int i = 0; i < inactiveClients.size(); i++) { %>
                    <h3>Nome do cliente: <%= inactiveClients.get(i).getName() %></h3>
                    <h3>CPF do cliente: <%= inactiveClients.get(i).getCpf()%></h3>
                    <a href="/UFFBank/RegisterClient?userId=<%= inactiveClients.get(i).getId() %>">
                        <button>Cadastrar cliente</button>
                    <br>
        <% }
        } else { %>
            <h3>Não há clientes para atualizar!</h3>
        <%
        } %>
        
         <%-- Exibe a mensagem de erro apenas se ela estiver presente --%>
            <% String sucessMessage = (String) request.getAttribute("sucessMessage"); %>
            <% if (sucessMessage != null) { %>
                <div style="color: green;"><%= sucessMessage %></div>
            <% } %>
    </body>
</html>
