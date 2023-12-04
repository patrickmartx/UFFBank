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
        <link rel="stylesheet" type="text/css" href="views/styles/estilo.css" />
        <link rel="stylesheet" type="text/css" href="views/styles/deposito.css" />
        <title>Área do administrador | UFFBANK</title>
    </head>
    <div class="page">
        <%@ include file="/views/components/sidebar.html" %>
        <main>
          <div class="topbar">
            <p>Bem-vindo, 
              <span><%=admin.getName()%></span>
            </p>
          </div>
    
          

            <% if (!inactiveClients.isEmpty()) { %>
                <div class="table">
                    <div class="tb-head">
                      <p>Nome</p>
                      <p>CPF</p>
                    </div>
                 <ul class="client-list ">

            <%    for (int i = 0; i < inactiveClients.size(); i++) { %>
                    <li class="data-client">
                        <p><%= inactiveClients.get(i).getName() %></p>
                        <p><%= inactiveClients.get(i).getCpf()%></p>
              
                        <a class="btn-login" href="/UFFBank/RegisterClient?userId=<%= inactiveClients.get(i).getId() %>">Cadastrar cliente</a>
                    </li>
                    
            </ul>
                    
        <% }
        } else { %>
            <h3 class="emp-clients">Não há clientes para atualizar!</h3>
        <%
        } %>

          
    
        </div>
        </main>
      </div>
    <!-- <body>
        <h1>Bem vindo, <%=admin.getName()%>!</h1>
        
        <h2>Contas para ativar:</h2>
        
        
        <br>
        <br>
        
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
    </body> -->
</html>
