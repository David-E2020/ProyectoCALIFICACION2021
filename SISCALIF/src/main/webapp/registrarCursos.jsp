<%-- 
    Document   : registrarCursos
    Created on : 15-jun-2021, 12:13:09
    Author     : FERMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Cursos</title>
    </head>
    <body>
        <jsp:useBean id="cursos" scope="session" class="com.test.bean.CursosBean"/>
        <%
            if(request.getParameter("guardar")!=null){
                String mensaje=cursos.registrarCursos(request);
                out.print(mensaje);
            }
        %>
        <h1>CURSOS</h1>
        <form method="POST">
            <table border="1">
                <thead>
                    <tr>
                        <th colspan="2">REGISTRAR CURSOS</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>NOMBRE: </td>
                        <td><input type="text" name="nomCur" value="" /></td>
                    </tr>
                    
                    <tr>
                        <td colspan="2" align="center"><input type="submit" value="REGISTRAR" name="guardar"/></td>
                    </tr>
                </tbody>
            </table>

        </form>
        <a href="index.jsp">INICIO</a>
        
    </body>
</html>
