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
        <title>JSP Page</title>
    </head>
    <body>
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
        </form>
    </body>
</html>
