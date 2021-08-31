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
        <title>Listado de los estudiantes</title>
    </head>
    <body>
        <jsp:useBean id="estudianteBean" scope="session" class="com.test.bean.EstudianteBean"/>
        <h1>LISTADO DE ESTUDIANTES</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>CODIGO</th>
                    <th>NOMBRE</th>
                    <th>APELLIDO</th>
                    <th>C.I.</th>
                    <th>CURSO</th>
                    <th>ACCIONES</th>
                </tr>
            </thead>
            <tbody>
                <%=estudianteBean.listarEstudiante()%>
            </tbody>
        </table>
            <a href="index.jsp">MENU INICIO</a>

    </body>
</html>
