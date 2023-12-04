<%-- 
    Document   : RegisterClient
    Created on : 2 de dez. de 2023, 15:54:19
    Author     : Patrick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="dev.services.AdminService" %>
<%@ page import="dev.services.impl.AdminServiceImpl" %>
<%@ page import="dev.entity.Admin" %>
<%@ page import="dev.entity.Client" %>
<%@page import="dev.services.ClientService"%>
<%@page import="dev.services.impl.ClientServiceImpl"%>

<%
    Admin admin = new Admin();
    admin = (Admin) session.getAttribute("admin");
%>
<%! ClientService clientService = new ClientServiceImpl(); %>
<!--% Client client = clientService.getById(id) %>-->

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="views/styles/estilo.css" />
        <link rel="stylesheet" type="text/css" href="views/styles/deposito.css" />
        <title>Criar conta | UFFBANK</title>
    </head>
    <body>
        <!-- <form action="/UFFBank/LoginAdmin" method="get">
            <button type="submit">Home</button>
        </form>
        <form action="/UFFBank/LogoutAdmin" method="post">
            <button type="submit">Logout</button>
        </form> -->
        <!-- <form action="RegisterClient" method="post">
            <label for="bankNumber">Número do banco</label>
            <input type="text" id="bankNumber" name="bankNumber" />

            <label for="accountNumber">Número da conta</label>
            <input type="text" id="accountNumber" name="accountNumber" />

            <input type="submit" value="RegisterClient" />
            
            <%-- Exibe a mensagem de erro apenas se ela estiver presente --%>
            <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
            <% if (errorMessage != null) { %>
                <div style="color: red;"><%= errorMessage %></div>
            <% } %>
        </form>  -->
        
        <div class="page">
          <%@ include file="/views/components/sidebarAdmin.jsp" %>
            <main>
              <%@ include file="/views/components/TopBarAdmin.jsp" %>
        
              <form class="form">
                <h2 class="title">Criar conta</h2>
        
                <div class="field">
                  <label for="bankNumber">Número da agência</label>
                  <input 
                    id="bankNumber" 
                    name="bankNumber"
                    required
                    autofocus
                    type="number"
                    placeholder="Ex: 22">
                </div>
        
                <div class="field">
                  <label for="accountNumber">Número da conta</label>
                  <input 
                    id="accountNumber" 
                    type="text" 
                    name="accountNumber"
                    required
                    placeholder="Ex: 2459488">
                </div>
        
                <button class="primary-btn" type="submit">
                  Transferir 
                </button>
              </form>
            </main>
          </div>
    </body>
</html>
