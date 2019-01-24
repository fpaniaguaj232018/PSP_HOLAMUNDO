<%@page import="com.fernandopaniagua.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User user = (User)request.getAttribute("nuevoUsuario");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>El usuario <%=user.getEmail()%> se ha creado satisfactoriamente</h1>
    </body>
</html>
