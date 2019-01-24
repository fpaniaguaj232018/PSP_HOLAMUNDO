<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String mensaje = (String)request.getAttribute("mensaje");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%=mensaje%></h1>
    </body>
</html>
