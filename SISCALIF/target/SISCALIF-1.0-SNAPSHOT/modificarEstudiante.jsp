<%-- 
    Document   : modificarCategoria
    Created on : 29-ago-2021, 18:18:44
    Author     : FERMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>MODIFICAR ESTUDIANTE</h1>
        <jsp:useBean id="estudiante" scope="session" class="com.test.bean.EstudianteBean"/>
        <jsp:useBean id="grado" scope="session" class="com.test.bean.GradoBean"/>
        <jsp:useBean id="curso" scope="session" class="com.test.bean.CursosBean"/>
        <%
            //rescatar el parametro del codigo categoria
            String codEstudiante=request.getParameter("cod");
            //llamando el busqueda
            estudiante.buscarEstudiante(codEstudiante);
            //verificando si se accionó el boton de modificar
            if(request.getParameter("modificar")!=null){
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
                        <td>CODIGO ESTUDIANTE:</td>
                        <td><input type="text"  value="<%=estudiante.getEstudianteModificar().getCodigoEstudiante()%>" disabled /></td>
                    </tr>
                    <tr>
                        <td>NOMBRE:</td>
                        <td><input type="text" name="nom_est" value="<%=estudiante.getEstudianteModificar().getNomEstudiante()%>" /></td>
                    </tr>
                    <tr>
                        <td>APELLIDOS:</td>
                        <td><input type="text" name="ap_est" value="<%=estudiante.getEstudianteModificar().getApEstudiante()%>" /></td>
                    </tr>
                    <tr>
                        <td>CEDULA DE IDENTIDAD:</td>
                        <td><input type="text" name="ci_est" value="<%=estudiante.getEstudianteModificar().getCiEstudiante()%>" /></td>
                    </tr>
                    <tr>
                    <input type="hidden" name="cod_gra" value="<%=estudiante.getEstudianteModificar().getIdgradoEstudiante()%>" />
                        <td>GRADO:</td>
                        <td><input type="text" value="<%=estudiante.getEstudianteGradoModificar().getNomGrado()%>"disabled/></td>
                        <td>
                            <select name="codG">
                                <option value="">SELECCIONE</option> 
                                <%=grado.listarGradoSelect()%>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <input type="hidden" name="cod_cur" value="<%=estudiante.getEstudianteModificar().getIdCursoEstudiante()%>" />
                        <td>MATERIA:</td>
                        <td><input type="text" value="<%=estudiante.getEstudianteGradoModificar().getNomCurso()%>"disabled/></td>
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
