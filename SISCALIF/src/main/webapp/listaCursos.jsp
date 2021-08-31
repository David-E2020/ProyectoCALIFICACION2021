<%-- 
    Document   : listaCursos
    Created on : 15-jun-2021, 15:05:09
    Author     : FERMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de cursos</title>
    </head>
    <body>
        <h1>LISTA DE CURSOS</h1>
        <jsp:useBean id="cursosBean" scope="session" class="com.test.bean.CursosBean"/>
        <table border="1" bgcolor="#aabbcc">
            <thead>
                <tr>
                    <th>CODIGO</th>
                    <th>NOMBRE</th>
                </tr>
            </thead>
            <tbody>
                <%=cursosBean.listarCategoria()%>
            </tbody>
        </table>
            
            <a href="index.jsp">MENU INICIO</a>

    </body>
</html>
