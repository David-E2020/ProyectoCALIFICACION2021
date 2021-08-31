<%-- 
    Document   : listaEstudiante
    Created on : 15-jun-2021, 18:40:09
    Author     : FERMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de notas</title>
    </head>
    <body>
        <jsp:useBean id="notasBean" scope="session" class="com.test.bean.NotasBean"/>
        <h1>LISTADO DE ESTUDIANTES</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>CODIGO</th>
                    <th>NOMBRE CURSO</th>
                    <th>NOTA</th>
                    <th>NOMBRE</th>
                    <th>APELLIDOS</th>
                </tr>
            </thead>
            <tbody>
                <%=notasBean.listarNotas()%>
            </tbody>
        </table>
            <a href="index.jsp">MENU INICIO</a>
    </body>
</html>
