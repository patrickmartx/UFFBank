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
<%@page import="java.text.SimpleDateFormat" %> 

<%
    Admin admin = new Admin();
    admin = (Admin) session.getAttribute("admin");
%>
<%! ClientService clientService = new ClientServiceImpl();%>
<% Long clientId = Long.valueOf(request.getParameter("userId"));%>
<% Client client = clientService.getById(clientId);%>
<%! SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="views/styles/estilo.css" />
        <link rel="stylesheet" type="text/css" href="views/styles/deposito.css" />
        <title>Criar conta | UFFBANK</title>
    </head>
    <body>
        <div class="page">
            <%@ include file="/views/components/sidebarAdmin.jsp" %>
            <main>
                <%@ include file="/views/components/TopBarAdmin.jsp" %>

                  <h2 class="info-title">Geral</h2>
                  <div class="register-info">
                    <div>
                      <p>Nome</p>
                      <p><%= client.getName()%></p>
                    </div>
            
                    <div>
                      <p>CPF</p>
                      <p><%= client.getCpf()%></p>
                    </div>
            
                    <div>
                      <p>Telefone</p>
                      <p><%= client.getPhone()%></p>
                    </div>
            
                    <div>
                      <p>Email</p>
                      <p><%= client.getEmail()%></p>
                    </div>
            
                    <div>
                      <p>Endereço</p>
                      <p><%= client.getAddress()%></p>
                    </div>
            
                    <div>
                      <p>Cep</p>
                      <p><%= client.getCep()%></p>
                    </div>
                    
                    <div>
                      <p>Data de nascimento</p>
                      <p><%= dateFormat.format(client.getBirthDate())%></p>
                    </div>
                  </div>

      <h2 class="info-title">Ativar conta</h2>

      
                <form class="form create-account" action="RegisterClient" method="post">
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
                        Criar conta e ativar cliente 
                    </button>
                </form>
            </main>
          </div>

          <%-- Exibe a mensagem de erro apenas se ela estiver presente --%>
          <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
          <% if (errorMessage != null) { %>
              <div class="error-msg"><%= errorMessage %></div>
          <% } %>

          <%-- Exibe a mensagem de sucesso apenas se ela estiver presente --%>
          <% String sucessMessege = (String) request.getAttribute("sucessMessege"); %>
          <% if (sucessMessege != null) { %>
              <div class="success-msg"><%= sucessMessege %></div>
          <% } %>
    </body>
</html>
