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
        <jsp:useBean id="gradoBean" scope="session" class="com.test.bean.GradoBean"/>
        <%
            if(request.getParameter("guardar")!=null){
                String mensaje=estudianteBean.registrarEstudiante(request);
                out.print(mensaje);
            }
        %>
        <h1>CURSOS</h1>
        <form method="POST">
            <table border="1">
                <thead>
                    <tr>
                        <th colspan="2">REGISTRAR ESTUDIANTES</th>
                    </tr>
                </thead>
                <tbody>
                    
                    <tr>
                        <td>NOMBRE: </td>
                        <td><input type="text" name="nomEst"/></td>
                    </tr>
                    <tr>
                        <td>APELLIDOS: </td>
                        <td><input type="text" name="apEst"/></td>
                    </tr>
                    <tr>
                        <td>CEDULA DE IDENTIDAD: </td>
                        <td><input type="text" name="cedEst"/></td>
                    </tr>
                    <tr>
                        <td>GRADO: </td>
                        <td>
                            <select name="codG">
                                <%=gradoBean.listarGradoSelect()%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>MATERIA: </td>
                        <td>
                            <select name="codC">
                                <%=cursosBean.listarCursosSelect()%>
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
