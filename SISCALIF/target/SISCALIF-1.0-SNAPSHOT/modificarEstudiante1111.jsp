<%-- 
    Document   : modificarEstudiante
    Created on : 30-ago-2021, 16:22:32
    Author     : FERMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MODIFICAR ESTUDIANTE</title>
    </head>
    <body>
        <h1>MODIFICAR CATEGORIA</h1>
        <jsp:useBean id="estudiante" scope="session" class="com.test.bean.EstudianteBean"/>
        <jsp:useBean id="grado" scope="session" class="com.test.bean.GradoBean"/>
        <jsp:useBean id="curso" scope="session" class="com.test.bean.CursosBean"/>
        <%
            //rescatar el parametro del codigo categoria
            String codEstudiante=request.getParameter("cod");
            //llamando el busqueda de categoria
            estudiante.buscarEstudiante(codEstudiante);
            //verificando si se accionó el boton de modificar
            if(request.getParameter("modificar")!=null){
                out.print(codEstudiante);
                out.print(request.getParameter("nomEst"));
                out.print(request.getParameter("apEst"));
                out.print(request.getParameter("ciEst"));
                out.print(request.getParameter("codG"));
                out.print(request.getParameter("codC"));
                String salida=estudiante.modificarEstudiante(request, codEstudiante);
                out.print(salida);
                
            }
        %>
        <form method="POST">
            <table border="1">
                <thead>
                    <tr>
                        <th colspan="2">DATOS DE REGISTRO ESTUDIANTE</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>CODIGO EST.:</td>
                        
                    </tr>
                    <tr>
                        <td>NOMBRE:</td>
                        <td><input type="text" name="nomEst" value="<%=estudiante.getEstudianteModificar().getNomEstudiante()%>" /></td>
                    </tr>
                    <tr>
                        <td>APELLIDOS:</td>
                       
                    </tr>
                    <tr>
                        <td>CEDULA DE IDENTIDAD:</td>
                        
                    </tr>
                    <tr>
                        <td> id GRADO:</td>
                        
                    </tr>
                    <tr>
                        <td>GRADO:</td>
                        
                        <td>
                            <select name="codG">
                                <option value="">SELECCIONE</option>
                                <%=grado.listarGradoSelect()%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td> id CURSO:</td>
                        
                        
                    </tr>
                    <tr>
                        <td>CURSO:</td>
                        
                        <td>
                            <select name="codC">
                                <option value="">SELECCIONE</option>
                                <%=curso.listarCursosSelect()%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="submit" value="Modificar" name="modificar" /> </td>
                    </tr>
                </tbody>
            </table>
                    <a href="index.jsp"> MENU INICIO </a>

        </form>
    </body>
</html>
