<%-- 
    Document   : login.jsp
    Created on : 7 de nov. de 2023, 19:56:25
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
        <form action="LoginAdmin" method="post">
            <label for="cpf">cpf</label>
            <input type="text" id="cpf" name="cpf" />

            <label for="password">Password:</label>
            <input type="password" id="password" name="password" />

            <input type="submit" value="LoginAdmin" />
            
             <%-- Exibe a mensagem de erro apenas se ela estiver presente --%>
            <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
            <% if (errorMessage != null) { %>
                <div style="color: red;"><%= errorMessage %></div>
            <% } %>
        </form>
    </body>
</html>
