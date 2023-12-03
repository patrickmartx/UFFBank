<%-- 
    Document   : AccountRequest
    Created on : 2 de dez. de 2023, 19:20:05
    Author     : Patrick
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="AccountRequest" method="post">
            <label for="cpf">cpf</label><br>
            <input type="text" id="cpf" name="cpf" /><br>
            <label for="name">nome</label><br>
            <input type="text" id="name" name="name" /><br>
            <label for="phone">telefone</label><br>
            <input type="text" id="phone" name="phone" /><br>
            <label for="cep">cep</label>
            <input type="text" id="cep" name="cep" /><br>
            <label for="address">cep</label>
            <input type="text" id="address" name="address" /><br>
            <label for="email">email</label>
            <input type="text" id="email" name="email" /><br>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" /><br>
            <label for="houseNumber">número da casa</label>
            <input type="number" id="houseNumber" name="houseNumber" /><br>
            <label for="birthDate">Data de nascimento</label>
            <input type="date" id="birthDate" name="birthDate" /><br>
            

            <input type="submit" value="AccountRequest" />
            
             <%-- Exibe a mensagem de erro apenas se ela estiver presente --%>
            <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
            <% if (errorMessage != null) { %>
                <div style="color: red;"><%= errorMessage %></div>
            <% } %>
        </form>
    </body>
</html>
