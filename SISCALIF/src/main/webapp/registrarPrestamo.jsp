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
        <jsp:useBean id="libroBean" scope="session" class="com.test.bean.LibroBean"/>
        <jsp:useBean id="usuarioBean" scope="session" class="com.test.bean.UsuarioBean"/>
        <jsp:useBean id="prestamoBean" scope="session" class="com.test.bean.PrestamoBean"/>
        <%
            if(request.getParameter("guardar")!=null){
                String mensaje=prestamoBean.registrarPrestamo(request);
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
                        <td>USUARIO: </td>
                        <td>
                            <select name="codUsuario">
                                <%=usuarioBean.listarUsuarioSelect()%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>LIBRO: </td>
                        <td>
                            <select name="codLibro">
                                <%=libroBean.listarLibroSelect()%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>FECHA ENTREGA: </td>
                        <td><input type="date" name="fechaEntrega"/></td>
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
