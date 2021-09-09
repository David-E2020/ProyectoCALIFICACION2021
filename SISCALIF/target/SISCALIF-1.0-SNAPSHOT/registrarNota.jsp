<%-- 
    Document   : registrarEstudiante
    Created on : 30-ago-2021, 14:44:01
    Author     : FERMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrar Estudiante</title>
    </head>
    <body>
        <jsp:useBean id="estudianteBean" scope="session" class="com.test.bean.EstudianteBean"/>
        <jsp:useBean id="cursosBean" scope="session" class="com.test.bean.CursosBean"/>
        <jsp:useBean id="notasBean" scope="session" class="com.test.bean.NotasBean"/>
        <%
            if(request.getParameter("guardar")!=null){
                String mensaje=notasBean.registrarNota(request);
                out.print(mensaje);
            }
        %>
        <h1>CURSOS</h1>
        <form method="POST">
            <table border="1">
                <thead>
                    <tr>
                        <th colspan="2">REGISTRAR NOTA</th>
                    </tr>
                </thead>
                <tbody>
                    
                    <tr>
                        <td>CURSO: </td>
                        <td>
                            <select name="idcur">
                                <%=cursosBean.listarCursosSelect()%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>NOTA: </td>
                        <td><input type="number" name="nota"/></td>
                    </tr>
                    <tr>
                        <td>ESTUDIANTE: </td>
                        <td>
                            <select name="idestud">
                                <%=estudianteBean.listarEstudianteSelect()%>
                            </select>
                        </td>
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
