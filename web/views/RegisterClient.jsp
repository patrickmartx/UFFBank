<%-- 
    Document   : RegisterClient
    Created on : 2 de dez. de 2023, 15:54:19
    Author     : Patrick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Criar conta | UFFBANK</title>
    </head>
    <body>
        <!-- <form action="/UFFBank/LoginAdmin" method="get">
            <button type="submit">Home</button>
        </form>
        <form action="/UFFBank/LogoutAdmin" method="post">
            <button type="submit">Logout</button>
        </form> -->
        <form action="RegisterClient" method="post">
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
        </form> -->

        <div class="page">
          <%@ include ﬁle=“components/sidebar.html” %>
            <main>
              <div class="topbar">
                <p>Bem-vindo, 
                  <span>Igor</span>
                </p>
                <h1>R$ <span>542,26</span></h1>
              </div>
        
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
                    placeholder="0,00">
                </div>
        
                <div class="field">
                  <label for="accountNumber">Número da conta</label>
                  <input 
                    id="accountNumber" 
                    type="text" 
                    name="accountNumber"
                    required
                    placeholder="Ex: John Doe">
                </div>
        
                <button class="primary-btn" type="submit">
                  Transferir 
                </button>
              </form>
            </main>
          </div>
    </body>
</html>
