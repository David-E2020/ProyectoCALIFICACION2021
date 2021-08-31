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
        <script type="text/javascript">
            function confirmarEliminacion(){
                var varConfirm=confirm("¿Esta seguro de eliminar el registro?");
                console.log('varConfirm: '+varConfirm);
                return varConfirm;
            }
        </script>
    </head>
    <body>
        <jsp:useBean id="estudianteBean" scope="session" class="com.test.bean.EstudianteBean"/>
        <%
            //obtener el dato del parametro cod
            String codEstudiante=request.getParameter("cod");
            if(codEstudiante!=null){
                String salida=estudianteBean.eliminarEstudiante(request,codEstudiante);
                out.print(salida);
            }
        %>
        <h1>LISTADO DE ESTUDIANTES</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>CODIGO</th>
                    <th>NOMBRE</th>
                    <th>APELLIDO</th>
                    <th>C.I.</th>
                    <th>MATERIA</th>
                    <th>GRADO</th>
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
