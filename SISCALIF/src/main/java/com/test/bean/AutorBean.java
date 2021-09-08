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
public class AutorBean {
    //atributos
    private Connection connection;
    private PreparedStatement insertAutor;
    private VariablesConexion variable;
    //objeto que rescatara los datos para ser modificados
    private Cursos cursosModificar;
    //objeto para poder modificar el registro
    private PreparedStatement updateCursos;
    //Objeto para eliminar un registro
    private PreparedStatement deleteAutor;
    
    //constructores
    public AutorBean()throws SQLException {
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
    
    
    
    //realizando el estado de todas las categorias que se tiene registradas
    public String listarAutor(){
        StringBuilder salidaTabla=new StringBuilder();
        StringBuilder query=new StringBuilder();
        query.append(" select * from autor ");
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
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(3));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(4));
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(5));
                salidaTabla.append("</td>");
                //adicionando enlace para modificar ese registro
                salidaTabla.append("<td>");
                salidaTabla.append("<a href=modificarCursos.jsp?cod=").append(resultado.getInt(1)).append(">Modificar</a>");
                salidaTabla.append("</td>");
                salidaTabla.append("<td>");
                salidaTabla.append("<a href='listarAutor.jsp?cod=").append(resultado.getInt(1)).append("' onclick='return confirmarEliminacion();'>Eliminar</a>");
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
    public String listarAutorSelect(){
        StringBuilder salidaTabla=new StringBuilder();
        StringBuilder query=new StringBuilder();
        query.append(" select * from autor ");
        try {
            PreparedStatement pst=connection.prepareStatement(query.toString());
            ResultSet resultado=pst.executeQuery();
            while(resultado.next()){
                salidaTabla.append("<option value='");
                salidaTabla.append(resultado.getInt(1));
                salidaTabla.append("'>");
                salidaTabla.append(resultado.getString(2)+" "+resultado.getString(3)+" "+resultado.getString(4));
                salidaTabla.append("</option>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salidaTabla.toString();
    }
    
     //metodo que permite buscar una cursos por su codigo o llave primaria
    
    
    //metodo que permite un registro de la tabla categoria
    public String eliminarAutor(HttpServletRequest request,String codAutor){
        String salida="";
        if(request==null){
            return "";
        }
        if(connection!=null && codAutor!=null && codAutor.length()>0){
            try {
                StringBuilder query=new StringBuilder();
                query.append("delete from autor ");
                query.append(" where cod_autor=? ");
                deleteAutor=connection.prepareStatement(query.toString());
                //pasando el parametro
                deleteAutor.setInt(1, Integer.parseInt(codAutor));
                //ejecutando la consulta
                int nroRegistros=deleteAutor.executeUpdate();
                if(nroRegistros==1){
                    salida="Registro Eliminado de forma correcta";
                }
                else{
                    salida="Existo un error al tratar de eliminar el registro";
                }
            } catch (SQLException e) {
                System.out.println("Error en el proceso ");
                salida="El autor tiene registrado un libro";
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

    public PreparedStatement getInsertAutor() {
        return insertAutor;
    }

    public void setInsertAutor(PreparedStatement insertAutor) {
        this.insertAutor = insertAutor;
    }

    public PreparedStatement getDeleteAutor() {
        return deleteAutor;
    }

    public void setDeleteAutor(PreparedStatement deleteAutor) {
        this.deleteAutor = deleteAutor;
    }

    
    
}
