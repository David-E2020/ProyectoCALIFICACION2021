/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.bean;

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
    
    //getter y setter

    
    
}
