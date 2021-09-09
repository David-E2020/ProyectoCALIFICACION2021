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
        <h1>MODIFICAR CATEGORIA</h1>
        <jsp:useBean id="curso" scope="session" class="com.test.bean.CursosBean"/>
        <%
            //rescatar el parametro del codigo categoria
            String codCursos=request.getParameter("cod");
            //llamando el busqueda de categoria
            curso.buscarCursos(codCursos);
            //verificando si se accionó el boton de modificar
            if(request.getParameter("modificar")!=null){
                String salida=curso.modificarCursos(request, codCursos);
                out.print(salida);
            }
        %>
        <form method="POST">
            <table border="1">
                <thead>
                    <tr>
                        <th colspan="2">DATOS DE REGISTRO CATEGORIA</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>NOMBRE:</td>
                        <td><input type="text" name="nom_cur" value="<%=curso.getCursosModificar().getNomCursos()%>" /></td>
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
