/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bean;

import com.test.clases.Estudiante;
import com.test.conexion.VariablesConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author FERMIN
 */
public class EstudianteBean {
    //atributos
    private Connection connection;
    private PreparedStatement insertEstudiante;
    private VariablesConexion variable;
    //objeto que rescatara los datos para ser modificados
    private Estudiante estudianteModificar;
    //objeto para poder modificar el registro
    private PreparedStatement updateEstudiante;
    //Objeto para eliminar un registro
    private PreparedStatement deleteEstudiante;
    
    //constructores
    public EstudianteBean()throws SQLException {
        //instanciando
        variable=new VariablesConexion();
        variable.inicioConexion();
        //obteniendo la conexion
        connection=variable.getConnection();
        System.out.println("Iniciando la conexion");
    }
    
    //metodos
    @PreDestroy
    public void cerrarConexion(){
        variable.cerrarConexion();
    }
    
    //Registrando el estudiante
    public String registrarEstudiante(HttpServletRequest request){
        String mensaje="";
        if(request==null){
            return "";
        }
        if(connection!=null){
            try {
                //definiendo la consulta
                StringBuilder query=new StringBuilder();
                query.append(" insert into estudiante ");
                query.append(" values (nextval('sec_est'),?,?,?,?,?,?) ");
                //enviando la consulta
                if(insertEstudiante==null){
                    insertEstudiante=connection.prepareStatement(query.toString()); 
                }
                //rescatando los parametros del formulario
                int codGrado=Integer.parseInt(request.getParameter("codG"));
                int codCurso=Integer.parseInt(request.getParameter("codC"));
                String nombre=request.getParameter("nomEst");
                String apellido=request.getParameter("apEst");
                int cedula=Integer.parseInt(request.getParameter("cedEst"));
                String codEstd=request.getParameter("codEst");
                
                //pasando los datos a los parametros de la consulta
                insertEstudiante.setInt(1, codGrado);
                insertEstudiante.setInt(2, codCurso);
                insertEstudiante.setString(3, nombre);
                insertEstudiante.setString(4, apellido);
                insertEstudiante.setInt(5, cedula);
                insertEstudiante.setString(6, codEstd);
                
                //ejecutando la consulta
                int registro=insertEstudiante.executeUpdate();
                if(registro==1){
                    mensaje="Registro realizado con exito";
                }
                else{
                    mensaje="Error al insertar el registro";
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        }
        return mensaje;
    }
    
    //realizando el estado de todas las categorias que se tiene registradas
    public String listarEstudiante(){
        StringBuilder salidaTabla=new StringBuilder();
        StringBuilder query=new StringBuilder();
        query.append(" select e.idestudiante,e.cod_est,e.nombre,e.apellido,e.ci,c.nombre_curso from cursos c ");
        query.append(" INNER JOIN estudiante e ON c.idcurso=e.idcurso ");
        try {
            PreparedStatement pst=connection.prepareStatement(query.toString());
            ResultSet resultado=pst.executeQuery();
            while(resultado.next()){
                salidaTabla.append("<tr>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(2));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(3));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(4));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getInt(5));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(6));
                salidaTabla.append("</td>");
                //adicionando enlace para modificar ese registro
                salidaTabla.append("<td>");
                salidaTabla.append("<a href=modificarEstudiante.jsp?cod=").append(resultado.getInt(1)).append(">Modificar</a>");
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append("<a href='listaEstudiante.jsp?cod=").append(resultado.getInt(1)).append("' onclick='return confirmarEliminacion();'>Eliminar</a>");
                salidaTabla.append("</td>");
                salidaTabla.append("</tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error de conexion");
        }
        return salidaTabla.toString();
    }
    
    
    //metodo que permite buscar un estudiante por su codigo o llave primaria
    public void buscarEstudiante(String codEstudiante){
        estudianteModificar=new Estudiante();
        StringBuilder query=new StringBuilder();
        query.append("select e.idestudiante, e.idgrado,e.idcurso,e.nombre,e.apellido,e. ci,e.cod_est,c.nombre_curso,g.grado " );
        query.append(" from estudiante e inner join cursos c on e.idcurso=c.idcurso");
        query.append("  inner join grado g on g.idgrado=e.idgrado");
        query.append(" where e.idestudiante=? ");
        try {
            PreparedStatement pst=connection.prepareStatement(query.toString());
            pst.setInt(1, Integer.parseInt(codEstudiante));
            ResultSet resultado=pst.executeQuery();
            //utilizamos una condicion porque la busqueda nos devuelve 1 registro
            if(resultado.next()){
                //cargando la informacion a nuestro objeto estudianteModificar de tipo estudiante
                estudianteModificar.setIdEstudiante(resultado.getInt(1));
                estudianteModificar.setIdGrado(resultado.getInt(2));
                estudianteModificar.setIdCurso(resultado.getInt(3));
                estudianteModificar.setNomEstudiante(resultado.getString(4));
                estudianteModificar.setApEstudiante(resultado.getString(5));
                estudianteModificar.setCiEstudiante(resultado.getInt(6));
                estudianteModificar.setCodEstudiante(resultado.getString(7));
                estudianteModificar.setNomCurso(resultado.getString(8));
                estudianteModificar.setNomGrado(resultado.getString(9));
            }
        } catch (SQLException e) {
            System.out.println("Error de conexion");
            e.printStackTrace();
        }
    }
    
    public String modificarEstudiante(HttpServletRequest request,String cdEstudiante){
        String salida="";
        if(request==null){
            return "";
        }
        if(connection!=null){
            try {
                StringBuilder query=new StringBuilder();
                query.append("update estudiante  ");
                query.append(" idgrado=?,idcurso=?,nombre=?,apellido=?,ci=?,cod_est=? ");
                query.append(" where idestudiante=? ");
                if(updateEstudiante==null){
                    updateEstudiante=connection.prepareStatement(query.toString());
                }
                //rescatando los datos que fueron modificados por el usuario
                
                int cdgrado=Integer.parseInt(request.getParameter("codG"));
                int cdcurso=Integer.parseInt(request.getParameter("codC"));
                String nombre=request.getParameter("nomEst");
                String apellido=request.getParameter("apEst");
                int ci=Integer.parseInt(request.getParameter("ciEst"));
                String codEstd=request.getParameter("coEst");
                //actualizando los atributos en el objeto categoriamodificar
                estudianteModificar.setIdGrado(cdgrado);
                estudianteModificar.setIdCurso(cdcurso);
                estudianteModificar.setNomEstudiante(nombre);
                estudianteModificar.setApEstudiante(apellido);
                estudianteModificar.setCiEstudiante(ci);
                //pasando los parametro a la consulta
                updateEstudiante.setInt(1, cdgrado);
                updateEstudiante.setInt(2,cdcurso);
                updateEstudiante.setString(3, nombre);
                updateEstudiante.setString(4, apellido);
                updateEstudiante.setInt(5, ci);
                updateEstudiante.setString(1, codEstd);
                updateEstudiante.setInt(2, Integer.parseInt(cdEstudiante==null?"0": cdEstudiante));
                int registros=updateEstudiante.executeUpdate();
                if(registros==1){
                    salida="Modificacion correcta";
                }
                else{
                    salida="Error al ejecutar el update";
                }
            } catch (SQLException e) {
                System.out.println("Error al ejecutar el update");
                e.printStackTrace();
            }
        }
        return salida;
    }
    
    //metodo que permite un registro de la tabla categoria
    public String eliminarCategoria(HttpServletRequest request,String codCategoria){
        String salida="";
        if(request==null){
            return "";
        }
        if(connection!=null && codCategoria!=null && codCategoria.length()>0){
            try {
                StringBuilder query=new StringBuilder();
                query.append("delete from categoria ");
                query.append(" where cod_cat=? ");
                deleteEstudiante=connection.prepareStatement(query.toString());
                //pasando el parametro
                deleteEstudiante.setInt(1, Integer.parseInt(codCategoria));
                //ejecutando la consulta
                int nroRegistros=deleteEstudiante.executeUpdate();
                if(nroRegistros==1){
                    salida="Registro Eliminado de forma correcta";
                }
                else{
                    salida="Existo un error al tratar de eliminar el registro";
                }
            } catch (SQLException e) {
                System.out.println("Error en el proceso ");
                e.printStackTrace();
            }
        }
        return salida;
    }
    
    
    //getter y setter

    public Estudiante getEstudianteModificar() {
        return estudianteModificar;
    }

    public void setEstudianteModificar(Estudiante estudianteModificar) {
        this.estudianteModificar = estudianteModificar;
    }
    
    
}
