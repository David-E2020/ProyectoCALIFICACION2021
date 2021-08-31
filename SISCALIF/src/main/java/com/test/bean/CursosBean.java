/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bean;

import com.test.clases.Cursos;
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
public class CursosBean {
    //atributos
    private Connection connection;
    private PreparedStatement insertCursos;
    private VariablesConexion variable;
    //objeto que rescatara los datos para ser modificados
    private Cursos cursosModificar;
    //objeto para poder modificar el registro
    private PreparedStatement updateCursos;
    //Objeto para eliminar un registro
    private PreparedStatement deleteCursos;
    
    //constructores
    public CursosBean()throws SQLException {
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
    
    public String registrarCursos(HttpServletRequest request){
        String mensaje="";
        if(request==null){
            return "";
        }
        if(connection!=null){
            try {
                //definiendo la consulta
                StringBuilder query=new StringBuilder();
                query.append(" insert into cursos ");
                query.append(" values (nextval('sec_cur'),?) ");
                //enviando la consulta
                if(insertCursos==null){
                    insertCursos=connection.prepareStatement(query.toString()); 
                }
                //rescatando los parametros del formulario
                String nombre=request.getParameter("nomCur");
                //pasando los datos a los parametros de la consulta
                insertCursos.setString(1, nombre);
                //ejecutando la consulta
                int registro=insertCursos.executeUpdate();
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
    public String listarCategoria(){
        StringBuilder salidaTabla=new StringBuilder();
        StringBuilder query=new StringBuilder();
        query.append(" select c.idcurso,c.nombre_curso ");
        query.append(" from cursos c ");
        try {
            PreparedStatement pst=connection.prepareStatement(query.toString());
            ResultSet resultado=pst.executeQuery();
            while(resultado.next()){
                salidaTabla.append("<tr>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getInt(1));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(2));
                salidaTabla.append("</td>");
                //adicionando enlace para modificar ese registro
                salidaTabla.append("<td>");
                salidaTabla.append("<a href=modificarCursos.jsp?cod=").append(resultado.getInt(1)).append(">Modificar</a>");
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append("<a href='listaCursos.jsp?cod=").append(resultado.getInt(1)).append("' onclick='return confirmarEliminacion();'>Eliminar</a>");
                salidaTabla.append("</td>");
                salidaTabla.append("</tr>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error de conexion");
        }
        return salidaTabla.toString();
    }
    
    //metodo que lista la cursos en un select
    public String listarCursosSelect(){
        StringBuilder salidaTabla=new StringBuilder();
        StringBuilder query=new StringBuilder();
        query.append(" select c.idcurso,c.nombre_curso ");
        query.append(" from cursos c ");
        try {
            PreparedStatement pst=connection.prepareStatement(query.toString());
            ResultSet resultado=pst.executeQuery();
            while(resultado.next()){
                salidaTabla.append("<option value='");
                salidaTabla.append(resultado.getInt(1));
                salidaTabla.append("'>");
                salidaTabla.append(resultado.getString(2));
                salidaTabla.append("</option>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salidaTabla.toString();
    }
    
     //metodo que permite buscar una cursos por su codigo o llave primaria
    public void buscarCursos(String codCursos){
        cursosModificar=new Cursos();
        StringBuilder query=new StringBuilder();
        query.append("select idcurso, nombre_curso " );
        query.append(" from cursos ");
        query.append(" where idcurso=? ");
        try {
            PreparedStatement pst=connection.prepareStatement(query.toString());
            pst.setInt(1, Integer.parseInt(codCursos));
            ResultSet resultado=pst.executeQuery();
            //utilizamos una condicion porque la busqueda nos devuelve 1 registro
            if(resultado.next()){
                //cargando la informacion a nuestro objeto categoriaModificarde tipo categoria
                cursosModificar.setCodCursos(resultado.getInt(1));
                cursosModificar.setNomCursos(resultado.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("Error de conexion");
            e.printStackTrace();
        }
    }
    
    public String modificarCursos(HttpServletRequest request,String codCursos){
        String salida="";
        if(request==null){
            return "";
        }
        if(connection!=null){
            try {
                StringBuilder query=new StringBuilder();
                query.append("update cursos ");
                query.append(" set nombre_curso=? ");
                query.append("where idcurso=?");
                if(updateCursos==null){
                    updateCursos=connection.prepareStatement(query.toString());
                }
                //restando los datos que fueron modificados por el usuario
                String nombre=request.getParameter("nom_cur");
                //actualizando los atributos en el objeto categoriamodificar
                cursosModificar.setNomCursos(nombre);
                //pasando los parametro a la consulta
                updateCursos.setString(1, nombre);
                updateCursos.setInt(2, Integer.parseInt(codCursos==null?"0": codCursos));
                int registros=updateCursos.executeUpdate();
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
    public String eliminarCursos(HttpServletRequest request,String codCursos){
        String salida="";
        if(request==null){
            return "";
        }
        if(connection!=null && codCursos!=null && codCursos.length()>0){
            try {
                StringBuilder query=new StringBuilder();
                query.append("delete from cursos ");
                query.append(" where idcurso=? ");
                deleteCursos=connection.prepareStatement(query.toString());
                //pasando el parametro
                deleteCursos.setInt(1, Integer.parseInt(codCursos));
                //ejecutando la consulta
                int nroRegistros=deleteCursos.executeUpdate();
                if(nroRegistros==1){
                    salida="Registro Eliminado de forma correcta";
                }
                else{
                    salida="Existo un error al tratar de eliminar el registro";
                }
            } catch (SQLException e) {
                System.out.println("Error en el proceso ");
                salida="En el curso esta registrado un estudiante";
                e.printStackTrace();
            }
        }
        return salida;
    }
    
    //getter y setter

    public Cursos getCursosModificar() {
        return cursosModificar;
    }

    public void setCursosModificar(Cursos cursosModificar) {
        this.cursosModificar = cursosModificar;
    }

    
    
}
