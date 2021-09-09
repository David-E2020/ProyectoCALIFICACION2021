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
        <script type="text/javascript">
            function confirmarEliminacion(){
                var varConfirm=confirm("¿Esta seguro de eliminar el registro?");
                console.log('varConfirm: '+varConfirm);
                return varConfirm;
            }
        </script>
    </head>
    <body>
        <h1>LISTA DE AUTORESS</h1>
        <jsp:useBean id="autorBean" scope="session" class="com.test.bean.AutorBean"/>
        <%
            //obtener el dato del parametro cod
            String codAutor=request.getParameter("cod");
            if(codAutor!=null){
                String salida=autorBean.eliminarAutor(request,codAutor);
                out.print(salida);
            }
        %>
        <table border="1" bgcolor="#aabbcc">
            <thead>
                <tr>
                    <th>CODIGO</th>
                    <th>NOMBRE</th>
                </tr>
            </thead>
            <tbody>
                <%=autorBean.listarAutor()%>
            </tbody>
        </table>
            
            <a href="index.jsp">MENU INICIO</a>

    </body>
</html>
